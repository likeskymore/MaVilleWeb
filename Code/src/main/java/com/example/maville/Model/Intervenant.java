/**
 * La classe Intervenant représente un utilisateur de type intervenant dans le système.
 * Elle hérite de la classe User et ajoute des attributs spécifiques tels que
 * l'identifiant de la ville et le type d'intervenant.
 */
package com.example.maville.Model;

public class Intervenant extends User {

    /**
     * Identifiant de la ville associé à l'intervenant.
     */
    private String cityId;

    /**
     * Type d'intervenant (PUBLIC, PRIVATE ou INDIVIDUAL).
     */
    private IntervenantType type;

    /**
     * Constructeur pour créer un intervenant avec tous les attributs.
     * 
     * @param id Identifiant unique de l'utilisateur.
     * @param name Nom de l'utilisateur.
     * @param email Adresse email de l'utilisateur.
     * @param password Mot de passe de l'utilisateur.
     * @param cityId Identifiant de la ville associée.
     * @param type Type de l'intervenant (PUBLIC, PRIVATE, INDIVIDUAL).
     */
    // Constructor with all fields
    public Intervenant(String id, String name, String email, String password, String cityId, IntervenantType type) {
        super(id, name, email, password);  // Call the superclass (User) constructor
        this.cityId = cityId;
        this.type = type;
    }

    /**
     * Constructeur par défaut.
     */
    // Default constructor
    public Intervenant() {
        super();  // Call the default constructor of User class
    }

    /**
     * Retourne l'identifiant de la ville associée à l'intervenant.
     * 
     * @return L'identifiant de la ville.
     */
    // Getter and setter for cityId
    public String getCityId() {
        return cityId;
    }

    /**
     * Modifie l'identifiant de la ville associée à l'intervenant.
     * 
     * @param cityId Le nouvel identifiant de la ville.
     */
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    /**
     * Retourne le type de l'intervenant.
     * 
     * @return Le type d'intervenant (PUBLIC, PRIVATE, INDIVIDUAL).
     */
    // Getter and setter for type
    public IntervenantType getType() {
        return type;
    }

    /**
     * Modifie le type de l'intervenant.
     * 
     * @param type Le nouveau type d'intervenant.
     */
    public void setType(IntervenantType type) {
        this.type = type;
    }

    /**
     * Indique si l'intervenant a des notifications.
     * 
     * @return True si des notifications sont présentes, sinon False.
     */
    @Override
    public boolean hasNotifications() {
        // Custom implementation for Intervenant if needed
        return super.hasNotifications();  // Optionally call the superclass method
    }

    /**
     * Retourne les travaux auxquels l'intervenant est abonné.
     * 
     * @return Les travaux abonnés par l'intervenant.
     */
    @Override
    public Projet getSubscribedWorks() {
        // Custom implementation for Intervenant if needed
        return super.getSubscribedWorks();  // Optionally call the superclass method
    }

    /**
     * Retourne les notifications associées à l'intervenant.
     * 
     * @return Les notifications de l'intervenant.
     */
    @Override
    public Notification getNotifications() {
        // Custom implementation for Intervenant if needed
        return super.getNotifications();  // Optionally call the superclass method
    }

    /**
     * Retourne une chaîne de caractères contenant les détails de l'intervenant.
     * 
     * @return Une chaîne représentant les détails de l'intervenant.
     */
    @Override
    public String getDetails() {
        return "Intervenant{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", cityId='" + cityId + '\'' +
                ", type=" + type +
                '}';
    }
}