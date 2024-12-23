// import static org.junit.jupiter.api.Assertions.*;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.*;

// import Model.*;
// import Controller.*;

// import java.time.LocalDate;
// import java.util.*;

// public class RequeteTravailControllerTest {

//     @Mock
//     private Scanner mockScanner;

//     @Mock
//     private Resident mockResident;

//     private RequeteTravailController controller;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//         controller = new RequeteTravailController();
//         RequeteTravailController.getRequetes().clear(); // Ensure a clean state
//         RequeteTravailController.initialiserRequetes(); // Initialize with default data
//     }

//     @Test
//     void testAjouterRequete() {
//         RequeteTravail newRequete = new RequeteTravail(
//             mockResident,
//             "Test Title",
//             "Test Description",
//             TypeTravail.ROUTIER,
//             LocalDate.now().plusDays(5)
//         );

//         RequeteTravailController.ajouterRequete(newRequete);

//         List<RequeteTravail> requetes = RequeteTravailController.getRequetes();
//         assertTrue(requetes.contains(newRequete), "New request should be added to the list");
//     }

//     @Test
//     void testFiltrerRequetesParType() {
//         List<RequeteTravail> filtered = RequeteTravailController.filtrerRequetesParType(TypeTravail.ROUTIER);
//         assertEquals(1, filtered.size(), "There should be 1 ROUTIER type request");
//     }

//     @Test
//     void testFiltrerRequetesParDate() {
//         List<RequeteTravail> filtered = RequeteTravailController.filtrerRequetesParDate();
//         assertTrue(filtered.get(0).getDateDebut().isAfter(filtered.get(1).getDateDebut()),
//                 "Requests should be sorted by most recent dates first");
//     }
// }