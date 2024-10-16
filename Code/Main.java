import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Interface for contact-related methods
interface Contactable {
    String getEmail();
    String getPhoneNumber();
    void updateContactInfo(String newEmail, String newPhoneNumber);
}

// Interface for feedback-related methods
interface Feedbackable {
    void submitFeedback(String message);
}

// Abstract class for common attributes and methods
abstract class Person {
    private static int idCounter = 1;
    protected int id;
    protected String name;

    public Person(String name) {
        this.id = idCounter++;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract void displayInfo(); // Abstract method to be implemented by subclasses
}

// User class extends Person and implements interfaces for multiple inheritance
class User extends Person implements Contactable, Feedbackable {
    private String email;
    private String phoneNumber;
    private String address;
    private String userType;

    // Constructor
    public User(String name, String email, String phoneNumber, String address, String userType) {
        super(name); // Call to the parent class constructor
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userType = userType;
    }

    // Copy constructor
    public User(User user) {
        super(user.name); // Call parent constructor
        this.email = user.email;
        this.phoneNumber = user.phoneNumber;
        this.address = user.address;
        this.userType = user.userType;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void updateContactInfo(String newEmail, String newPhoneNumber) {
        this.email = newEmail;
        this.phoneNumber = newPhoneNumber;
        System.out.println("Contact information updated: " + email + ", " + phoneNumber);
    }

    @Override
    public void submitFeedback(String message) {
        System.out.println("Feedback submitted by User ID: " + id + " - " + message);
    }

    @Override
    public void displayInfo() {
        System.out.println("User ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phoneNumber);
    }
}

// Abstract class for services
abstract class Service {
    protected int serviceId;
    protected String serviceName;
    protected String description;

    public Service(int serviceId, String serviceName, String description) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.description = description;
    }

    public abstract void displayServiceInfo(); // Abstract method for displaying service info

    public double calculateFee() {
        return 0.0; // Default implementation, can be overridden
    }
}

// Concrete class for specific services
class DepartmentService extends Service {
    private double fee;
    private String requiredDocuments;

    public DepartmentService(int serviceId, String serviceName, String description, double fee, String requiredDocuments) {
        super(serviceId, serviceName, description);
        this.fee = fee;
        this.requiredDocuments = requiredDocuments;
    }

    @Override
    public void displayServiceInfo() {
        System.out.println("Service ID: " + serviceId);
        System.out.println("Service Name: " + serviceName);
        System.out.println("Description: " + description);
        System.out.println("Fee: " + fee);
        System.out.println("Required Documents: " + requiredDocuments);
    }

    @Override
    public double calculateFee() {
        return fee; // Return specific fee for this service
    }
}

// Main class to run the application with user interaction
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;

    public static void main(String[] args) {
        // Sample services for demonstration
        Service service1 = new DepartmentService(1, "Birth Certificate", "Issuance of Birth Certificate", 50.0, "ID Proof");
        Service service2 = new DepartmentService(2, "Residence Certificate", "Issuance of Residence Certificate", 200.0, "Application Form, ID Proof");

        // User interaction loop
        while (true) {
            System.out.println("\n--- Welcome to the Service Application ---");
            System.out.println("1. Register User");
            System.out.println("2. Login User");
            System.out.println("3. View Services");
            System.out.println("4. Submit Feedback");
            System.out.println("5. Update Contact Information");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    viewServices(service1, service2);
                    break;
                case 4:
                    submitFeedback();
                    break;
                case 5:
                    updateContactInfo();
                    break;
                case 6:
                    System.out.println("Thank you for using the application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerUser() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter user type: ");
        String userType = scanner.nextLine();

        currentUser = new User(name, email, phoneNumber, address, userType);
        System.out.println("User registered successfully!");
        currentUser.displayInfo();
    }

    private static void loginUser() {
        if (currentUser != null) {
            System.out.println("User logged in successfully: " + currentUser.getName());
        } else {
            System.out.println("No user registered. Please register first.");
        }
    }

    private static void viewServices(Service service1, Service service2) {
        System.out.println("Available Services:");
        service1.displayServiceInfo();
        System.out.println();
        service2.displayServiceInfo();
    }

    private static void submitFeedback() {
        if (currentUser != null) {
            System.out.print("Enter your feedback: ");
            String feedback = scanner.nextLine();
            currentUser.submitFeedback(feedback);
        } else {
            System.out.println("Please register or log in to submit feedback.");
        }
    }

    private static void updateContactInfo() {
        if (currentUser != null) {
            System.out.print("Enter new email: ");
            String newEmail = scanner.nextLine();
            System.out.print("Enter new phone number: ");
            String newPhoneNumber = scanner.nextLine();
            currentUser.updateContactInfo(newEmail, newPhoneNumber);
        } else {
            System.out.println("Please register or log in to update contact information.");
        }
    }
}
