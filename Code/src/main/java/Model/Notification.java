package Model;

import java.time.LocalDateTime;

public class Notification {
    private String id;
    private String message;
    private Boolean read;
    private LocalDateTime creationDate;

    // Constructor
    public Notification(String id, String message, Boolean read, LocalDateTime creationDate) {
        this.id = id;
        this.message = message;
        this.read = read;
        this.creationDate = creationDate;
    }

    // Default constructor
    public Notification() {}

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean isRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

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