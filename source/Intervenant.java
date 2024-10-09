import java.util.Scanner;

public class Intervenant {

    // Gestion temporaire des user/password qui sont valides)
    private static String[] intervenants = {"intervenant1:password1", "intervenant2:password2"};
    private String username;

    public Intervenant(String username) {
        this.username = username;
    }

    public static Intervenant authentifier(Scanner scanner) {
        System.out.print("\n\n\n\n\n\n\n");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Veuillez vous authentifier en tant qu'intervenant");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println();
        System.out.println("Nom d'utilisateur :");
        System.out.println();
        String username = scanner.nextLine();
        System.out.println();
        System.out.println("Mot de passe :");
        String password = scanner.nextLine();

        for (String utilisateur : intervenants) {
            String[] details = utilisateur.split(":");
            if (details[0].equals(username) && details[1].equals(password)) {
                return new Intervenant(username);  // Si authentification réussie, retourne un intervenant
            }
        }
        return null;  // Échec de l'authentification
    }

    public void afficherMenuPrincipal(Scanner scanner) {
        boolean valide = false;  // Variable qui controle la boucle du menu. Elle devient 'true' lorsque le user fait une choix valide 

        System.out.print("\n\n\n\n\n\n\n");
        System.out.println("\nBienvenue intervenant!");
        System.out.println("\n");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Veuillez choisir ce que vous voulez accomplir");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println();
        System.out.println("  +------------------------------------------------+");
        System.out.println("  |1. Consulter la liste des requêtes de travail ~ |");
        System.out.println("  |------------------------------------------------|");
        System.out.println("  |2. Soumettre un nouveau projet de travaux ~ ~ ~ |");
        System.out.println("  |------------------------------------------------|");
        System.out.println("  |3. Mettre à jour les informations d'un chantier |");
        System.out.println("  |------------------------------------------------|");
        System.out.println("  |                                                |");
        System.out.println("  |------------------------------------------------|");
        System.out.println("  |5. Retour au menu principal ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ |");   // Pas necessaire ici mais il faudra l'ajouter dans les sous-menus
        System.out.println("  +------------------------------------------------+");
        System.out.print("\n");
        System.out.println("- - 5. Quitter l'application - -");

        while (!valide) {
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    System.out.println("Consultation des requêtes de travail...");
                    valide = true;
                    break;

                case "2":
                    System.out.println("Soumission d'un nouveau projet...");
                    valide = true;
                    break;

                case "3":
                    System.out.println("Mise à jour des informations d'un chantier...");
                    valide = true;
                    break;

                case "4":
                    valide = true;  // Retour au menu principal
                    break;

                case "5":
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
