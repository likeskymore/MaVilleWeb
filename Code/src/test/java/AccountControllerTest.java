import Controller.AccountController;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class AccountControllerTest {

    private AccountController accountController;
    private Scanner mockScanner;
    private Path tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        // Set up the mock objects and the controller
        mockScanner = mock(Scanner.class);
        accountController = new AccountController();

        // Create a temporary file for testing
        tempFile = Files.createTempFile("test_users", ".json");

        // Write initial JSON data to the file
        JsonObject jsonData = new JsonObject();
        JsonArray intervenants = new JsonArray();
        jsonData.add("intervenants", intervenants);
        jsonData.add("residents", new JsonArray());

        Files.write(tempFile, jsonData.toString().getBytes());

        // Set the path to the temp file for the AccountController
        accountController.setPath(tempFile.toString());
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Delete the temporary file after the test
        Files.deleteIfExists(tempFile);
    }

    @Test
    public void testCreateResidentAccount() throws IOException {
        // Mock user input for creating a resident account
        when(mockScanner.nextLine())
                .thenReturn("1") // Account type: Resident
                .thenReturn("John Doe") // Name
                .thenReturn("1990-01-01") // Date of birth
                .thenReturn("john@example.com") // Email
                .thenReturn("password123") // Password
                .thenReturn("123") // Street number
                .thenReturn("Main St") // Street name
                .thenReturn("H2X"); // Postal code;

        // Call the method to create the account
        accountController.createAccount(mockScanner);

        // Verify that the new resident is added
        JsonObject jsonData = accountController.loadJsonData();
        JsonArray residents = jsonData.getAsJsonArray("residents");
        assertEquals(1, residents.size());

        JsonObject newResident = residents.get(0).getAsJsonObject();
        assertEquals("John Doe", newResident.get("name").getAsString());
        assertEquals("john@example.com", newResident.get("email").getAsString());
    }

    @Test
    public void testCreateIntervenantAccount() throws IOException {
        // Mock user input for creating an intervenant account
        when(mockScanner.nextLine())
                .thenReturn("2") // Account type: Intervenant
                .thenReturn("Jane Smith") // Name
                .thenReturn("2") // Type
                .thenReturn("jane@example.com") // Email
                .thenReturn("password456") // Password
                .thenReturn("12345678"); // City ID

        // Call the method to create the account
        accountController.createAccount(mockScanner);

        // Verify that the new intervenant is added
        JsonObject jsonData = accountController.loadJsonData();
        JsonArray intervenants = jsonData.getAsJsonArray("intervenants");
        assertEquals(1, intervenants.size());

        JsonObject newIntervenant = intervenants.get(0).getAsJsonObject();
        assertEquals("Jane Smith", newIntervenant.get("name").getAsString());
        assertEquals("jane@example.com", newIntervenant.get("email").getAsString());
    }

    @Test
    public void testLoadAndSaveJsonData() throws IOException {
        // Ensure the file has initial data
        JsonObject jsonData = accountController.loadJsonData();
        assertNotNull(jsonData);

        JsonArray residents = jsonData.getAsJsonArray("residents");
        JsonArray intervenants = jsonData.getAsJsonArray("intervenants");

        // Check initial state (both should be empty)
        assertEquals(0, residents.size());
        assertEquals(0, intervenants.size());

        // Add a dummy resident and intervenant, and save it
        JsonObject newResident = new JsonObject();
        newResident.addProperty("name", "Alice");
        newResident.addProperty("email", "alice@example.com");
        residents.add(newResident);

        JsonObject newIntervenant = new JsonObject();
        newIntervenant.addProperty("name", "Bob");
        newIntervenant.addProperty("email", "bob@example.com");
        intervenants.add(newIntervenant);

        // Save updated data back to the JSON file
        try (FileWriter writer = new FileWriter(tempFile.toString())) {
            Gson gson = new Gson();
            gson.toJson(jsonData, writer);
        }

        // Reload the data and verify it's saved
        jsonData = accountController.loadJsonData();
        residents = jsonData.getAsJsonArray("residents");
        intervenants = jsonData.getAsJsonArray("intervenants");

        assertEquals(1, residents.size());
        assertEquals(1, intervenants.size());
    }
}
