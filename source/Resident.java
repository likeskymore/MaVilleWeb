import java.util.Scanner;

public class Resident {

    // Gestion temporaire des user/password qui sont valides)
    private static String[] residents = {"resident1:password1", "resident2:password2"};
    private String username;

    public Resident(String username) {
        this.username = username;
    }

    public static Resident authentifier(Scanner scanner) {
        System.out.print("\n\n\n\n\n\n\n");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Veuillez vous authentifier en tant que résident");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println();
        System.out.println("Nom d'utilisateur :");
        String username = scanner.nextLine();
        System.out.println();
        System.out.println("Mot de passe :");
        String password = scanner.nextLine();

        for (String utilisateur : residents) {
            String[] details = utilisateur.split(":");
            if (details[0].equals(username) && details[1].equals(password)) {
                return new Resident(username);  // Si authentification réussie, retourne un résident
            }
        }
        return null;  // Échec de l'authentification
    }

    public void afficherMenuPrincipal(Scanner scanner) {
        boolean valide = false;
        System.out.print("\n\n\n\n\n\n\n");
        System.out.println("\nBienvenue résident!");
        System.out.println("\n");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Veuillez choisir ce que vous voulez accomplir");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println();
        System.out.println("  +---------------------------------------------+");
        System.out.println("  |1. Consulter les travaux en cours ou à venir |");
        System.out.println("  |---------------------------------------------|");
        System.out.println("  |2. Rechercher des travaux ~ ~ ~ ~ ~ ~ ~ ~ ~ ~|");
        System.out.println("  |---------------------------------------------|");
        System.out.println("  |3. Notifications ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
        System.out.println("  |---------------------------------------------|");
        System.out.println("  |4. Planification participative ~ ~ ~ ~ ~ ~ ~ |");
        System.out.println("  |---------------------------------------------|");
        System.out.println("  |5. Soumettre une requête de travaux ~ ~ ~ ~ ~|");
        System.out.println("  |---------------------------------------------|");
        System.out.println("  |6. Signaler un problème  ~ ~ ~ ~ ~ ~ ~ |");
        System.out.println("  |---------------------------------------------|");
        System.out.println("  |                                             |");
        System.out.println("  |---------------------------------------------|");
        System.out.println("  |7. Retour au menu principal ~ ~ ~ ~ ~ ~ ~ ~ ~|");         // Pas necessaire ici mais il faudra l'ajouter dans les sous-menus
        System.out.println("  +---------------------------------------------+");
        System.out.print("\n");
        System.out.println("- - 6. Quitter l'application - -");

        while (!valide) {
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    System.out.print("\n\n\n\n\n\n\n");
                    System.out.println("Consultation des travaux en cours ou à venir...");
                    valide = true;
                    break;

                case "2":
                    System.out.println("Recherche de travaux...");
                    valide = true;
                    break;

                case "3":
                    System.out.println("Réception des notifications...");
                    valide = true;
                    break;

                case "4":
                    System.out.println("Planification participative...");
                    valide = true;
                    break;

                case "5":
                    valide = true;  // Retour au menu principal
                    break;

                case "6":
                    System.out.print("\n\n\n\n\n\n\n");
                    System.out.println("+--------------------------------+");
                    System.out.println("| Merci d'avoir utilisé MaVille. |");
                    System.out.println("|        À la prochaine!         |");
                    System.out.println("+--------------------------------+");
                    System.out.print("\n\n");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
                    break;
            }
        }
    }

}
