package View;

import java.util.Scanner;

public class MenuIntervenant extends Menu {
    private boolean running = true;
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void start() {
        while (running) {
            showMenu();
            handleInput();
        }
        exit();
    }

    @Override
    public void showMenu() {
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
    }

    @Override
    public void select(int option) {
        switch (option) {
            case 1:
                print("Consulter les requêtes de travail sélectionné.");
                break;
            case 2:
                print("Soumettre un nouveau projet sélectionné.");
                break;
            case 3:
                print("Mettre à jour les informations d'un chantier sélectionné.");
                break;
            default:
                print("Option invalide. Veuillez réessayer.");
                break;
        }
    }

    @Override
    public void handleInput() {
        System.out.print("Votre choix : ");
        String input = scanner.nextLine().trim();

        switch (input.toUpperCase()) {
            case "1":
            case "2":
            case "3":
                select(Integer.parseInt(input));
                break;
            case "D":
                print("Déconnexion...");
                running = false; // Stops the menu loop
                break;
            case "Q":
                print("Quitter l'application...");
                exit();
                break;
            default:
                print("Entrée invalide. Veuillez réessayer.");
                break;
        }
    }

    @Override
    public void exit() {
        running = false;
        System.out.println("Application fermée. À bientôt !");
        scanner.close();
    }
}