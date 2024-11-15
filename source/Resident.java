import java.util.Scanner;

public class Resident {

    // Gestion temporaire des user/password qui sont valides)
    private static String[] residents = {"resident1@mail.com:password1", "resident2@mail.com:password2", "resident3@mail.com:password3", "resident4@mail.com:password4"};
    private String username;

    public Resident(String username) {
        this.username = username;
    }

    public static Resident authentifier(Scanner scanner) {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Veuillez vous authentifier en tant que résident");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println();
        System.out.println("Adresse courriel :");
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
        boolean valide = false;   // Variable qui controle la boucle du menu. Elle devient 'true' lorsque le user fait un choix valide
        
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\nBienvenue résident!");
        System.out.println("\n");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Veuillez choisir ce que vous voulez accomplir");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println();
        System.out.println("  +-------------------------------------+");
        System.out.println("  |[1] Soumettre une requête de travaux |");                
        System.out.println("  |-------------------------------------|");
        System.out.println("  |[2] Rechercher des travaux ~ ~ ~ ~ ~ |");
        System.out.println("  |-------------------------------------|");
        System.out.println("  |[3] Notifications ~ ~ ~ ~ ~ ~ ~ ~ ~ ~|");
        System.out.println("  |-------------------------------------|");
        System.out.println("  |[4] Planification participative ~ ~ ~|");
        System.out.println("  |-------------------------------------|");
        System.out.println("  |[5] Signaler un problème ~ ~ ~ ~ ~ ~ |");
        System.out.println("  +-------------------------------------+");
        System.out.print("\n\n");
        System.out.println("- - [D] Se déconnecter - -");
        System.out.println("- - [Q] Quitter l'application - -");

        while (!valide) {
            String choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("- - - - - - - - - - - - - - - - - - -");
                    System.out.println("Affichage du formulaire de requête...");
                    System.out.println("       Implémentation à venir        ");
                    System.out.println("- - - - - - - - - - - - - - - - - - -");
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
                    System.out.println("- - - - - - - - - - - -"); 
                    System.out.println("Recherche de travaux...");
                    System.out.println("Implémentation à venir ");
                    System.out.println("- - - - - - - - - - - -");
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
                    System.out.println("- - - - - - - - - - - - - - - - - ");
                    System.out.println("Consultations des notifications...");
                    System.out.println("      Implémentation à venir      ");
                    System.out.println("- - - - - - - - - - - - - - - - - ");
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

                

                case "5":
                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("Affichage du formulaire de signalement de problème...");
                    System.out.println("               Implémentation à venir                ");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println();
                    System.out.println("  +------------------------------+");
                    System.out.println("  |[M]. Retour au menu principal |");         
                    System.out.println("  +------------------------------+");
                    System.out.print("\n\n");
                    System.out.println("- - [Q] Quitter l'application - -");
                    
                    valide = true;
                    sousMenu(scanner);
                    break;

                case "d":
                case "D":
                    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("+----------------------------+");
                    System.out.println("| Vous êtes bien déconnecté. |");
                    System.out.println("|       À la prochaine!      |");
                    System.out.println("+----------------------------+");
                    System.out.print("\n\n");
                    return;

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
            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("\nBienvenue résident!");
            System.out.println("\n");
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println("Veuillez choisir ce que vous voulez accomplir");
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println();
            System.out.println("  +-------------------------------------+");
            System.out.println("  |[1] Soumettre une requête de travaux |");                
            System.out.println("  |-------------------------------------|");
            System.out.println("  |[2] Rechercher des travaux ~ ~ ~ ~ ~ |");
            System.out.println("  |-------------------------------------|");
            System.out.println("  |[3] Notifications ~ ~ ~ ~ ~ ~ ~ ~ ~ ~|");
            System.out.println("  |-------------------------------------|");
            System.out.println("  |[4] Planification participative ~ ~ ~|");
            System.out.println("  |-------------------------------------|");
            System.out.println("  |[5] Signaler un problème ~ ~ ~ ~ ~ ~ |");
            System.out.println("  +-------------------------------------+");
            System.out.print("\n\n");
            System.out.println("- - [D] Se déconnecter - -");
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
