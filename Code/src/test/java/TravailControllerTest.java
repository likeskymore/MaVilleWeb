import static org.mockito.Mockito.*;

import Model.*;
import Controller.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Scanner;

public class TravailControllerTest {

    @Mock
    private HttpClientApi api;

    @InjectMocks
    private TravailController travailController;

    @Mock
    private Scanner scanner;

    private ApiResponse mockResponse;

    @BeforeEach
    public void setup() {
        // Mocking the HttpClientApi's behavior
        mockResponse = new ApiResponse(200, "Mocked response body", "OK");
        when(api.getData(anyString())).thenReturn(mockResponse);
    }

    @Test
    public void testProjetsEnCours() {
        // Simulate API response with a list of projects
        String mockJsonResponse = "{ \"result\": { \"records\": [ { \"titre\": \"Project 1\", \"dateDebut\": \"2024-01-01\", \"dateFin\": \"2024-02-01\", \"quartiersAffectes\": [\"Ahuntsic-Cartierville\"] }, { \"titre\": \"Project 2\", \"dateDebut\": \"2024-03-01\", \"dateFin\": \"2024-04-01\", \"quartiersAffectes\": [\"Plateau-Mont-Royal\"] } ] } }";
        when(api.getData("cc41b532-f12d-40fb-9f55-eb58c9a2b12b")).thenReturn(new ApiResponse(200, mockJsonResponse, "OK"));

        // Call the method
        travailController.projetsEnCours(scanner);

        // Verify if the correct API call was made
        verify(api, times(1)).getData("cc41b532-f12d-40fb-9f55-eb58c9a2b12b");
    }

    @Test
    public void testSelectBorough() {
        // Simulate user input for selecting a borough
        when(scanner.nextLine()).thenReturn("1"); // Choosing "Ahuntsic-Cartierville"

        // Call the method
        travailController.selectBorough(scanner);

        // Verify that the filtering by borough was triggered
        verify(api, times(1)).getData("cc41b532-f12d-40fb-9f55-eb58c9a2b12b");
    }

    // Optionally, test for invalid or edge cases

    @Test
    public void testApiFailure() {
        // Simulate API failure
        when(api.getData(anyString())).thenReturn(new ApiResponse(500, "Server error", "ERROR"));

        // Call the method and verify error handling
        travailController.projetsEnCours(scanner);
        verify(api, times(1)).getData(anyString());
    }
}