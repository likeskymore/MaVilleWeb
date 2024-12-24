import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.file.*;

import Model.*;

public class UserAuthenticatorTest {

    private UserAuthenticator userAuthenticator;

    @BeforeEach
    public void setUp() {
        userAuthenticator = UserAuthenticator.getInstance(); // Get a fresh instance
    }

    @AfterEach
    public void tearDown() {
        // Reset the path or any static state to avoid interference between tests
        userAuthenticator.setPath(null); // Reset file path if it is set
    }

    // Test for successful Resident login
    @Test
    public void testLogin_SuccessfulResident() throws IOException {
        // Arrange
        String email = "resident@example.com";
        String password = "validPassword";

        // Mock JSON response for residents
        String json = """
        {
            "residents": [
                {
                    "email": "resident@example.com",
                    "password": "validPassword",
                    "name": "Resident User"
                }
            ],
            "intervenants": []
        }
        """; 

        // Write the mock JSON to a temporary file
        Path tempFile = Files.createTempFile("users", ".json");
        Files.write(tempFile, json.getBytes());
        
        userAuthenticator.setPath(tempFile.toString());  // Set file path to the temp file

        // Act
        User user = userAuthenticator.login(email, password);

        // Assert
        assertNotNull(user);
        assertEquals("Resident User", user.getName());  // Check that the user details match
        assertTrue(user instanceof Resident);  // Ensure the user is a Resident

        // Clean up
        Files.delete(tempFile);
    }

    // Test for successful Intervenant login
    @Test
    public void testLogin_SuccessfulIntervenant() throws IOException {
        // Arrange
        String email = "intervenant@example.com";
        String password = "validPassword";

        // Mock JSON response for intervenants
        String json = """
        {
            "residents": [],
            "intervenants": [
                {
                    "email": "intervenant@example.com",
                    "password": "validPassword",
                    "name": "Intervenant User"
                }
            ]
        }
        """; 

        // Write the mock JSON to a temporary file
        Path tempFile = Files.createTempFile("users", ".json");
        Files.write(tempFile, json.getBytes());
        
        userAuthenticator.setPath(tempFile.toString());  // Set file path to the temp file

        // Act
        User user = userAuthenticator.login(email, password);

        // Assert
        assertNotNull(user);
        assertEquals("Intervenant User", user.getName());  // Check that the user details match
        assertTrue(user instanceof Intervenant);  // Ensure the user is an Intervenant

        // Clean up
        Files.delete(tempFile);
    }

    // Test for failed login (invalid credentials)
    @Test
    public void testLogin_Failed() throws IOException {
        // Arrange
        String email = "nonexistent@example.com";
        String password = "wrongPassword";

        // Mock JSON response with no matching user
        String json = """
        {
            "residents": [
                {
                    "email": "resident@example.com",
                    "password": "validPassword",
                    "name": "Resident User"
                }
            ],
            "intervenants": [
                {
                    "email": "intervenant@example.com",
                    "password": "validPassword",
                    "name": "Intervenant User"
                }
            ]
        }
        """; 

        // Write the mock JSON to a temporary file
        Path tempFile = Files.createTempFile("users", ".json");
        Files.write(tempFile, json.getBytes());

        userAuthenticator.setPath(tempFile.toString());  // Set file path to the temp file

        // Act
        User user = userAuthenticator.login(email, password);

        // Assert
        assertNull(user);  // The login should fail, so the result should be null

        // Clean up
        Files.delete(tempFile);
    }
}
