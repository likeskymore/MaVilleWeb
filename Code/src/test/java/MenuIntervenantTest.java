// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import java.util.Scanner;

// import static org.mockito.Mockito.*;
// import Controller.*;
// import View.*;

// class MenuIntervenantTest {
//     @Mock
//     private Scanner mockScanner;

//     @Mock
//     private RequeteTravailController mockController;

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
//         // Simulate user selecting option 1 and then exiting
//         when(mockScanner.nextLine()).thenReturn("1", "D");

//         menuIntervenant.start();

//         verify(mockController, times(1)).consulterRequetes(mockScanner);
//     }

//     @Test
//     void testQuitApplication() {
//         // Simulate quitting
//         when(mockScanner.nextLine()).thenReturn("Q");

//         menuIntervenant.start();

//         assert !menuIntervenant.isRunning();
//     }

//     @Test
//     void testReturnToMainMenu() {
//         // Simulate navigating to a sub-menu and returning to the main menu
//         when(mockScanner.nextLine()).thenReturn("1", "M", "Q");

//         menuIntervenant.start();

//         verify(mockController, times(1)).consulterRequetes(mockScanner);
//         assert menuIntervenant.getCurrentLevel() == 0.0;
//     }
// }