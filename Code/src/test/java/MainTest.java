import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalSystemOut = System.out;

    @BeforeEach
    void setUp() {
        // Redirect System.out to capture output for verification
        System.setOut(new PrintStream(outputStreamCaptor));

        // Set up a logger to prevent system exit without using the deprecated security manager
        Logger logger = Logger.getLogger(Main.class.getName());
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.SEVERE); // Only show severe logs for better focus
        logger.addHandler(handler);
    }

    @AfterEach
    void restoreSystemOut() {
        // Restore original System.out after tests
        System.setOut(originalSystemOut);
    }

    // Test for afficherErreurAuth using reflection
    @Test
    void testAfficherErreurAuth() throws Exception {
        // Use reflection to invoke the private afficherErreurAuth method
        Method method = Main.class.getDeclaredMethod("afficherErreurAuth");
        method.setAccessible(true);  // Bypass Java access control
        method.invoke(null);  // Invoke the method (static, so pass null)

        // Verify the output
        String expectedOutput = "\n\n" +
            "!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!" +
            "\n!~   Échec de l'authentification, veuillez réessayer.  ~!" +
            "\n!~          Retour à l'écran de connexion ...          ~!" +
            "\n!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!" +
            "\n\n";

        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    // Test for clearScreen using reflection
    @Test
    void testClearScreen() throws Exception {
        // Use reflection to invoke the private clearScreen method
        Method method = Main.class.getDeclaredMethod("clearScreen");
        method.setAccessible(true);  // Bypass Java access control
        method.invoke(null);  // Invoke the method (static, so pass null)

        // Verify that it simulates clearing the console with 5 newlines
        String expectedOutput = "--------------------------------------------------------------------------------\n\n\n\n\n\n--------------------------------------------------------------------------------\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    // Test for afficherAccueil with valid user input for '1' (Résident)
    @Test
    void testAfficherAccueil_ValidChoiceResident() throws Exception {
        // Simulate user input for choosing '1' (Résident)
        String userInput = "1\n";
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        // Use reflection to invoke the private afficherAccueil method
        Method method = Main.class.getDeclaredMethod("afficherAccueil", Scanner.class);
        method.setAccessible(true);  // Bypass Java access control
        method.invoke(null, new Scanner(System.in));  // Invoke the method

        // Verify that the method correctly printed the message for login prompt
        assertTrue(outputStreamCaptor.toString().contains("Veuillez vous connecter en tant que :"));

        // Restore the original System.in
        System.setIn(originalSystemIn);
    }

    // Test for afficherAccueil with invalid user input (e.g., '5')
    @Test
    void testAfficherAccueil_InvalidChoice() throws Exception {
        // Simulate invalid user input
        String userInput = "5\n";
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        // Use reflection to invoke the private afficherAccueil method
        Method method = Main.class.getDeclaredMethod("afficherAccueil", Scanner.class);
        method.setAccessible(true);  // Bypass Java access control
        method.invoke(null, new Scanner(System.in));  // Invoke the method

        // Verify that the method correctly printed the invalid choice error message
        assertTrue(outputStreamCaptor.toString().contains("!~ Choix invalide, veuillez entrer une option valide (ex : 1)"));
        
        // Restore the original System.in
        System.setIn(originalSystemIn);
    }

    // Test for afficherAccueil with exit input ('q')
    @Test
    void testAfficherAccueil_Exit() throws Exception {
        // Simulate user input for quitting the application
        String userInput = "q\n";
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        // Use reflection to invoke the private afficherAccueil method
        Method method = Main.class.getDeclaredMethod("afficherAccueil", Scanner.class);
        method.setAccessible(true);  // Bypass Java access control
        method.invoke(null, new Scanner(System.in));  // Invoke the method

        // Verify that the method correctly printed the exit message
        assertTrue(outputStreamCaptor.toString().contains("Merci d'avoir utilisé MaVille."));
        
        // Restore the original System.in
        System.setIn(originalSystemIn);
    }
}