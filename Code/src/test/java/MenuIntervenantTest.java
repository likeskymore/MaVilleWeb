// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import static org.mockito.Mockito.*;
// import Controller.RequeteTravailController;
// import Model.Intervenant;
// import View.MenuIntervenant;
// import java.util.Scanner;

// import static org.junit.jupiter.api.Assertions.*;

// class MenuIntervenantTest {

//     @Mock
//     private Scanner mockScanner;

//     @Mock
//     private RequeteTravailController mockController;

//     @Mock
//     private Intervenant mockIntervenant;

//     private MenuIntervenant menuIntervenant;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//         menuIntervenant = new MenuIntervenant();
//         menuIntervenant.setRequestController(mockController);
//         menuIntervenant.setScanner(mockScanner);
//     }

//     @Test
//     void testStartMainMenu() {
//         // Simulate user selecting option 1 to consult requests
//         when(mockScanner.nextLine()).thenReturn("1","1","N","","Q");

//         menuIntervenant.start();

//         // Verify that the controller's consulterRequetes method was called with the expected arguments
//         verify(mockController, times(1)).consulterRequetes(mockScanner, mockIntervenant);
//     }

//     @Test
//     void testQuitApplication() {
//         // Simulate quitting the application
//         when(mockScanner.nextLine()).thenReturn("Q");

//         menuIntervenant.start();

//         // Verify that the menu has stopped running and the exit method was called
//         assertFalse(menuIntervenant.isRunning());
//     }

//     @Test
//     void testReturnToMainMenu() {
//         // Simulate navigating to sub-menu level 1 and then returning to the main menu
//         when(mockScanner.nextLine()).thenReturn("2", "M", "Q");

//         menuIntervenant.start();

//         // Check if we're back to the main menu (level 0)
//         assertEquals(0.0, menuIntervenant.getCurrentLevel());
//     }
// }
