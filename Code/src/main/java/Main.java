/**
 * La classe Main est le point d'entrée principal de l'application MaVille.
 * Elle gère le cycle de vie de l'application, incluant l'authentification,
 * la navigation entre les menus, et la gestion des comptes utilisateurs.
 * 
 * Cette classe utilise les contrôleurs et les menus pour permettre
 * aux utilisateurs (résidents et intervenants) d'interagir avec les fonctionnalités.
 * 
 * @author Olivier Simard
 * @author Trung Nguyen
 * @author Mamour Ndiaye
 */
import java.util.Scanner;

import Controller.AccountController;
import Model.*;
import View.MenuIntervenant;
import View.MenuResident;

public class Main {
    /**
     * Instance du contrôleur de gestion des comptes.
     */
    static AccountController accountController = new AccountController();   

    /**
     * Méthode principale qui initialise et lance l'application.
     * 
     * @param args Arguments de ligne de commande (non utilisés dans cette application).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean applicationEnCours = true;

        while (applicationEnCours) {
            applicationEnCours = afficherAccueil(scanner);  // Continue running if true
        }

        scanner.close();
    }

    /**
     * Affiche un message d'erreur en cas d'échec d'authentification.
     */
    public static void afficherErreurAuth() {
        System.out.println("\n\n" +
                "!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!\n" +
                "!~   Échec de l'authentification, veuillez réessayer.  ~!\n" +
                "!~          Retour à l'écran de connexion ...          ~!\n" +
                "!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!");
    }

    /**
     * Simule un effacement de l'écran de la console.
     */
    public static void clearScreen() {
        System.out.print("--------------------------------------------------------------------------------");
        System.out.print("\n".repeat(5)); // Simulates clearing the console
        System.out.print("--------------------------------------------------------------------------------");
    }

    /**
     * Affiche l'écran d'accueil et gère les options principales.
     * 
     * @param scanner Scanner pour lire les entrées utilisateur.
     * @return True si l'application doit continuer à fonctionner, sinon False.
     */
    public static boolean afficherAccueil(Scanner scanner) {
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
                accountController.createAccount(scanner);
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

    /**
     * Gère la connexion des utilisateurs (résidents ou intervenants).
     * 
     * @param scanner Instance de Scanner pour lire les entrées utilisateur.
     * @return True pour rester dans l'application après connexion, False pour quitter.
     */
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