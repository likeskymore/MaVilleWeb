/**
 * La classe MenuIntervenant gère l'interface utilisateur pour les intervenants.
 * Elle permet à l'intervenant connecté d'accéder à des fonctionnalités spécifiques
 * telles que consulter les requêtes de travail, soumettre un nouveau projet,
 * ou mettre à jour les informations d'un chantier.
 * 
 * Cette classe hérite de la classe abstraite {@link Menu}.
 */
    package com.maville.View;

    import java.util.Scanner;

import com.maville.Controller.RequeteTravailController;
import com.maville.Model.Intervenant;
import com.maville.Model.UserAuthenticator;

public class MenuIntervenant extends Menu {
    /**
     * Contrôleur pour gérer les requêtes de travail.
     */
    private RequeteTravailController requestController = new RequeteTravailController();
    
    /**
     * Niveau actuel du menu (contexte).
     */
    private double currentLevel = 0.0;

    /**
     * Indicateur pour maintenir le menu actif.
     */
    private boolean running = true;

    /**
     * Scanner pour gérer les entrées utilisateur.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Utilisateur actuellement connecté (doit être un intervenant).
     */
    private Intervenant user = (Intervenant) UserAuthenticator.getInstance().getConnectedUser(); //Pour stocker l'utilisateur connecté

    /**
     * Démarre la boucle principale du menu.
     * Affiche les options et gère les entrées utilisateur jusqu'à la déconnexion ou la fermeture.
     */
    @Override
    public void start() {
        while (running) {
            showMenu(currentLevel);
            handleInput();
        }
        exit();
    }

    /**
     * Affiche le menu selon le niveau actuel.
     * 
     * @param level Le niveau ou contexte du menu.
     */
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

    /**
     * Gère la sélection des options du menu.
     * 
     * @param option L'option sélectionnée par l'utilisateur.
     */
    @Override
    public void select(int option) {
        switch (option) {
            case 1:
                requestController.consulterRequetes(scanner, (Intervenant)user);
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

    /**
     * Traite les entrées utilisateur et navigue dans le menu.
     */
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

    /**
     * Quitte le menu et affiche un message de confirmation.
     */
    @Override
    public void exit() {
        running = false;
        System.out.println("Application fermée. À bientôt !");
        scanner.close();
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
}
