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
            System.out.println("Bienvenue dans l'application MaVille!");
            System.out.print( "\n" );
            System.out.println("Veuillez vous connecter en tant que :");
            System.out.print( "\n" );
            System.out.println("1. Résident");
            System.out.println("2. Intervenant");
            System.out.println("\n");
            System.out.println("3. Quitter l'application");

            String choix = scanner.nextLine();
        

            switch (choix) {
                case "1":
                    authReussie = authentifier(residents, scanner);
                    if (authReussie) {
                        System.out.println("Bienvenue résident!");
                        afficherMenuResident(scanner);
                    } else {
                        System.out.println("Échec de l'authentification, veuillez réessayer");
                    }
                    break;

                case "2": 
                    authReussie = authentifier(intervenants, scanner);
                    if (authReussie) {
                        System.out.println("Bienvenue intervenant!");
                        afficherMenuIntervenant(scanner);
                    } else {
                        System.out.println("Échec de l'authentification, veuillez réessayer");
                    
                    }
                    break;
                  
                case "3": 
                    System.out.println("Merci d'avoir utilisé MaVille. À la prochaine!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Choix invalide, veuillez choisir une option (ex: 1)");
                    break;

            }
        }




    }

    public static boolean authentifier(String[] utilisateurs, Scanner scanner) {

        System.out.print( "\n\n\n" );
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
        boolean quitter = false;
        while (!quitter) {
            
        }
    }

    public static void afficherMenuResident(Scanner scanner) {
        boolean quitter = false;
        while (!quitter) {

        }
    }
}

    