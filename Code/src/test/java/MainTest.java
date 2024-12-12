// import org.junit.jupiter.api.Test;
// import java.io.ByteArrayOutputStream;
// import java.io.PrintStream;
// import java.lang.reflect.Method;
// import java.util.Scanner;
// import static org.junit.jupiter.api.Assertions.*;

// public class MainTest {

//     @Test
//     public void testLoginWithInvalidInput() throws Exception {
//         String input = "X\n"; 
//         Scanner scanner = new Scanner(input);

//         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//         System.setOut(new PrintStream(outputStream));

//         Method method = Main.class.getDeclaredMethod("afficherAccueil", Scanner.class);
//         method.setAccessible(true);
//         method.invoke(null, scanner);

//         String output = outputStream.toString();
//         assertTrue(output.contains("Choix invalide"));
//     }

//     @Test
//     public void testValidResidentLogin() throws Exception {
//         String input = "1\n" + "validResident@mail.com\n" + "password123\n";  // Simulating valid login input
//         Scanner scanner = new Scanner(input);

//         // Mock or simulate successful Resident authentication (assuming Resident.authentifier() returns a non-null object)
//         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//         System.setOut(new PrintStream(outputStream));

//         Method method = Main.class.getDeclaredMethod("afficherAccueil", Scanner.class);
//         method.setAccessible(true);
//         method.invoke(null, scanner);

//         String output = outputStream.toString();
//         assertTrue(output.contains("Bienvenue"));  // Assuming the resident sees a welcome message in the menu
//     }

//     @Test
//     public void testValidIntervenantLogin() throws Exception {
//         String input = "2\n" + "validIntervenant@mail.com\n" + "password123\n";  // Simulating valid login input
//         Scanner scanner = new Scanner(input);

//         // Mock or simulate successful Intervenant authentication (assuming Intervenant.authentifier() returns a non-null object)
//         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//         System.setOut(new PrintStream(outputStream));

//         Method method = Main.class.getDeclaredMethod("afficherAccueil", Scanner.class);
//         method.setAccessible(true);
//         method.invoke(null, scanner);

//         String output = outputStream.toString();
//         assertTrue(output.contains("Bienvenue"));  // Assuming the intervenant sees a welcome message in the menu
//     }
// }