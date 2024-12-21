package Model;

import java.time.LocalDate;

public class Resident extends User {
    private LocalDate birthDate;
    private Address address; // Unique attribute for Resident

    // Constructor with all fields
    public Resident(String id, String name, String email, String password, Address address, LocalDate birthDate) {
        super(id, name, email, password);
        this.address = address;
        this.birthDate = birthDate;
    }

    // Default constructor
    public Resident() {
        super();
    }

    // Getters and setters for unique attributes
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
        // Optional: Custom implementation for residents
        return super.getNotifications();
    }

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