package Model;

import java.time.LocalDate;

public class Resident extends User {
    private LocalDate birthDate;

    // Constructor with all fields
    public Resident(String id, String name, String email, String password, String phone, Address address, LocalDate birthDate) {
        super(id, name, email, password, phone, address);
        this.birthDate = birthDate;
    }

    // Default constructor
    public Resident() {
        super();
    }

    // Getter and setter for birthDate
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean hasNotifications() {
        // Optional: Custom implementation for residents
        return super.hasNotifications();
    }

    @Override
    public Projet getSubscribedWorks() {
        // Optional: Custom implementation for residents
        return super.getSubscribedWorks();
    }

    @Override
    public Notification getNotifications() {
        // Fixed typo in method name from geNotifications to getNotifications
        return super.getNotifications();
    }

    @Override
    public String getDetails() {
        return "Resident{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", address=" + getAddress() +
                ", birthDate=" + birthDate +
                '}';
    }
}