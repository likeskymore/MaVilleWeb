/**
 * La classe Projet représente un projet dans le système, contenant des informations
 * telles que les rues et quartiers affectés, ainsi que les horaires du projet.
 * Elle hérite de la classe Travail et ajoute des attributs spécifiques aux projets.
 */
package com.example.maville.Model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Projet extends Travail {
    /**
     * Liste des rues affectées par le projet.
     */
    private List<String> ruesAffectees;     // List of affected streets

    /**
     * Liste des quartiers affectés par le projet.
     */
    private List<String> quartiersAffectes; // List of affected neighborhoods

    /**
     * Horaires prévus pour le projet.
     */
    private String horaires;                // Schedule for the project

    /**
     * Constructeur pour créer un projet avec tous les attributs.
     * 
     * @param id Identifiant unique du projet.
     * @param titre Titre du projet.
     * @param description Description du projet.
     * @param dateDebut Date de début du projet.
     * @param dateFin Date de fin du projet.
     * @param statut Statut actuel du projet.
     * @param type Type de travaux associé au projet.
     * @param horaires Horaires prévus pour le projet.
     */
    public Projet(String id, String titre, String description, LocalDate dateDebut, LocalDate dateFin, StatutProjet statut, TypeTravail type, String horaires) {
        super(id, titre, description, dateDebut, dateFin, statut, type);
        this.ruesAffectees = new ArrayList<>();       // Initialize lists to empty lists
        this.quartiersAffectes = new ArrayList<>();
        this.horaires = horaires;
    }

    /**
     * Constructeur par défaut.
     */
    public Projet() {}

    /**
     * Met à jour le statut du projet.
     * 
     * @param nouveauStatut Le nouveau statut du projet.
     */
    // Implementing the abstract method to update the project status
    @Override
    public void updateStatut(StatutProjet nouveauStatut) {
        this.statut = nouveauStatut;
    }

    /**
     * Modifie les dates de début et de fin du projet.
     * 
     * @param nouveauDateDebut La nouvelle date de début.
     * @param nouveauDateFin La nouvelle date de fin.
     * @throws IllegalArgumentException Si la date de début est postérieure à la date de fin.
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
     * Affiche les détails complets du projet.
     */
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

    /**
     * Ajoute un quartier affecté à la liste des quartiers impactés.
     * 
     * @param quartier Le nom du quartier à ajouter.
     */
    // Adds a new affected neighborhood to the list
    public void ajouterQuartierAffecte(String quartier) {
        if (!quartiersAffectes.contains(quartier)) {
            quartiersAffectes.add(quartier);
        }
    }

    /**
     * Ajoute une rue affectée à la liste des rues impactées.
     * 
     * @param rue Le nom de la rue à ajouter.
     */
    // Adds a new affected street to the list
    public void ajouterRueAffectee(String rue) {
        if (!ruesAffectees.contains(rue)) {
            ruesAffectees.add(rue);
        }
    }

    /**
     * Modifie les horaires du projet.
     * 
     * @param nouveauHoraire Le nouvel horaire du projet.
     */
    // Modifies the project schedule
    public void modifierHoraire(String nouveauHoraire) {
        this.horaires = nouveauHoraire;
    }

    // Getters for additional attributes
    /**
     * Retourne la liste des rues affectées par le projet.
     * 
     * @return Liste des rues affectées.
     */
    public List<String> getRuesAffectees() {
        return ruesAffectees;
    }

    /**
     * Retourne la liste des quartiers affectés par le projet.
     * 
     * @return Liste des quartiers affectés.
     */
    public List<String> getQuartiersAffectes() {
        return quartiersAffectes;
    }

    /**
     * Retourne les horaires prévus pour le projet.
     * 
     * @return Horaires du projet.
     */
    public String getHoraires() {
        return horaires;
    }
}