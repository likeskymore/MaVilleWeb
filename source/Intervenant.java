import java.util.Scanner;

public class Intervenant {

    // Gestion temporaire des user/password qui sont valides)
    private static String[] intervenants = {"intervenant1@mail.com:password1", "intervenant2@mail.com:password2"};
    private String username;

    public Intervenant(String username) {
        this.username = username;
    }

    public static Intervenant authentifier(Scanner scanner) {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Veuillez vous authentifier en tant qu'intervenant");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println();
        System.out.println("Adresse courriel :");
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
        boolean valide = false;  // Variable qui controle la boucle du menu. Elle devient 'true' lorsque le user fait un choix valide 

        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\nBienvenue intervenant!");
        System.out.println("\n");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Veuillez choisir ce que vous voulez accomplir");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println();
        System.out.println("  +-------------------------------------------------+");
        System.out.println("  |[1] Consulter les requêtes de travail ~ ~ ~ ~ ~ ~|");
        System.out.println("  |-------------------------------------------------|");
        System.out.println("  |[2] Soumettre un nouveau projet ~ ~ ~ ~ ~ ~ ~ ~ ~|");
        System.out.println("  |-------------------------------------------------|");
        System.out.println("  |[3] Mettre à jour les informations d'un chantier |");
        System.out.println("  +-------------------------------------------------+");
        System.out.print("\n\n");
        System.out.println("- - [Q] Quitter l'application - -");
        

        while (!valide) {
            String choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("- - - - - - - - - - - - - - - - - - - ");                 
                    System.out.println("Affichage des requêtes disponibles... ");
                    System.out.println("        Implémentation à venir        ");
                    System.out.println("- - - - - - - - - - - - - - - - - - - ");
                    System.out.println();
                    System.out.println("  +------------------------------+");
                    System.out.println("  |[M]. Retour au menu principal |");         
                    System.out.println("  +------------------------------+");
                    System.out.print("\n\n");
                    System.out.println("- - [Q] Quitter l'application - -");

                    valide = true;
                    sousMenu(scanner);
                    break;

                case "2":
                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - "); 
                    System.out.println("Affichage du formulaire de soummission de projet... ");
                    System.out.println("               Implémentation à venir               ");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - ");
                    System.out.println(); 
                    System.out.println("  +------------------------------+");
                    System.out.println("  |[M]. Retour au menu principal |");         
                    System.out.println("  +------------------------------+");
                    System.out.print("\n\n");
                    System.out.println("- - [Q] Quitter l'application - -");
        
                    valide = true;
                    sousMenu(scanner);
                    break;

                case "3":
                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("Affichage des projets pouvant être modifiés... ");
                    System.out.println("            Implémentation à venir             ");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println();
                    System.out.println("  +------------------------------+");
                    System.out.println("  |[M]. Retour au menu principal |");         
                    System.out.println("  +------------------------------+");
                    System.out.print("\n\n");
                    System.out.println("- - [Q] Quitter l'application - -");

                    valide = true;
                    sousMenu(scanner);
                    break;

                case "4":
                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("- - - - - - - - - - - - - - - - - - ");
                    System.out.println("Affichage des travaux à planifier...");
                    System.out.println("       Implémentation à venir       ");
                    System.out.println("- - - - - - - - - - - - - - - - - - ");
                    System.out.println();
                    System.out.println("  +------------------------------+");
                    System.out.println("  |[M]. Retour au menu principal |");         
                    System.out.println("  +------------------------------+");
                    System.out.print("\n\n");
                    System.out.println("- - [Q] Quitter l'application - -");

                    valide = true;
                    sousMenu(scanner);
                    break;

                case "Q":
                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("+--------------------------------+");
                    System.out.println("| Merci d'avoir utilisé MaVille. |");
                    System.out.println("|        À la prochaine!         |");
                    System.out.println("+--------------------------------+");
                    System.out.print("\n\n");
                    System.exit(0);
                    break;

                default:
                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!");
                    System.out.println("!~ Choix invalide, veuillez entrer une option valide (ex : 1)  ~!");
                    System.out.println("!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!");
                    break;
            }
            System.out.println("\n");
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println("Veuillez choisir ce que vous voulez accomplir");
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println();
            System.out.println("  +-------------------------------------------------+");
            System.out.println("  |[1] Consulter les requêtes de travail ~ ~ ~ ~ ~ ~|");
            System.out.println("  |-------------------------------------------------|");
            System.out.println("  |[2] Soumettre un nouveau projet ~ ~ ~ ~ ~ ~ ~ ~ ~|");
            System.out.println("  |-------------------------------------------------|");
            System.out.println("  |[3] Mettre à jour les informations d'un chantier |");
            System.out.println("  +-------------------------------------------------+");
            System.out.print("\n\n");
            System.out.println("- - [Q] Quitter l'application - -");
        }
    }

    private void sousMenu(Scanner scanner) {
        boolean valide = false; // Variable qui controle la boucle du menu. Elle devient 'true' lorsque le user fait un choix valide

        while (!valide) {
            String choix = scanner.nextLine();
            switch(choix) {
                case "M" :

                case "m" :
                    afficherMenuPrincipal(scanner);
                    valide = true;
                    break;
                
                case "Q" :

                case "q" :
                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("+--------------------------------+");
                    System.out.println("| Merci d'avoir utilisé MaVille. |");
                    System.out.println("|        À la prochaine!         |");
                    System.out.println("+--------------------------------+");
                    System.out.print("\n\n");
                    System.exit(0);
                    break;
                
                default:
                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!");
                    System.out.println("!~ Choix invalide, veuillez entrer une option valide (ex : 1)  ~!");
                    System.out.println("!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!");
                    break;
            }
            System.out.println();
            System.out.println("  +------------------------------+");
            System.out.println("  |[M]. Retour au menu principal |");         
            System.out.println("  +------------------------------+");
            System.out.print("\n\n");
            System.out.println("- - [Q] Quitter l'application - -");
            
        }

        afficherMenuPrincipal(scanner);
    }
}
