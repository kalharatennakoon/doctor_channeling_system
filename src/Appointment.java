public class Appointment {
    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private String timeSlot;

    public Appointment(String appointmentId, Patient patient, Doctor doctor, String timeSlot) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.timeSlot = timeSlot;
    }

    public String getAppointmentId() { return appointmentId; }
    public Patient getPatient() { 
        return patient; 
    }

    public Doctor getDoctor() { 
        return doctor; 
    }
    
    public String getTimeSlot() { 
        return timeSlot; 
    }

    @Override
    public String toString() {
        return "Appointment: " + patient.getName() + " with " + doctor.getName() +
               " at " + timeSlot + " | Specialization: " + doctor.getSpecialization();
    }
}




