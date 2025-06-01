/**
 * La classe Candidature représente une candidature soumise par un intervenant
 * pour une requête de travail d'un résident. Elle contient les informations
 * relatives à l'intervenant, au résident, la date de soumission, et le statut
 * de la candidature.
 */
package com.example.maville.Model;

import java.time.LocalDateTime;

public class Candidature {
    /**
     * Adresse email de l'intervenant ayant soumis la candidature.
     */
    private String intervenantEmail;

    /**
     * Adresse email du résident associé à la candidature.
     */
    private String residentEmail;

    /**
     * Date et heure de soumission de la candidature.
     */
    private LocalDateTime dateSoumission;

     /**
     * Statut actuel de la candidature.
     */
    private String statut;

    /**
     * Constructeur pour créer une candidature avec des détails fournis.
     * 
     * @param intervenantEmail Adresse email de l'intervenant.
     * @param residentEmail Adresse email du résident.
     * @param dateSoumission Date de soumission de la candidature.
     */
    public Candidature(String intervenantEmail, String residentEmail,String dateSoumission) {
        this.intervenantEmail = intervenantEmail;
        this.residentEmail = residentEmail;
        this.dateSoumission = LocalDateTime.now();
        this.statut = "En attente";
    }

    /**
     * Retourne l'adresse email de l'intervenant.
     * 
     * @return Adresse email de l'intervenant.
     */
    public String getIntervenantEmail() {
        return intervenantEmail;
    }


    /**
     * Retourne l'adresse email du résident.
     * 
     * @return Adresse email du résident.
     */
    public String getresidentEmail() {
        return residentEmail;
    }

    /**
     * Retourne la date et l'heure de soumission de la candidature.
     * 
     * @return Date et heure de soumission.
     */
    public LocalDateTime getDateSoumission() {
        return dateSoumission;
    }

    /**
     * Retourne le statut actuel de la candidature.
     * 
     * @return Statut de la candidature.
     */
    public String getStatut() {
        return statut;
    }

    /**
     * Modifie le statut de la candidature.
     * 
     * @param statut Nouveau statut de la candidature.
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la candidature.
     * 
     * @return Une chaîne représentant la candidature.
     */
    @Override
    public String toString() {
        return "Candidature [Intervenant: " + intervenantEmail + ", Requête ID: " +
               ", Date de soumission: " + dateSoumission + ", Statut: " + statut + "]";
    }
}
