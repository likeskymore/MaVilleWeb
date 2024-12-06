package View;

import java.util.Scanner;

public class MenuResident extends Menu {
    private boolean running = true;
    private Scanner scanner = new Scanner(System.in);  // Declare the scanner at the class level

    @Override
    public void start() {
        while (running) {
            showMenu();
            handleInput();
        }
        exit();  // Close scanner when the program exits
    }

    @Override
    public void showMenu() {
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

    @Override
    public void select(int option) {
        switch (option) {
            case 1:
                print("Soumettre une requête de travaux sélectionnée.");
                break;
            case 2:
                print("Rechercher des travaux sélectionné.");
                break;
            case 3:
                print("Accéder aux notifications.");
                break;
            case 4:
                print("Planification participative sélectionnée.");
                break;
            case 5:
                print("Consulter les entraves sélectionné.");
                break;
            case 6:
                print("Consulter mes requêtes sélectionné.");
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
            case "4":
            case "5":
            case "6":
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
        scanner.close();  // Close the scanner here
    }
}