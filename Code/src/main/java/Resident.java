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
                case "5":
                    handleEntraves(scanner);
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
                    rechercherParFiltres(scanner);
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
        while (true) {
            clearScreen();
            System.out.println("Soumission Requete...");
            System.out.println("Implementation à venir.");
            System.out.println("[M]. Retour au menu principal");
            System.out.println("[Q]. Quitter l'application");
            
            String choix = scanner.nextLine();
            switch (choix) {
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

    // Handles "Notifications"
    private void handleNotifications(Scanner scanner) {
        while (true) {
            clearScreen();
            System.out.println("Personaliser les notifications...");
            System.out.println("Implementation à venir.");
            System.out.println("[M]. Retour au menu principal");
            System.out.println("[Q]. Quitter l'application");
            
            String choix = scanner.nextLine();
            switch (choix) {
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

    // Handles "Planification participative"
    private void handlePlanification(Scanner scanner) {
        while (true) {
            clearScreen();
            System.out.println("Planification participative...");
            System.out.println("Implementation à venir.");
            System.out.println("[M]. Retour au menu principal");
            System.out.println("[Q]. Quitter l'application");
            
            String choix = scanner.nextLine();
            switch (choix) {
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

    private void handleEntraves(Scanner scanner) {
        while (true) {
            clearScreen();
            System.out.println("1. Consulter les entraves routières causées par les travaux en cours.");
            System.out.println("2. Filtrer les entraves par travail particulier ou par rue.");
            System.out.println("[M]. Retour au menu principal");
            System.out.println("[Q]. Quitter l'application");
            
            String choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    consulterEntraves(scanner);
                    clearScreen();
                    break;
                case "2":
                    System.out.println("filtrer les entrave. (à implémenter)");
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

    // Exits the application
    private void exitApplication() {
        System.out.println("Merci d'avoir utilisé MaVille. À la prochaine!");
        System.exit(0);
    }

    private void consulterProjets(Scanner scanner) {
        HttpClientApi api = new HttpClientApi();
        String resourceId = "cc41b532-f12d-40fb-9f55-eb58c9a2b12b";
        String category = "Travaux";
    
        ApiResponse response = api.getData(resourceId);
        if (response != null && response.getStatusCode() == 200) {
            try {
                JsonObject jsonResponse = JsonParser.parseString(response.getBody()).getAsJsonObject();
                JsonArray records = jsonResponse.getAsJsonObject("result").getAsJsonArray("records");
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(Projet.class, new TravailDeserializer(category))
                        .create();
    
                List<Projet> projetEnCours = new ArrayList<>();
                LocalDate now = LocalDate.now();
                LocalDate threeMonthsFromNow = now.plusMonths(3);
    
                for (JsonElement jsonElement : records) {
                    try {
                        Projet projet = gson.fromJson(jsonElement, Projet.class);
                        if (projet != null) {
                            LocalDate dateDebut = projet.getDateDebut();
                            LocalDate dateFin = projet.getDateFin();
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
    
                if (projetEnCours.isEmpty()) {
                    System.out.println("Aucun projet ne correspond aux critères.");
                    return;
                }
    
                // Display the list of projects
                while (true) { // Loop to allow returning to the project list
                    int counter = 1;
                    System.out.println("\nListe des projets en cours : (Type Travaux --- Rue affectées)");
                    for (Projet projet : projetEnCours) {
                        System.out.println(counter + ". " + projet.getTitre());
                        counter++;
                    }
    
                    // Allow user to select a project by index or return to the previous menu
                    System.out.println("\nEntrez le numéro du projet pour voir les détails ou '0' pour revenir au menu précédent :");
                    try {
                        String input = scanner.nextLine();
                        int index = Integer.parseInt(input);
    
                        if (index == 0) {
                            return; // Exit to the previous menu immediately
                        }
    
                        if (index >= 1 && index <= projetEnCours.size()) {
                            Projet selectedProjet = projetEnCours.get(index - 1);
                            selectedProjet.afficherDetails(); // Display details of the selected project
    
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

    private void rechercherParFiltres(Scanner scanner) {
        while (true) {
            clearScreen();
            System.out.println("Rechercher des travaux par filtres.");
            System.out.println("1. Filtrer par quartier");
            System.out.println("2. Filtrer par type de projet");
            System.out.println("0. Retour au menu précédent");
    
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    selectBorough(scanner); // Display the borough menu and filter
                    break;
                case "2":
                    System.out.println("Filtrer par type de projet. (À implémenter)");
                    break;
                case "0":
                    return; // Exit back to the Travaux menu
                default:
                    System.out.println("Choix invalide. Veuillez entrer un numéro valide.");
            }
        }
    }

    private void selectBorough(Scanner scanner) {
        String[] boroughs = {
            "Ahuntsic-Cartierville", "Anjou", "Côte-des-Neiges–Notre-Dame-de-Grâce",
            "Lachine", "LaSalle", "Le Plateau-Mont-Royal", "Le Sud-Ouest",
            "L'Île-Bizard–Sainte-Geneviève", "Mercier–Hochelaga-Maisonneuve",
            "Montréal-Nord", "Outremont", "Pierrefonds-Roxboro", "Rivière-des-Prairies–Pointe-aux-Trembles",
            "Rosemont–La Petite-Patrie", "Saint-Laurent", "Saint-Léonard", "Verdun",
            "Ville-Marie", "Villeray–Saint-Michel–Parc-Extension"
        };
    
        while (true) {
            clearScreen();
            System.out.println("Choisissez un quartier :");
            for (int i = 0; i < boroughs.length; i++) {
                System.out.println((i + 1) + ". " + boroughs[i]);
            }
            System.out.println("0. Retour au menu précédent");
    
            try {
                String input = scanner.nextLine();
                int choice = Integer.parseInt(input);
    
                if (choice == 0) {
                    return; // Return to the previous menu
                } else if (choice >= 1 && choice <= boroughs.length) {
                    String selectedBorough = boroughs[choice - 1];
                    filterByBorough(selectedBorough, scanner); // Call filter method with selected borough
                    break;
                } else {
                    System.out.println("Choix invalide. Veuillez entrer un numéro valide.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un numéro.");
            }
        }
    }

    private void filterByBorough(String borough, Scanner scanner) {
        HttpClientApi api = new HttpClientApi();
        String resourceId = "cc41b532-f12d-40fb-9f55-eb58c9a2b12b";
        String category = "Travaux";
    
        // Fetch the data using the HttpClientApi
        ApiResponse response = api.getData(resourceId);
    
        if (response != null && response.getStatusCode() == 200) {
            try {
                JsonObject jsonResponse = JsonParser.parseString(response.getBody()).getAsJsonObject();
                JsonArray records = jsonResponse.getAsJsonObject("result").getAsJsonArray("records");
    
                Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Projet.class, new TravailDeserializer(category))
                    .create();
    
                List<Projet> filteredProjects = new ArrayList<>();
                for (JsonElement jsonElement : records) {
                    try {
                        Projet projet = gson.fromJson(jsonElement, Projet.class);
                        if (projet != null && projet.getQuartiersAffectes().contains(borough)) {
                            filteredProjects.add(projet);
                        }
                    } catch (JsonParseException e) {
                        System.err.println("Erreur de désérialisation : " + e.getMessage());
                    }
                }
    
                if (filteredProjects.isEmpty()) {
                    System.out.println("Aucun projet trouvé pour le quartier " + borough + ".");
                } 

                while (true) { // Loop to allow returning to the project list
                    int counter = 1;
                    System.out.println("\nListe des projets en cours : (Type Travaux --- Rue affectées)");
                    for (Projet projet : filteredProjects) {
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
    
                        if (index >= 1 && index <= filteredProjects.size()) {
                            Projet selectedProjet = filteredProjects.get(index - 1);
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
                System.err.println("Erreur d'analyse de la réponse : " + e.getMessage());
            }
        } else {
            System.err.println("Échec de récupération des données ou réponse invalide.");
        }
    
        System.out.println("\nAppuyez sur 'Entrée' pour revenir au menu précédent...");
        scanner.nextLine(); // Wait for user input to continue
    }

    private void consulterEntraves(Scanner scanner) {
        HttpClientApi api = new HttpClientApi();
        String resourceId = "a2bc8014-488c-495d-941b-e7ae1999d1bd";
        String category = "Entraves";
    
        // Fetch the data using the HttpClientApi
        ApiResponse response = api.getData(resourceId);
    
        if (response != null && response.getStatusCode() == 200) {
            try {
                JsonObject jsonResponse = JsonParser.parseString(response.getBody()).getAsJsonObject();
                JsonArray records = jsonResponse.getAsJsonObject("result").getAsJsonArray("records");
    
                // Create Gson instance with custom deserializer for Entraves
                Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Entraves.class, new TravailDeserializer(category))
                    .create();
    
                List<Entraves> entravesEnCours = new ArrayList<>();
                for (JsonElement jsonElement : records) {
                    try {
                        Entraves entrave = gson.fromJson(jsonElement, Entraves.class);
                        if (entrave != null) {
                            entravesEnCours.add(entrave);
                        }
                    } catch (JsonParseException e) {
                        System.err.println("Erreur de désérialisation : " + e.getMessage());
                    }
                }
    
                if (entravesEnCours.isEmpty()) {
                    System.out.println("Aucune entrave trouvée.");
                    return;
                }
    
                while (true) { // Loop to allow returning to the entrave list
                    int counter = 1;
                    System.out.println("\nListe des entraves en cours : (Type Entrave --- Rue affectées)");
                    for (Entraves entrave : entravesEnCours) {
                        System.out.println(counter + ". " + entrave.getTitre());
                        counter++;
                    }
    
                    // Allow user to select an entrave by index
                    System.out.println("\nEntrez le numéro de l'entrave pour voir les détails ou '0' pour revenir au menu precedent :");
                    try {
                        String input = scanner.nextLine();
                        int index = Integer.parseInt(input);
    
                        if (index == 0) {
                            return; // Exit to the previous menu immediately
                        }
    
                        if (index >= 1 && index <= entravesEnCours.size()) {
                            Entraves selectedEntrave = entravesEnCours.get(index - 1);
                            selectedEntrave.afficherDetails();
    
                            System.out.println("\nAppuyez sur 'Enter' pour retourner à la liste des entraves...");
                            scanner.nextLine(); // Wait for user input to return to the list
                        } else {
                            System.out.println("Index invalide. Veuillez entrer un numéro valide.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrée invalide. Veuillez entrer un numéro.");
                    }
                }
    
            } catch (JsonSyntaxException e) {
                System.err.println("Erreur d'analyse de la réponse : " + e.getMessage());
            }
        } else {
            System.err.println("Échec de récupération des données ou réponse invalide.");
        }
    
        System.out.println("\nAppuyez sur 'Entrée' pour revenir au menu précédent...");
        scanner.nextLine(); // Wait for user input to continue
    }

    // Helper Methods
    private void printMenuOptions() {
        System.out.println("Veuillez choisir ce que vous voulez accomplir");
        System.out.println("1. Soumettre une requête de travaux");
        System.out.println("2. Rechercher des travaux");
        System.out.println("3. Notifications");
        System.out.println("4. Planification participative");
        System.out.println("5. Consulter les entraves");
        System.out.println("[Q]. Quitter l'application");
    }

    private void printInvalidChoiceMessage() {
        System.out.println("Choix invalide, veuillez entrer une option valide.");
    }

    private void clearScreen() {
        System.out.print("\n".repeat(5)); // Simulates clearing the console
    }
}