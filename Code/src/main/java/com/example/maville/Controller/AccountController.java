/**
 * La classe est responsable de la gestion des comptes utilisateurs, ainsi que de la création 
 * des comptes Résident et Intervenant, le chargement des données utilisateur à partir d'un fichier JSON,
 * et la validation des entrées utilisateur lors du processus de création de compte.
 * 
 * Cette classe interagit avec les données JSON stockées dans un fichier et assure
 * que les détails des utilisateurs sont correctement gérés et enregistrés.
 */

package com.example.maville.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.example.maville.Model.User;

public class AccountController extends Controller {
    /**
    * Chemin d'accès au fichier JSON où les données utilisateur sont stockées.
    */
    private String FILE_PATH = "Code/src/main/java/Data/Users.json";

    /**
     * Crée un nouveau compte pour un Résident ou un Intervenant.
     * Demande à l'utilisateur les informations nécessaires et sauvegarde les détails du compte
     * dans la section appropriée du fichier JSON.
     * 
     * @param scanner Scanner pour lire les entrées utilisateur.
     */
    public void createAccount(Scanner scanner) {
        try {
            // Load the existing JSON data
            JsonObject jsonData = loadJsonData();
    
            // Initialize arrays if not already present
            JsonArray residents = jsonData.has("residents") ? jsonData.getAsJsonArray("residents") : new JsonArray();
            JsonArray intervenants = jsonData.has("intervenants") ? jsonData.getAsJsonArray("intervenants") : new JsonArray();
    
            System.out.println("Choisissez le type de compte à créer :");
            System.out.println("1. Résident");
            System.out.println("2. Intervenant");
            System.out.print("Entrez votre choix (1 ou 2) : ");
            int choice = -1;
    
            while (choice != 1 && choice != 2) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice != 1 && choice != 2) {
                        System.out.println("Choix invalide. Veuillez entrer 1 ou 2 :");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrée invalide. Veuillez entrer un numéro (1 ou 2) :");
                }
            }
    
            switch (choice) {
                case 1:
                    // Resident registration
                    System.out.println("Inscription Résident :");
                    String fullNameResident = promptForNonEmptyInput(scanner, "Entrez votre nom complet : ");
                    String dob = promptForValidDate(scanner, "Entrez votre date de naissance (AAAA-MM-JJ) : ");
                    String emailResident = promptForUniqueEmail(scanner, "Entrez votre adresse email : ", residents, intervenants);
                    String passwordResident = promptForValidPassword(scanner, "Entrez votre mot de passe : ");
                    String streetNumber = promptForNonEmptyInput(scanner, "Entrez votre numéro de rue : ");
                    String streetName = promptForNonEmptyInput(scanner, "Entrez le nom de votre rue : ");
                    String postalCode = promptForPostalCode(scanner, "Entrez votre code postal (3 premières lettres ou chiffres): ");
    
                    // Create a Resident JSON object
                    JsonObject residentJson = new JsonObject();
                    residentJson.addProperty("id", String.valueOf(residents.size() + 1));
                    residentJson.addProperty("name", fullNameResident);
                    residentJson.addProperty("email", emailResident);
                    residentJson.addProperty("password", passwordResident);
                    residentJson.addProperty("birthDate", dob);
    
                    JsonObject address = new JsonObject();
                    address.addProperty("streetNumber", streetNumber);
                    address.addProperty("streetName", streetName);
                    address.addProperty("postalCode", postalCode);
                    residentJson.add("address", address);
    
                    residents.add(residentJson);
    
                    System.out.println("Compte Résident créé avec succès !");
                    break;
    
                case 2:
                    // Intervenant registration
                    System.out.println("Inscription Intervenant :");
                    String fullNameIntervenant = promptForNonEmptyInput(scanner, "Entrez votre nom complet : ");
                    String typeIntervenant = promptForIntervenantType(scanner);
                    String emailIntervenant = promptForUniqueEmail(scanner, "Entrez votre adresse email : ", residents, intervenants);
                    String passwordIntervenant = promptForValidPassword(scanner, "Entrez votre mot de passe : ");
                    String cityId = promptForValidCityId(scanner, "Entrez votre identifiant de ville (code à 8 chiffres) : ");
    
                    // Create an Intervenant JSON object
                    JsonObject intervenantJson = new JsonObject();
                    intervenantJson.addProperty("id", String.valueOf(intervenants.size() + 1));
                    intervenantJson.addProperty("name", fullNameIntervenant);
                    intervenantJson.addProperty("email", emailIntervenant);
                    intervenantJson.addProperty("password", passwordIntervenant);
                    intervenantJson.addProperty("cityId", cityId);
                    intervenantJson.addProperty("type", typeIntervenant);
    
                    intervenants.add(intervenantJson);
    
                    System.out.println("Compte Intervenant créé avec succès !");
                    break;
    
                default:
                    System.out.println("Choix invalide. Veuillez entrer 1 ou 2.");
            }
    
            // Debug: Print the jsonData before saving
            System.out.println("JSON data before saving: " + jsonData.toString());
    
