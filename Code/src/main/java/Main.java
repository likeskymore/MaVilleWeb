import java.util.Scanner;
import Model.*;
import View.MenuIntervenant;
import View.MenuResident;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean applicationEnCours = true;

        while (applicationEnCours) {
            applicationEnCours = afficherAccueil(scanner);  // Continue running if true
        }

        scanner.close();
    }

    public static void afficherErreurAuth() {
        System.out.println("\n\n" +
                "!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!\n" +
                "!~   Échec de l'authentification, veuillez réessayer.  ~!\n" +
                "!~          Retour à l'écran de connexion ...          ~!\n" +
                "!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!");
    }

    public static void clearScreen() {
        System.out.print("--------------------------------------------------------------------------------");
        System.out.print("\n".repeat(5)); // Simulates clearing the console
        System.out.print("--------------------------------------------------------------------------------");
    }

    private static boolean afficherAccueil(Scanner scanner) {
        System.out.println("+------------------------------------------+");
        System.out.println("|       Bienvenue dans l'application       |");
        System.out.println("|                 MaVille!                 |");
        System.out.println("+------------------------------------------+");

        System.out.print("\n\n");
        System.out.println("- - - - - - - - - - - - - - - - - - -");
        System.out.println("Veuillez vous connecter ou vous inscrire :");
        System.out.println("- - - - - - - - - - - - - - - - - - -");
        System.out.print("\n");
        System.out.println("  +-----------------+");
        System.out.println("  |[1] Se connecter |");
        System.out.println("  |-----------------|");
        System.out.println("  |[2] S'inscrire   |");
        System.out.println("  +-----------------+");
        System.out.print("\n\n");
        System.out.println("- - [Q] Quitter l'application - -");

        String choix = scanner.nextLine();

        switch (choix) {
            case "1":
                return login(scanner);  // Return to login logic
            case "2":
                // Future implementation for sign-up
                return true;  // Keep running the application
            case "Q":
            case "q":
                System.out.print("\n\n");
                System.out.println("+--------------------------------+");
                System.out.println("| Merci d'avoir utilisé MaVille. |");
                System.out.println("|        À la prochaine!         |");
                System.out.println("+--------------------------------+");
                return false;  // Exit the application
            default:
                clearScreen();
                System.out.print("\n\n");
                System.out.println("!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!");
                System.out.println("!~ Choix invalide, veuillez entrer une option valide (ex : 1)  ~!");
                System.out.println("!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!");
                return true;  // Stay in the application
        }
    }

    public static boolean login(Scanner scanner) {
        System.out.println("Veuillez entrer votre email :");
        String email = scanner.nextLine();

        System.out.println("Veuillez entrer votre mot de passe :");
        String password = scanner.nextLine();

        UserAuthenticator authenticator = UserAuthenticator.getInstance();
        User user = authenticator.login(email, password);

        if (user != null) {
            clearScreen();
            System.out.println("\nConnexion réussie! Bienvenue, " + user.getName() + "!\n");
            if (user instanceof Resident) {
                MenuResident residentMenu = new MenuResident();
                residentMenu.start();  // This starts the resident menu
            } else {
                MenuIntervenant intervenantMenu = new MenuIntervenant();
                intervenantMenu.start();  // Start the intervenant menu
            }
            return true;  // After logging in, return to the main menu to allow logout
        } else {
            clearScreen();
            afficherErreurAuth();
            return true;  // Retry login
        }
    }

    
}