import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Projet extends Travail {
    private List<String> ruesAffectees;     // List of affected streets
    private List<String> quartiersAffectes; // List of affected neighborhoods
    private String horaires;                // Schedule for the project

    public Projet(String id, String titre, String description, LocalDate dateDebut, LocalDate dateFin, StatutProjet statut, TypeTravail type, String horaires) {
        super(id, titre, description, dateDebut, dateFin, statut, type);
        this.ruesAffectees = new ArrayList<>();       // Initialize lists to empty lists
        this.quartiersAffectes = new ArrayList<>();
        this.horaires = horaires;
    }

    // Implementing the abstract method to update the project status
    @Override
    public void updateStatut(StatutProjet nouveauStatut) {
        this.statut = nouveauStatut;
    }

    // Implementing the abstract method to modify the project dates
    @Override
    public void modifierDate(LocalDate nouveauDateDebut, LocalDate nouveauDateFin) {
        if (nouveauDateDebut.isAfter(nouveauDateFin)) {
            throw new IllegalArgumentException("La date de début doit être avant la date de fin.");
        }
        this.dateDebut = nouveauDateDebut;
        this.dateFin = nouveauDateFin;
    }

    // Implementing the abstract method to display project details
    @Override
    public void afficherDetails() {
        System.out.println(
            "Projet ID: " + id +
            "\nTitre: " + titre +
            "\nDescription: " + description +
            "\nDate de début: " + dateDebut +
            "\nDate de fin: " + dateFin +
            "\nStatut: " + statut +
            "\nType de travail: " + type +
            "\nQuartiers affectés: " + quartiersAffectes +
            "\nRues affectées: " + ruesAffectees +
            "\nHoraires: " + horaires
        );
    }

    // New methods specific to Projet

    // Adds a new affected neighborhood to the list
    public void ajouterQuartierAffecte(String quartier) {
        if (!quartiersAffectes.contains(quartier)) {
            quartiersAffectes.add(quartier);
        }
    }

    // Adds a new affected street to the list
    public void ajouterRueAffectee(String rue) {
        if (!ruesAffectees.contains(rue)) {
            ruesAffectees.add(rue);
        }
    }

    // Modifies the project schedule
    public void modifierHoraire(String nouveauHoraire) {
        this.horaires = nouveauHoraire;
    }

    // Getters for additional attributes
    public List<String> getRuesAffectees() {
        return ruesAffectees;
    }

    public List<String> getQuartiersAffectes() {
        return quartiersAffectes;
    }

    public String getHoraires() {
        return horaires;
    }
}