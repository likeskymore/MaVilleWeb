import View.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MenuIntervenantTest {

    private MenuIntervenant menu;
    private ByteArrayOutputStream outputStream;
    @Mock private Scanner mockScanner;

    @BeforeAll
    public void setup() {
        // Initialize the menu and mock objects
        MockitoAnnotations.openMocks(this);
        menu = new MenuIntervenant();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Mocking the Scanner input (used by menu methods)
        when(mockScanner.nextLine()).thenReturn("1"); // Default mock behavior
    }

    @AfterAll
    public void teardown() {
        System.setOut(System.out);
    }

    @Test
    public void testDisplayMainMenu() {
        menu.showMenu(0.0);
        String output = outputStream.toString();
        assertTrue(output.contains("Bienvenue intervenant!"), "Le message de bienvenue est manquant.");
        assertTrue(output.contains("[1] Consulter les requêtes de travail"), "L'option 1 est manquante.");
        assertTrue(output.contains("[D] Se déconnecter"), "L'option de déconnexion est manquante.");
    }

    @Test
    public void testValidOptionSelection() {
        // Mocking the behavior of selection input
        when(mockScanner.nextLine()).thenReturn("1"); // Simulate selecting option 1

        menu.select(1);
        assertEquals(1.0, menu.getCurrentLevel(), "Le niveau actuel devrait être 1.0 après avoir sélectionné l'option 1.");
    }

    @Test
    public void testHandleInputQuit() {
        // Simulate user input to quit
        simulateInput("Q");
        menu.handleInput();
        assertFalse(menu.isRunning(), "Le menu devrait arrêter de fonctionner après avoir quitté.");
    }

    private void simulateInput(String input) {
        // Mocking Scanner input instead of using real System.setIn
        when(mockScanner.nextLine()).thenReturn(input);
        menu.handleInput();  // Trigger the handleInput method with the mocked input
    }
}