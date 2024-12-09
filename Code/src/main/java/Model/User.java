package Model;

public abstract class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private Address address;

    public User(String id, String name, String email, String password, String phone, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public User() {}

    public boolean hasNotifications() {
        return true;
    }

    public Projet getSubscribedWorks() {
        return new Projet();
    }

    public Notification getNotifications() {
        return new Notification();
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public abstract String getDetails();
}