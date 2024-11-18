import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class Resident {

    private static final String[] RESIDENTS = {
        "resident1@mail.com:password1", 
        "resident2@mail.com:password2"
    };
    private String username;

    public Resident(String username) {
        this.username = username;
    }

    // Authenticates a resident
    public static Resident authentifier(Scanner scanner) {
        System.out.println("Veuillez vous authentifier en tant que résident");
        System.out.println("Adresse courriel :");
        String username = scanner.nextLine();
        System.out.println("Mot de passe :");
        String password = scanner.nextLine();

        for (String user : RESIDENTS) {
            String[] details = user.split(":");
            if (details[0].equals(username) && details[1].equals(password)) {
                return new Resident(username);
            }
        }
        System.out.println("Échec de l'authentification. Veuillez réessayer.");
        return null;
    }

    // Displays the main menu
    public void afficherMenuPrincipal(Scanner scanner) {
        while (true) {
            clearScreen();
            System.out.println("Bienvenue résident!");
            printMenuOptions();
            
            String choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    handleSoumissionRequete(scanner);
                    break;
                case "2":
                    handleTravauxMenu(scanner);
                    break;
                case "3":
                    handleNotifications(scanner);
                    break;
                case "4":
                    handlePlanification(scanner);
                    break;
                case "Q":
                case "q":
                    exitApplication();
                    break;
                default:
                    printInvalidChoiceMessage();
            }
        }
    }

    // Handles the Travaux submenu
    private void handleTravauxMenu(Scanner scanner) {
        while (true) {
            clearScreen();
            System.out.println("1. Consulter les travaux en cours ou à venir");
            System.out.println("2. Rechercher des travaux par filtres");
            System.out.println("[M]. Retour au menu principal");
            System.out.println("[Q]. Quitter l'application");
            
            String choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    clearScreen();
                    consulterProjets(scanner);
                    break;
                case "2":
                    clearScreen();
                    System.out.println("Recherche des travaux avec filtres...");
                    break;
                case "M":
                case "m":
                    return; // Return to main menu
                case "Q":
                case "q":
                    exitApplication();
                    break;
                default:
                    printInvalidChoiceMessage();
            }
        }
    }

    // Handles "Soumettre une requete"
    private void handleSoumissionRequete(Scanner scanner) {
        clearScreen();
        System.out.println("Soumettre une requete...");
        System.out.println("Implémentation à venir.");
    }

    // Handles "Notifications"
    private void handleNotifications(Scanner scanner) {
        clearScreen();
        System.out.println("Consultations des notifications...");
        System.out.println("Implémentation à venir.");
    }

    // Handles "Planification participative"
    private void handlePlanification(Scanner scanner) {
        clearScreen();
        System.out.println("Affichage des travaux à planifier...");
        System.out.println("Implémentation à venir.");
    }

    // Exits the application
    private void exitApplication() {
        clearScreen();
        System.out.println("Merci d'avoir utilisé MaVille. À la prochaine!");
        System.exit(0);
    }

    private void consulterProjets(Scanner scanner) {
        HttpClientApi api = new HttpClientApi();
        String resourceId = "cc41b532-f12d-40fb-9f55-eb58c9a2b12b";
        String category = "Travaux";
    
        // Fetch the data using the HttpClientApi
        ApiResponse response = api.getData(resourceId);
    
        if (response != null && response.getStatusCode() == 200) {
            try {
                // Parse the response body into JSON
                JsonObject jsonResponse = JsonParser.parseString(response.getBody()).getAsJsonObject();
    
                // Get the "records" array from the JSON response
                JsonArray records = jsonResponse.getAsJsonObject("result").getAsJsonArray("records");
    
                // Set up Gson with the custom deserializer
                Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Projet.class, new TravailDeserializer(category))
                    .create();
    
                // Deserialize and filter the projects
                List<Projet> projetEnCours = new ArrayList<>();
                LocalDate now = LocalDate.now();
                LocalDate threeMonthsFromNow = now.plusMonths(3);
    
                for (JsonElement jsonElement : records) {
                    try {
                        Projet projet = gson.fromJson(jsonElement, Projet.class);
    
                        if (projet != null) {
                            LocalDate dateDebut = projet.getDateDebut();
                            LocalDate dateFin = projet.getDateFin();
    
                            // Apply the filter: Start date is in the next 3 months or End date is ongoing
                            boolean isInNextThreeMonths = (dateDebut.isEqual(now) || dateDebut.isAfter(now)) && dateDebut.isBefore(threeMonthsFromNow);
                            boolean isEndDateOngoing = dateFin.isAfter(now);
    
                            if (isInNextThreeMonths || isEndDateOngoing) {
                                projetEnCours.add(projet);
                            }
                        }
                    } catch (JsonParseException e) {
                        System.err.println("Error deserializing record: " + e.getMessage());
                    }
                }
    
                // Display the filtered projects
                if (projetEnCours.isEmpty()) {
                    System.out.println("Aucun projet ne correspond aux critères.");
                    return;
                }
    
                while (true) { // Loop to allow returning to the project list
    int counter = 1;
    System.out.println("\nListe des projets en cours : (Type Travaux - Rue affectées)");
    for (Projet projet : projetEnCours) {
        System.out.println(counter + ". " + projet.getTitre());
        counter++;
    }

    // Allow user to select a project by index
    System.out.println("\nEntrez le numéro du projet pour voir les détails ou '0' pour revenir au menu precedent :");
    try {
        String input = scanner.nextLine();
        int index = Integer.parseInt(input);

        if (index == 0) {
            return; // Exit to the previous menu immediately
        }

        if (index >= 1 && index <= projetEnCours.size()) {
            Projet selectedProjet = projetEnCours.get(index - 1);
            selectedProjet.afficherDetails();

            System.out.println("\nAppuyez sur 'Enter' pour retourner à la liste des projets...");
            scanner.nextLine(); // Wait for user input to return to the list
        } else {
            System.out.println("Index invalide. Veuillez entrer un numéro valide.");
        }
    } catch (NumberFormatException e) {
        System.out.println("Entrée invalide. Veuillez entrer un numéro.");
    }
}
            } catch (JsonSyntaxException e) {
                System.err.println("Error parsing response body: " + e.getMessage());
            }
        } else {
            System.err.println("Failed to fetch data or invalid response: " + (response != null ? response.getMessage() : "No response"));
        }
    }

    // Helper Methods
    private void printMenuOptions() {
        System.out.println("Veuillez choisir ce que vous voulez accomplir");
        System.out.println("1. Soumettre une requête de travaux");
        System.out.println("2. Rechercher des travaux");
        System.out.println("3. Notifications");
        System.out.println("4. Planification participative");
        System.out.println("5. Signaler un problème");
        System.out.println("[Q]. Quitter l'application");
    }

    private void printInvalidChoiceMessage() {
        System.out.println("Choix invalide, veuillez entrer une option valide.");
    }

    private void clearScreen() {
        System.out.print("\n".repeat(20)); // Simulates clearing the console
    }


}