/**
 * RequeteTravailController gère les requêtes de travail soumises par les résidents
 * et leur interaction avec les intervenants. Elle prend en charge la création, la gestion,
 * et le filtrage des requêtes de travail ainsi que la soumission des candidatures.
 * 
 * Cette classe utilise des fichiers JSON pour conserver les données des requêtes et
 * des candidatures.
 */
package com.example.maville.Controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.example.maville.Model.Intervenant;
import com.example.maville.Model.RequeteTravail;
import com.example.maville.Model.Resident;
import com.example.maville.Model.TypeTravail;

public class RequeteTravailController {
    /**
     * Chemin d'accès au fichier JSON contenant les requêtes de travail.
     */
    private static final String FILE_PATH = "Code/src/main/java/Data/Requetes.json";

    /**
     * Chemin d'accès au fichier JSON contenant les candidatures.
     */
    private static final String FILE_PATH_CANDIDATURES = "Code/src/main/java/Data/Candidature.json";
    
     /**
     * Liste des requêtes de travail chargées en mémoire.
     */
    private static List<RequeteTravail> requetesTravail;
    

    /**
     * Constructeur initialisant le contrôleur et chargeant les requêtes existantes.
     */
    public RequeteTravailController() {
        this.requetesTravail = new ArrayList<>();
        chargerRequetes();  // Charger les requêtes des la création du contrôleur  
    }


    /**
     * Ajoute une nouvelle requête de travail à la liste des requêtes.
     * 
     * @param requete La requête de travail à ajouter.
     */
    public static void ajouterRequete(RequeteTravail requete) {
        requetesTravail.add(requete);
    }

    /**
     * Retourne la liste des requêtes de travail en mémoire.
     * 
     * @return Liste des requêtes de travail.
     */
    public static List<RequeteTravail> getRequetes() {
        return requetesTravail;
    }

