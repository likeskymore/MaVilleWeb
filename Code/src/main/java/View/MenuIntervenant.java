package View;

import java.util.Scanner;

import Controller.RequeteTravailController;

public class MenuIntervenant extends Menu {
    RequeteTravailController requestController = new RequeteTravailController(); 
    double currentLevel = 0.0;             
    private boolean running = true;
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void start() {
        while (running) {
            showMenu(0.0);
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
        }
        else if (level == 2.0) {
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
        }
        else if (level == 3.0) {
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
        }
    }


    @Override
    public void select(int option) {

        switch (option) {
            case 1:
                requestController.consulterRequetes(scanner);
                break;
            case 2:
                currentLevel = 2.0;
                break;
            case 3:
                currentLevel = 3.0;
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

        if (input.equalsIgnoreCase("M") && currentLevel > 0.0) {
            currentLevel = 0.0; // Return to main menu
        } else if (input.equalsIgnoreCase("D")) {
            print("Déconnexion...");
            running = false; // Stops the menu loop
        } else if (input.equalsIgnoreCase("Q")) {
            print("Quitter l'application...");
            exit();
        } else {
            try {
                int option = Integer.parseInt(input);
                select(option);
            } catch (NumberFormatException e) {
                print("Entrée invalide. Veuillez réessayer.");
            }
        }
    }

    public double getCurrentLevel() {
        return currentLevel;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRequestController(RequeteTravailController requestController) {
        this.requestController = requestController;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
    
    @Override
    public void exit() {
        running = false;
        System.out.println("Application fermée. À bientôt !");
        scanner.close();
    }
}