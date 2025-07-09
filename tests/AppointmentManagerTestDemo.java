package tests;

import java.util.*;

public class AppointmentManagerTestDemo {
    private static int totalTests = 0;
    private static int passedTests = 0;
    private static int failedTests = 0;
    
    public static void main(String[] args) {
        System.out.println(" APPOINTMENT MANAGER TEST SUITE - DEMO (with intentional failures)");
        System.out.println("====================================================================");
        
        AppointmentManager manager = new AppointmentManager();

        List<String> slots = Arrays.asList("09:00AM", "10:00AM", "11:00AM");
        Doctor d1 = new Doctor("D001", "REG001", "Dr. Fernando Silva", "Dermatology", new ArrayList<>(slots), 1500);
        manager.registerDoctor(d1);

        // Register patients
        Patient p1 = new Patient("PAT001", "Suresh", "0711111111", "suresh@gmail.com", "Colombo", 45, "Allergies");
        Patient p2 = new Patient("PAT002", "Kamal", "0722222222", "kamal@gmail.com", "Kandy", 30, "Cold");
        Patient p3 = new Patient("PAT003", "Sunil", "0733333333", "sunil@gmail.com", "Galle", 60, "Flu");
        Patient p4 = new Patient("PAT004", "Nadeesha", "0744444444", "nadeesha@gmail.com", "Jaffna", 28, "Cough");

        // Test Cases - some with intentional failures for demo
        runTest(1, "Book valid slot", () -> {
            manager.bookAppointment(p1, d1, "09:00AM");
            return manager.appointments.size() == 1; // Should pass
        });

        runTest(2, "Book another valid slot (intentional fail)", () -> {
            manager.bookAppointment(p2, d1, "10:00AM");
            return manager.appointments.size() == 5; // Should fail - expecting wrong number
        });

        runTest(3, "Book final available slot", () -> {
            manager.bookAppointment(p3, d1, "11:00AM");
            return manager.appointments.size() == 3; // Should pass
        });

        runTest(4, "Try to book already taken slot (intentional fail)", () -> {
            manager.bookAppointment(p4, d1, "09:00AM"); // should queue
            return manager.rescheduleQueue.size() == 5; // Should fail - expecting wrong queue size
        });

        runTest(5, "Exception handling test", () -> {
            // This will cause an exception
            throw new RuntimeException("Simulated error");
        });

        // Test Results Summary
        printTestSummary();
    }
    
    private static void runTest(int testNumber, String testName, TestCase testCase) {
        totalTests++;
        System.out.println("\n Test Case " + testNumber + ": " + testName);
        System.out.println("─".repeat(60));
        
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
        System.out.println("📈 Success Rate: " + String.format("%.1f%%", (double) passedTests / totalTests * 100));
        
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
