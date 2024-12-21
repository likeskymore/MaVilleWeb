package Model;

public class Intervenant extends User {
    private String cityId;
    private IntervenantType type;

    // Constructor with all fields
    public Intervenant(String id, String name, String email, String password, String cityId, IntervenantType type) {
        super(id, name, email, password);  // Call the superclass (User) constructor
        this.cityId = cityId;
        this.type = type;
    }

    // Default constructor
    public Intervenant() {
        super();  // Call the default constructor of User class
    }

    // Getter and setter for cityId
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    // Getter and setter for type
    public IntervenantType getType() {
        return type;
    }

    public void setType(IntervenantType type) {
        this.type = type;
    }

    @Override
    public boolean hasNotifications() {
        // Custom implementation for Intervenant if needed
        return super.hasNotifications();  // Optionally call the superclass method
    }

    @Override
    public Projet getSubscribedWorks() {
        // Custom implementation for Intervenant if needed
        return super.getSubscribedWorks();  // Optionally call the superclass method
    }

    @Override
    public Notification getNotifications() {
        // Custom implementation for Intervenant if needed
        return super.getNotifications();  // Optionally call the superclass method
    }

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