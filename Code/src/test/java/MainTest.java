import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import Model.UserAuthenticator;

public class MainTest {

    private Scanner mockScanner;
    private UserAuthenticator mockAuthenticator;
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        mockScanner = mock(Scanner.class);
        mockAuthenticator = mock(UserAuthenticator.class);

        // Backup original System.out stream
        originalOut = System.out;

        // Set the mock authenticator as the instance of UserAuthenticator via reflection
        Field instanceField = UserAuthenticator.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, mockAuthenticator);  // Mocking the instance directly
    }

    @AfterEach
    public void tearDown() throws NoSuchFieldException, IllegalAccessException {
        // Reset the singleton instance of UserAuthenticator
        Field instanceField = UserAuthenticator.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null); // Set instance to null to reset the singleton
        
        // Reset the System.out stream back to original
        System.setOut(originalOut);
    }

    @Test
    public void testLogin_FailedAuthentication() {
        // Arrange
        when(mockScanner.nextLine())
            .thenReturn("invalidUser@example.com")
            .thenReturn("wrongPassword")
            .thenReturn("Q");

        when(mockAuthenticator.login("invalidUser@example.com", "wrongPassword")).thenReturn(null);

        // Act
        boolean result = Main.login(mockScanner);

        // Assert
        assertTrue(result); // The application continues running after failed login
        verify(mockAuthenticator).login("invalidUser@example.com", "wrongPassword");
        // Optionally, verify the error message was displayed (if stdout is mocked)
    }

    @Test
    public void testAfficherAccueil_UserQuits() {
        // Arrange
        when(mockScanner.nextLine()).thenReturn("q"); // User selects "Quit"

        // Act
        boolean result = Main.afficherAccueil(mockScanner);

        // Assert
        assertFalse(result); // The application stops running
    }

    @Test
    public void testAfficherErreurAuth() {
        // Arrange: Mock the System.out print stream to capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act: Call the method that prints the error message
        Main.afficherErreurAuth();

        // Assert: Verify if the expected message is in the output
        String expectedMessage = "!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!\n" +
                "!~   Échec de l'authentification, veuillez réessayer.  ~!\n" +
                "!~          Retour à l'écran de connexion ...          ~!\n" +
                "!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!~ ~!";

        assertTrue(outputStream.toString().contains(expectedMessage));
    }
}
