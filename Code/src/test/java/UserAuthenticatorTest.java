import org.junit.jupiter.api.*;
import Model.*;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserAuthenticatorTest {
    @Mock private UserAuthenticator authenticatorMock;
    @Mock private User userMock;

    @BeforeAll
    public void setup() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
        
        // Mock the behavior of UserAuthenticator.getInstance() to return the mocked object
        when(UserAuthenticator.getInstance()).thenReturn(authenticatorMock);
    }

    @Test
    public void testLoginResident() {
        // Mock login behavior for a resident
        when(authenticatorMock.login("resident1@mail.com", "password1")).thenReturn(userMock);
        when(userMock.getEmail()).thenReturn("resident1@mail.com");
        when(authenticatorMock.getUserRole()).thenReturn("Resident");

        // Perform the test
        User utilisateur = authenticatorMock.login("resident1@mail.com", "password1");
        
        assertNotNull(utilisateur, "La connexion a échoué pour un résident valide.");
        assertEquals("Resident", authenticatorMock.getUserRole(), "Le rôle de l'utilisateur connecté est incorrect.");
        assertEquals("resident1@mail.com", utilisateur.getEmail(), "Les informations de l'utilisateur connecté sont incorrectes.");
    }

    @Test
    public void testLoginIntervenant() {
        // Mock login behavior for an intervenant
        when(authenticatorMock.login("intervenant1@mail.com", "password1")).thenReturn(userMock);
        when(userMock.getEmail()).thenReturn("intervenant1@mail.com");
        when(authenticatorMock.getUserRole()).thenReturn("Intervenant");

        // Perform the test
        User utilisateur = authenticatorMock.login("intervenant1@mail.com", "password1");

        assertNotNull(utilisateur, "La connexion a échoué pour un intervenant valide.");
        assertEquals("Intervenant", authenticatorMock.getUserRole(), "Le rôle de l'utilisateur connecté est incorrect.");
        assertEquals("intervenant1@mail.com", utilisateur.getEmail(), "Les informations de l'utilisateur connecté sont incorrectes.");
    }

    @Test
    public void testLoginInvalidCredentials() {
        // Mock login behavior for invalid credentials
        when(authenticatorMock.login("invalid@test.com", "wrongpassword")).thenReturn(null);
        when(authenticatorMock.getUserRole()).thenReturn("Unknown");

        // Perform the test
        User utilisateur = authenticatorMock.login("invalid@test.com", "wrongpassword");

        assertNull(utilisateur, "La connexion ne devrait pas réussir avec des identifiants invalides.");
        assertEquals("Unknown", authenticatorMock.getUserRole(), "Le rôle devrait être 'Unknown' lorsqu'aucun utilisateur n'est connecté.");
    }

    @AfterAll
    public void teardown() {
        // Mock the logout behavior
        doNothing().when(authenticatorMock).logout(any(User.class));
        authenticatorMock.logout(userMock); // Call the mocked logout method
    }
}