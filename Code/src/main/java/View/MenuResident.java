package View;

import java.util.Scanner;
import Controller.RequeteTravailController;
import Controller.TravailController;
import Model.*;

public class MenuResident extends Menu {
    RequeteTravailController requestController = new RequeteTravailController();               
    TravailController workController = new TravailController();
    Resident activeUser = (Resident) UserAuthenticator.getInstance().getConnectedUser();
    private boolean running = true;
    private Scanner scanner = new Scanner(System.in);  
    private double currentLevel = 0.0;

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
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println("Veuillez choisir ce que vous voulez accomplir :");
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println();
            System.out.println("  +-------------------------------------+");
            System.out.println("  |[1] Soumettre une requête de travaux |");
            System.out.println("  |-------------------------------------|");
            System.out.println("  |[2] Rechercher des travaux ~ ~ ~ ~ ~ |");
            System.out.println("  |-------------------------------------|");
            System.out.println("  |[3] Notifications ~ ~ ~ ~ ~ ~ ~ ~ ~ ~|");
            System.out.println("  |-------------------------------------|");
            System.out.println("  |[4] Planification participative ~ ~ ~|");
            System.out.println("  |-------------------------------------|");
            System.out.println("  |[5] Consulter les entraves ~ ~ ~ ~ ~ |");
            System.out.println("  |-------------------------------------|");
            System.out.println("  |[6] Consulter mes requêtes ~ ~ ~ ~ ~ |");
            System.out.println("  +-------------------------------------+");
            System.out.print("\n\n");
            System.out.println("- - [D] Se déconnecter - -");
            System.out.println("- - [Q] Quitter l'application - -");
        }
        else if (level == 1.0){
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println("Veuillez choisir ce que vous voulez accomplir :");
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println();
            System.out.println("+------------------------------------------------+");
            System.out.println("|[1] Remplir le formulaire de requête de travaux |");
            System.out.println("+------------------------------------------------+");
            System.out.print("\n\n");
            System.out.println("[M] Retour au menu principal");
            System.out.println("[Q] Quitter l'application");
        }
        else if (level == 2.0){
            System.out.println("1. Consulter les travaux en cours ou à venir");
            System.out.println("2. Rechercher des travaux par filtres");
            System.out.println("[M]. Retour au menu principal");
            System.out.println("[Q]. Quitter l'application");
        }
        else if (level == 2.1){
            System.out.println("Rechercher des travaux par filtres.");
            System.out.println("1. Filtrer par quartier");
            System.out.println("0. Retour au menu précédent");
        }
        else if (level == 3.0){
            System.out.println("Personaliser les notifications...");
            System.out.println("Implementation à venir.");
            System.out.println("[M]. Retour au menu principal");
            System.out.println("[Q]. Quitter l'application");
        }
        else if (level == 4.0){
            System.out.println("Planification participative...");
            System.out.println("Implementation à venir.");
            System.out.println("[M]. Retour au menu principal");
            System.out.println("[Q]. Quitter l'application");
        }
        else if (level == 5.0){
            System.out.println("1. Consulter les entraves routières causées par les travaux en cours.");
            System.out.println("2. Filtrer les entraves par travail particulier ou par rue.");
            System.out.println("[M]. Retour au menu principal");
            System.out.println("[Q]. Quitter l'application");
        }
        else if (level == 6.0){
            requestController.consulterMesRequetes(scanner, activeUser);
        }
    }

    @Override
    public void select(int option) {
        if (currentLevel == 0.0) {
            switch (option) {
                case 1:
                    currentLevel = 1.0; // Move to submenu level
                    break;
                case 2:
                    currentLevel = 2.0; // Move to submenu level
                    break;
                case 3:
                    currentLevel = 3.0;
                    break;
                case 4:
                    currentLevel = 4.0;
                    break;
                case 5:
                    currentLevel = 5.0;
                    break;
                case 6:
                    currentLevel = 6.0;
                    break;
                default:
                    print("Option invalide. Veuillez réessayer.");
                    break;
            }
        }
        else if (currentLevel == 1.0) {
            if (option == 1) {
                requestController.soumettreRequete(scanner, activeUser);
            } else {
                print("Option invalide. Veuillez réessayer.");
            }
        }
        else if (currentLevel == 2.0){
            switch (option) {
                case 1:
                    workController.projetsEnCours(scanner);
                    break;
                case 2:
                    currentLevel = 2.1;
                    break;
                default:
                    print("Option invalide. Veuillez réessayer.");
                    break;
            }
        } 
        else if (currentLevel == 2.1){
            switch (option) {
                case 1:
                    workController.selectBorough(scanner);
                    break;
                case 0:
                    currentLevel = 2.0;
                    break;
                default:
                    print("Option invalide. Veuillez réessayer.");
                    break;
            }
        }
        else if (currentLevel == 5){
            switch (option) {
                case 1:
                    workController.consulterEntraves(scanner);
                    break;
                case 2:
                    print("something");
                    break;
                default:
                    print("Option invalide. Veuillez réessayer.");
                    break;
            }
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

    @Override
    public void exit() {
        running = false;
    }
}