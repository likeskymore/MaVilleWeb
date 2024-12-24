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
    private String filePath = "Code/src/main/java/Data/Users.json";
    private static UserAuthenticator instance; // Singleton instance
    private User connectedUser;

    // Private constructor for Singleton pattern
    private UserAuthenticator() {}

    /**
     * Get the Singleton instance of UserAuthenticator.
     *
     * @return The Singleton instance.
     */
    public static UserAuthenticator getInstance() {
        if (instance == null) {
            instance = new UserAuthenticator();
        }
        return instance;
    }

    /**
     * Log in a user by verifying their email and password.
     *
     * @param email    The email of the user.
     * @param password The password of the user.
     * @return The connected User object, or null if authentication fails.
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
     * Get the role of the connected user.
     *
     * @return A string representing the role of the user ("Resident" or "Intervenant"),
     * or "Unknown" if no user is connected.
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
     * Log out the currently connected user.
     *
     * @param user The user to log out. If it matches the connected user, disconnect them.
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
     * Getter for the currently connected user.
     *
     * @return The currently connected user, or null if no user is logged in.
     */
    public User getConnectedUser() {
        return connectedUser;
    }

    public void setPath(String filePath) {
        this.filePath = filePath;
    }

    // Custom TypeAdapter for LocalDate
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