    /**
     * Permet à un résident de soumettre une nouvelle requête de travail.
     * 
     * @param scanner Scanner pour lire les entrées utilisateur.
     * @param activeResident Le résident actuellement connecté.
     */
    public void soumettreRequete(Scanner scanner, Resident activeResident) {
    System.out.println("                  --- Soumettre une Requête de Travail ---                 ");
    System.out.println("* * * Vous pouvez annuler la soumission à tout moment en entrant 'A'. * * *");

    // Saisie du titre
    String titre = null;
    while (titre == null || titre.isEmpty()) {
        System.out.print("Titre du travail : ");
        titre = scanner.nextLine().trim();
        if (titre.equalsIgnoreCase("A")) {
            System.out.println("Soumission annulée. Retour au menu principal.");
            return;
        }
        if (titre.isEmpty()) {
            System.out.println("Erreur : Le titre ne peut pas être vide. Veuillez entrer un titre valide.");
        }
    }

    // Saisie de la description
    String description = null;
    while (description == null || description.isEmpty()) {
        System.out.print("Description détaillée : ");
        description = scanner.nextLine().trim();
        if (description.equalsIgnoreCase("A")) {
            System.out.println("Soumission annulée. Retour au menu principal.");
            return;
        }
        if (description.isEmpty()) {
            System.out.println("Erreur : La description ne peut pas être vide. Veuillez entrer une description valide.");
        }
    }

    // Sélection du type de travail
    TypeTravail typeTravaux = null;
    while (typeTravaux == null) {
        System.out.println("Entrez le numéro correspondant au type de travaux souhaité :");
        System.out.println("1. Travaux Routiers");
        System.out.println("2. Gaz et Électricité");
        System.out.println("3. Construction / Rénovation");
        System.out.println("4. Entretien Paysager");
        System.out.println("5. Transport en Commun");
        System.out.println("6. Signalisation et Éclairage");
        System.out.println("7. Travaux Souterrains");
        System.out.println("8. Travaux Résidentiels");
        System.out.println("9. Entretien Urbain");
        System.out.println("10. Réseaux de Télécommunication");

        String choix = scanner.nextLine().trim();
        if (choix.equalsIgnoreCase("A")) {
            System.out.println("Soumission annulée. Retour au menu principal.");
            return;
        }

        switch (choix) {
            case "1": typeTravaux = TypeTravail.ROUTIER; break;
            case "2": typeTravaux = TypeTravail.GAZ_ELECTRIQUE; break;
            case "3": typeTravaux = TypeTravail.CONSTRUCTION_RENOVATION; break;
            case "4": typeTravaux = TypeTravail.ENTRETIEN_PAYSAGER; break;
            case "5": typeTravaux = TypeTravail.TRANSPORT_COMMUN; break;
            case "6": typeTravaux = TypeTravail.SIGNALISATION_ECLAIRAGE; break;
            case "7": typeTravaux = TypeTravail.SOUTERRAINS; break;
            case "8": typeTravaux = TypeTravail.RESIDENTIEL; break;
            case "9": typeTravaux = TypeTravail.ENTRETIEN_URBAIN; break;
            case "10": typeTravaux = TypeTravail.ENTRETIEN_RESEAU_TELECOMMUNICATION; break;
            default: System.out.println("Choix invalide. Veuillez entrer un numéro entre 1 et 10.");
        }
    }

    // Saisie de la date de début (max 1 an dans le futur)
    LocalDate dateDebut = null;
    while (dateDebut == null) {
        System.out.print("Date de début espérée (format yyyy-mm-dd) : ");
        String dateInput = scanner.nextLine();

        if (dateInput.equalsIgnoreCase("A")) {
            System.out.println("Soumission annulée. Retour au menu principal.");
            return;
        }

        try {
            LocalDate dateSaisie = LocalDate.parse(dateInput);

            // Vérification si la date est dans le futur et dans un délai d'un an
            if (dateSaisie.isAfter(LocalDate.now()) && dateSaisie.isBefore(LocalDate.now().plusYears(1))) {
                dateDebut = dateSaisie; // Date valide
            } else if (dateSaisie.isBefore(LocalDate.now())) {
                System.out.println("Erreur : La date doit être dans le futur. Veuillez réessayer.");
            } else {
                System.out.println("Erreur : La date ne peut pas dépasser un an à partir d'aujourd'hui. Veuillez réessayer.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Format de date invalide. Veuillez entrer une date au format yyyy-mm-dd.");
        }
    }

    // Création de la requête de travail
    RequeteTravail nouvelleRequete = new RequeteTravail(activeResident, titre, description, typeTravaux, dateDebut);

    // Charger les données JSON existantes
    Gson gson = new Gson();
    JsonObject jsonData = new JsonObject();
    try (FileReader reader = new FileReader(FILE_PATH)) {
        jsonData = gson.fromJson(reader, JsonObject.class);
    } catch (IOException e) {
        System.out.println("Création d'un nouveau fichier JSON car aucun n'existe.");
    }

    // Initialisation des tableaux si non présents
    JsonArray requetesArray = jsonData.has("requetes") ? jsonData.getAsJsonArray("requetes") : new JsonArray();

    // Ajouter la nouvelle requête au tableau
    JsonObject requeteJson = new JsonObject();
    requeteJson.addProperty("residentEmail", activeResident.getEmail());
    requeteJson.addProperty("titre", nouvelleRequete.getTitre());
    requeteJson.addProperty("description", nouvelleRequete.getDescription());
    requeteJson.addProperty("typeTravaux", nouvelleRequete.getTypeTravaux().toString());
    requeteJson.addProperty("dateDebut", nouvelleRequete.getDateDebut().toString());

    requetesArray.add(requeteJson);

    // Mettre à jour le JSON avec les nouvelles requêtes
    jsonData.add("requetes", requetesArray);

    // Sauvegarder les données dans le fichier
    try (FileWriter writer = new FileWriter(FILE_PATH)) {
        gson.toJson(jsonData, writer);
        System.out.println("\nRequête soumise et sauvegardée avec succès !");
    } catch (IOException e) {
        System.out.println("Erreur lors de la sauvegarde dans le fichier JSON.");
    }
}

    
    /**
     * Permet à un résident de consulter ses requêtes soumises.
     * 
     * @param scanner Scanner pour lire les entrées utilisateur.
     * @param activeResident Le résident actuellement connecté.
     */
    public void consulterMesRequetes(Scanner scanner, Resident activeResident) {
        List<RequeteTravail> mesRequetes = RequeteTravailController.getRequetesParResident(activeResident);
    
        if (mesRequetes.isEmpty()) {
            System.out.println("\nVous n'avez soumis aucune requête de travail.");
            return;
        }
    
        System.out.println("\n--- Vos Requêtes de Travail ---");
        for (int i = 0; i < mesRequetes.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + mesRequetes.get(i));
        }
    
        System.out.println("\nAppuyez sur 'Enter' pour revenir au menu principal.");
        scanner.nextLine();
    }

