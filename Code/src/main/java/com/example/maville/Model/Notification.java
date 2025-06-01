/**
 * La classe Notification représente une notification dans le système.
 * Elle contient des informations telles que l'identifiant unique, le message,
 * l'état de lecture, et la date de création de la notification.
 */
package com.example.maville.Model;

import java.time.LocalDateTime;

public class Notification {

    /**
     * Identifiant unique de la notification.
     */
    private String id;

    /**
     * Message contenu dans la notification.
     */
    private String message;

    /**
     * Indique si la notification a été lue.
     */
    private Boolean read;

    /**
     * Date de création de la notification.
     */
    private LocalDateTime creationDate;


    /**
     * Constructeur pour créer une notification avec des informations complètes.
     * 
     * @param id Identifiant unique de la notification.
     * @param message Contenu du message de la notification.
     * @param read Indique si la notification a été lue.
     * @param creationDate Date et heure de création de la notification.
     */
    // Constructor
    public Notification(String id, String message, Boolean read, LocalDateTime creationDate) {
        this.id = id;
        this.message = message;
        this.read = read;
        this.creationDate = creationDate;
    }

    /**
     * Constructeur par défaut.
     */
    // Default constructor
    public Notification() {}

    // Getters and setters

    /**
     * Retourne l'identifiant unique de la notification.
     * 
     * @return L'identifiant de la notification.
     */
    public String getId() {
        return id;
    }

    /**
     * Modifie l'identifiant de la notification.
     * 
     * @param id Le nouvel identifiant de la notification.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retourne le message de la notification.
     * 
     * @return Le message de la notification.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Modifie le message de la notification.
     * 
     * @param message Le nouveau message de la notification.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Indique si la notification a été lue.
     * 
     * @return True si la notification a été lue, sinon False.
     */
    public Boolean isRead() {
        return read;
    }

    /**
     * Modifie l'état de lecture de la notification.
     * 
     * @param read Le nouvel état de lecture de la notification.
     */
    public void setRead(Boolean read) {
        this.read = read;
    }

    /**
     * Retourne la date de création de la notification.
     * 
     * @return La date de création de la notification.
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Modifie la date de création de la notification.
     * 
     * @param creationDate La nouvelle date de création de la notification.
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la notification.
     * 
     * @return Une chaîne représentant la notification.
     */
    // toString method
    @Override
    public String toString() {
        return "Notification {" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", read=" + read +
                ", creationDate=" + creationDate +
                '}';
    }
}