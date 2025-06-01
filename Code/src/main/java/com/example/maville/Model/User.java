/**
 * La classe abstraite User représente un utilisateur générique dans le système.
 * Elle fournit des attributs communs tels que l'identifiant, le nom, l'adresse email,
 * et le mot de passe. Elle définit également des comportements génériques,
 * tout en laissant certaines méthodes abstraites pour être implémentées
 * par les classes concrètes.
 */
package com.example.maville.Model;

public abstract class User {
    /**
     * Identifiant unique de l'utilisateur.
     */
    private String id;

    /**
     * Nom de l'utilisateur.
     */
    private String name;

    /**
     * Adresse email de l'utilisateur.
     */
    private String email;

    /**
     * Mot de passe de l'utilisateur.
     */
    private String password;


    /**
     * Constructeur pour créer un utilisateur avec tous les attributs.
     * 
     * @param id Identifiant unique de l'utilisateur.
     * @param name Nom de l'utilisateur.
     * @param email Adresse email de l'utilisateur.
     * @param password Mot de passe de l'utilisateur.
     */
    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructeur par défaut.
     */
    public User() {}

    /**
     * Indique si l'utilisateur a des notifications.
     * 
     * @return True si des notifications sont présentes, sinon False.
     */
    public boolean hasNotifications() {
        return true;
    }

    /**
     * Retourne les travaux auxquels l'utilisateur est abonné.
     * 
     * @return Les travaux abonnés par l'utilisateur.
     */
    public Projet getSubscribedWorks() {
        return new Projet();
    }

    /**
     * Retourne les notifications associées à l'utilisateur.
     * 
     * @return Les notifications de l'utilisateur.
     */
    public Notification getNotifications() {
        return new Notification();
    }

    // Getters and setters

    /**
     * Retourne l'identifiant unique de l'utilisateur.
     * 
     * @return L'identifiant unique.
     */
    public String getId() {
        return id;
    }

    /**
     * Modifie l'identifiant de l'utilisateur.
     * 
     * @param id Le nouvel identifiant.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retourne le nom de l'utilisateur.
     * 
     * @return Le nom de l'utilisateur.
     */
    public String getName() {
        return name;
    }

    /**
     * Modifie le nom de l'utilisateur.
     * 
     * @param name Le nouveau nom.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retourne l'adresse email de l'utilisateur.
     * 
     * @return L'adresse email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Modifie l'adresse email de l'utilisateur.
     * 
     * @param email La nouvelle adresse email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retourne le mot de passe de l'utilisateur.
     * 
     * @return Le mot de passe.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Modifie le mot de passe de l'utilisateur.
     * 
     * @param password Le nouveau mot de passe.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retourne les détails complets de l'utilisateur.
     * Cette méthode doit être implémentée par les sous-classes.
     * 
     * @return Une chaîne représentant les détails de l'utilisateur.
     */
    public abstract String getDetails();
}