/**
 * La classe Address représente une adresse postale avec des attributs tels que le numéro
 * de rue, le nom de rue, le code postal, la ville et le pays.
 * 
 * Cette classe fournit des méthodes pour obtenir les détails de l'adresse,
 * modifier certains attributs, et déterminer le quartier associé à l'adresse
 * en fonction du code postal.
 */
package com.maville.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Address {
     /**
     * Numéro de la rue de l'adresse.
     */
    private String streetNumber;

    /**
     * Nom de la rue de l'adresse.
     */
    private String streetName;

    /**
     * Code postal de l'adresse.
     */
    private String postalCode;

     /**
     * Ville associée à l'adresse. Constante pour "Montreal".
     */
    private final String city = "Montreal";

    /**
     * Pays associé à l'adresse. Constante pour "Canada".
     */
    private final String country = "Canada";

    /**
     * Constructeur pour créer une adresse avec des détails complets.
     * 
     * @param streetNumber Le numéro de la rue.
     * @param streetName Le nom de la rue.
     * @param postalCode Le code postal.
     */
    // Constructor
    public Address(String streetNumber, String streetName, String postalCode) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.postalCode = postalCode;
    }

    public Address() {}   // Temp

    /**
     * Retourne le quartier associé au code postal.
     * 
     * @return Le nom du quartier ou "Quartier inconnu" si non identifiable.
     */
    public String getQuartier() {
        if (postalCode == null || postalCode.length() < 3) {
            return "Quartier inconnu";
        }

        String prefix = postalCode.substring(0, 3).toUpperCase();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/codesPostaux.csv"))) { // Adjust path as needed
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

    /**
     * Retourne le numéro de rue.
     * 
     * @return Le numéro de rue.
     */
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * Définit le numéro de rue.
     * 
     * @param streetNumber Le nouveau numéro de rue.
     */
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    /**
     * Retourne le nom de la rue.
     * 
     * @return Le nom de la rue.
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Définit le nom de la rue.
     * 
     * @param streetName Le nouveau nom de la rue.
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * Retourne le code postal.
     * 
     * @return Le code postal.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Définit le code postal.
     * 
     * @param postalCode Le nouveau code postal.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Retourne la ville associée à l'adresse.
     * 
     * @return La ville.
     */
    public String getCity() {
        return city;
    }

    /**
     * Retourne le pays associé à l'adresse.
     * 
     * @return Le pays.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'adresse.
     * 
     * @return Une chaîne représentant l'adresse.
     */
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