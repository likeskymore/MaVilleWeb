import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean authReussie = false;
        System.out.println("+------------------------------------------+");
        System.out.println("|       Bienvenue dans l'application       |");
        System.out.println("|                 MaVille!                 |");
        System.out.println("+------------------------------------------+");

        while (!authReussie) {
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
                        authReussie = true;
                        resident.afficherMenuPrincipal(scanner); 
                    } else {
                        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        System.out.println("!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!");
                        System.out.println("!~   Échec de l'authentification, veuillez réessayer   ~!");
                        System.out.println("!~          Retour à l'écran de connexion ...          ~!");
                        System.out.println("!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!");
                    }
                    break;

                case "2": 
                    Intervenant intervenant = Intervenant.authentifier(scanner);
                    if (intervenant != null) {
                        authReussie = true;
                        intervenant.afficherMenuPrincipal(scanner);  
                    } else {
                        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        System.out.println("!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!");
                        System.out.println("!~   Échec de l'authentification, veuillez réessayer   ~!");
                        System.out.println("!~          Retour à l'écran de connexion ...          ~!");
                        System.out.println("!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!");
                    }
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
        }
    }
}
