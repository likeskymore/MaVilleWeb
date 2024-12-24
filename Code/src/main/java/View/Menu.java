/**
 * La classe abstraite Menu définit la structure de base pour les menus de l'application.
 * 
 * Cette classe fournit des méthodes abstraites pour afficher le menu, gérer les options,
 * et traiter les entrées utilisateur. Elle inclut également des méthodes concrètes pour
 * imprimer des messages et quitter le menu.
 */
package View;

public abstract class Menu {
    // Starts the menu loop
    /**
     * Démarre la boucle du menu.
     * Cette méthode doit être implémentée par les sous-classes.
     */
    public abstract void start();

    // Displays the menu options
    /**
     * Affiche les options du menu.
     * Cette méthode doit être implémentée par les sous-classes.
     * 
     * @param level Le niveau ou contexte du menu actuel.
     */
    public abstract void showMenu(Double level);

    // Handles the selection of a menu option
    /**
     * Gère la sélection d'une option du menu.
     * Cette méthode doit être implémentée par les sous-classes.
     * 
     * @param option Le numéro de l'option sélectionnée.
     */
    public abstract void select(int option);

    // Processes user input
    /**
     * Traite les entrées utilisateur pour le menu.
     * Cette méthode doit être implémentée par les sous-classes.
     */
    public abstract void handleInput();

    // Prints a message to the user
    /**
     * Affiche un message à l'utilisateur.
     * 
     * @param msg Le message à afficher.
     */
    public void print(String msg) {
        System.out.println(msg);
    }

    // Exits the menu
    /**
     * Quitte le menu et affiche un message de confirmation.
     */
    public void exit() {
        System.out.println("Exiting the menu...");
    }
}