// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.when;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.Test;

// import Model.*;
// import Controller.*;

// import java.time.LocalDate;
// import java.util.*;

// public class RequeteTravailControllerTest {

//     private RequeteTravailController requeteTravailController;
//     private Resident resident;
//     private Intervenant intervenant;

//     @BeforeEach
//     public void setUp() {
//         requeteTravailController = new RequeteTravailController();
//         resident = mock(Resident.class);
//         intervenant = mock(Intervenant.class);
//     }

//     @AfterEach
//     public void tearDown() {
//         // Reset the state of any shared objects or dependencies after each test
//         requeteTravailController = null;  // Optionally clear the instance
//         resident = null;  // Optionally clear the instance
//         intervenant = null;  // Optionally clear the instance
//     }

//     @Test
//     public void testAjouterRequete() {
//         // Arrange
//         String titre = "Test Requête";
//         String description = "Test description pour requête";
//         TypeTravail typeTravaux = TypeTravail.ROUTIER;
//         LocalDate dateDebut = LocalDate.now().plusDays(5);

//         RequeteTravail requete = new RequeteTravail(resident, titre, description, typeTravaux, dateDebut);

//         // Act
//         requeteTravailController.ajouterRequete(requete);

//         // Assert
//         assertEquals(1, requeteTravailController.getRequetes().size());
//         assertEquals(titre, requeteTravailController.getRequetes().get(0).getTitre());
//         assertEquals(description, requeteTravailController.getRequetes().get(0).getDescription());
//     }

//     @Test
//     public void testAjouterPlusieursRequetes() {
//         // Arrange
//         RequeteTravail requete1 = new RequeteTravail(resident, "Requête 1", "Description 1", TypeTravail.ROUTIER, LocalDate.now().plusDays(3));
//         RequeteTravail requete2 = new RequeteTravail(resident, "Requête 2", "Description 2", TypeTravail.RESIDENTIEL, LocalDate.now().plusDays(10));

//         // Act
//         requeteTravailController.ajouterRequete(requete1);
//         requeteTravailController.ajouterRequete(requete2);

//         // Assert
//         assertEquals(2, requeteTravailController.getRequetes().size());
//         assertEquals("Requête 1", requeteTravailController.getRequetes().get(0).getTitre());
//         assertEquals("Requête 2", requeteTravailController.getRequetes().get(1).getTitre());
//     }

//     @Test
//     public void testFiltrerRequetesParType() {
//         // Arrange
//         RequeteTravail requete1 = new RequeteTravail(resident, "Requête Routier", "Travail routier", TypeTravail.ROUTIER, LocalDate.now().plusDays(2));
//         RequeteTravail requete2 = new RequeteTravail(resident, "Requête Infrastructure", "Travail infrastructure", TypeTravail.RESIDENTIEL, LocalDate.now().plusDays(4));

//         requeteTravailController.ajouterRequete(requete1);
//         requeteTravailController.ajouterRequete(requete2);

//         // Act
//         requeteTravailController.filtrerRequetesParType(TypeTravail.ROUTIER);

//         // Assert
//         List<RequeteTravail> requetesFiltrees = requeteTravailController.getRequetes(); // Assuming getRequetes returns the modified list
//         assertEquals(2, requetesFiltrees.size(), "Le filtre devrait contenir une seule requête de type ROUTIER.");
//     }
// }
