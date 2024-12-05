package Model;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Horaire {
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean available;

    // Constructor
    public Horaire(DayOfWeek day, LocalTime startTime, LocalTime endTime, Boolean available) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.available = available;
    }

    // Default constructor
    public Horaire() {}

    // Getters and setters
    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Boolean isAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

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