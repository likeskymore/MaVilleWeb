// import org.junit.jupiter.api.*;
// import Model.*;
// import org.mockito.*;

// import com.google.gson.JsonArray;
// import com.google.gson.JsonObject;
// import com.google.gson.JsonParser;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.*;

// import java.io.FileReader;

// public class UserAuthenticatorTest {
//     private UserAuthenticator userAuthenticator;

//     @BeforeEach
//     void setUp() {
//         userAuthenticator = UserAuthenticator.getInstance();
//     }

//     @Test
//     void testLogin_withValidResident() {
//         JsonObject mockJson = createMockJson(); // Create mock JSON data

//         try (MockedStatic<JsonParser> mockedJsonParser = mockStatic(JsonParser.class)) {
//             mockedJsonParser.when(() -> JsonParser.parseReader(any(FileReader.class)))
//                     .thenReturn(mockJson);

//             // Attempt login with valid resident credentials
//             User loggedInUser = userAuthenticator.login("resident@example.com", "password123");
//             assertNotNull(loggedInUser);
//             assertTrue(loggedInUser instanceof Resident);
//             assertEquals("resident@example.com", loggedInUser.getEmail());
//         }
//     }

//     @Test
//     void testLogin_withValidIntervenant() {
//         JsonObject mockJson = createMockJson(); // Create mock JSON data

//         try (MockedStatic<JsonParser> mockedJsonParser = mockStatic(JsonParser.class)) {
//             mockedJsonParser.when(() -> JsonParser.parseReader(any(FileReader.class)))
//                     .thenReturn(mockJson);

//             // Attempt login with valid intervenant credentials
//             User loggedInUser = userAuthenticator.login("intervenant@example.com", "password456");
//             assertNotNull(loggedInUser);
//             assertTrue(loggedInUser instanceof Intervenant);
//             assertEquals("intervenant@example.com", loggedInUser.getEmail());
//         }
//     }

//     @Test
//     void testLogin_withInvalidCredentials() {
//         JsonObject mockJson = createMockJson(); // Create mock JSON data

//         try (MockedStatic<JsonParser> mockedJsonParser = mockStatic(JsonParser.class)) {
//             mockedJsonParser.when(() -> JsonParser.parseReader(any(FileReader.class)))
//                     .thenReturn(mockJson);

//             // Attempt login with invalid credentials
//             User loggedInUser = userAuthenticator.login("invalid@example.com", "wrongpassword");
//             assertNull(loggedInUser);
//         }
//     }

//     @Test
//     void testGetUserRole() {
//         JsonObject mockJson = createMockJson(); // Create mock JSON data

//         try (MockedStatic<JsonParser> mockedJsonParser = mockStatic(JsonParser.class)) {
//             mockedJsonParser.when(() -> JsonParser.parseReader(any(FileReader.class)))
//                     .thenReturn(mockJson);

//             // Log in as a resident and check role
//             userAuthenticator.login("resident@example.com", "password123");
//             assertEquals("Resident", userAuthenticator.getUserRole());

//             // Log in as an intervenant and check role
//             userAuthenticator.login("intervenant@example.com", "password456");
//             assertEquals("Intervenant", userAuthenticator.getUserRole());
//         }
//     }

//     @Test
//     void testLogout() {
//         JsonObject mockJson = createMockJson(); // Create mock JSON data

//         try (MockedStatic<JsonParser> mockedJsonParser = mockStatic(JsonParser.class)) {
//             mockedJsonParser.when(() -> JsonParser.parseReader(any(FileReader.class)))
//                     .thenReturn(mockJson);

//             // Log in as a resident and log out
//             User loggedInUser = userAuthenticator.login("resident@example.com", "password123");
//             assertNotNull(loggedInUser);
//             userAuthenticator.logout(loggedInUser);
//             assertNull(userAuthenticator.getConnectedUser());
//         }
//     }

//     // Helper method to create mock JSON data
//     private JsonObject createMockJson() {
//         JsonObject jsonObject = new JsonObject();

//         JsonArray residentsArray = new JsonArray();
//         JsonObject resident = new JsonObject();
//         resident.addProperty("email", "resident@example.com");
//         resident.addProperty("password", "password123");
//         residentsArray.add(resident);

//         JsonArray intervenantsArray = new JsonArray();
//         JsonObject intervenant = new JsonObject();
//         intervenant.addProperty("email", "intervenant@example.com");
//         intervenant.addProperty("password", "password456");
//         intervenantsArray.add(intervenant);

//         jsonObject.add("residents", residentsArray);
//         jsonObject.add("intervenants", intervenantsArray);

//         return jsonObject;
//     }
// }