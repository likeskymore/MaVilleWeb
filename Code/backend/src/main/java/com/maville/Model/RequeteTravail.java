/**
 * La classe RequeteTravail représente une requête de travail soumise par un résident,
 * avec des informations telles que le titre, la description, le type de travaux,
 * la date de début, et le statut du projet.
 */
package com.maville.Model;
import java.time.LocalDate;

public class RequeteTravail {

    /**
     * Résident ayant soumis la requête.
     */
    private Resident resident;

    /**
     * Titre de la requête de travail.
     */
    private String titre;

    /**
     * Description de la requête de travail.
     */
    private String description;

    /**
     * Type de travaux requis.
     */
    private TypeTravail typeTravaux; // Enum pour les types travaaux

    /**
     * Date de début souhaitée pour les travaux.
     */
    private LocalDate dateDebut;

    /**
     * Statut actuel de la requête de travail.
     */
    private StatutProjet statut; // Enum pour le statut

    /**
     * Constructeur pour créer une requête de travail avec les informations fournies.
     * 
     * @param resident Le résident ayant soumis la requête.
     * @param titre Le titre de la requête.
     * @param description La description des travaux.
     * @param typeTravaux Le type de travaux requis.
     * @param dateDebut La date de début souhaitée.
     */
    public RequeteTravail(Resident resident, String titre, String description, TypeTravail typeTravaux, LocalDate dateDebut) {
        this.resident = resident;
        this.titre = titre;
        this.description = description;
        this.typeTravaux = typeTravaux;
        this.dateDebut = dateDebut;
        this.statut = StatutProjet.EN_ATTENTE; // Statut par défaut
    }

    /**
     * Modifie le résident associé à la requête.
     * 
     * @param resident Le nouveau résident.
     */
    public void setResident(Resident resident) {
        this.resident = resident;
    }

    /**
     * Retourne le nom du résident ayant soumis la requête.
     * 
     * @return Le nom du résident.
     */
    public String getResident() {
        return resident.getName();
    }

    /**
     * Retourne le titre de la requête.
     * 
     * @return Le titre de la requête.
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Retourne la description de la requête.
     * 
     * @return La description des travaux.
     */
    public String getDescription() {
        return description;
    }

     /**
     * Retourne le type de travaux requis.
     * 
     * @return Le type de travaux.
     */
    public TypeTravail getTypeTravaux() {
        return typeTravaux;
    }

    /**
     * Retourne la date de début souhaitée pour les travaux.
     * 
     * @return La date de début.
     */
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    /**
     * Retourne le statut actuel de la requête de travail.
     * 
     * @return Le statut du projet.
     */
    public StatutProjet getStatut() {
        return statut;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la requête.
     * 
     * @return Une chaîne représentant la requête de travail.
     */
    @Override
    public String toString() {
        return "Requête de Travail :" +
                "\nSoumis par : " + getResident() +
                "\nTitre : " + titre +
                "\nDescription : " + description +
                "\nType de travaux : " + typeTravaux +
                "\nDate de début espéré: " + dateDebut +
                "\nStatut : " + statut;                
    }
}
