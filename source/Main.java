import java.util.Scanner;

public class Main {


    // Stockage infos utilisateur
    private static String[] residents = {"resident1:password1", "resident2:password2"};
    private static String[] intervenants = {"intervenant1:password1", "intervenant2:password2"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean authReussie = false;

        while (!authReussie) {
            // Menu connexion
            System.out.println("+------------------------------------------+");
            System.out.println("|       Bienvenue dans l'application       |");
            System.out.println("|                 MaVille!                 |");
            System.out.println("+------------------------------------------+");
            System.out.print( "\n" );
            System.out.println("Veuillez vous connecter en tant que :");
            System.out.print( "\n" );
            System.out.println("+---------------+");
            System.out.println("|1. Résident ~ ~|");
            System.out.println("|---------------|");
            System.out.println("|2. Intervenant |");
            System.out.println("+---------------+");
            System.out.print("\n");
            System.out.println("3. Quitter l'application");

            String choix = scanner.nextLine();
        

            switch (choix) {
                case "1":
                    authReussie = authentifier(residents, scanner);
                    if (authReussie) {
                        afficherMenuResident(scanner);
                    } else {
                        System.out.println("Échec de l'authentification, veuillez réessayer");
                    }
                    break;

                case "2": 
                    authReussie = authentifier(intervenants, scanner);
                    if (authReussie) {
                        afficherMenuIntervenant(scanner);
                    } else {
                        System.out.println("Échec de l'authentification, veuillez réessayer");
                    
                    }
                    break;
                  
                case "3": 
                    System.out.print("\n\n\n\n\n\n\n");
                    System.out.println("Merci d'avoir utilisé MaVille. À la prochaine!");
                    System.out.print("\n\n");
                    System.exit(0);
                    break;

                default:
                    System.out.print("\n\n\n\n\n\n\n");
                    System.out.println("Choix invalide, veuillez choisir une option (ex: 1)");
                    break;

            }
        }
    }

    public static boolean authentifier(String[] utilisateurs, Scanner scanner) {
        // Affichage menu authentification
        System.out.print("\n\n\n\n\n\n\n");
        System.out.println("Veuillez vous authentifier"); 
        System.out.print( "\n" );
        System.out.println("Nom d'utilisateur :");
        String username = scanner.nextLine();
        System.out.println("Mot de passe :");
        String password = scanner.nextLine();

        for (String utilisateur : utilisateurs) {
            String [] details = utilisateur.split(":");
            if (details[0].equals(username) && details[1].equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static void afficherMenuIntervenant(Scanner scanner) {
        boolean valide = false;   // Variable pour sortir de la boucle quand le user entre une entree valide

        // Menu avec les options pour un intervenant
        System.out.print("\n\n\n\n\n\n\n");
        System.out.println("Bienvenue intervenant!");
        System.out.println("Veuillez choisir ce que vous voulez accomplir");
        System.out.print("\n");
        System.out.println("1. Consulter les travaux en cours ou à venir");
        System.out.println("2. Rechercher des travaux");
        System.out.println("3. Notifications");
        System.out.println("4. Soumettre une requête de travail");
        System.out.println("5. Retour au menu principal");
        System.out.print("\n");
        System.out.println("6. Quitter l'application");

        while (!valide) {
            String choix = scanner.nextLine();

            switch(choix) {
                case "1":
                    valide = true;
                    System.out.println("Consultation des travaux...");
                    break;

                case "2":
                    valide = true;
                    System.out.println("Affichage de l'écran de recherche...");
                    System.out.println();
                    break;

                case "3":
                    System.out.println("Affichage des paramètres de notifications...");
                    System.out.println();
                    break;

                case "6":
                    valide = true; 
                    System.out.print("\n\n\n\n\n\n\n");
                    System.out.println("Merci d'avoir utilisé MaVille. À la prochaine!");
                    System.out.print("\n\n");
                    System.exit(0);
                    break;

                default:
                    System.out.print("\n\n\n\n\n\n\n");
                    System.out.println("Choix invalide, veuillez choisir une option (ex: 1)");
                    System.out.print("\n");
                    System.out.println("1. Consulter les travaux en cours ou à venir");
                    System.out.println("2. Rechercher des travaux");
                    System.out.println("3. Notifications");
                    System.out.println("4. Soumettre une requête de travail");
                    System.out.println("5. Retour au menu principal");
                    System.out.print("\n");
                    System.out.println("6. Quitter l'application");
                    break;
            }
        }
    }

    public static void afficherMenuResident(Scanner scanner) {
        boolean valide = false;   // Variable pour sortir de la boucle quand le user entre une entree valide
        // Menu avec les options pour un resident
        System.out.print("\n\n\n\n\n\n\n");
        System.out.println("Bienvenue résident!");
        System.out.println("Veuillez choisir ce que vous voulez accomplir");
        System.out.print("\n");
        System.out.println("+--------------------------------------------+");
        System.out.println("|1. Consulter les travaux en cours ou à venir|");
        System.out.println("|--------------------------------------------|");
        System.out.println("|2. Rechercher des travaux ~ ~ ~ ~ ~ ~ ~ ~ ~ |");
        System.out.println("|--------------------------------------------|");
        System.out.println("|3. Notifications ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~|");
        System.out.println("|--------------------------------------------|");
        System.out.println("|4. Planification participative ~ ~ ~ ~ ~ ~ ~|");
        System.out.println("5. Retour au menu principal");
        System.out.println("      Bienvenue dans l'application               ");
        System.out.print("\n");
        System.out.println("6. Quitter l'application");


        while (!valide) {
            String choix = scanner.nextLine();

            switch(choix) {
                case "1":
                    valide = true;
                    System.out.println("^^^^^^^");
                    break;

                case "2":
                    valide = true;
                    System.out.println("Affichage de l'écran de recherche...");
                    System.out.println("Différents champs de filtrage...");
                    System.out.println();
                    break;

                case "3":
                    valide = true;
                    System.out.println("Affichage des paramètres de notifications...");
                    System.out.println("Abonnements actuels...");
                    System.out.println("Désactiver les notifications...");
                    System.out.println();
                    break;
                
                case "4":
                    valide = true;
                    System.out.println("Affichage des paramètres de notifications...");
                    System.out.println("Abonnements actuels...");
                    System.out.println("Désactiver les notifications...");
                    System.out.println();
                    break;

                default:
                    System.out.println("Choix invalide, veuillez choisir une option (ex: 1)");
                    System.out.print("\n");
                    System.out.println("1. Consulter les travaux en cours ou à venir");
                    System.out.println("2. Rechercher des travaux");
                    System.out.println("3. Notifications");
                    System.out.println("4. Soumettre une requête de travail");
                    System.out.println("5. Retour au menu principal");
                    System.out.print("\n");
                    System.out.println("6. Quitter l'application");
                    break;
            }
        }
        
    }
}

    