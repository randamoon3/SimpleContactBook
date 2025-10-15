/**
 * SimpleContactBook - Contact class
 * Represents a contact with name, phone, email
 */
public class Contact {
    private String name;
    private String phone;
    private String email;
    
    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    @Override
    public String toString() {
        return name + " - " + phone + " - " + email;
    }
}