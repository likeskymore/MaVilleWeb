package Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import Model.RequeteTravail;
import Model.Resident;
import Model.TypeTravail;

public class RequeteTravailManager {
    private static List<RequeteTravail> requetesTravail = new ArrayList<>();

    public static void initialiserRequetes() {
    // Création de résidents 
    Resident resident1 = new Resident("resident1@mail.com", "Quartier A");
    Resident resident2 = new Resident("resident2@mail.com", "Quartier B");
    Resident resident3 = new Resident("resident3@mail.com", "Quartier C");

    // Ajout de requêtes fictives (initialiser avec 3)
    ajouterRequete(new RequeteTravail(resident1, "Réparation de route", "Réparer les nids de poule", 
            TypeTravail.ROUTIER, LocalDate.now().plusDays(10)));
    ajouterRequete(new RequeteTravail(resident2, "Entretien de parc", "Nettoyage et aménagement", 
            TypeTravail.ENTRETIEN_PAYSAGER, LocalDate.now().plusDays(15)));
    ajouterRequete(new RequeteTravail(resident3, "Installation d'éclairage", "Installer des lampadaires LED", 
            TypeTravail.SIGNALISATION_ECLAIRAGE, LocalDate.now().plusDays(20)));
}



    public static void ajouterRequete(RequeteTravail requete) {
        requetesTravail.add(requete);
    }

    public static List<RequeteTravail> getRequetes() {
        return requetesTravail;
    }
    public static List<RequeteTravail> getRequetesParResident(Resident resident) {
    return requetesTravail.stream()
            .filter(requete -> requete.getResident().equals(resident.getUsername()))
            .collect(Collectors.toList());
}
public static List<RequeteTravail> filtrerRequetesParType(TypeTravail type) {
    return requetesTravail.stream()
            .filter(requete -> requete.getTypeTravaux() == type)
            .collect(Collectors.toList());
}

public static List<RequeteTravail> filtrerRequetesParDate() {
    return requetesTravail.stream()
            .sorted(Comparator.comparing(RequeteTravail::getDateDebut).reversed())
            .collect(Collectors.toList());
}

public static List<RequeteTravail> filtrerRequetesParQuartier(String quartier) {
    return requetesTravail.stream()
            .filter(requete -> requete.getQuartier().equalsIgnoreCase(quartier))
            .collect(Collectors.toList());
}

}
