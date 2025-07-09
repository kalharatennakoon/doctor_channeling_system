import java.io.*;
import java.util.*;

public class CSVManager {
    // Get the project root directory (parent of src if running from src, otherwise current directory)
    private static final String PROJECT_ROOT = getProjectRoot();
    private static final String DATA_DIR = PROJECT_ROOT + "/data";
    private static final String DOCTORS_FILE = DATA_DIR + "/doctors.csv";
    private static final String PATIENTS_FILE = DATA_DIR + "/patients.csv";
    private static final String APPOINTMENTS_FILE = DATA_DIR + "/appointments.csv";
    private static final String CANCELLATIONS_FILE = DATA_DIR + "/cancellations.csv";
    
    // Determine the correct project root directory
    private static String getProjectRoot() {
        String currentDir = System.getProperty("user.dir");
        // If current directory ends with "src", go up one level
        if (currentDir.endsWith("src")) {
            return new File(currentDir).getParent();
        }
        return currentDir;
    }
    
    // Ensure data directory exists
    private static void ensureDataDirectoryExists() {
        File dataDir = new File(DATA_DIR);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
            System.out.println("📁 Created data directory: data/");
        }
    }
    
    // Save doctors to CSV
    public static void saveDoctors(List<Doctor> doctors) {
        saveDoctors(doctors, false);
    }
    
    // Save doctors to CSV with verbose option
    public static void saveDoctors(List<Doctor> doctors, boolean verbose) {
        ensureDataDirectoryExists();
        try (PrintWriter writer = new PrintWriter(new FileWriter(DOCTORS_FILE))) {
            // Write header
            writer.println("DoctorID,RegistrationNumber,Name,Specialization,ConsultationFee,AvailableTimeSlots");
            
            // Write doctor data
            for (Doctor doctor : doctors) {
                writer.printf("%s,%s,%s,%s,%.2f,\"%s\"%n",
                    escapeCSV(doctor.getDoctorId()),
                    escapeCSV(doctor.getRegistrationNumber()),
                    escapeCSV(doctor.getName()),
                    escapeCSV(doctor.getSpecialization()),
                    doctor.getConsultationFee(),
                    String.join(";", doctor.getAvailableTimeSlots())
                );
            }
            if (verbose) {
                System.out.println("✅ Doctors data saved to data/doctors.csv");
            }
        } catch (IOException e) {
            System.err.println("❌ Error saving doctors: " + e.getMessage());
        }
    }
    
    // Save patients to CSV
    public static void savePatients(List<Patient> patients) {
        savePatients(patients, false);
    }
    
    // Save patients to CSV with verbose option
    public static void savePatients(List<Patient> patients, boolean verbose) {
        ensureDataDirectoryExists();
        try (PrintWriter writer = new PrintWriter(new FileWriter(PATIENTS_FILE))) {
            // Write header
            writer.println("PatientID,Name,Mobile,Email,City,Age,MedicalHistory");
            
            // Write patient data
            for (Patient patient : patients) {
                writer.printf("%s,%s,%s,%s,%s,%d,%s%n",
                    escapeCSV(patient.getPatientId()),
                    escapeCSV(patient.getName()),
                    escapeCSV(patient.getMobile()),
                    escapeCSV(patient.getEmail()),
                    escapeCSV(patient.getCity()),
                    patient.getAge(),
                    escapeCSV(patient.getMedicalHistory())
                );
            }
            if (verbose) {
                System.out.println("✅ Patients data saved to data/patients.csv");
            }
        } catch (IOException e) {
            System.err.println("❌ Error saving patients: " + e.getMessage());
        }
    }
    
    // Save appointments to CSV
    public static void saveAppointments(List<Appointment> appointments) {
        saveAppointments(appointments, false);
    }
    
    // Save appointments to CSV with verbose option
    public static void saveAppointments(List<Appointment> appointments, boolean verbose) {
        ensureDataDirectoryExists();
        try (PrintWriter writer = new PrintWriter(new FileWriter(APPOINTMENTS_FILE))) {
            // Write header
            writer.println("AppointmentID,PatientID,PatientName,DoctorID,DoctorName,DoctorSpecialization,TimeSlot,ConsultationFee");
            
            // Write appointment data
            for (Appointment appointment : appointments) {
                writer.printf("%s,%s,%s,%s,%s,%s,%s,%.2f%n",
                    escapeCSV(appointment.getAppointmentId()),
                    escapeCSV(appointment.getPatient().getPatientId()),
                    escapeCSV(appointment.getPatient().getName()),
                    escapeCSV(appointment.getDoctor().getDoctorId()),
                    escapeCSV(appointment.getDoctor().getName()),
                    escapeCSV(appointment.getDoctor().getSpecialization()),
                    escapeCSV(appointment.getTimeSlot()),
                    appointment.getDoctor().getConsultationFee()
                );
            }
            if (verbose) {
                System.out.println("✅ Appointments data saved to data/appointments.csv");
            }
        } catch (IOException e) {
            System.err.println("❌ Error saving appointments: " + e.getMessage());
        }
    }
    
    // Save cancellation history to CSV
    public static void saveCancellations(Stack<Appointment> cancellations) {
        saveCancellations(cancellations, false);
    }
    
    // Save cancellation history to CSV with verbose option
    public static void saveCancellations(Stack<Appointment> cancellations, boolean verbose) {
        ensureDataDirectoryExists();
        try (PrintWriter writer = new PrintWriter(new FileWriter(CANCELLATIONS_FILE))) {
            // Write header
            writer.println("CancellationID,AppointmentID,PatientID,PatientName,DoctorID,DoctorName,DoctorSpecialization,TimeSlot,ConsultationFee,CancellationOrder");
            
            // Convert stack to list to maintain order
            List<Appointment> cancellationList = new ArrayList<>(cancellations);
            
            // Write cancellation data
            for (int i = 0; i < cancellationList.size(); i++) {
                Appointment appointment = cancellationList.get(i);
                String cancellationId = String.format("CAN%03d", i + 1);
                writer.printf("%s,%s,%s,%s,%s,%s,%s,%s,%.2f,%d%n",
                    escapeCSV(cancellationId),
                    escapeCSV(appointment.getAppointmentId()),
                    escapeCSV(appointment.getPatient().getPatientId()),
                    escapeCSV(appointment.getPatient().getName()),
                    escapeCSV(appointment.getDoctor().getDoctorId()),
                    escapeCSV(appointment.getDoctor().getName()),
                    escapeCSV(appointment.getDoctor().getSpecialization()),
                    escapeCSV(appointment.getTimeSlot()),
                    appointment.getDoctor().getConsultationFee(),
                    i + 1
                );
            }
            if (verbose) {
                System.out.println("✅ Cancellation history saved to data/cancellations.csv");
            }
        } catch (IOException e) {
            System.err.println("❌ Error saving cancellations: " + e.getMessage());
        }
    }
    
    // Save all data at once (silent mode)
    public static void saveAllData(AppointmentManager manager) {
        saveAllData(manager, false);
    }
    
    // Save all data at once with verbose option
    public static void saveAllData(AppointmentManager manager, boolean verbose) {
        if (verbose) {
            System.out.println("\n💾 SAVING ALL DATA TO CSV FILES...");
            System.out.println("=".repeat(50));
        }
        
        // Ensure data directory exists
        ensureDataDirectoryExists();
        
        // Get all doctors from the manager
        List<Doctor> doctors = manager.getAvailableDoctors();
        // Add doctors without available slots too
        for (Doctor doctor : manager.getAllDoctors()) {
            if (!doctors.contains(doctor)) {
                doctors.add(doctor);
            }
        }
        
        // Get all patients from appointments and reschedule queue
        Set<Patient> patientSet = new HashSet<>();
        for (Appointment appointment : manager.appointments) {
            patientSet.add(appointment.getPatient());
        }
        for (Patient patient : manager.rescheduleQueue) {
            patientSet.add(patient);
        }
        for (Appointment cancelledAppointment : manager.cancellationHistory) {
            patientSet.add(cancelledAppointment.getPatient());
        }
        
        saveDoctors(doctors, verbose);
        savePatients(new ArrayList<>(patientSet), verbose);
        saveAppointments(manager.appointments, verbose);
        saveCancellations(manager.cancellationHistory, verbose);
        if (verbose) {
            createConfigFile();
            System.out.println("=".repeat(50));
            System.out.println("🎉 ALL DATA SUCCESSFULLY SAVED TO: data/");
        }
    }
    
    // Load doctors from CSV
    public static List<Doctor> loadDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        File file = new File(DOCTORS_FILE);
        
        if (!file.exists()) {
            System.out.println("📄 No doctors file found in data/. Starting fresh.");
            return doctors;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine(); // Skip header
            
            while ((line = reader.readLine()) != null) {
                String[] parts = parseCSVLine(line);
                if (parts.length >= 6) {
                    String doctorId = parts[0];
                    String registrationNumber = parts[1];
                    String name = parts[2];
                    String specialization = parts[3];
                    double consultationFee = Double.parseDouble(parts[4]);
                    
                    // Parse available time slots
                    List<String> timeSlots = new ArrayList<>();
                    if (parts.length > 5 && !parts[5].isEmpty()) {
                        String[] slots = parts[5].split(";");
                        for (String slot : slots) {
                            if (!slot.trim().isEmpty()) {
                                timeSlots.add(slot.trim());
                            }
                        }
                    }
                    
                    doctors.add(new Doctor(doctorId, registrationNumber, name, specialization, timeSlots, consultationFee));
                }
            }
            System.out.println("✅ Loaded " + doctors.size() + " doctors from data/doctors.csv");
        } catch (IOException | NumberFormatException e) {
            System.err.println("❌ Error loading doctors: " + e.getMessage());
        }
        
        return doctors;
    }
    
    // Load patients from CSV
    public static List<Patient> loadPatients() {
        List<Patient> patients = new ArrayList<>();
        File file = new File(PATIENTS_FILE);
        
        if (!file.exists()) {
            System.out.println("📄 No patients file found in data/. Starting fresh.");
            return patients;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine(); // Skip header
            
            while ((line = reader.readLine()) != null) {
                String[] parts = parseCSVLine(line);
                if (parts.length >= 7) {
                    String patientId = parts[0];
                    String name = parts[1];
                    String mobile = parts[2];
                    String email = parts[3];
                    String city = parts[4];
                    int age = Integer.parseInt(parts[5]);
                    String medicalHistory = parts[6];
                    
                    patients.add(new Patient(patientId, name, mobile, email, city, age, medicalHistory));
                }
            }
            System.out.println("✅ Loaded " + patients.size() + " patients from data/patients.csv");
        } catch (IOException | NumberFormatException e) {
            System.err.println("❌ Error loading patients: " + e.getMessage());
        }
        
        return patients;
    }
    
    // Load appointments from CSV
    public static List<Appointment> loadAppointments(List<Doctor> doctors, List<Patient> patients) {
        List<Appointment> appointments = new ArrayList<>();
        File file = new File(APPOINTMENTS_FILE);
        
        if (!file.exists()) {
            System.out.println("📄 No appointments file found in data/. Starting fresh.");
            return appointments;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine(); // Skip header
            
            while ((line = reader.readLine()) != null) {
                String[] parts = parseCSVLine(line);
                if (parts.length >= 8) {
                    String appointmentId = parts[0];
                    String patientId = parts[1];
                    String doctorId = parts[3];
                    String timeSlot = parts[6];
                    
                    // Find the doctor and patient objects
                    Doctor doctor = findDoctorById(doctors, doctorId);
                    Patient patient = findPatientById(patients, patientId);
                    
                    if (doctor != null && patient != null) {
                        // Mark the time slot as booked for the doctor
                        doctor.bookTimeSlot(timeSlot);
                        appointments.add(new Appointment(appointmentId, patient, doctor, timeSlot));
                    }
                }
            }
            System.out.println("✅ Loaded " + appointments.size() + " appointments from data/appointments.csv");
        } catch (IOException | NumberFormatException e) {
            System.err.println("❌ Error loading appointments: " + e.getMessage());
        }
        
        return appointments;
    }
    
    // Load cancellations from CSV
    public static Stack<Appointment> loadCancellations(List<Doctor> doctors, List<Patient> patients) {
        Stack<Appointment> cancellations = new Stack<>();
        File file = new File(CANCELLATIONS_FILE);
        
        if (!file.exists()) {
            System.out.println("📄 No cancellations file found in data/. Starting fresh.");
            return cancellations;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine(); // Skip header
            
            // Store cancellations in a list first to maintain order
            List<Appointment> cancellationList = new ArrayList<>();
            
            while ((line = reader.readLine()) != null) {
                String[] parts = parseCSVLine(line);
                if (parts.length >= 10) {
                    String appointmentId = parts[1];
                    String patientId = parts[2];
                    String doctorId = parts[4];
                    String timeSlot = parts[7];
                    
                    // Find the doctor and patient objects
                    Doctor doctor = findDoctorById(doctors, doctorId);
                    Patient patient = findPatientById(patients, patientId);
                    
                    if (doctor != null && patient != null) {
                        cancellationList.add(new Appointment(appointmentId, patient, doctor, timeSlot));
                    }
                }
            }
            
            // Add to stack in reverse order to maintain the original order
            for (Appointment appointment : cancellationList) {
                cancellations.push(appointment);
            }
            
            System.out.println("✅ Loaded " + cancellations.size() + " cancellations from data/cancellations.csv");
        } catch (IOException | NumberFormatException e) {
            System.err.println("❌ Error loading cancellations: " + e.getMessage());
        }
        
        return cancellations;
    }
    
    // Helper method to find doctor by ID
    private static Doctor findDoctorById(List<Doctor> doctors, String doctorId) {
        for (Doctor doctor : doctors) {
            if (doctor.getDoctorId().equals(doctorId)) {
                return doctor;
            }
        }
        return null;
    }
    
    // Helper method to find patient by ID
    private static Patient findPatientById(List<Patient> patients, String patientId) {
        for (Patient patient : patients) {
            if (patient.getPatientId().equals(patientId)) {
                return patient;
            }
        }
        return null;
    }
    
    // Utility method to escape CSV values
    private static String escapeCSV(String value) {
        if (value == null) return "";
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
    
    // Utility method to parse CSV line
    private static String[] parseCSVLine(String line) {
        List<String> result = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder current = new StringBuilder();
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            if (c == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    current.append('"');
                    i++; // Skip next quote
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (c == ',' && !inQuotes) {
                result.add(current.toString());
                current = new StringBuilder();
            } else {
                current.append(c);
            }
        }
        
        result.add(current.toString());
        return result.toArray(new String[0]);
    }
    
    // Create a configuration file documenting the data structure
    public static void createConfigFile() {
        ensureDataDirectoryExists();
        String configFile = DATA_DIR + "/README.txt";
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(configFile))) {
            writer.println("HOSPITAL APPOINTMENT MANAGEMENT SYSTEM - DATA DIRECTORY");
            writer.println("======================================================");
            writer.println("Generated on: " + new java.util.Date());
            writer.println();
            writer.println("This directory contains all the CSV data files for the system:");
            writer.println();
            writer.println("📁 DATA STRUCTURE:");
            writer.println("├── doctors.csv      - Doctor information and available slots");
            writer.println("├── patients.csv     - Patient details and medical history");
            writer.println("├── appointments.csv - Active appointment records");
            writer.println("├── cancellations.csv- Cancelled appointment history");
            writer.println("└── README.txt       - This configuration file");
            writer.println();
            writer.println("📋 FILE DESCRIPTIONS:");
            writer.println("doctors.csv     : Contains doctor ID, name, specialization, fees, and available time slots");
            writer.println("patients.csv    : Contains patient name, contact info, age, city, and medical history");
            writer.println("appointments.csv: Contains current appointments with patient-doctor-time mappings");
            writer.println("cancellations.csv: Contains history of cancelled appointments for audit trail");
            writer.println();
            writer.println("🔄 USAGE:");
            writer.println("- All files are automatically created and updated by the system");
            writer.println("- Files are in CSV format for easy import/export to other systems");
            writer.println("- Data is saved automatically after every operation");
            writer.println("- Manual editing is not recommended - use the system interface");
            writer.println();
            writer.println("⚠️  IMPORTANT:");
            writer.println("- Do not delete or modify these files manually");
            writer.println("- Keep this directory secure and backed up");
            writer.println("- All data is stored in plain text CSV format");
            
            System.out.println("📄 Configuration file created: data/README.txt");
        } catch (IOException e) {
            System.err.println("❌ Error creating config file: " + e.getMessage());
        }
    }
    
    // Get file paths for reference
    public static void showFilePaths() {
        System.out.println("\n📁 CSV FILES LOCATION:");
        System.out.println("=".repeat(50));
        System.out.println("📂 Data Directory: data/");
        System.out.println("👨‍⚕️ Doctors: data/doctors.csv");
        System.out.println("🏥 Patients: data/patients.csv");
        System.out.println("📅 Appointments: data/appointments.csv");
        System.out.println("❌ Cancellations: data/cancellations.csv");
        System.out.println("=".repeat(50));
    }
}
