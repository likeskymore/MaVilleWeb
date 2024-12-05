import java.util.Scanner;

import Controller.RequeteTravailManager;
import Model.Intervenant;
import Model.Resident;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean applicationEnCours = true;
        RequeteTravailManager.initialiserRequetes();  // initialisation des 3 requetes

        while (applicationEnCours) {
            afficherAccueil(scanner);
            }

        scanner.close();
        }

        public static void afficherErreurAuth() {
            System.out.println("\n\n" +
                "!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!\n" +
                "!~   Échec de l'authentification, veuillez réessayer.  ~!\n" +
                "!~          Retour à l'écran de connexion ...          ~!\n" +
                "!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!\n");
        }

    public static void clearScreen() {
        System.out.print("--------------------------------------------------------------------------------");
        System.out.print("\n".repeat(5)); // Simulates clearing the console
        System.out.print("--------------------------------------------------------------------------------");
    }
    private static void afficherAccueil(Scanner scanner) {
        
        System.out.println("+------------------------------------------+");
        System.out.println("|       Bienvenue dans l'application       |");
        System.out.println("|                 MaVille!                 |");
        System.out.println("+------------------------------------------+");

         // Menu connexion
        System.out.print( "\n\n" );
        System.out.println("- - - - - - - - - - - - - - - - - - -");
        System.out.println("Veuillez vous connecter en tant que :");
        System.out.println("- - - - - - - - - - - - - - - - - - -");
        System.out.print( "\n" );
        System.out.println("  +----------------+");
        System.out.println("  |[1] Résident ~ ~|");
        System.out.println("  |----------------|");
        System.out.println("  |[2] Intervenant |");
        System.out.println("  +----------------+");
        System.out.print("\n\n");
        System.out.println("- - [Q] Quitter l'application - -");

        String choix = scanner.nextLine();

            // Selon l'input, on choisi le code à executer
        switch (choix) {
            case "1":
                Resident resident = Resident.authentifier(scanner); 
                if (resident != null) {
                    resident.afficherMenuPrincipal(scanner); 
                    } else {
                        clearScreen();
                        afficherErreurAuth();
                    }
                    break;

                case "2": 
                    Intervenant intervenant = Intervenant.authentifier(scanner);
                    if (intervenant != null) { 
                        intervenant.afficherMenuPrincipal(scanner);
                    } else {
                        clearScreen();
                        afficherErreurAuth();
                    }
                    break;
                  
                case "Q" :
                
                case "q" :
                    System.out.print("\n\n");
                    System.out.println("+--------------------------------+");
                    System.out.println("| Merci d'avoir utilisé MaVille. |");
                    System.out.println("|        À la prochaine!         |");
                    System.out.println("+--------------------------------+");
                    System.out.print("\n\n");
                    System.exit(0);
                    break;

                default:
                    clearScreen();
                    System.out.print("\n\n");
                    System.out.println("!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!");
                    System.out.println("!~ Choix invalide, veuillez entrer une option valide (ex : 1)  ~!");
                    System.out.println("!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!");
                    System.out.print("\n\n");
            }
        }
    
    }
