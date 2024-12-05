package Model;
import java.time.LocalDate;

public class RequeteTravail {
    private Resident resident;
    private String titre;
    private String description;
    private TypeTravail typeTravaux; // Enum pour les types travaaux
    private LocalDate dateDebut;
    private StatutProjet statut; // Enum pour le statut

    public RequeteTravail(Resident resident, String titre, String description, TypeTravail typeTravaux, LocalDate dateDebut) {
        this.resident = resident;
        this.titre = titre;
        this.description = description;
        this.typeTravaux = typeTravaux;
        this.dateDebut = dateDebut;
        this.statut = StatutProjet.EN_ATTENTE; // Statut par défaut
    }


    
    
    public void setResident(Resident resident) {
        this.resident = resident;
    }

    // Getters
    public String getQuartier() {
        return resident.getQuartier();
    }

    public String getResident() {
        return resident.getUsername();
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public TypeTravail getTypeTravaux() {
        return typeTravaux;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public StatutProjet getStatut() {
        return statut;
    }

    @Override
    public String toString() {
        return "Requête de Travail :" +
                "\nSoumis par : " + getResident() +
                "\nTitre : " + titre +
                "\nDescription : " + description +
                "\nType de travaux : " + typeTravaux +
                "\nDate de début espéré: " + dateDebut +
                "\nStatut : " + statut +
                "\nQuartier : " + getQuartier();
                
    }
}
