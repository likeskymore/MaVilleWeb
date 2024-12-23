// import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
// import static org.mockito.ArgumentMatchers.anyString;
// import static org.mockito.Mockito.*;

// import Model.*;
// import Controller.*;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.*;
// import java.util.Scanner;

// public class TravailControllerTest {

//     @Mock
//     private HttpClientApi mockApi;

//     @InjectMocks
//     private TravailController travailController;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//     }

//     @Test
//     void testProjetsEnCours_SuccessfulResponse() {
//         // Arrange
//         String jsonResponse = """
//         {
//             \"result\": {
//                 \"records\": [
//                     {"titre": "Projet 1", "date_debut": "2024-12-01", "date_fin": "2025-03-01"},
//                     {"titre": "Projet 2", "date_debut": "2024-11-15", "date_fin": "2024-12-31"}
//                 ]
//             }
//         }
//         """;

//         ApiResponse apiResponse = new ApiResponse(200, jsonResponse, "Success");
//         when(mockApi.getData(anyString())).thenReturn(apiResponse);

//         Scanner mockScanner = mock(Scanner.class);
//         when(mockScanner.nextLine()).thenReturn("0"); // Simulate user input to exit immediately

//         // Act
//         assertDoesNotThrow(() -> travailController.projetsEnCours(mockScanner));

//         // Assert
//         verify(mockApi, times(1)).getData(anyString());
//         verify(mockScanner, atLeastOnce()).nextLine();
//     }

//     @Test
//     void testProjetsEnCours_FailedApiResponse() {
//         // Arrange
//         ApiResponse apiResponse = new ApiResponse(500, "", "Server Error");
//         when(mockApi.getData(anyString())).thenReturn(apiResponse);

//         Scanner mockScanner = mock(Scanner.class);

//         // Act
//         assertDoesNotThrow(() -> travailController.projetsEnCours(mockScanner));

//         // Assert
//         verify(mockApi, times(1)).getData(anyString());
//     }

//     @Test
//     void testSelectBorough_ValidChoice() {
//         // Arrange
//         ApiResponse apiResponse = new ApiResponse(200, """
//         {
//             \"result\": {
//                 \"records\": [
//                     {"titre": "Projet Borough 1", "quartiers_affectes": "Ahuntsic-Cartierville"},
//                     {"titre": "Projet Borough 2", "quartiers_affectes": "Le Sud-Ouest"}
//                 ]
//             }
//         }
//         """, "Success");
        
//         when(mockApi.getData(anyString())).thenReturn(apiResponse);

//         Scanner mockScanner = mock(Scanner.class);
//         when(mockScanner.nextLine()).thenReturn("1", "0"); // Simulate user input to select borough and exit

//         // Act
//         assertDoesNotThrow(() -> travailController.selectBorough(mockScanner));

//         // Assert
//         verify(mockApi, times(1)).getData(anyString());
//         verify(mockScanner, atLeast(2)).nextLine();
//     }

//     @Test
//     void testConsulterEntraves_SuccessfulResponse() {
//         // Arrange
//         String jsonResponse = """
//         {
//             \"result\": {
//                 \"records\": [
//                     {"titre": "Entrave 1", "rue_affectees": ["Rue 1"]},
//                     {"titre": "Entrave 2", "rue_affectees": ["Rue 2"]}
//                 ]
//             }
//         }
//         """;

//         ApiResponse apiResponse = new ApiResponse(200, jsonResponse, "Success");
//         when(mockApi.getData(anyString())).thenReturn(apiResponse);

//         Scanner mockScanner = mock(Scanner.class);
//         when(mockScanner.nextLine()).thenReturn("0"); // Simulate user input to exit immediately

//         // Act
//         assertDoesNotThrow(() -> travailController.consulterEntraves(mockScanner));

//         // Assert
//         verify(mockApi, times(1)).getData(anyString());
//         verify(mockScanner, atLeastOnce()).nextLine();
//     }

//     @Test
//     void testConsulterEntraves_FailedApiResponse() {
//         // Arrange
//         ApiResponse apiResponse = new ApiResponse(500, "", "Server Error");
//         when(mockApi.getData(anyString())).thenReturn(apiResponse);

//         Scanner mockScanner = mock(Scanner.class);

//         // Act
//         assertDoesNotThrow(() -> travailController.consulterEntraves(mockScanner));

//         // Assert
//         verify(mockApi, times(1)).getData(anyString());
//     }
// }