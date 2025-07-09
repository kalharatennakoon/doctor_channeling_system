import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("HOSPITAL APPOINTMENT MANAGEMENT SYSTEM");
        System.out.println("==========================================");
        System.out.println("Welcome to the Digital Healthcare Platform!");
        
        AppointmentManager manager = new AppointmentManager();
        
        // Main menu loop
        while (true) {
            displayMainMenu();
            
            System.out.print("Enter your choice (1-10): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    manager.printHeader("DOCTOR REGISTRATION");
                    registerDoctors(manager);
                    break;
                case 2:
                    manager.printHeader("PATIENT REGISTRATION");
                    List<Patient> patients = registerPatients(manager);
                    System.out.println("Patients registered successfully!");
                    break;
                case 3:
                    manager.displayAvailableDoctors();
                    break;
                case 4:
                    manager.printHeader("APPOINTMENT BOOKING");
                    bookAppointments(manager);
                    break;
                case 5:
                    manager.printHeader("CANCELLATION & RESCHEDULING");
                    handleCancellations(manager);
                    break;
                case 6:
                    manager.displayAppointments();
                    break;
                case 7:
                    manager.displayCancellationHistory();
                    break;
                case 8:
                    manager.displaySystemStatus();
                    break;
                case 9:
                    csvDataMenu(manager);
                    break;
                case 10:
                    System.out.println("\nThank you for using the Hospital Appointment Management System!");
                    System.out.println("All data has been automatically saved to CSV files.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    
    private static void displayMainMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("MAIN MENU");
        System.out.println("=".repeat(60));
        System.out.println("1. Register Doctors");
        System.out.println("2. Register Patients");
        System.out.println("3. View Available Doctors");
        System.out.println("4. Book Appointments");
        System.out.println("5. Cancel Appointments");
        System.out.println("6. View All Appointments");
        System.out.println("7. View Cancellation History");
        System.out.println("8. System Status");
        System.out.println("9. CSV Data Management");
        System.out.println("10. Exit");
        System.out.println("=".repeat(60));
    }
    
    private static void csvDataMenu(AppointmentManager manager) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("CSV DATA MANAGEMENT");
        System.out.println("=".repeat(60));
        System.out.println("1. Save All Data to CSV");
        System.out.println("2. Show CSV File Locations");
        System.out.println("3. Create Data Configuration File");
        System.out.println("4. Back to Main Menu");
        System.out.println("=".repeat(60));
        
        System.out.print("Enter your choice (1-4): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        switch (choice) {
            case 1:
                manager.saveAllDataToCSV(true);
                break;
            case 2:
                manager.showCSVFileLocations();
                break;
            case 3:
                manager.createDataConfigFile();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    private static void registerDoctors(AppointmentManager manager) {
        System.out.println("Enter doctor information...");
        
        System.out.print("Enter number of doctors to register: ");
        int numDoctors = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        for (int i = 1; i <= numDoctors; i++) {
            System.out.println("\n--- Doctor " + i + " ---");
            
            // Auto-generate unique doctor ID
            String doctorId = manager.generateDoctorId();
            System.out.println("Auto-generated Doctor ID: " + doctorId);
            
            // Get and validate registration number
            String registrationNumber;
            while (true) {
                System.out.print("Enter Doctor Registration Number: ");
                registrationNumber = scanner.nextLine();
                
                if (manager.isRegistrationNumberExists(registrationNumber)) {
                    System.out.println("Registration number already exists! Please enter a different one.");
                } else {
                    break;
                }
            }
            
            System.out.print("Enter Doctor Name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter Specialization: ");
            String specialization = scanner.nextLine();
            
            System.out.print("Enter Consultation Fee: ");
            double fee = scanner.nextDouble();
            scanner.nextLine(); // consume newline
            
            System.out.print("Enter number of time slots: ");
            int numSlots = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            List<String> slots = new ArrayList<>();
            for (int j = 1; j <= numSlots; j++) {
                System.out.print("Enter time slot " + j + " (e.g., 10:00AM): ");
                String slot = scanner.nextLine();
                slots.add(slot);
            }
            
            Doctor doctor = new Doctor(doctorId, registrationNumber, name, specialization, slots, fee);
            manager.registerDoctor(doctor);
            System.out.println("Registered: Dr. " + doctor.getName() + " (" + specialization + ")");
            System.out.println("Doctor ID: " + doctorId + " | Registration: " + registrationNumber);
        }
        
        System.out.println("Doctor registration completed!");
    }
    
    private static List<Patient> registerPatients(AppointmentManager manager) {
        System.out.println("Enter patient information...");
        
        System.out.print("Enter number of patients to register: ");
        int numPatients = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        List<Patient> patients = new ArrayList<>();
        
        for (int i = 1; i <= numPatients; i++) {
            System.out.println("\n--- Patient " + i + " ---");
            
            // Auto-generate unique patient ID
            String patientId = manager.generatePatientId();
            System.out.println("Auto-generated Patient ID: " + patientId);
            
            System.out.print("Enter Patient Name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter Mobile Number: ");
            String mobile = scanner.nextLine();
            
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            
            System.out.print("Enter City: ");
            String city = scanner.nextLine();
            
            System.out.print("Enter Age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            System.out.print("Enter Medical History: ");
            String medicalHistory = scanner.nextLine();
            
            Patient patient = new Patient(patientId, name, mobile, email, city, age, medicalHistory);
            patients.add(patient);
            System.out.println("Patient: " + patient.getName() + " (ID: " + patientId + ", Age: " + patient.getAge() + ", " + patient.getCity() + ")");
        }
        
        System.out.println("Patient registration completed!");
        
        return patients;
    }
    
    private static void bookAppointments(AppointmentManager manager) {
        // Check if there are any doctors
        List<Doctor> availableDoctors = manager.getAvailableDoctors();
        if (availableDoctors.isEmpty()) {
            System.out.println("No doctors available! Please register doctors first.");
            return;
        }
        
        System.out.println("Processing appointment requests...");
        
        System.out.print("Enter number of appointment bookings to process: ");
        int numBookings = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        for (int i = 1; i <= numBookings; i++) {
            manager.printSeparator();
            System.out.println("Booking Request #" + i + ":");
            
            // Register patient for this appointment
            System.out.println("Enter patient details:");
            System.out.print("Patient Name: ");
            String name = scanner.nextLine();
            
            System.out.print("Mobile Number: ");
            String mobile = scanner.nextLine();
            
            System.out.print("Email: ");
            String email = scanner.nextLine();
            
            System.out.print("City: ");
            String city = scanner.nextLine();
            
            System.out.print("Age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            System.out.print("Medical History: ");
            String medicalHistory = scanner.nextLine();
            
            // Auto-generate unique patient ID
            String patientId = manager.generatePatientId();
            System.out.println("Auto-generated Patient ID: " + patientId);
            
            Patient patient = new Patient(patientId, name, mobile, email, city, age, medicalHistory);
            
            // Display available doctors
            System.out.println("\nAvailable doctors:");
            availableDoctors = manager.getAvailableDoctors(); // Refresh the list
            for (int j = 0; j < availableDoctors.size(); j++) {
                Doctor doc = availableDoctors.get(j);
                System.out.println((j + 1) + ". Dr. " + doc.getName() + " (" + doc.getSpecialization() + ")");
                System.out.println("   Available slots: " + doc.getAvailableTimeSlots());
            }
            
            System.out.print("Select doctor (enter number): ");
            int doctorIndex = scanner.nextInt() - 1;
            scanner.nextLine(); // consume newline
            
            if (doctorIndex < 0 || doctorIndex >= availableDoctors.size()) {
                System.out.println("Invalid doctor selection!");
                continue;
            }
            
            System.out.print("Enter time slot: ");
            String timeSlot = scanner.nextLine();
            
            Doctor selectedDoctor = availableDoctors.get(doctorIndex);
            
            manager.bookAppointment(patient, selectedDoctor, timeSlot);
        }
    }
    
    private static void handleCancellations(AppointmentManager manager) {
        System.out.println("Processing cancellation requests...");
        
        System.out.print("Do you want to cancel any appointments? (y/n): ");
        String response = scanner.nextLine();
        
        while (response.equalsIgnoreCase("y")) {
            System.out.print("Enter patient name to cancel appointment: ");
            String patientName = scanner.nextLine();
            
            manager.cancelAppointment(patientName);
            
            System.out.print("Cancel another appointment? (y/n): ");
            response = scanner.nextLine();
        }
    }
}
