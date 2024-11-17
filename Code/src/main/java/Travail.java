import java.time.LocalDate;

public abstract class Travail {
    protected String id;
    protected String titre;
    protected String description;
    protected LocalDate dateDebut;
    protected LocalDate dateFin;
    protected StatutProjet statut;
    protected TypeTravail type;

    // Constructor
    public Travail(String id, String titre, String description, LocalDate dateDebut, LocalDate dateFin, StatutProjet statut, TypeTravail type) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
        this.type = type;
    }

    // Abstract methods that subclasses need to implement
    public void updateStatut(StatutProjet nouveauStatut) {
        this.statut = nouveauStatut;
    }
    public void modifierDate(LocalDate nouveauDateDebut, LocalDate nouveauDateFin) {
        if (nouveauDateDebut.isAfter(nouveauDateFin)) {
            throw new IllegalArgumentException("La date de début doit être avant la date de fin.");
        }
        this.dateDebut = nouveauDateDebut;
        this.dateFin = nouveauDateFin;
    }
    public abstract void afficherDetails();

    // Getters and setters for shared attributes
    public String getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public StatutProjet getStatut() {
        return statut;
    }

    public void setStatut(StatutProjet statut) {
        this.statut = statut;
    }

    public TypeTravail getType() {
        return type;
    }

    public void setType(TypeTravail type) {
        this.type = type;
    }
}