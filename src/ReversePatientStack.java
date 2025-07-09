import java.util.*;

public class ReversePatientStack {
    Stack<Patient> patientStack = new Stack<>(); // Package-private for testing
    private List<Patient> registeredPatients = new ArrayList<>();

    // Simulate registration from oldest to newest
    public void registerPatient(Patient patient) {
        registeredPatients.add(patient);
        System.out.println("Patient registered: " + patient.getName());
    }

    // Load into stack for LIFO retrieval
    public void loadPatientsInReverseOrder() {
        patientStack.clear();
        for (Patient p : registeredPatients) {
            patientStack.push(p);
        }
    }

    // View patients from newest to oldest
    public void displayPatientsFromNewestToOldest() {
        System.out.println("Patients from Newest to Oldest:");
        while (!patientStack.isEmpty()) {
            Patient p = patientStack.pop();
            System.out.println("- " + p.getName() + " (Age: " + p.getAge() + ", City: " + p.getCity() + ")");
        }
    }

    public boolean isEmpty() {
        return patientStack.isEmpty();
    }

    public int size() {
        return patientStack.size();
    }
}
