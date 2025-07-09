import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private String doctorId;
    private String registrationNumber;
    private String name;
    private String specialization;
    private List<String> availableTimeSlots;
    private double consultationFee;

    public Doctor(String doctorId, String registrationNumber, String name, String specialization, List<String> availableTimeSlots, double consultationFee) {
        this.doctorId = doctorId;
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.specialization = specialization;
        this.availableTimeSlots = new ArrayList<>(availableTimeSlots);
        this.consultationFee = consultationFee;
    }

    public String getDoctorId() { return doctorId; }
    public String getRegistrationNumber() { return registrationNumber; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public List<String> getAvailableTimeSlots() { return availableTimeSlots; }
    public double getConsultationFee() { return consultationFee; }

    public boolean isAvailable(String timeSlot) {
        return availableTimeSlots.contains(timeSlot);
    }
    public void bookTimeSlot(String timeSlot) {
        availableTimeSlots.remove(timeSlot);
    }
    public void freeTimeSlot(String timeSlot) {
        if (!availableTimeSlots.contains(timeSlot)) {
            availableTimeSlots.add(timeSlot);
        }
    }

    @Override
    public String toString() {
        String displayName = name.startsWith("Dr.") ? name : "Dr. " + name;
        return displayName + " (" + specialization + ", Fee: Rs." + String.format("%.2f", consultationFee) + ")";
    }
}














