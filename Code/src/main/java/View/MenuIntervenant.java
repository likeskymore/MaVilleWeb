package View;

import java.util.Scanner;
import Controller.RequeteTravailController;
import Model.Intervenant;
import Model.User;

public class MenuIntervenant extends Menu {
    private RequeteTravailController requestController = new RequeteTravailController();
    private double currentLevel = 0.0;
    private boolean running = true;
    private Scanner scanner = new Scanner(System.in);
    private User user; //Pour stocker l'utilisateur connecté


    public MenuIntervenant(User user) {
        this.user = user;  // Initialisation de l'utilisateur
    }

    @Override
    public void start() {
        while (running) {
            showMenu(currentLevel);
            handleInput();
        }
        exit();
    }

    @Override
    public void showMenu(Double level) {
        if (level == 0.0) {
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
        } else if (level == 1.0) {
            System.out.println("\n- - - Consulter les requêtes de travail - - -");
            System.out.println("Voici les requêtes disponibles :");
            requestController.consulterRequetes(scanner, (Intervenant)user); // <--- à reverifier
            System.out.println("Tapez [M] pour retourner au menu principal.");
        } else if (level == 2.0) {
            System.out.println("\n- - - Soumettre un nouveau projet - - -");
            System.out.println("Cette fonctionnalité sera implémentée prochainement.");
            System.out.println("Tapez [M] pour retourner au menu principal.");
        } else if (level == 3.0) {
            System.out.println("\n- - - Mettre à jour un chantier - - -");
            System.out.println("Cette fonctionnalité sera implémentée prochainement.");
            System.out.println("Tapez [M] pour retourner au menu principal.");
        }
    }

    @Override
    public void select(int option) {
        switch (option) {
            case 1:
                currentLevel = 1.0;
                break;
            case 2:
                currentLevel = 2.0;
                break;
            case 3:
                currentLevel = 3.0;
                break;
            default:
                System.out.println("Option invalide. Veuillez réessayer.");
                break;
        }
    }

    @Override
    public void handleInput() {
        System.out.print("Votre choix : ");
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("M") && currentLevel > 0.0) {
            currentLevel = 0.0; // Retour au menu principal
        } else if (input.equalsIgnoreCase("D")) {
            System.out.println("Déconnexion...");
            running = false; // Quitte la boucle principale
        } else if (input.equalsIgnoreCase("Q")) {
            System.out.println("Quitter l'application...");
            exit();
        } else {
            try {
                int option = Integer.parseInt(input);
                select(option);
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un numéro.");
            }
        }
    }

    @Override
    public void exit() {
        running = false;
        System.out.println("Application fermée. À bientôt !");
        scanner.close();
    }
}