    /**
     * Permet à un intervenant de consulter les requêtes disponibles et d'y postuler.
     * 
     * @param scanner Scanner pour lire les entrées utilisateur.
     * @param activeIntervenant L'intervenant actuellement connecté.
     */
    public void consulterRequetes(Scanner scanner, Intervenant activeIntervenant) {
        System.out.println("\n--- Liste des Requêtes de Travail ---");
        afficherRequetesDepuisJson(); // Affiche toutes les requêtes depuis le JSON
        System.out.println("Voulez-vous appliquer un filtre ?");
        System.out.println("1. Pas de filtre");
        System.out.println("2. Filtrer par type de travaux");
        
        String choix = scanner.nextLine();
        
        switch (choix) {
            case "2":
                System.out.print("Entrez le type de travaux (ex : CONSTRUCTION_RENOVATION,ROUTIER, GAZ_ELECTRIQUE) : ");
                try {
                    TypeTravail type = TypeTravail.valueOf(scanner.nextLine().toUpperCase());
                    filtrerRequetesParType(type); // filtre par type
                } catch (IllegalArgumentException e) {
                    System.out.println("Type invalide. Affichage de toutes les requêtes.");
                    afficherRequetesDepuisJson(); // Affiche requetes si le type est invalide
                }
                break;
            default:
                afficherRequetesDepuisJson(); // Affiche toutes les requetes
                break;
        }
        

        System.out.println("Souhaitez-vous soumettre votre candidature à une de ces requêtes ? (O/N)");
        String decision = scanner.nextLine().trim().toUpperCase();
        if ("O".equals(decision)) {
            System.out.print("Entrez le numéro de la requête à laquelle vous souhaitez postuler : ");
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (index >= 0 && index < requetesTravail.size()) {
                RequeteTravail requeteChoisie = requetesTravail.get(index);
                soumettreCandidature(requeteChoisie, activeIntervenant);
            } else {
                System.out.println("Numéro de requête invalide."); 
            }
        }

        System.out.println("\nAppuyez sur 'Enter' pour revenir au menu principal.");        
        scanner.nextLine();
    }                 
    
    /**
     * Récupère les requêtes associées à un résident donné.
     * 
     * @param resident Le résident pour lequel rechercher les requêtes.
     * @return Liste des requêtes associées au résident.
     */
    public static List<RequeteTravail> getRequetesParResident(Resident resident) {
    return requetesTravail.stream()
            .filter(requete -> requete.getResident().equals(resident.getEmail()))
            .collect(Collectors.toList());
    }

