import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import Controller.RequeteTravailController;
import Model.*;
import View.MenuIntervenant;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MenuIntervenantTest {

    @Mock
    private Scanner mockScanner;

    @Mock
    private RequeteTravailController mockController;

    private MenuIntervenant menuIntervenant;
    private Intervenant mockIntervenant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        // Mock the login process to simulate that the user has logged in
        UserAuthenticator mockAuthenticator = mock(UserAuthenticator.class);
        mockAuthenticator.login("intervenant1@mail.com", "password1");  // Simulate a login
        when(mockAuthenticator.getConnectedUser()).thenReturn(mockIntervenant);  // Return mockIntervenant as the logged-in user

        // Create instance of MenuIntervenant
        menuIntervenant = new MenuIntervenant();

        // Inject mocked controllers and scanner
        menuIntervenant.setRequestController(mockController);
        menuIntervenant.setScanner(mockScanner);

        // If needed, you could set the mockAuthenticator as a dependency in your MenuIntervenant
        // This depends on how your MenuIntervenant class is structured.
    }

    @AfterEach
    void tearDown() {
        // Reset any state after tests if necessary
        menuIntervenant = null; // Optionally, clear the instance
    }

    @Test
    void testStartMainMenu() {
        // Simulate user selecting option 1 to consult requests
        when(mockScanner.nextLine()).thenReturn("1", "N", "Q");

        menuIntervenant.start();

        // Verify that the controller's consulterRequetes method was called with the expected arguments
        verify(mockController, times(1)).consulterRequetes(mockScanner, mockIntervenant);
    }

    @Test
    void testQuitApplication() {
        // Simulate quitting the application
        when(mockScanner.nextLine()).thenReturn("Q");

        menuIntervenant.start();

        // Verify that the menu has stopped running and the exit method was called
        assertFalse(menuIntervenant.isRunning());
    }

    @Test
    void testReturnToMainMenu() {
        // Simulate navigating to sub-menu level 1 and then returning to the main menu
        when(mockScanner.nextLine()).thenReturn("2", "M", "Q");

        menuIntervenant.start();

        // Check if we're back to the main menu (level 0)
        assertEquals(0.0, menuIntervenant.getCurrentLevel());
    }
}
