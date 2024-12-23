package Model;

import java.time.LocalDateTime;

public class Candidature {
    private String intervenantEmail;
    private String residentEmail;
    private LocalDateTime dateSoumission;
    private String statut;

    public Candidature(String intervenantEmail, String residentEmail,String dateSoumission) {
        this.intervenantEmail = intervenantEmail;
        this.residentEmail = residentEmail;
        this.dateSoumission = LocalDateTime.now();
        this.statut = "En attente";
    }

    public String getIntervenantEmail() {
        return intervenantEmail;
    }


    
    public String getresidentEmail() {
        return residentEmail;
    }


    public LocalDateTime getDateSoumission() {
        return dateSoumission;
    }

    public String getStatut() {
        return statut;
    }

    
    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Candidature [Intervenant: " + intervenantEmail + ", Requête ID: " +
               ", Date de soumission: " + dateSoumission + ", Statut: " + statut + "]";
    }
}
