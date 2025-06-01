/**
 * La classe Horaire représente un horaire avec un jour de la semaine, une heure de début,
 * une heure de fin, et une disponibilité associée.
 * 
 * Cette classe est utilisée pour gérer les plages horaires dans l'application.
 */
package com.maville.Model;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Horaire {
    /**
     * Jour de la semaine associé à l'horaire.
     */
    private DayOfWeek day;

    /**
     * Heure de début de l'horaire.
     */
    private LocalTime startTime;

    /**
     * Heure de fin de l'horaire.
     */
    private LocalTime endTime;

    /**
     * Disponibilité associée à l'horaire.
     */
    private Boolean available;


    /**
     * Constructeur pour créer un horaire avec des informations complètes.
     * 
     * @param day Jour de la semaine.
     * @param startTime Heure de début.
     * @param endTime Heure de fin.
     * @param available Disponibilité associée.
     */
    // Constructor
    public Horaire(DayOfWeek day, LocalTime startTime, LocalTime endTime, Boolean available) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.available = available;
    }

    /**
     * Constructeur par défaut.
     */
    // Default constructor
    public Horaire() {}

    // Getters and setters

    /**
     * Retourne le jour de la semaine associé à l'horaire.
     * 
     * @return Le jour de la semaine.
     */
    public DayOfWeek getDay() {
        return day;
    }

    /**
     * Modifie le jour de la semaine associé à l'horaire.
     * 
     * @param day Le nouveau jour de la semaine.
     */
    public void setDay(DayOfWeek day) {
        this.day = day;
    }

     /**
     * Retourne l'heure de début de l'horaire.
     * 
     * @return L'heure de début.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Modifie l'heure de début de l'horaire.
     * 
     * @param startTime La nouvelle heure de début.
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Retourne l'heure de fin de l'horaire.
     * 
     * @return L'heure de fin.
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Modifie l'heure de fin de l'horaire.
     * 
     * @param endTime La nouvelle heure de fin.
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Retourne la disponibilité associée à l'horaire.
     * 
     * @return True si disponible, sinon False.
     */
    public Boolean isAvailable() {
        return available;
    }

    /**
     * Modifie la disponibilité associée à l'horaire.
     * 
     * @param available La nouvelle disponibilité.
     */
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'horaire.
     * 
     * @return Une chaîne représentant l'horaire.
     */
    // toString method
    @Override
    public String toString() {
        return "Horaire {" +
                "day=" + day +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", available=" + available +
                '}';
    }
}