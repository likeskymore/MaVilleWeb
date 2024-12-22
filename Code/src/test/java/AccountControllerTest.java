import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Controller.*;

public class AccountControllerTest {

    @Mock
    private Scanner mockScanner;

    @Mock
    private FileReader fileReader;

    @Mock
    private FileWriter fileWriter;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAccount_Resident() throws IOException {
        // Mock the scanner input
        when(mockScanner.nextLine()).thenReturn("1") // Account type: Resident
                               .thenReturn("John Doe") // Name
                               .thenReturn("1990-01-01") // Date of birth
                               .thenReturn("john@example.com") // Email
                               .thenReturn("password123") // Password
                               .thenReturn("123") // Street number
                               .thenReturn("Main St") // Street name
                               .thenReturn("H2X"); // Postal code

        // Mock file reading and writing
        JsonObject jsonData = new JsonObject();
        JsonArray residents = new JsonArray();
        jsonData.add("residents", residents);
        when(fileReader.read()).thenReturn(-1); // Simulate empty file or no data

        // Run the method under test
        accountController.createAccount(mockScanner);

        // Verify that the account is added to the residents JSON array
        assertEquals(1, residents.size(), "A new resident account should be created.");

        // Verify interactions with fileWriter (we won't actually write to a file)
        verify(fileWriter, times(1)).write(anyString());
    }

    @Test
    public void testCreateAccount_Intervenant() throws IOException {
        // Mock the scanner input
        when(mockScanner.nextLine()).thenReturn("2") // Account type: Intervenant
                               .thenReturn("Jane Smith") // Name
                               .thenReturn("PUBLIC") // Type
                               .thenReturn("jane@example.com") // Email
                               .thenReturn("password456") // Password
                               .thenReturn("12345678"); // City ID

        // Mock file reading and writing
        JsonObject jsonData = new JsonObject();
        JsonArray intervenants = new JsonArray();
        jsonData.add("intervenants", intervenants);
        when(fileReader.read()).thenReturn(-1); // Simulate empty file or no data

        // Run the method under test
        accountController.createAccount(mockScanner);

        // Verify that the account is added to the intervenants JSON array
        assertEquals(1, intervenants.size(), "A new intervenant account should be created.");

        // Verify interactions with fileWriter (we won't actually write to a file)
        verify(fileWriter, times(1)).write(anyString());
    }


    @Test
    public void testPasswordValidation() {
        // Simulating invalid password input
        when(mockScanner.nextLine()).thenReturn("1")  // Resident option
                                .thenReturn("John Doe")
                                .thenReturn("1990-01-01")
                                .thenReturn("john.doe@example.com")
                                .thenReturn("short")  // Invalid password
                                .thenReturn("123")
                                .thenReturn("Main Street")
                                .thenReturn("A1B");

        // Call the method
        accountController.createAccount(mockScanner);

        // Ensure the password prompt was validated twice
        verify(mockScanner, times(2)).nextLine();  // Ensure password prompt was requested twice
    }
}