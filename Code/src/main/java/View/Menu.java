package View;

public abstract class Menu {
    // Starts the menu loop
    public abstract void start();

    // Displays the menu options
    public abstract void showMenu();

    // Handles the selection of a menu option
    public abstract void select(int option);

    // Processes user input
    public abstract void handleInput();

    // Prints a message to the user
    public void print(String msg) {
        System.out.println(msg);
    }

    // Exits the menu
    public void exit() {
        System.out.println("Exiting the menu...");
    }
}