            // Save updated data back to the JSON file
            jsonData.add("residents", residents);
            jsonData.add("intervenants", intervenants);
    
            try (FileWriter writer = new FileWriter(FILE_PATH)) {
                Gson gson = new Gson();
                gson.toJson(jsonData, writer);
    
                // Debug: Confirm file is saved
                System.out.println("Data saved to JSON file at: " + FILE_PATH);
            }
    
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de la création du compte : " + e.getMessage());
        }
    }

    /**
     * Charge les données JSON à partir du fichier spécifié par FILE_PATH.
     * Si le fichier est manquant ou illisible, initialise une structure JSON par défaut.
     * 
     * @return Un JsonObject contenant les données chargées ou une structure par défaut.
     */
    // Load JSON data from the file
    public JsonObject loadJsonData() {
        JsonObject jsonData = new JsonObject();  // Initialize as empty JsonObject by default
        FileReader reader = null;
        try {
            String filePath = this.getPath();
            System.out.println("Loading data from file: " + filePath);  // Debugging: Print file path
    
            reader = new FileReader(filePath);
            Gson gson = new Gson();
            jsonData = gson.fromJson(reader, JsonObject.class);
        } catch (IOException e) {
            // Log the error if the file is missing or unreadable
            System.out.println("Error reading file, creating a new JSON file: " + e.getMessage());
    
            // Initialize default JSON structure if file doesn't exist or is empty
            jsonData.add("residents", new JsonArray());
            jsonData.add("intervenants", new JsonArray());
        } finally {
            try {
                if (reader != null) {
                    reader.close();  // Ensure file is closed after reading
                }
            } catch (IOException e) {
                System.out.println("Error closing file reader: " + e.getMessage());
            }
        }
    
        return jsonData;
    }

    /**
     * Demande à l'utilisateur une saisie jusqu'à ce qu'une valeur non vide soit fournie.
     * 
     * @param scanner Scanner pour lire les entrées utilisateur.
     * @param message Message à afficher à l'utilisateur.
     * @return La chaîne de caractères non vide fournie par l'utilisateur.
     */
    private String promptForNonEmptyInput(Scanner scanner, String message) {
        String input;
        do {
            System.out.print(message);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Ce champ ne peut pas être vide. Veuillez réessayer.");
            }
        } while (input.isEmpty());
        return input;
    }

    /**
     * Demande à l'utilisateur une adresse email unique, en s'assurant qu'aucun doublon n'existe
     * parmi les résidents ou intervenants.
     * 
     * @param scanner Scanner pour lire les entrées utilisateur.
     * @param message Message à afficher à l'utilisateur.
     * @param residents JsonArray des résidents existants.
     * @param intervenants JsonArray des intervenants existants.
     * @return L'adresse email unique fournie par l'utilisateur.
     */
    private String promptForUniqueEmail(Scanner scanner, String message, JsonArray residents, JsonArray intervenants) {
        String email;
        boolean isUnique;
        do {
            System.out.print(message);
            email = scanner.nextLine().trim();
            if (!email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                System.out.println("Format d'email invalide. Veuillez réessayer.");
                isUnique = false;
                continue;
            }
            isUnique = isEmailUnique(email, residents, intervenants);
            if (!isUnique) {
                System.out.println("Cet email est déjà enregistré. Veuillez utiliser un email différent.");
            }
        } while (!email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$") || !isUnique);
        return email;
    }

    /**
     * Valide si une adresse email est unique parmi les utilisateurs existants.
     * 
     * @param email Adresse email à valider.
     * @param residents JsonArray des résidents existants.
     * @param intervenants JsonArray des intervenants existants.
     * @return True si l'email est unique, false sinon.
     */
    private boolean isEmailUnique(String email, JsonArray residents, JsonArray intervenants) {
        for (JsonElement resident : residents) {
            JsonObject residentObj = resident.getAsJsonObject();
            if (residentObj.get("email").getAsString().equalsIgnoreCase(email)) {
                return false;
            }
        }
        for (JsonElement intervenant : intervenants) {
            JsonObject intervenantObj = intervenant.getAsJsonObject();
            if (intervenantObj.get("email").getAsString().equalsIgnoreCase(email)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Demande à l'utilisateur un mot de passe valide respectant les exigences minimales.
     * 
     * @param scanner Scanner pour lire les entrées utilisateur.
     * @param message Message à afficher à l'utilisateur.
     * @return Le mot de passe valide fourni par l'utilisateur.
     */
    private String promptForValidPassword(Scanner scanner, String message) {
        String password;
        do {
            System.out.print(message);
            password = scanner.nextLine();
            if (password.length() < 8 || !password.matches(".*[A-Za-z].*") || !password.matches(".*\\d.*")) {
                System.out.println("Le mot de passe doit contenir au moins 8 caractères, incluant des lettres et des chiffres.");
            }
        } while (password.length() < 8 || !password.matches(".*[A-Za-z].*") || !password.matches(".*\\d.*"));
        return password;
    }

    /**
     * Demande à l'utilisateur une date valide au format AAAA-MM-JJ.
     * 
     * @param scanner Scanner pour lire les entrées utilisateur.
     * @param message Message à afficher à l'utilisateur.
     * @return Une chaîne de caractères représentant la date valide.
     */
    private String promptForValidDate(Scanner scanner, String message) {
        String date;
        do {
            System.out.print(message);
            date = scanner.nextLine().trim();
    
            if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                System.out.println("Format de date invalide. Veuillez utiliser AAAA-MM-JJ.");
                continue;
            }
    
            try {
                String[] parts = date.split("-");
                int year = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int day = Integer.parseInt(parts[2]);
    
                if (month < 1 || month > 12) {
                    System.out.println("Mois invalide. Veuillez entrer un mois entre 1 et 12.");
                    continue;
                }
    
                int daysInMonth;
                switch (month) {
                    case 2: // February
                        daysInMonth = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28;
                        break;
                    case 4: case 6: case 9: case 11: // Months with 30 days
                        daysInMonth = 30;
                        break;
                    default: // All other months have 31 days
                        daysInMonth = 31;
                }
    
                if (day < 1 || day > daysInMonth) {
                    System.out.println("Jour invalide pour le mois donné. Veuillez entrer un jour valide.");
                    continue;
                }
    
                return date; // Valid date
            } catch (NumberFormatException e) {
                System.out.println("Erreur lors de l'analyse de la date. Veuillez réessayer.");
            }
        } while (true);
    }

    /**
     * Demande à l'utilisateur un identifiant de ville valide composé de 8 chiffres.
     * 
     * @param scanner Scanner pour lire les entrées utilisateur.
     * @param message Message à afficher à l'utilisateur.
     * @return Un identifiant de ville valide fourni par l'utilisateur.
     */
    private String promptForValidCityId(Scanner scanner, String message) {
        String cityId;
        do {
            System.out.print(message);
            cityId = scanner.nextLine().trim();
            if (!cityId.matches("\\d{8}")) {
                System.out.println("Identifiant de ville invalide ! Veuillez entrer un code à 8 chiffres :");
            }
        } while (!cityId.matches("\\d{8}"));
        return cityId;
    }

    /**
     * Demande à l'utilisateur un code postal composé exactement de trois lettres ou chiffres.
     * 
     * @param scanner Scanner pour lire les entrées utilisateur.
     * @param message Message à afficher à l'utilisateur.
     * @return Un code postal valide fourni par l'utilisateur.
     */
    private String promptForPostalCode(Scanner scanner, String message) {
        String postalCode;
        do {
            System.out.print(message);
            postalCode = scanner.nextLine().trim().toUpperCase();
            if (!postalCode.matches("^[A-Z0-9]{3}$")) {
                System.out.println("Code postal invalide! Veuillez entrer exactement 3 lettres ou chiffres.");
            }
        } while (!postalCode.matches("^[A-Z0-9]{3}$"));
        return postalCode;
    }

    /**
     * Demande à l'utilisateur de sélectionner un type d'Intervenant parmi les options prédéfinies.
     * 
     * @param scanner Scanner pour lire les entrées utilisateur.
     * @return Le type d'Intervenant sélectionné.
     */
    private String promptForIntervenantType(Scanner scanner) {
        System.out.println("Choisissez votre type d'intervenant :");
        System.out.println("1. PUBLIC");
        System.out.println("2. PRIVATE");
        System.out.println("3. INDIVIDUAL");
        
        int choice = -1;
        while (choice < 1 || choice > 3) {
            try {
                System.out.print("Entrez votre choix (1, 2 ou 3) : ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 3) {
                    System.out.println("Choix invalide. Veuillez entrer 1, 2 ou 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un numéro (1, 2 ou 3) :");
            }
        }
    
        switch (choice) {
            case 1: return "PUBLIC";
            case 2: return "PRIVATE";
            case 3: return "INDIVIDUAL";
            default: return ""; // This case won't be reached because of the validation
        }
    }
    
    /**
     * Récupère les plages horaires préférées d'un utilisateur.
     * 
     * @param activeUser L'utilisateur dont les plages horaires doivent être récupérées.
     */
    public void getTimeSlot(User activeUser) {

    } 
    
    /**
     * Modifie les plages horaires préférées d'un utilisateur.
     * 
     * @param activeUser L'utilisateur dont les plages horaires doivent être modifiées.
     * @param data Un tableau contenant les nouvelles données de plages horaires.
     */
    public void editTimeSlot(User activeUser, String[] data) {
        
    }
    
    /**
     * Définit le chemin d'accès au fichier JSON utilisé par le contrôleur.
     * 
     * @param path Le nouveau chemin du fichier.
     */
    public void setPath(String path){
        this.FILE_PATH = path;
    }

    /**
     * Récupère le chemin actuel du fichier JSON utilisé par le contrôleur.
     * 
     * @return Le chemin actuel du fichier.
     */
    public String getPath() {
        return FILE_PATH;
    }

    /**
     * Vérifie si l'utilisateur actuel est autorisé à effectuer des actions dans ce contrôleur.
     * 
     * @return True si autorisé, false sinon.
     */
    @Override
    protected boolean isAuthorized() {
        return true;
    }
}