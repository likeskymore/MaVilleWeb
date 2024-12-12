import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import Model.*;

public class UserAuthenticator {
    private User connectedUser;

    public User login(String email, String password) {
        try (Reader reader = new FileReader("Code/src/main/java/Data/Users.json")) {
            // Register a custom TypeAdapter for LocalDate
            Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

            // Parse the JSON file into a JsonObject
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

            // Get residents and intervenants arrays
            JsonArray residentsArray = jsonObject.getAsJsonArray("residents");
            JsonArray intervenantsArray = jsonObject.getAsJsonArray("intervenants");

            // Deserialize residents and check credentials
            List<Resident> residents = gson.fromJson(residentsArray, new TypeToken<List<Resident>>() {}.getType());
            for (Resident resident : residents) {
                if (resident.getEmail().equals(email) && resident.getPassword().equals(password)) {
                    connectedUser = resident;
                    return connectedUser; // Found the matching resident
                }
            }

            // Deserialize intervenants and check credentials
            List<Intervenant> intervenants = gson.fromJson(intervenantsArray, new TypeToken<List<Intervenant>>() {}.getType());
            for (Intervenant intervenant : intervenants) {
                if (intervenant.getEmail().equals(email) && intervenant.getPassword().equals(password)) {
                    connectedUser = intervenant;
                    return connectedUser; // Found the matching intervenant
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null if no user is found
    }

    // Custom TypeAdapter for LocalDate
    private static class LocalDateAdapter extends TypeAdapter<LocalDate> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        @Override
        public void write(JsonWriter out, LocalDate value) throws IOException {
            out.value(value.format(formatter));
        }

        @Override
        public LocalDate read(JsonReader in) throws IOException {
            return LocalDate.parse(in.nextString(), formatter);
        }
    }
}