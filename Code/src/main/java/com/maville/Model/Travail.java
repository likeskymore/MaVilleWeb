/**
 * La classe abstraite Travail représente une entité de base pour les projets et les entraves.
 * Elle contient des attributs communs tels que l'identifiant, le titre, la description,
 * les dates de début et de fin, le statut et le type de travail.
 * 
 * Les sous-classes doivent implémenter la méthode abstraite {@code afficherDetails()}.
 */
package com.maville.Model;
import java.time.LocalDate;

public abstract class Travail {

    /**
     * Identifiant unique du travail.
     */
    protected String id;

    /**
     * Titre du travail.
     */
    protected String titre;

    /**
     * Description du travail.
     */
    protected String description;

    /**
     * Date de début du travail.
     */
    protected LocalDate dateDebut;

    /**
     * Date de fin du travail.
     */
    protected LocalDate dateFin;

    /**
     * Statut actuel du travail.
     */
    protected StatutProjet statut;

    /**
     * Type de travail (par exemple : ENTRETIEN_PAYSAGER, CONSTRUCTION).
     */
    protected TypeTravail type;

    // Constructor
    /**
     * Constructeur pour initialiser un travail avec des informations complètes.
     * 
     * @param id Identifiant unique.
     * @param titre Titre du travail.
     * @param description Description du travail.
     * @param dateDebut Date de début.
     * @param dateFin Date de fin.
     * @param statut Statut du travail.
     * @param type Type de travail.
     */
    public Travail(String id, String titre, String description, LocalDate dateDebut, LocalDate dateFin, StatutProjet statut, TypeTravail type) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
        this.type = type;
    }


    /**
     * Constructeur par défaut.
     */
    public Travail() {}

    // Abstract methods that subclasses need to implement
    /**
     * Met à jour le statut du travail.
     * 
     * @param nouveauStatut Le nouveau statut à appliquer.
     */
    public void updateStatut(StatutProjet nouveauStatut) {
        this.statut = nouveauStatut;
    }

    /**
     * Modifie les dates de début et de fin du travail.
     * 
     * @param nouveauDateDebut La nouvelle date de début.
     * @param nouveauDateFin La nouvelle date de fin.
     * @throws IllegalArgumentException Si la date de début est postérieure à la date de fin.
     */
    public void modifierDate(LocalDate nouveauDateDebut, LocalDate nouveauDateFin) {
        if (nouveauDateDebut.isAfter(nouveauDateFin)) {
            throw new IllegalArgumentException("La date de début doit être avant la date de fin.");
        }
        this.dateDebut = nouveauDateDebut;
        this.dateFin = nouveauDateFin;
    }

    /**
     * Affiche les détails complets du travail.
     * Cette méthode doit être implémentée par les sous-classes.
     */
    public abstract void afficherDetails();

    // Getters and setters for shared attributes
    /**
     * Retourne l'identifiant unique du travail.
     * 
     * @return L'identifiant unique.
     */
    public String getId() {
        return id;
    }

    /**
     * Retourne le titre du travail.
     * 
     * @return Le titre.
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Modifie le titre du travail.
     * 
     * @param titre Le nouveau titre.
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Retourne la description du travail.
     * 
     * @return La description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modifie la description du travail.
     * 
     * @param description La nouvelle description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retourne la date de début du travail.
     * 
     * @return La date de début.
     */
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    /**
     * Modifie la date de début du travail.
     * 
     * @param dateDebut La nouvelle date de début.
     */
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Retourne la date de fin du travail.
     * 
     * @return La date de fin.
     */
    public LocalDate getDateFin() {
        return dateFin;
    }

    /**
     * Modifie la date de fin du travail.
     * 
     * @param dateFin La nouvelle date de fin.
     */
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Retourne le statut actuel du travail.
     * 
     * @return Le statut.
     */
    public StatutProjet getStatut() {
        return statut;
    }

    /**
     * Modifie le statut du travail.
     * 
     * @param statut Le nouveau statut.
     */
    public void setStatut(StatutProjet statut) {
        this.statut = statut;
    }

    /**
     * Retourne le type de travail.
     * 
     * @return Le type de travail.
     */
    public TypeTravail getType() {
        return type;
    }

    /**
     * Modifie le type de travail.
     * 
     * @param type Le nouveau type de travail.
     */
    public void setType(TypeTravail type) {
        this.type = type;
    }
}