import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import Model.Intervenant;

import java.io.*;
import java.util.*;

public class IntervenantTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalSystemOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalSystemOut);
    }

    @Test
    void testAuthentifier_validCredentials() {
        String username = "intervenant1@mail.com";
        String password = "password1";
        System.setIn(new ByteArrayInputStream((username + "\n" + password + "\n").getBytes())); 

        Intervenant result = Intervenant.authentifier(new Scanner(System.in));

        assertNotNull(result, "Authentication should succeed with valid credentials.");
        assertEquals(username, result.getUsername(), "Authenticated username should match input.");
    }

    @Test
    void testAuthentifier_invalidCredentials() {
        String username = "wrong@mail.com";
        String password = "wrongpassword";
        System.setIn(new ByteArrayInputStream((username + "\n" + password + "\n").getBytes())); 

        Intervenant result = Intervenant.authentifier(new Scanner(System.in));

        assertNull(result, "Authentication should fail with invalid credentials.");
    }

    @Test
    void testAfficherMenuPrincipal_logoutOption() {
        String input = "D\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Intervenant intervenant = new Intervenant("intervenant1@mail.com");
        intervenant.afficherMenuPrincipal(new Scanner(System.in));

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String output = outputStreamCaptor.toString();
        System.out.println(output);

        assertTrue(output.contains("Vous êtes bien déconnecté."),
                "Logout message should be displayed.");
    }
}