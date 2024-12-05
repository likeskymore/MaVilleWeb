package Model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Entraves extends Travail {
    private List<String> ruesAffectees;     // List of affected streets
    private String niveauImpact;           // Impact level (e.g., low, medium, high)

    // Constructor that only takes id, titre, description, and niveauImpact
    public Entraves(String id, String titre, String description, String niveauImpact) {
        super(id, titre, description, LocalDate.now(), LocalDate.now(), StatutProjet.EN_COURS, TypeTravail.ENTRETIEN_PAYSAGER);
        this.id = id;
        this.ruesAffectees = new ArrayList<>();
        this.niveauImpact = niveauImpact;
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
            "Entraves ID: " + id +
            "\nTitre: " + titre +
            "\nDescription: " + description +
            "\nStatut: " + statut +
            "\nNiveau d'impact: " + niveauImpact +
            "\nRues affectées: " + ruesAffectees
        );
    }

    // New methods specific to Entraves

    // Adds a new affected street to the list
    public void ajouterRueAffectee(String rue) {
        if (rue != null && !ruesAffectees.contains(rue)) {
            ruesAffectees.add(rue);
        }
    }

    // Modifies the impact level
    public void modifierNiveauImpact(String nouveauNiveauImpact) {
        this.niveauImpact = nouveauNiveauImpact;
    }

    // Getters for additional attributes
    public List<String> getRuesAffectees() {
        return ruesAffectees;
    }

    public String getNiveauImpact() {
        return niveauImpact;
    }
}