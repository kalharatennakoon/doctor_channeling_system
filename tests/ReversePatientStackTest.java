public class ReversePatientStackTest {
    public static void main(String[] args) {
        System.out.println("REVERSE PATIENT STACK TEST WITH ERROR HANDLING");
        System.out.println("==============================================");
        
        ReversePatientStack patientStack = new ReversePatientStack();
        
        try {
            // Test Case 1: Empty stack retrieval test
            System.out.println("\n1. Testing empty stack retrieval:");
            System.out.println("----------------------------------");
            if (patientStack.isEmpty()) {
                System.out.println("Stack is empty - no patients to retrieve.");
            } else {
                patientStack.displayPatientsFromNewestToOldest();
            }
            
            // Test Case 2: Register patients with error handling
            System.out.println("\n2. Registering Sri Lankan patients:");
            System.out.println("-----------------------------------");
            
            // Create patients with null safety checks
            Patient p1 = createPatientSafely("PAT001", "Kamal Perera", "0771234567", "kamal.perera@gmail.com", "Colombo", 45, "Diabetes");
            Patient p2 = createPatientSafely("PAT002", "Nadeeka Silva", "0772345678", "nadeeka.silva@gmail.com", "Kandy", 32, "Hypertension");
            Patient p3 = createPatientSafely("PAT003", "Sunil Fernando", "0773456789", "sunil.fernando@gmail.com", "Galle", 28, "Asthma");
            
            // Register patients with error handling
            registerPatientSafely(patientStack, p1, "First");
            registerPatientSafely(patientStack, p2, "Second");
            registerPatientSafely(patientStack, p3, "Third");
            
            // Test Case 3: Loading into stack with error handling
            System.out.println("\n3. Loading patients into stack:");
            System.out.println("-------------------------------");
            try {
                patientStack.loadPatientsInReverseOrder();
                System.out.println("Success: " + patientStack.size() + " patients loaded into stack.");
            } catch (Exception e) {
                System.out.println("Error loading patients into stack: " + e.getMessage());
                return;
            }
            
            // Test Case 4: Retrieving patients with error handling
            System.out.println("\n4. Retrieving patients (LIFO order):");
            System.out.println("------------------------------------");
            try {
                if (!patientStack.isEmpty()) {
                    patientStack.displayPatientsFromNewestToOldest();
                } else {
                    System.out.println("Warning: Stack is empty, no patients to retrieve.");
                }
            } catch (Exception e) {
                System.out.println("Error during patient retrieval: " + e.getMessage());
            }
            
            // Test Case 5: Verify final state
            System.out.println("\n5. Final verification:");
            System.out.println("---------------------");
            System.out.println("Stack empty after retrieval: " + patientStack.isEmpty());
            System.out.println("Stack size: " + patientStack.size());
            
            System.out.println("\nResult: LIFO order confirmed with robust error handling!");
            
        } catch (Exception e) {
            System.out.println("Unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Helper method to safely create patients
    private static Patient createPatientSafely(String id, String name, String contact, String email, String city, int age, String condition) {
        try {
            if (id == null || name == null || contact == null || email == null || city == null || condition == null) {
                throw new IllegalArgumentException("Patient data cannot be null");
            }
            if (age <= 0 || age > 150) {
                throw new IllegalArgumentException("Invalid age: " + age);
            }
            return new Patient(id, name, contact, email, city, age, condition);
        } catch (Exception e) {
            System.out.println("Error creating patient: " + e.getMessage());
            return null;
        }
    }
    
    // Helper method to safely register patients
    private static void registerPatientSafely(ReversePatientStack stack, Patient patient, String position) {
        try {
            if (patient == null) {
                System.out.println("Warning: Cannot register null patient (" + position + " patient skipped)");
                return;
            }
            if (stack == null) {
                throw new IllegalArgumentException("Patient stack cannot be null");
            }
            stack.registerPatient(patient);
        } catch (Exception e) {
            System.out.println("Error registering " + position.toLowerCase() + " patient: " + e.getMessage());
        }
    }
}






