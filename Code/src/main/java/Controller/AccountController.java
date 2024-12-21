package Controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Model.User;

public class AccountController extends Controller {
    private static final String FILE_PATH = "Code/src/main/java/Data/Users.json";

    public void createAccount(Scanner scanner) {
        try {
            // Load the existing JSON data
            Gson gson = new Gson();
            JsonObject jsonData = new JsonObject();
            try (FileReader reader = new FileReader(FILE_PATH)) {
                jsonData = gson.fromJson(reader, JsonObject.class);
            } catch (IOException e) {
                System.out.println("Création d'un nouveau fichier JSON car aucun n'existe.");
            }

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

            // Save updated data back to the JSON file
            jsonData.add("residents", residents);
            jsonData.add("intervenants", intervenants);

            try (FileWriter writer = new FileWriter(FILE_PATH)) {
                gson.toJson(jsonData, writer);
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de la création du compte : " + e.getMessage());
        }
    }

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

    private String promptForValidDate(Scanner scanner, String message) {
        String date;
        do {
            System.out.print(message);
            date = scanner.nextLine().trim();
            if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                System.out.println("Format de date invalide. Veuillez utiliser AAAA-MM-JJ.");
            }
        } while (!date.matches("\\d{4}-\\d{2}-\\d{2}"));
        return date;
    }

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
    
    public void getTimeSlot(User activeUser) {

    } 
    
    public void editTimeSlot(User activeUser, String[] data) {
        
    }
    
    
    @Override
    protected boolean isAuthorized() {
        // TODO Auto-generated method stub
        return true;
    }
}
