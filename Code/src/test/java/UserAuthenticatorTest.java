import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileWriter;
import java.io.IOException;
import Model.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserAuthenticatorTest {
    private static final String TEST_FILE_PATH = "testUsers.json";
    private UserAuthenticator userAuthenticator;

    @BeforeAll
    public void setupTestData() {
        // Create a test JSON file
        String testJsonData = """
        {
          "residents": [
            {
              "email": "resident1@example.com",
              "password": "password123",
              "name": "Resident One"
            }
          ],
          "intervenants": [
            {
              "email": "intervenant1@example.com",
              "password": "securepassword",
              "name": "Intervenant One"
            }
          ]
        }
        """;

        try (FileWriter writer = new FileWriter(TEST_FILE_PATH)) {
            writer.write(testJsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void setup() {
        userAuthenticator = UserAuthenticator.getInstance();
        userAuthenticator.setPath(TEST_FILE_PATH);
    }
    @Test
    public void testLoginResidentSuccess() {
        User user = userAuthenticator.login("resident1@example.com", "password123");
        assertNotNull(user, "Login should return a user object for valid credentials");
        assertEquals("resident1@example.com", user.getEmail(), "Email should match the logged-in resident");
        assertEquals("Resident", userAuthenticator.getUserRole(), "Role should be Resident for a logged-in resident");
    }

    @Test
    public void testLoginIntervenantSuccess() {
        User user = userAuthenticator.login("intervenant1@example.com", "securepassword");
        assertNotNull(user, "Login should return a user object for valid credentials");
        assertEquals("intervenant1@example.com", user.getEmail(), "Email should match the logged-in intervenant");
        assertEquals("Intervenant", userAuthenticator.getUserRole(), "Role should be Intervenant for a logged-in intervenant");
    }

    @Test
    public void testLogoutSuccess() {
        User user = userAuthenticator.login("resident1@example.com", "password123");
        assertNotNull(user, "Login should return a user object for valid credentials");
        userAuthenticator.logout(user);
        assertNull(userAuthenticator.getConnectedUser(), "Connected user should be null after logout");
    }

    @AfterAll
    public void cleanup() {
        // Clean up the test file
        java.io.File file = new java.io.File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }
}