    /**
     * Filtre les requêtes de travail par type de travaux.
     * 
     * @param type Le type de travaux pour filtrer les requêtes.
     */
    public void filtrerRequetesParType(TypeTravail type) {       // filtrer par type
        try (FileReader reader = new FileReader(FILE_PATH)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                if (jsonObject.has("requetes")) {
                    JsonArray requetesArray = jsonObject.getAsJsonArray("requetes");
                    boolean found = false;
                    for (int i = 0; i < requetesArray.size(); i++) {
                        JsonObject requete = requetesArray.get(i).getAsJsonObject();
                        String typeTravail = requete.get("typeTravaux").getAsString();
                        if (typeTravail.equalsIgnoreCase(type.name())) {
                            found = true;
                            System.out.println("[" + (i + 1) + "]");
                            System.out.println("  - Résident : " + requete.get("residentEmail").getAsString());
                            System.out.println("  - Titre : " + requete.get("titre").getAsString());
                            System.out.println("  - Description : " + requete.get("description").getAsString());
                            System.out.println("  - Type : " + typeTravail);
                            System.out.println("  - Date de début : " + requete.get("dateDebut").getAsString());
                            System.out.println();
                        }
                    }
                    if (!found) {
                        System.out.println("\nAucune requête de type " + type.name() + " trouvée.");
                    }
                } else {
                    System.out.println("\nAucune requête trouvée dans le fichier JSON.");
                }
            } else {
                System.out.println("\nLe fichier JSON est mal formaté.");
            }
        } catch (IOException e) {
            System.out.println("\nErreur lors de la lecture du fichier JSON : " + e.getMessage());
        }
    }
    
    /**
     * Filtre les requêtes de travail par date de début, en les triant par ordre décroissant.
     * 
     * @return Liste triée des requêtes de travail par date de début.
     */
    public static List<RequeteTravail> filtrerRequetesParDate() {        // à changer
        return requetesTravail.stream()
                .sorted(Comparator.comparing(RequeteTravail::getDateDebut).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Affiche toutes les requêtes de travail depuis le fichier JSON.
     */
    public void afficherRequetesDepuisJson() {                            // affiche toutes les requetes
        try (FileReader reader = new FileReader(FILE_PATH)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                if (jsonObject.has("requetes")) {
                    JsonArray requetesArray = jsonObject.getAsJsonArray("requetes");
                    if (requetesArray.size() > 0) {
                        System.out.println("\n--- Requêtes de Travail ---");
                        for (int i = 0; i < requetesArray.size(); i++) {
                            JsonObject requete = requetesArray.get(i).getAsJsonObject();
                            System.out.println("[" + (i + 1) + "]");
                            System.out.println("  - Résident : " + requete.get("residentEmail").getAsString());
                            System.out.println("  - Titre : " + requete.get("titre").getAsString());
                            System.out.println("  - Description : " + requete.get("description").getAsString());
                            System.out.println("  - Type : " + requete.get("typeTravaux").getAsString());
                            System.out.println("  - Date de début : " + requete.get("dateDebut").getAsString());
                            System.out.println();
                        }
                    } else {
                        System.out.println("\nAucune requête à afficher.");
                    }
                } else {
                    System.out.println("\nAucune requête trouvée dans le fichier JSON.");
                }
            } else {
                System.out.println("\nLe fichier JSON est mal formaté.");
            }
        } catch (IOException e) {
            System.out.println("\nErreur lors de la lecture du fichier JSON : " + e.getMessage());
        }
    }
    

    /**
     * Permet à un intervenant de soumettre sa candidature pour une requête de travail.
     * 
     * @param requete La requête pour laquelle postuler.
     * @param activeIntervenant L'intervenant actuellement connecté.
     */
    public void soumettreCandidature(RequeteTravail requete, Intervenant activeIntervenant) { 
        // Permet à un intervenant de soumettre sa candidature
        Gson gson = new Gson();
        JsonObject jsonData = new JsonObject();
        
        // Lecture du fichier existant ou création d'un nouveau fichier JSON si non existant
        try (FileReader reader = new FileReader(FILE_PATH_CANDIDATURES)) {
            jsonData = gson.fromJson(reader, JsonObject.class);
        } catch (IOException e) {
            System.out.println("Création d'un nouveau fichier JSON car aucun n'existe.");
            jsonData = new JsonObject(); 
        }
    
        // Initialise  tableaux si non présents
        JsonArray candidaturesArray = jsonData.has("candidatures") ? jsonData.getAsJsonArray("candidatures") : new JsonArray();
    
        // Vérification si l'intervenant a déjà soumis une candidature pour cette requête
        for (int i = 0; i < candidaturesArray.size(); i++) {
            JsonObject candidature = candidaturesArray.get(i).getAsJsonObject();
            String intervenantEmail = candidature.get("intervenantEmail").getAsString();
            String residentEmail = candidature.get("residentEmail").getAsString();
    
            
            if (intervenantEmail.equals(activeIntervenant.getEmail()) && residentEmail.equals(requete.getResident())) {
                System.out.println("Vous avez déjà postulé pour ce poste.");
                return;  
            }
        }
    
        // Ajouter la nouvelle candidature au tableau si l'intervenant n'a pas encore postulé
        JsonObject candidatureJson = new JsonObject();
        candidatureJson.addProperty("intervenantEmail", activeIntervenant.getEmail()); // email de l'intervenant
        candidatureJson.addProperty("residentEmail", requete.getResident()); // email du résident (poste)
        candidatureJson.addProperty("dateSoumission", LocalDate.now().toString()); // Date de soumission
    
        candidaturesArray.add(candidatureJson);
    
        jsonData.add("candidatures", candidaturesArray);
    
        // Sauvegarder les données dans le fichier
        try (FileWriter writer = new FileWriter(FILE_PATH_CANDIDATURES)) {
            gson.toJson(jsonData, writer);
            System.out.println("\nCandidature soumise et sauvegardée avec succès !");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde dans le fichier Candidature.json.");
        }
    }
    
    
    /**
     * Charge les requêtes de travail depuis le fichier JSON en mémoire.
     */
    public void chargerRequetes() {  // à faire pour candidature aussi
        Gson gson = new Gson();
        JsonObject jsonData = new JsonObject();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            jsonData = gson.fromJson(reader, JsonObject.class);
        } catch (IOException e) {
            System.out.println("Aucun fichier JSON trouvé, ou erreur lors de la lecture.");
        }

        
        if (jsonData.has("requetes")) {
            JsonArray requetesArray = jsonData.getAsJsonArray("requetes");
            for (int i = 0; i < requetesArray.size(); i++) {
                JsonObject requeteJson = requetesArray.get(i).getAsJsonObject();
                
               
                String residentEmail = requeteJson.get("residentEmail").getAsString();
                String titre = requeteJson.get("titre").getAsString();
                String description = requeteJson.get("description").getAsString();
                String typeTravauxStr = requeteJson.get("typeTravaux").getAsString();
                String dateDebutStr = requeteJson.get("dateDebut").getAsString();

                
                TypeTravail typeTravaux = TypeTravail.valueOf(typeTravauxStr);
                
                
                LocalDate dateDebut = LocalDate.parse(dateDebutStr);

                
                Resident resident = new Resident();
                resident.setName(residentEmail);

                // Créer une nouvelle requête
                RequeteTravail requete = new RequeteTravail(resident, titre, description, typeTravaux, dateDebut);
                
                // Ajouter la requête à la liste
                requetesTravail.add(requete);
            }
        }
    }
}