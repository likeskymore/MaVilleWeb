/**
 * La classe TravailDeserializer implémente l'interface {@link JsonDeserializer}
 * pour désérialiser des objets JSON en instances de {@link Travail}.
 * 
 * Cette classe gère la désérialisation des catégories spécifiques telles que
 * "Travaux" pour les projets et "Entraves" pour les contraintes liées aux travaux.
 */
package com.example.maville.Model;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TravailDeserializer implements JsonDeserializer<Travail> {
    /**
     * Catégorie des objets à désérialiser (par exemple : "Travaux", "Entraves").
     */
    private final String category;

    /**
     * Constructeur pour initialiser le désérialiseur avec une catégorie spécifique.
     * 
     * @param category La catégorie des objets à désérialiser.
     */
    public TravailDeserializer(String category) {
        this.category = category;
    }

    /**
     * Désérialise un objet JSON en une instance de {@link Travail} ou l'une de ses sous-classes.
     * 
     * @param json L'élément JSON à désérialiser.
     * @param typeOfT Le type attendu de l'objet.
     * @param context Le contexte de désérialisation.
     * @return Une instance de {@link Travail}, {@link Projet} ou {@link Entraves}.
     * @throws JsonParseException Si une erreur survient lors de la désérialisation.
     */
    @Override
    public Travail deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        // Extract shared attributes with null checks
        String id = jsonObject.has("id") && !jsonObject.get("id").isJsonNull()
                ? jsonObject.get("id").getAsString()
                : "Unknown ID";

        String reasonCategory = jsonObject.has("reason_category") && !jsonObject.get("reason_category").isJsonNull()
                ? jsonObject.get("reason_category").getAsString()
                : "Unknown Category";

        String occupancyName = jsonObject.has("occupancy_name") && !jsonObject.get("occupancy_name").isJsonNull()
                ? jsonObject.get("occupancy_name").getAsString()
                : "Unknown Occupancy";

        String titre = reasonCategory + " --- " + occupancyName;
        String description = reasonCategory;

        LocalDate dateDebut = jsonObject.has("duration_start_date") && !jsonObject.get("duration_start_date").isJsonNull()
                ? LocalDate.parse(jsonObject.get("duration_start_date").getAsString(), DateTimeFormatter.ISO_DATE_TIME)
                : LocalDate.now();

        LocalDate dateFin = jsonObject.has("duration_end_date") && !jsonObject.get("duration_end_date").isJsonNull()
                ? LocalDate.parse(jsonObject.get("duration_end_date").getAsString(), DateTimeFormatter.ISO_DATE_TIME)
                : LocalDate.now();

        StatutProjet statut = StatutProjet.EN_COURS;
        TypeTravail type = TypeTravail.ENTRETIEN_PAYSAGER;

        if (category.equalsIgnoreCase("Travaux")) {
            // Create and populate a Projet instance
            String horaires = generateHoraires(jsonObject);
            Projet projet = new Projet(id, titre, description, dateDebut, dateFin, statut, type, horaires);

            // Add specific attributes with null checks
            if (jsonObject.has("boroughid") && !jsonObject.get("boroughid").isJsonNull()) {
                String quartier = jsonObject.get("boroughid").getAsString();
                projet.ajouterQuartierAffecte(quartier);
            }

            if (jsonObject.has("occupancy_name") && !jsonObject.get("occupancy_name").isJsonNull()) {
                String rue = jsonObject.get("occupancy_name").getAsString();
                projet.ajouterRueAffectee(rue);
            }

            return projet;

        } else if (category.equalsIgnoreCase("Entraves")) {
            // Create and populate an Entraves instance
            String entraveId = jsonObject.has("id_request") && !jsonObject.get("id_request").isJsonNull()
                    ? jsonObject.get("id_request").getAsString()
                    : "Unknown Entrave ID";

            String streetImpactWidth = jsonObject.has("streetimpactwidth") && !jsonObject.get("streetimpactwidth").isJsonNull()
                    ? jsonObject.get("streetimpactwidth").getAsString()
                    : "Unknown Width";
            
            String streetImpactType = jsonObject.has("streetimpacttype") && !jsonObject.get("streetimpacttype").isJsonNull()
                    ? jsonObject.get("streetimpacttype").getAsString()
                    : "Unknown Type";

            String niveauImpact = "width: "+ streetImpactWidth + " --- " + streetImpactType;
            String entraveTitre = "L'entrave du projet " + entraveId;
            String entraveDescription = "None";

            Entraves entrave = new Entraves(entraveId, entraveTitre, entraveDescription, niveauImpact);

            if (jsonObject.has("streetid") && !jsonObject.get("streetid").isJsonNull()) {
                String street = jsonObject.get("streetid").getAsString();
                entrave.ajouterRueAffectee(street);
            }
            return entrave;

        } else {
            throw new JsonParseException("Unsupported category: " + category);
        }
    }

    /**
     * Génère une chaîne de caractères représentant les horaires d'un projet à partir
     * des informations présentes dans l'objet JSON.
     * 
     * @param jsonObject L'objet JSON contenant les informations d'horaires.
     * @return Une chaîne formatée représentant les horaires.
     */
    private String generateHoraires(JsonObject jsonObject) {
        StringBuilder horaires = new StringBuilder();
        String[] days = {"mon", "tue", "wed", "thu", "fri", "sat", "sun"};
        for (String day : days) {
            boolean active = jsonObject.has("duration_days_" + day + "_active") 
                    && "t".equals(jsonObject.get("duration_days_" + day + "_active").getAsString());
    
            if (active) {
                String startTime = jsonObject.has("duration_days_" + day + "_start_time") 
                        && !jsonObject.get("duration_days_" + day + "_start_time").isJsonNull()
                        ? jsonObject.get("duration_days_" + day + "_start_time").getAsString()
                        : "N/A";
    
                String endTime = jsonObject.has("duration_days_" + day + "_end_time") 
                        && !jsonObject.get("duration_days_" + day + "_end_time").isJsonNull()
                        ? jsonObject.get("duration_days_" + day + "_end_time").getAsString()
                        : "N/A";
    
                horaires.append(day.substring(0, 1).toUpperCase()).append(day.substring(1)).append(": ")
                        .append(startTime).append(" - ").append(endTime).append("; ");
            }
        }
        return horaires.toString().trim();
    }
}