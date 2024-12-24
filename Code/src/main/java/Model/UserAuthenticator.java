/**
 * La classe UserAuthenticator gère l'authentification des utilisateurs dans le système.
 * 
 * Cette classe implémente le pattern Singleton pour assurer qu'une seule instance
 * de l'authentificateur est utilisée. Elle fournit des méthodes pour se connecter,
 * déterminer le rôle de l'utilisateur connecté, et se déconnecter.
 */
package Model;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UserAuthenticator {
    // Singleton instance
    /**
     * Utilisateur actuellement connecté.
     */
    private static UserAuthenticator instance; 
    private User connectedUser;
    private String filePath = "Code/src/main/java/Data/Users.json";
    
    /**
     * Constructeur privé pour le pattern Singleton.
     */
    // Private constructor for Singleton pattern
    private UserAuthenticator() {}

    /**
     * Retourne l'instance Singleton de UserAuthenticator.
     * 
     * @return L'instance unique de UserAuthenticator.
     */
    public static UserAuthenticator getInstance() {
        if (instance == null) {
            instance = new UserAuthenticator();
        }
        return instance;
    }

    /**
     * Permet à un utilisateur de se connecter en vérifiant son email et son mot de passe.
     * 
     * @param email    L'email de l'utilisateur.
     * @param password Le mot de passe de l'utilisateur.
     * @return L'objet User connecté ou null si l'authentification échoue.
     */
    public User login(String email, String password) {
        try (Reader reader = new FileReader(filePath)) {
            // Register a custom TypeAdapter for LocalDate
            Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

            // Parse the JSON file into a JsonObject
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

            // Get residents and intervenants arrays
            JsonArray residentsArray = jsonObject.getAsJsonArray("residents");
            JsonArray intervenantsArray = jsonObject.getAsJsonArray("intervenants");

            // Deserialize residents and check credentials
            List<Resident> residents = gson.fromJson(residentsArray, new TypeToken<List<Resident>>() {}.getType());
            for (Resident resident : residents) {
                if (resident.getEmail().equals(email) && resident.getPassword().equals(password)) {
                    connectedUser = resident;
                    return connectedUser; // Found the matching resident
                }
            }

            // Deserialize intervenants and check credentials
            List<Intervenant> intervenants = gson.fromJson(intervenantsArray, new TypeToken<List<Intervenant>>() {}.getType());
            for (Intervenant intervenant : intervenants) {
                if (intervenant.getEmail().equals(email) && intervenant.getPassword().equals(password)) {
                    connectedUser = intervenant;
                    return connectedUser; // Found the matching intervenant
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null if no user is found
    }

    /**
     * Retourne le rôle de l'utilisateur connecté.
     * 
     * @return Une chaîne représentant le rôle de l'utilisateur ("Resident" ou "Intervenant"),
     * ou "Unknown" si aucun utilisateur n'est connecté.
     */
    public String getUserRole() {
        if (connectedUser instanceof Resident) {
            return "Resident";
        } else if (connectedUser instanceof Intervenant) {
            return "Intervenant";
        }
        return "Unknown";
    }

    /**
     * Déconnecte l'utilisateur actuellement connecté.
     * 
     * @param user L'utilisateur à déconnecter. Si l'utilisateur correspond à celui connecté, il est déconnecté.
     */
    public void logout(User user) {
        if (user != null && user.equals(connectedUser)) {
            connectedUser = null;
            System.out.println("L'utilisateur a été déconnecté avec succès.");
        } else {
            System.out.println("Aucun utilisateur correspondant n'est connecté.");
        }
    }

    /**
     * Retourne l'utilisateur actuellement connecté.
     * 
     * @return L'utilisateur connecté ou null si aucun utilisateur n'est connecté.
     */
    public User getConnectedUser() {
        return connectedUser;
    }

    public void setPath(String filePath) {
        this.filePath = filePath;
    }

    // Custom TypeAdapter for LocalDate
    /**
     * TypeAdapter personnalisé pour gérer la sérialisation et la désérialisation de LocalDate.
     */
    private static class LocalDateAdapter extends TypeAdapter<LocalDate> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        @Override
        public void write(JsonWriter out, LocalDate value) throws IOException {
            out.value(value.format(formatter));
        }

        @Override
        public LocalDate read(JsonReader in) throws IOException {
            return LocalDate.parse(in.nextString(), formatter);
        }
    }
}