import java.util.*;

public class AppointmentManager {
    private List<Doctor> doctors;
    List<Appointment> appointments; // Package-private for testing
    Queue<Patient> rescheduleQueue; // Package-private for testing
    Stack<Appointment> cancellationHistory; // Package-private for testing

    public AppointmentManager() {
        doctors = new ArrayList<>();
        appointments = new ArrayList<>();
        rescheduleQueue = new LinkedList<>();
        cancellationHistory = new Stack<>();
        
        // Load existing data from CSV files at startup
        loadDataFromCSV();
    }
    
    // Load data from CSV files at startup
    private void loadDataFromCSV() {
        System.out.println("\n🔄 LOADING DATA FROM CSV FILES...");
        System.out.println("=".repeat(50));
        
        // Load doctors from CSV
        List<Doctor> loadedDoctors = CSVManager.loadDoctors();
        if (loadedDoctors != null && !loadedDoctors.isEmpty()) {
            doctors.addAll(loadedDoctors);
        }
        
        // Load patients from CSV
        List<Patient> loadedPatients = CSVManager.loadPatients();
        
        // Load appointments from CSV (requires doctors and patients to be loaded first)
        List<Appointment> loadedAppointments = CSVManager.loadAppointments(doctors, loadedPatients);
        if (loadedAppointments != null && !loadedAppointments.isEmpty()) {
            appointments.addAll(loadedAppointments);
        }
        
        // Load cancellations from CSV
        Stack<Appointment> loadedCancellations = CSVManager.loadCancellations(doctors, loadedPatients);
        if (loadedCancellations != null && !loadedCancellations.isEmpty()) {
            cancellationHistory.addAll(loadedCancellations);
        }
        
        System.out.println("=".repeat(50));
        System.out.println("✅ DATA LOADING COMPLETE");
    }

