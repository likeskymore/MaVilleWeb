import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RequeteTravailManager {
    private static List<RequeteTravail> requetesTravail = new ArrayList<>();

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
