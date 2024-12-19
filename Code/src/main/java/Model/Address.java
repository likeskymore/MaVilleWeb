package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Address {
    private String streetNumber;
    private String streetName;
    private String postalCode;
    private final String city = "Montreal";
    private final String country = "Canada";

    // Constructor
    public Address(String streetNumber, String streetName, String postalCode) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.postalCode = postalCode;
    }

    public Address() {}

    public String getQuartier() {
        if (postalCode == null || postalCode.length() < 3) {
            return "Quartier inconnu";
        }

        String prefix = postalCode.substring(0, 3).toUpperCase();

        try (BufferedReader br = new BufferedReader(new FileReader("Code/src/main/java/Data/codesPostaux.csv"))) { // Adjust path as needed
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equalsIgnoreCase(prefix)) {
                    return parts[1];
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading quartiers.csv: " + e.getMessage());
        }

        return "Quartier inconnu";
    }

    // Getters and setters
    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetNumber='" + streetNumber + '\'' +
                ", streetName='" + streetName + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}