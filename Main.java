import java.util.*;
import java.io.*;

/**
 * SimpleContactBook - Main class
 * Manages contacts in a list, with add, view, search, delete
 */
public class Main {
    private static List<Contact> contacts = new ArrayList<>();
    private static final String DATA_FILE = "contacts.txt";
    
    public static void main(String[] args) {
        loadContacts();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== Simple Contact Book ===");
            System.out.println("1. Add contact");
            System.out.println("2. View all");
            System.out.println("3. Search by name");
            System.out.println("4. Delete contact");
            System.out.println("5. Quit");
            System.out.print("Choose (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    addContact(scanner);
                    break;
                case 2:
                    viewContacts();
                    break;
                case 3:
                    searchContact(scanner);
                    break;
                case 4:
                    deleteContact(scanner);
                    break;
                case 5:
                    saveContacts();
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    
    private static void addContact(Scanner scanner) {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Phone: ");
        String phone = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        
        if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
            contacts.add(new Contact(name, phone, email));
            System.out.println("Contact added!");
        } else {
            System.out.println("All fields required.");
        }
    }
    
    private static void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts.");
            return;
        }
        System.out.println("\nYour Contacts:");
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println((i + 1) + ". " + contacts.get(i));
        }
    }
    
    private static void searchContact(Scanner scanner) {
        System.out.print("Search name: ");
        String search = scanner.nextLine().toLowerCase().trim();
        boolean found = false;
        for (Contact c : contacts) {
            if (c.getName().toLowerCase().contains(search)) {
                System.out.println(c);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching contacts.");
        }
    }
    
    private static void deleteContact(Scanner scanner) {
        viewContacts();
        if (!contacts.isEmpty()) {
            try {
                System.out.print("Enter number to delete: ");
                int index = scanner.nextInt() - 1;
                if (index >= 0 && index < contacts.size()) {
                    Contact removed = contacts.remove(index);
                    System.out.println("Deleted: " + removed.getName());
                } else {
                    System.out.println("Invalid number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
            }
        }
    }
    
    private static void loadContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    contacts.add(new Contact(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            // File not found, start empty
        }
    }
    
    private static void saveContacts() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Contact c : contacts) {
                writer.println(c.getName() + "|" + c.getPhone() + "|" + c.getEmail());
            }
        } catch (IOException e) {
            System.out.println("Error saving contacts.");
        }
    }
}