import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import Model.*;
import Controller.*;

import java.time.LocalDate;
import java.util.*;

public class RequeteTravailControllerTest {

    @Mock
    private Scanner mockScanner;

    @Mock
    private Resident mockResident;

    @InjectMocks
    private RequeteTravailController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        RequeteTravailController.initialiserRequetes(); // Initialize some dummy data for testing
    }

    @Test
    public void testSoumettreRequete() {
        // Setup mock inputs
        when(mockScanner.nextLine()).thenReturn("Réparation de route", "Réparer les nids de poule", "1", "2024-12-01");

        // Create expected RequeteTravail object
        RequeteTravail expectedRequete = new RequeteTravail(mockResident, "Réparation de route", "Réparer les nids de poule",
                TypeTravail.ROUTIER, LocalDate.parse("2024-12-01"));

        // Call the method
        controller.soumettreRequete(mockScanner, mockResident);

        // Verify if the requete was added to the list
        List<RequeteTravail> requetes = RequeteTravailController.getRequetes();
        assertTrue(requetes.contains(expectedRequete), "La requête devrait avoir été ajoutée.");
    }

    @Test
    public void testConsulterMesRequetes() {
        // Mock scanner input
        when(mockScanner.nextLine()).thenReturn("Enter");

        // Create a mock RequeteTravail for the resident
        RequeteTravail requete = new RequeteTravail(mockResident, "Entretien de parc", "Nettoyage du parc",
                TypeTravail.ENTRETIEN_PAYSAGER, LocalDate.now().plusDays(5));
        RequeteTravailController.ajouterRequete(requete);

        // Test the method
        controller.consulterMesRequetes(mockScanner, mockResident);

        // Verify the correct output (this assumes you print to the console in your method)
        verify(mockScanner).nextLine();
    }

    @Test
    public void testFiltrerRequetesParType() {
        // Setup mock inputs
        when(mockScanner.nextLine()).thenReturn("2");

        // Add some test data
        Resident resident1 = new Resident();
        RequeteTravail requete1 = new RequeteTravail(resident1, "Travaux routiers", "Réparer les routes",
                TypeTravail.ROUTIER, LocalDate.now().plusDays(10));
        RequeteTravail requete2 = new RequeteTravail(resident1, "Travaux d'entretien", "Nettoyage du parc",
                TypeTravail.ENTRETIEN_PAYSAGER, LocalDate.now().plusDays(15));
        RequeteTravailController.ajouterRequete(requete1);
        RequeteTravailController.ajouterRequete(requete2);

        // Filter by type "ROUTIER"
        List<RequeteTravail> filteredRequetes = RequeteTravailController.filtrerRequetesParType(TypeTravail.ROUTIER);

        // Assert that only the "ROUTIER" type is present
        assertEquals(1, filteredRequetes.size(), "Il devrait y avoir 1 requête de type 'ROUTIER'.");
        assertTrue(filteredRequetes.contains(requete1), "La requête de type 'ROUTIER' doit être présente.");
    }
}