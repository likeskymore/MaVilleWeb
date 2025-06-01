/**
 * La classe Resident représente un utilisateur de type résident dans le système.
 * Elle hérite de la classe User et ajoute des attributs spécifiques tels que
 * la date de naissance et l'adresse du résident.
 */
package com.example.maville.Model;

import java.time.LocalDate;

public class Resident extends User {

    /**
     * Date de naissance du résident.
     */
    private LocalDate birthDate;

    /**
     * Adresse associée au résident.
     */
    private Address address; // Unique attribute for Resident

    // Constructor with all fields
    /**
     * Constructeur pour créer un résident avec tous les attributs.
     * 
     * @param id Identifiant unique de l'utilisateur.
     * @param name Nom de l'utilisateur.
     * @param email Adresse email de l'utilisateur.
     * @param password Mot de passe de l'utilisateur.
     * @param address Adresse associée au résident.
     * @param birthDate Date de naissance du résident.
     */
    public Resident(String id, String name, String email, String password, Address address, LocalDate birthDate) {
        super(id, name, email, password);
        this.address = address;
        this.birthDate = birthDate;
    }

    /**
     * Constructeur par défaut.
     */
    // Default constructor
    public Resident() {
        super();
    }

    // Getters and setters for unique attributes
    /**
     * Retourne la date de naissance du résident.
     * 
     * @return La date de naissance.
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Modifie la date de naissance du résident.
     * 
     * @param birthDate La nouvelle date de naissance.
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Retourne l'adresse du résident.
     * 
     * @return L'adresse associée.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Modifie l'adresse associée au résident.
     * 
     * @param address La nouvelle adresse.
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Indique si le résident a des notifications.
     * 
     * @return True si des notifications sont présentes, sinon False.
     */
    @Override
    public boolean hasNotifications() {
        // Optional: Custom implementation for residents
        return super.hasNotifications();
    }

    /**
     * Retourne les travaux auxquels le résident est abonné.
     * 
     * @return Les travaux abonnés par le résident.
     */
    @Override
    public Projet getSubscribedWorks() {
        // Optional: Custom implementation for residents
        return super.getSubscribedWorks();
    }

    /**
     * Retourne les notifications associées au résident.
     * 
     * @return Les notifications du résident.
     */
    @Override
    public Notification getNotifications() {
        // Optional: Custom implementation for residents
        return super.getNotifications();
    }

    /**
     * Retourne une chaîne de caractères contenant les détails du résident.
     * 
     * @return Une chaîne représentant les détails du résident.
     */
    @Override
    public String getDetails() {
        return "Resident{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", address=" + address +
                ", birthDate=" + birthDate +
                '}';
    }
}