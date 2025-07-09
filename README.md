# Hospital Appointment Management System

A comprehensive Java-based hospital appointment management system with interactive user interface, CSV data persistence, and robust appointment scheduling capabilities.

## 🎯 Project Overview

This system implements a complete hospital appointment management solution featuring:
- **Queue-based reschedule system** (FIFO) for managing appointment conflicts
- **Stack-based cancellation history** (LIFO) for tracking cancelled appointments
- **CSV data persistence** for all user data
- **Interactive menu system** with professional console interface
- **Comprehensive error handling** and input validation

## 📁 Project Structure

```
doctor_channeling_system/
├── src/                           # Source code directory
│   ├── Main.java                  # Main application entry point
│   ├── AppointmentManager.java    # Core appointment management logic
│   ├── CSVManager.java           # CSV file handling and data persistence
│   ├── Doctor.java               # Doctor entity class
│   ├── Patient.java              # Patient entity class
│   └── Appointment.java          # Appointment entity class
├── data/                          # CSV data storage directory
│   ├── doctors.csv               # Doctor information and available slots
│   ├── patients.csv              # Patient details and medical history
│   ├── appointments.csv          # Active appointment records
│   ├── cancellations.csv         # Cancelled appointment history
│   └── README.txt               # Auto-generated data documentation
├── tests/                         # Test files and test data
│   ├── AppointmentManagerTest.java     # Comprehensive unit tests (10/10 passing)
│   ├── AppointmentManagerTestDemo.java # Demo test runner (3 pass, 2 fail)
│   └── README.md                       # Test documentation
├── bin/                           # Compiled classes (auto-generated)
├── .gitignore                     # Git ignore configuration
└── README.md                      # Project documentation
```

## ✅ Features

### Core System Features
- ✅ **Doctor Registration**: Register doctors with specializations, consultation fees, and available time slots
- ✅ **Patient Management**: Register patients with detailed medical history and contact information
- ✅ **Appointment Booking**: Book appointments with automatic conflict detection and resolution
- ✅ **Queue-based Rescheduling**: FIFO queue system for managing appointment conflicts
- ✅ **Cancellation Management**: Stack-based (LIFO) cancellation history with automatic rescheduling
- ✅ **CSV Data Persistence**: All data automatically saved to CSV files for easy retrieval
- ✅ **Interactive Menu System**: Professional console interface with clear navigation
- ✅ **Real-time Status**: Live appointment status tracking and system monitoring

### Data Structures Used
- **Queue (FIFO)**: For reschedule requests - first come, first served
- **Stack (LIFO)**: For cancellation history - last cancelled, first retrieved
- **ArrayList**: For dynamic storage of doctors, patients, and appointments
- **HashMap**: For efficient appointment grouping and display

### CSV Data Management
- **Automatic Operations**: CSV saving runs automatically after every operation
- **Verbose Mode**: Detailed CSV information available via CSV Data Management menu
- **Data Integrity**: Data saved after every operation for data integrity
- **Structured Format**: Professional CSV formatting with headers and proper escaping

## 🚀 How to Use

### Quick Start
```bash
# Compile all source files
javac -cp src src/*.java

# Run the application
java -cp src Main

# Run unit tests
javac -cp src tests/AppointmentManagerTest.java
java -cp src:tests AppointmentManagerTest

# Run demo tests (with intentional failures)
javac -cp src tests/AppointmentManagerTestDemo.java
java -cp src:tests AppointmentManagerTestDemo
```

### System Menu Options
1. **👨‍⚕️ Register Doctors** - Add new doctors with specializations and time slots
2. **🏥 Register Patients** - Add new patients with medical history
3. **📋 View Available Doctors** - Display all doctors and their available time slots
4. **📅 Book Appointments** - Book appointments with automatic conflict handling
5. **❌ Cancel Appointments** - Cancel appointments with automatic rescheduling
6. **📊 View All Appointments** - Display current appointment schedule
7. **📈 View Cancellation History** - Show cancelled appointment history
8. **💻 System Status** - Show system statistics and queue/stack status
9. **💾 CSV Data Management** - Manage CSV files and view data locations
10. **🚪 Exit** - Exit the application with automatic data saving

## 🧪 Testing

### Unit Tests
- **Comprehensive Test Suite**: 10 test cases covering all functionality in `AppointmentManagerTest.java`
- **100% Success Rate**: All tests pass with full feature coverage
- **Demo Test Suite**: 5 test cases in `AppointmentManagerTestDemo.java` (3 pass, 2 intentional failures)

### Test Coverage
- Doctor registration and ID generation
- Patient appointment booking and conflict handling
- Time slot availability checking
- Queue-based reschedule system testing
- Stack-based cancellation history validation
- CSV data persistence verification
- Error handling and edge case testing

## 🔧 Technical Specifications

### Requirements
- **Java Development Kit (JDK)**: Version 8 or higher
- **Operating System**: Cross-platform (Windows, macOS, Linux)
- **Memory**: Minimum 256MB RAM
- **Storage**: 50MB for application and data files

### Data Management
- **Automatic ID Generation**: DOC### for doctors, PAT### for patients, APT### for appointments
- **Data Validation**: Input validation and duplicate checking
- **File Structure**: Organized CSV files in dedicated data/ directory
- **Backup Strategy**: Real-time data saving after every operation

### System Capabilities
- **Concurrent Appointments**: Handles multiple appointments per doctor
- **Conflict Resolution**: Automatic queue management for time slot conflicts
- **History Tracking**: Complete cancellation history with stack-based retrieval
- **Status Monitoring**: Real-time system statistics and performance metrics

## 📝 Usage Notes

### Getting Started
1. Compile the project using the commands above
2. Run the main application
3. Register doctors first to make appointments available
4. Register patients or book appointments directly
5. Use the CSV Data Management menu to export or view data

### Best Practices
- Register doctors before booking appointments
- Use the system status option to monitor queue and history
- Regularly check available doctors for optimal scheduling
- Use cancellation feature to free up slots for rescheduling

### Data Files
All data is automatically stored in the `data/` directory:
- System generates CSV files automatically
- Data persists between application runs
- Manual CSV management available through menu option 9

---

**Developed by**: Hospital Management System Team  
**Version**: 1.0  
**Last Updated**: July 2025
