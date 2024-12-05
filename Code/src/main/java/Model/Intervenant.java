package Model;
import java.util.List;
import java.util.Scanner;

public class Intervenant {

    // Gestion temporaire des user/password qui sont valides)
    private static String[] intervenants = {"intervenant1@mail.com:password1", "intervenant2@mail.com:password2", "intervenant3@mail.com:password3", "intervenant4@mail.com:password4"};
    private String username;

    public Intervenant(String username) {
        this.username = username;
    }
    public String getUsername(){
        return username;
    }

    public static Intervenant authentifier(Scanner scanner) {
        clearScreen();
        System.out.println("\n");
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
        boolean enSession = true;  // Variable qui controle la boucle du menu.

        while (enSession) {
        System.out.print("\n\n");
        System.out.println("   -  -  -  Bienvenue intervenant!  -  -  -    ");
        System.out.println("\n");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Veuillez choisir ce que vous voulez accomplir :");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println();
        System.out.println("  +-------------------------------------------------+");
        System.out.println("  |[1] Consulter les requêtes de travail ~ ~ ~ ~ ~ ~|");
        System.out.println("  |-------------------------------------------------|");
        System.out.println("  |[2] Soumettre un nouveau projet ~ ~ ~ ~ ~ ~ ~ ~ ~|");
        System.out.println("  |-------------------------------------------------|");
        System.out.println("  |[3] Mettre à jour les informations d'un chantier |");
        System.out.println("  +-------------------------------------------------+");
        System.out.print("\n\n");
        System.out.println("- - [D] Se déconnecter - -");
        System.out.println("- - [Q] Quitter l'application - -");

        String choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    consulterRequetes(scanner);
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
                    enSession = false;
                    break;

                case "q":
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
            }
        }
    }

    public void consulterRequetes(Scanner scanner) {
    System.out.println("\n--- Liste des Requêtes de Travail ---");
    System.out.println("Voulez-vous appliquer un filtre ?");
    System.out.println("1. Pas de filtre");
    System.out.println("2. Filtrer par type de travaux");
    System.out.println("3. Filtrer par date (plus récentes d'abord)");
    System.out.println("4. Filtrer par quartier");

    String choix = scanner.nextLine();
    List<RequeteTravail> requetesFiltrees;

    switch (choix) {
        case "2":
            System.out.print("Entrez le type de travaux (ex : ROUTIER, GAZ_ELECTRIQUE) (implémentation incomplète): ");
            try {
                TypeTravail type = TypeTravail.valueOf(scanner.nextLine().toUpperCase());
                requetesFiltrees = RequeteTravailManager.filtrerRequetesParType(type);
            } catch (IllegalArgumentException e) {
                System.out.println("Type invalide. Affichage de toutes les requêtes.");
                requetesFiltrees = RequeteTravailManager.getRequetes();
            }
            break;
        case "3":
            requetesFiltrees = RequeteTravailManager.filtrerRequetesParDate();
            break;
        case "4":
            System.out.print("Entrez le quartier :     (implémentation incomplète)");
            String quartier = scanner.nextLine();
            requetesFiltrees = RequeteTravailManager.filtrerRequetesParQuartier(quartier);
            break;
        default:
            requetesFiltrees = RequeteTravailManager.getRequetes();
            break;
    }

    if (requetesFiltrees.isEmpty()) {
        System.out.println("\nAucune requête correspondant à vos critères.");
    } else {
        for (int i = 0; i < requetesFiltrees.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + requetesFiltrees.get(i));
        }
    }

    System.out.println("\nAppuyez sur 'Enter' pour revenir au menu principal.");
    scanner.nextLine();
    }


    public static void clearScreen() {  
        System.out.print("--------------------------------------------------------------------------------");
        System.out.print("\n".repeat(5)); // Simulates clearing the console
        System.out.print("--------------------------------------------------------------------------------");
        System.out.print("\n");
    }


    private void sousMenu(Scanner scanner) {
        boolean enSousMenu = true; // Variable qui controle la boucle du menu. 

        while (enSousMenu) {
            System.out.println();
            System.out.println("  +------------------------------+");
            System.out.println("  |[M]. Retour au menu principal |");         
            System.out.println("  +------------------------------+");
            System.out.print("\n\n");
            System.out.println("- - [Q] Quitter l'application - -");

            String choix = scanner.nextLine();

            switch(choix) {
                case "M" :
                case "m" :
                    enSousMenu = false;
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
            
            
        }
       
    }    
}