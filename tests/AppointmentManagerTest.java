import java.util.*;

public class AppointmentManagerTest {
    private static int totalTests = 0;
    private static int passedTests = 0;
    private static int failedTests = 0;
    
    public static void main(String[] args) {
        System.out.println(" APPOINTMENT MANAGER TEST SUITE");
        System.out.println("==================================");
        
        AppointmentManager manager = new AppointmentManager();

        List<String> slots = Arrays.asList("09:00AM", "10:00AM", "11:00AM");
        Doctor d1 = new Doctor("D001", "REG001", "Dr. Fernando Silva", "Dermatology", new ArrayList<>(slots), 1500);
        manager.registerDoctor(d1);

        // Register patients
        Patient p1 = new Patient("PAT001", "Suresh", "0711111111", "suresh@gmail.com", "Colombo", 45, "Allergies");
        Patient p2 = new Patient("PAT002", "Kamal", "0722222222", "kamal@gmail.com", "Kandy", 30, "Cold");
        Patient p3 = new Patient("PAT003", "Sunil", "0733333333", "sunil@gmail.com", "Galle", 60, "Flu");
        Patient p4 = new Patient("PAT004", "Nadeesha", "0744444444", "nadeesha@gmail.com", "Jaffna", 28, "Cough");
        Patient p5 = new Patient("PAT005", "Ruwan", "0755555555", "ruwan@gmail.com", "Matara", 50, "Asthma");
        Patient p6 = new Patient("PAT006", "Dilani", "0766666666", "dilani@gmail.com", "Anuradhapura", 35, "Fever");
        Patient p7 = new Patient("PAT007", "Chamara", "0777777777", "chamara@gmail.com", "Badulla", 40, "Headache");

        // Test Cases
        runTest(1, "Book valid slot", () -> {
            manager.bookAppointment(p1, d1, "09:00AM");
            return manager.appointments.size() == 1; // Should have 1 appointment
        });

        runTest(2, "Book another valid slot", () -> {
            manager.bookAppointment(p2, d1, "10:00AM");
            return manager.appointments.size() == 2; // Should have 2 appointments
        });

        runTest(3, "Book final available slot", () -> {
            manager.bookAppointment(p3, d1, "11:00AM");
            return manager.appointments.size() == 3; // Should have 3 appointments
        });

        runTest(4, "Try to book already taken slot", () -> {
            int appointmentsBefore = manager.appointments.size();
            manager.bookAppointment(p4, d1, "09:00AM"); // should queue
            return manager.appointments.size() == appointmentsBefore && manager.rescheduleQueue.size() == 1;
        });

        runTest(5, "Try to book another taken slot", () -> {
            int appointmentsBefore = manager.appointments.size();
            manager.bookAppointment(p5, d1, "10:00AM"); // should queue
            return manager.appointments.size() == appointmentsBefore && manager.rescheduleQueue.size() == 2;
        });

        runTest(6, "Cancel an appointment (Suresh)", () -> {
            int queueBefore = manager.rescheduleQueue.size();
            manager.cancelAppointment("Suresh"); // p4 should be assigned
            return manager.rescheduleQueue.size() == queueBefore - 1 && manager.cancellationHistory.size() == 1;
        });

        runTest(7, "Cancel another appointment (Kamal)", () -> {
            int queueBefore = manager.rescheduleQueue.size();
            manager.cancelAppointment("Kamal"); // p5 should be assigned
            return manager.rescheduleQueue.size() == queueBefore - 1 && manager.cancellationHistory.size() == 2;
        });

        runTest(8, "Try to cancel non-existent appointment", () -> {
            int appointmentsBefore = manager.appointments.size();
            int historyBefore = manager.cancellationHistory.size();
            manager.cancelAppointment("Unknown"); // should fail gracefully
            return manager.appointments.size() == appointmentsBefore && manager.cancellationHistory.size() == historyBefore;
        });

        runTest(9, "Cancel remaining appointment (Sunil)", () -> {
            int appointmentsBefore = manager.appointments.size();
            manager.cancelAppointment("Sunil");
            return manager.appointments.size() == appointmentsBefore - 1 && manager.cancellationHistory.size() == 3;
        });

        runTest(10, "Book remaining slot (now available again)", () -> {
            int appointmentsBefore = manager.appointments.size();
            manager.bookAppointment(p6, d1, "11:00AM");
            return manager.appointments.size() == appointmentsBefore + 1;
        });

        // Final Appointments
        System.out.println("\n Final Scheduled Appointments:");
        manager.displayAppointments();
        
        // Test Results Summary
        printTestSummary();
    }
    
    private static void runTest(int testNumber, String testName, TestCase testCase) {
        totalTests++;
        System.out.println("\n Test Case " + testNumber + ": " + testName);
        System.out.println("".repeat(50));
        
        try {
            boolean result = testCase.run();
            if (result) {
                passedTests++;
                System.out.println(" PASSED: " + testName);
            } else {
                failedTests++;
                System.out.println(" FAILED: " + testName);
            }
        } catch (Exception e) {
            failedTests++;
            System.out.println(" ERROR: " + testName + " - " + e.getMessage());
        }
    }
    
    private static void printTestSummary() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println(" TEST RESULTS SUMMARY");
        System.out.println("=".repeat(60));
        System.out.println(" Total Tests: " + totalTests);
        System.out.println(" Passed: " + passedTests);
        System.out.println(" Failed: " + failedTests);
        System.out.println(" Success Rate: " + String.format("%.1f%%", (double) passedTests / totalTests * 100));
        
        if (failedTests == 0) {
            System.out.println("\n ALL TESTS PASSED! The appointment system is working correctly.");
        } else {
            System.out.println("\n  Some tests failed. Please check the implementation.");
        }
        System.out.println("=".repeat(60));
    }
    
    // Functional interface for test cases
    @FunctionalInterface
    interface TestCase {
        boolean run() throws Exception;
    }
}
