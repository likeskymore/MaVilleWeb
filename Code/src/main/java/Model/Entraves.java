/**
 * La classe Entraves représente une entrave causée par des travaux publics,
 * avec des détails tels que les rues affectées et le niveau d'impact.
 * Elle hérite de la classe Travail et implémente des méthodes spécifiques
 * pour gérer les entraves.
 */
package Model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Entraves extends Travail {
    /**
     * Liste des rues affectées par l'entrave.
     */
    private List<String> ruesAffectees;     // List of affected streets

    /**
     * Niveau d'impact de l'entrave.
     */
    private String niveauImpact;           // Impact level (e.g., low, medium, high)

    /**
     * Constructeur pour créer une instance d'Entraves avec des informations de base.
     * 
     * @param id Identifiant unique de l'entrave.
     * @param titre Titre descriptif de l'entrave.
     * @param description Description des travaux.
     * @param niveauImpact Niveau d'impact associé à l'entrave.
     */
    // Constructor that only takes id, titre, description, and niveauImpact
    public Entraves(String id, String titre, String description, String niveauImpact) {
        super(id, titre, description, LocalDate.now(), LocalDate.now(), StatutProjet.EN_COURS, TypeTravail.ENTRETIEN_PAYSAGER);
        this.id = id;
        this.ruesAffectees = new ArrayList<>();
        this.niveauImpact = niveauImpact;
    }

    /**
     * Constructeur par défaut.
     */
    public Entraves() {}

    /**
     * Met à jour le statut du projet associé à l'entrave.
     * 
     * @param nouveauStatut Le nouveau statut du projet.
     */
    // Implementing the abstract method to update the project status
    @Override
    public void updateStatut(StatutProjet nouveauStatut) {
        this.statut = nouveauStatut;
    }

    /**
     * Modifie les dates de début et de fin du projet associé à l'entrave.
     * 
     * @param nouveauDateDebut La nouvelle date de début.
     * @param nouveauDateFin La nouvelle date de fin.
     * @throws IllegalArgumentException Si la date de début est plus tard que la date de fin.
     */
    // Implementing the abstract method to modify the project dates
    @Override
    public void modifierDate(LocalDate nouveauDateDebut, LocalDate nouveauDateFin) {
        if (nouveauDateDebut.isAfter(nouveauDateFin)) {
            throw new IllegalArgumentException("La date de début doit être avant la date de fin.");
        }
        this.dateDebut = nouveauDateDebut;
        this.dateFin = nouveauDateFin;
    }

    /**
     * Affiche les détails complets de l'entrave.
     */
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

    /**
     * Ajoute une nouvelle rue affectée à la liste des rues impactées par l'entrave.
     * 
     * @param rue Le nom de la rue à ajouter.
     */
    // Adds a new affected street to the list
    public void ajouterRueAffectee(String rue) {
        if (rue != null && !ruesAffectees.contains(rue)) {
            ruesAffectees.add(rue);
        }
    }

    /**
     * Modifie le niveau d'impact de l'entrave.
     * 
     * @param nouveauNiveauImpact Le nouveau niveau d'impact.
     */
    // Modifies the impact level
    public void modifierNiveauImpact(String nouveauNiveauImpact) {
        this.niveauImpact = nouveauNiveauImpact;
    }

    /**
     * Retourne la liste des rues affectées par l'entrave.
     * 
     * @return Liste des rues affectées.
     */
    // Getters for additional attributes
    public List<String> getRuesAffectees() {
        return ruesAffectees;
    }

    /**
     * Retourne le niveau d'impact de l'entrave.
     * 
     * @return Niveau d'impact.
     */
    public String getNiveauImpact() {
        return niveauImpact;
    }
}