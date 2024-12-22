
import View.*;
import Controller.RequeteTravailController;
import Controller.TravailController;
import Model.Resident;
import Model.UserAuthenticator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MenuResidentTest {

    @Mock
    private RequeteTravailController mockRequestController;

    @Mock
    private TravailController mockWorkController;

    @Mock
    private Resident mockActiveUser;

    @Mock
    private UserAuthenticator mockAuthenticator;

    @InjectMocks
    private MenuResident menuResident;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        when(mockAuthenticator.getConnectedUser()).thenReturn(mockActiveUser); // Stub the authenticator
    }

    @Test
    void testInitialMenuState() {
        // Vérifier l'état initial du menu
        assertEquals(0.0, menuResident.getCurrentLevel(), "Le niveau initial du menu doit être 0.0");
        assertTrue(menuResident.isRunning(), "Le menu doit être en cours d'exécution au départ");
    }

    @Test
    void testSoumettreRequeteOption() {
        // Navigate to submenu level 1.0
        menuResident.select(1); // Simulate "Soumettre une requête" option

        // Simulate input for filling the form
        menuResident.handleInput();

        // Verify the method call to submit a request
        verify(mockRequestController).soumettreRequete(any(), eq(mockActiveUser));
    }

    @Test
    void testQuitApplication() {
        // Simulate user input to quit the application
        menuResident.select(6); // Simulate quitting
        menuResident.handleInput();

        // Verify the menu is no longer running
        assertFalse(menuResident.isRunning(), "Menu should stop running after quitting");
    }
}