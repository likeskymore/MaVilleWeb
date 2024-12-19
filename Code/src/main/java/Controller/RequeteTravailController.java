package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import Model.RequeteTravail;
import Model.Resident;
import Model.TypeTravail;

public class RequeteTravailController {
    private static List<RequeteTravail> requetesTravail = new ArrayList<>();

    public static void initialiserRequetes() {
    // Création de résidents 
    Resident resident1 = new Resident();
    Resident resident2 = new Resident();
    Resident resident3 = new Resident();

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

    public void soumettreRequete(Scanner scanner, Resident activeResident) {
        System.out.println("                  --- Soumettre une Requête de Travail ---                 ");
        System.out.println("* * * Vous pouvez annuler la soumission à tout moment en entrant 'A'. * * *");
        
        String titre = null;
        while (titre == null || titre.isEmpty()) {
            System.out.print("Titre du travail : ");
            titre = scanner.nextLine().trim();
            if (titre.equalsIgnoreCase("A")) {
                System.out.println("Soumission annulée. Retour au menu principal.");
                return;
            }
            if (titre.isEmpty()) {
                System.out.println("Erreur : Le titre ne peut pas être vide. Veuillez entrer un titre valide.");
            }
        }

        String description = null;
        while (description == null || description.isEmpty()) {
            System.out.print("Description détaillée : ");
            description = scanner.nextLine().trim();
            if (description.equalsIgnoreCase("A")) {
                System.out.println("Soumission annulée. Retour au menu principal.");
                return;
            }
            if (description.isEmpty()) {
                System.out.println("Erreur : La description ne peut pas être vide. Veuillez entrer une description valide.");
            }
        }

        TypeTravail typeTravaux = null; 
        while (typeTravaux == null) {
            System.out.println("Entrez le numéro correspondant au type de travaux souhaité :");
            System.out.println("1. Travaux Routiers");
            System.out.println("2. Gaz et Électricité");
            System.out.println("3. Construction / Rénovation");
            System.out.println("4. Entretien Paysager");
            System.out.println("5. Transport en Commun");
            System.out.println("6. Signalisation et Éclairage");
            System.out.println("7. Travaux Souterrains");
            System.out.println("8. Travaux Résidentiels");
            System.out.println("9. Entretien Urbain");
            System.out.println("10. Réseaux de Télécommunication");
            
            String choix = scanner.nextLine().trim();
            if (titre.equalsIgnoreCase("A")) {
                System.out.println("Soumission annulée. Retour au menu principal.");
                return;
            }
            
            switch (choix) {
                    case "1":
                    typeTravaux = TypeTravail.ROUTIER;
                    break;
                    case "2":
                    typeTravaux = TypeTravail.GAZ_ELECTRIQUE;
                    break;
                    case "3":
                    typeTravaux = TypeTravail.CONSTRUCTION_RENOVATION;
                    break;
                    case "4":
                    typeTravaux = TypeTravail.ENTRETIEN_PAYSAGER;
                    break;
                    case "5":
                    typeTravaux = TypeTravail.TRANSPORT_COMMUN;
                    break;
                    case "6":
                    typeTravaux = TypeTravail.SIGNALISATION_ECLAIRAGE;
                    break;
                    case "7":
                    typeTravaux = TypeTravail.SOUTERRAINS;
                    break;
                    case "8":
                    typeTravaux = TypeTravail.RESIDENTIEL;
                    break;
                    case "9":
                    typeTravaux = TypeTravail.ENTRETIEN_URBAIN;
                    break;
                    case "10":
                    typeTravaux = TypeTravail.ENTRETIEN_RESEAU_TELECOMMUNICATION;
                    break;
                    default :
                    System.out.println("Choix invalide. Veuillez entrer un numéro entre 1 et 10");
                    break;
            }
        }
      

        LocalDate dateDebut = null;
        while (dateDebut == null) {
            
            System.out.print("Date de début espérée (format yyyy-mm-dd) : ");
            String dateInput = scanner.nextLine();
            
            if (dateInput.equalsIgnoreCase("A")) {
                System.out.println("Soumission annulée. Retour au menu principal.");
                return; 
            }
            try {
                LocalDate dateSaisie = LocalDate.parse(dateInput);
                if (dateSaisie.isAfter(LocalDate.now())) {
                    dateDebut = dateSaisie; // Date valide
                } else {
                    System.out.println("Erreur : La date doit être dans le futur. Veuillez réessayer.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Format de date invalide. Veuillez entrer une date au format yyyy-mm-dd.");
            }
        }

        // Création de la requête
        RequeteTravail nouvelleRequete = new RequeteTravail(activeResident, titre, description, typeTravaux, dateDebut);

        // Ajout à la liste des requêtes
        RequeteTravailController.ajouterRequete(nouvelleRequete);

        System.out.println("\nRequête soumise avec succès !");
        System.out.println("\n");
        System.out.println(nouvelleRequete);
    }

    public static List<RequeteTravail> getRequetesParResident(Resident resident) {
    return requetesTravail.stream()
            .filter(requete -> requete.getResident().equals(resident.getName()))
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
