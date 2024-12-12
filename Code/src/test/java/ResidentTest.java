// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import Model.Resident;

// import java.io.ByteArrayInputStream;
// import java.io.ByteArrayOutputStream;
// import java.io.PrintStream;
// import java.util.Scanner;
// import static org.junit.jupiter.api.Assertions.*;

// public class ResidentTest {

//     private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
//     private final PrintStream originalSystemOut = System.out;

//     @BeforeEach
//     void setUp() {
//         System.setOut(new PrintStream(outputStreamCaptor));
//     }

//     @AfterEach
//     void tearDown() {
//         System.setOut(originalSystemOut);
//     }

//     @Test
//     void testAuthentifier_validCredentials() {
//         String username = "resident1@mail.com";
//         String password = "password1";
//         System.setIn(new ByteArrayInputStream((username + "\n" + password + "\n").getBytes())); 

//         Resident result = Resident.authentifier(new Scanner(System.in));

//         assertNotNull(result, "Authentication should succeed with valid credentials.");
//         assertEquals(username, result.getUsername(), "Authenticated username should match input.");
//         assertEquals("Verdun", result.getQuartier(), "The quartier should match the input data.");
//     }

//     @Test
//     void testAuthentifier_invalidCredentials() {
//         String username = "wrong@mail.com";
//         String password = "wrongpassword";
//         System.setIn(new ByteArrayInputStream((username + "\n" + password + "\n").getBytes())); 

//         Resident result = Resident.authentifier(new Scanner(System.in));

//         assertNull(result, "Authentication should fail with invalid credentials.");
//     }

//     @Test
//     void testAfficherMenuPrincipal_logoutOption() throws InterruptedException {
//         String input = "D\n"; 
//         System.setIn(new ByteArrayInputStream(input.getBytes()));
    
//         Resident resident = new Resident("resident1@mail.com", "Verdun");
//         resident.afficherMenuPrincipal(new Scanner(System.in));
    
//         Thread.sleep(100);
    
//         String output = outputStreamCaptor.toString();
//         System.out.println("Captured output: " + output);
    
//         assertTrue(output.contains("Vous êtes bien déconnecté."),
//                 "Logout message should be displayed.");
//     }
// }