import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TravailDeserializer implements JsonDeserializer<Travail> {
    private final String category;

    public TravailDeserializer(String category) {
        this.category = category;
    }

    @Override
    public Travail deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        // Extract shared attributes
        String id = jsonObject.get("id").getAsString();
        String reasonCategory = jsonObject.get("reason_category").getAsString();
        String occupancyName = jsonObject.get("occupancy_name").getAsString();
        String titre = reasonCategory + " - " + occupancyName;
        String description = jsonObject.get("reason_category").getAsString();
        LocalDate dateDebut = LocalDate.parse(jsonObject.get("duration_start_date").getAsString(), DateTimeFormatter.ISO_DATE_TIME);
        LocalDate dateFin = LocalDate.parse(jsonObject.get("duration_end_date").getAsString(), DateTimeFormatter.ISO_DATE_TIME);
        StatutProjet statut = StatutProjet.EN_COURS;
        TypeTravail type = TypeTravail.ENTRETIEN_PAYSAGER;

        // Use the specified category
        if (category.equalsIgnoreCase("Travaux")) {
            // Create and populate a Projet instance
            String horaires = generateHoraires(jsonObject);
            Projet projet = new Projet(id, titre, description, dateDebut, dateFin, statut, type, horaires);

            // Add specific attributes
            String quartier = jsonObject.get("boroughid").getAsString();
            String rue = jsonObject.get("occupancy_name").getAsString();
            projet.ajouterQuartierAffecte(quartier);
            projet.ajouterRueAffectee(rue);

            return projet;
        // } else if (category.equalsIgnoreCase("Entraves")) {
        //     // Create and populate an Entraves instance
        //     Entraves entrave = new Entraves(id, titre, description, dateDebut, dateFin, statut, type);

        //     // Add specific attributes for Entraves (if any exist in the JSON)
        //     return entrave;
        } else {
            throw new JsonParseException("Unknown category: " + category);
        }
    }

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