    public void registerDoctor(Doctor doctor) {
        doctors.add(doctor);
        // Save to CSV after adding doctor
        CSVManager.saveAllData(this);
    }
    
    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors);
    }
    
    public List<Doctor> getAvailableDoctors() {
        List<Doctor> availableDoctors = new ArrayList<>();
        for (Doctor d : doctors) {
            if (!d.getAvailableTimeSlots().isEmpty()) {
                availableDoctors.add(d);
            }
        }
        return availableDoctors;
    }

    public void displayAvailableDoctors() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📋 AVAILABLE DOCTORS");
        System.out.println("=".repeat(60));
        
        if (doctors.isEmpty()) {
            System.out.println("❌ No doctors registered yet.");
            return;
        }
        
        for (Doctor d : doctors) {
            if (!d.getAvailableTimeSlots().isEmpty()) {
                System.out.println("🩺 " + d);
                System.out.println("   � Registration: " + d.getRegistrationNumber() + " | ID: " + d.getDoctorId());
                System.out.println("   �📅 Available Slots: " + d.getAvailableTimeSlots());
                System.out.println("   💰 Consultation Fee: Rs. " + String.format("%.2f", d.getConsultationFee()));
                System.out.println("   " + "-".repeat(55));
            }
        }
    }

    public Doctor findDoctorByName(String name) {
        for (Doctor d : doctors) {
            if (d.getName().equalsIgnoreCase(name)) {
                return d;
            }
        }
        return null;
    }

    public void bookAppointment(Patient patient, Doctor doctor, String timeSlot) {
        if (doctor.isAvailable(timeSlot)) {
            String appointmentId = generateAppointmentId();
            Appointment appt = new Appointment(appointmentId, patient, doctor, timeSlot);
            appointments.add(appt);
            doctor.bookTimeSlot(timeSlot);
            System.out.println("✅ SUCCESS: Appointment booked for " + patient.getName());
            System.out.println("   📋 Appointment ID: " + appointmentId);
            System.out.println("   👨‍⚕️ Doctor: " + doctor.getName() + " (" + doctor.getSpecialization() + ")");
            System.out.println("   🕒 Time: " + timeSlot);
            System.out.println("   💰 Fee: Rs. " + String.format("%.2f", doctor.getConsultationFee()));
            // Save to CSV after booking appointment
            CSVManager.saveAllData(this);
        } else {
            System.out.println("⚠️  CONFLICT: Time slot " + timeSlot + " is not available!");
            System.out.println("   📝 Adding " + patient.getName() + " to reschedule queue...");
            rescheduleQueue.offer(patient);
            System.out.println("   📊 Queue Position: " + rescheduleQueue.size());
        }
    }

    public void cancelAppointment(String patientName) {
        System.out.println("\n🔄 PROCESSING CANCELLATION...");
        Appointment toRemove = null;
        for (Appointment appt : appointments) {
            if (appt.getPatient().getName().equalsIgnoreCase(patientName)) {
                toRemove = appt;
                break;
            }
        }
        if (toRemove != null) {
            appointments.remove(toRemove);
            cancellationHistory.push(toRemove);
            // Free up the time slot
            toRemove.getDoctor().freeTimeSlot(toRemove.getTimeSlot());
            System.out.println("❌ CANCELLED: " + toRemove.getPatient().getName() + "'s appointment");
            System.out.println("   🕒 Freed slot: " + toRemove.getTimeSlot() + " with Dr. " + toRemove.getDoctor().getName());
            
            if (!rescheduleQueue.isEmpty()) {
                Patient next = rescheduleQueue.poll();
                System.out.println("🔄 AUTO-RESCHEDULING from queue...");
                bookAppointment(next, toRemove.getDoctor(), toRemove.getTimeSlot());
            } else {
                System.out.println("📋 No patients in reschedule queue. Slot remains open.");
            }
            // Save to CSV after cancellation
            CSVManager.saveAllData(this);
        } else {
            System.out.println("❌ ERROR: No appointment found for patient '" + patientName + "'");
        }
    }

    public void displayAppointments() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📅 CURRENT APPOINTMENTS SCHEDULE");
        System.out.println("=".repeat(60));
        
        if (appointments.isEmpty()) {
            System.out.println("📋 No appointments scheduled yet.");
            return;
        }
        
        // Group appointments by doctor
        Map<String, List<Appointment>> appointmentsByDoctor = new HashMap<>();
        for (Appointment appt : appointments) {
            String doctorKey = appt.getDoctor().getName();
            appointmentsByDoctor.computeIfAbsent(doctorKey, k -> new ArrayList<>()).add(appt);
        }
        
        int totalAppointments = 0;
        for (String doctorName : appointmentsByDoctor.keySet()) {
            List<Appointment> doctorAppts = appointmentsByDoctor.get(doctorName);
            Doctor doctor = doctorAppts.get(0).getDoctor();
            
            System.out.println("🩺 Dr. " + doctorName + " (" + doctor.getSpecialization() + ")");
            System.out.println("   💰 Fee: Rs. " + String.format("%.2f", doctor.getConsultationFee()));
            
            for (Appointment appt : doctorAppts) {
                Patient patient = appt.getPatient();
                System.out.println("   📍 " + appt.getTimeSlot() + " → " + patient.getName() + 
                                 " (Age: " + patient.getAge() + ", " + patient.getCity() + ")");
                System.out.println("      � Appointment ID: " + appt.getAppointmentId() + " | Patient ID: " + patient.getPatientId());
                System.out.println("      �📱 " + patient.getMobile() + " | 📧 " + patient.getEmail());
                System.out.println("      🏥 Medical History: " + patient.getMedicalHistory());
                totalAppointments++;
            }
            System.out.println("   " + "-".repeat(55));
        }
        
        System.out.println("📊 Total Appointments: " + totalAppointments);
        if (!rescheduleQueue.isEmpty()) {
            System.out.println("⏳ Patients in Reschedule Queue: " + rescheduleQueue.size());
        }
    }
    
    public void displayCancellationHistory() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("❌ CANCELLATION HISTORY");
        System.out.println("=".repeat(60));
        
        if (cancellationHistory.isEmpty()) {
            System.out.println("📋 No cancellations recorded yet.");
            return;
        }
        
        System.out.println("📊 Total Cancellations: " + cancellationHistory.size());
        System.out.println("-".repeat(60));
        
        // Display cancellations in reverse order (most recent first)
        List<Appointment> reversedHistory = new ArrayList<>(cancellationHistory);
        Collections.reverse(reversedHistory);
        
        for (Appointment cancelledAppt : reversedHistory) {
            Patient patient = cancelledAppt.getPatient();
            Doctor doctor = cancelledAppt.getDoctor();
            
            System.out.println("🗂️ Cancellation Details:");
            System.out.println("   📋 Appointment ID: " + cancelledAppt.getAppointmentId());
            System.out.println("   👤 Patient: " + patient.getName() + " (ID: " + patient.getPatientId() + ")");
            System.out.println("   🩺 Doctor: Dr. " + doctor.getName() + " (" + doctor.getSpecialization() + ")");
            System.out.println("   📅 Time Slot: " + cancelledAppt.getTimeSlot());
            System.out.println("   📱 Contact: " + patient.getMobile() + " | 📧 " + patient.getEmail());
            System.out.println("   🏥 Medical History: " + patient.getMedicalHistory());
            System.out.println("   " + "-".repeat(55));
        }
        
        System.out.println("📊 Total Cancellations: " + cancellationHistory.size());
    }
    
    public void displaySystemStatus() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🏥 HOSPITAL APPOINTMENT SYSTEM STATUS");
        System.out.println("=".repeat(60));
        System.out.println("👨‍⚕️ Total Doctors: " + doctors.size());
        System.out.println("📅 Active Appointments: " + appointments.size());
        System.out.println("⏳ Reschedule Queue: " + rescheduleQueue.size());
        System.out.println("🗂️ Cancellation History: " + cancellationHistory.size());
        System.out.println("=".repeat(60));
    }
    
    public void saveAllDataToCSV() {
        CSVManager.saveAllData(this);
    }
    
    public void saveAllDataToCSV(boolean verbose) {
        CSVManager.saveAllData(this, verbose);
    }
    
    public void showCSVFileLocations() {
        CSVManager.saveAllData(this, true);
        CSVManager.showFilePaths();
    }
    
    public void createDataConfigFile() {
        CSVManager.createConfigFile();
    }
    
    public void printHeader(String title) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🏥 " + title);
        System.out.println("=".repeat(60));
    }
    
    public void printSeparator() {
        System.out.println("\n" + "-".repeat(60));
    }
    
    // Generate unique doctor ID automatically
    public String generateDoctorId() {
        int maxId = 0;
        for (Doctor doctor : doctors) {
            String id = doctor.getDoctorId();
            if (id.startsWith("DOC")) {
                try {
                    int num = Integer.parseInt(id.substring(3));
                    maxId = Math.max(maxId, num);
                } catch (NumberFormatException e) {
                    // Ignore non-numeric IDs
                }
            }
        }
        return String.format("DOC%03d", maxId + 1);
    }
    
    // Check if registration number already exists
    public boolean isRegistrationNumberExists(String registrationNumber) {
        for (Doctor doctor : doctors) {
            if (doctor.getRegistrationNumber().equalsIgnoreCase(registrationNumber)) {
                return true;
            }
        }
        return false;
    }
    
    // Generate unique patient ID automatically
    public String generatePatientId() {
        int maxId = 0;
        Set<Patient> allPatients = new HashSet<>();
        
        // Collect all patients from appointments
        for (Appointment appointment : appointments) {
            allPatients.add(appointment.getPatient());
        }
        
        // Collect all patients from reschedule queue
        allPatients.addAll(rescheduleQueue);
        
        // Collect all patients from cancellation history
        for (Appointment cancelledAppointment : cancellationHistory) {
            allPatients.add(cancelledAppointment.getPatient());
        }
        
        // Find the highest existing patient ID number
        for (Patient patient : allPatients) {
            String id = patient.getPatientId();
            if (id != null && id.startsWith("PAT")) {
                try {
                    int num = Integer.parseInt(id.substring(3));
                    maxId = Math.max(maxId, num);
                } catch (NumberFormatException e) {
                    // Ignore non-numeric IDs
                }
            }
        }
        return String.format("PAT%03d", maxId + 1);
    }
    
    // Generate unique appointment ID automatically
    public String generateAppointmentId() {
        int maxId = 0;
        
        // Check all current appointments
        for (Appointment appointment : appointments) {
            String id = appointment.getAppointmentId();
            if (id != null && id.startsWith("APT")) {
                try {
                    int num = Integer.parseInt(id.substring(3));
                    maxId = Math.max(maxId, num);
                } catch (NumberFormatException e) {
                    // Ignore non-numeric IDs
                }
            }
        }
        
        // Check cancellation history for higher IDs
        for (Appointment appointment : cancellationHistory) {
            String id = appointment.getAppointmentId();
            if (id != null && id.startsWith("APT")) {
                try {
                    int num = Integer.parseInt(id.substring(3));
                    maxId = Math.max(maxId, num);
                } catch (NumberFormatException e) {
                    // Ignore non-numeric IDs
                }
            }
        }
        
        return String.format("APT%03d", maxId + 1);
    }
    
    // Generate unique cancellation ID automatically
    public String generateCancellationId() {
        return String.format("CAN%03d", cancellationHistory.size() + 1);
    }
}
