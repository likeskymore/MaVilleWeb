import View.*;
import Controller.RequeteTravailController;
import Controller.TravailController;
import Model.Resident;
import Model.UserAuthenticator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MenuResidentTest {

    @Mock
    private RequeteTravailController requestController;

    @Mock
    private TravailController workController;

    @Mock
    private Resident activeUser;

    @Mock
    private Scanner scanner;

    @InjectMocks
    private MenuResident menuResident;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Mock UserAuthenticator to return the mocked activeUser
        UserAuthenticator mockAuthenticator = mock(UserAuthenticator.class);
        mockAuthenticator.login("resident1@mail.com", "password1");
        when(mockAuthenticator.getConnectedUser()).thenReturn(activeUser);
    }

    @AfterEach
    public void tearDown() {
        // Reset any state or resources after each test
        menuResident = null;  // Optionally, clear the instance
    }

    @Test
    public void testInvalidOption() {
        // Mock scanner to simulate invalid input
        when(scanner.nextLine()).thenReturn("invalid");

        menuResident.handleInput();

        // Verify no interactions with controllers on invalid input
        verifyNoInteractions(requestController, workController);
    }

    @Test
    public void testValidOption() {
        // Mock scanner to simulate input "1" for "Soumettre une requête de travaux"
        when(scanner.nextLine()).thenReturn("1");

        menuResident.handleInput();

        assertEquals(1.0, menuResident.getCurrentLevel(), 0.0);
    }

    @Test
    public void testBackToMainMenu() {
        // Set the current level to 1.0
        menuResident.showMenu(1.0);
        when(scanner.nextLine()).thenReturn("M");

        menuResident.handleInput();

        assertEquals(0.0, menuResident.getCurrentLevel(), 0.0);
    }
}
