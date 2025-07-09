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
DoctorChannelingSystem/
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
│   ├── AppointmentManagerTest.java  # Comprehensive unit tests
│   ├── AppointmentManagerTestDemo.java # Demo test runner
│   ├── demo_input.txt              # Clean demo input data
│   └── README.md                   # Test documentation
├── bin/                           # Compiled classes (auto-generated)
├── build.sh                       # Build and run script
└── README.md                      # This file
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
- **HashSet**: For efficient duplicate detection and patient management

### CSV Data Management
- **Silent Operations**: CSV saving runs quietly during normal operations
- **Verbose Mode**: Detailed CSV information available via CSV Data Management menu
- **Automatic Backup**: Data saved after every operation for data integrity
- **Structured Format**: Professional CSV formatting with headers and proper escaping

## 🚀 How to Use

### Quick Start
```bash
# Make build script executable (if needed)
chmod +x build.sh

# Compile and run the system
./build.sh run

# Run with demo data
./build.sh test

# Run unit tests
./build.sh run-tests

# Clean build artifacts
./build.sh clean
```

### Manual Compilation
```bash
# Compile all source files
javac -d bin src/*.java

# Run the application
java -cp bin Main

# Run with demo input
java -cp bin Main < tests/demo_input.txt
```

### System Menu Options
1. **👨‍⚕️ Register Doctors** - Add new doctors with specializations and time slots
2. **🏥 Register Patients** - Add new patients with medical history
3. **📋 View Available Doctors** - Display all doctors and their available time slots
4. **📅 Book Appointments** - Book appointments with automatic conflict handling
5. **❌ Cancel Appointments** - Cancel appointments with automatic rescheduling
6. **📊 View All Appointments** - Display current appointment schedule
7. **📈 System Status** - Show system statistics and queue/stack status
8. **💾 CSV Data Management** - Manage CSV files and view data locations
9. **🚪 Exit** - Exit the application with automatic data saving

## 🧪 Testing

### Unit Tests
- **Comprehensive Test Suite**: 10 test cases covering all functionality
- **100% Success Rate**: All tests pass with full feature coverage
- **Automated Testing**: Run tests with `./build.sh run-tests`

### Demo Mode
- **Pre-configured Data**: Use `./build.sh test` for demo with sample data
- **Interactive Testing**: Use `./build.sh run` for manual testing

### Test Coverage
- Doctor registration and management
- Patient appointment booking
- Time slot conflict handling
- Queue-based reschedule system
- Stack-based cancellation history
- CSV data persistence
- Error handling and validation
- Automatic CSV file generation and updates
- Organized data storage in dedicated directory
- Real-time data saving after every operation
- Configuration file documentation

### ✅ Advanced Features
- Queue management for appointment rescheduling
- Stack-based cancellation history
- System status reporting
- CSV data management menu

## Quick Start

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command line interface

### Compilation
```bash
javac *.java
```

### Run the Application
## 🔧 Technical Specifications

### System Requirements
- **Language**: Java 8 or higher
- **Platform**: Cross-platform (Windows, macOS, Linux)
- **Dependencies**: None (uses standard Java libraries)

### Architecture
- **Design Pattern**: Object-oriented design with separation of concerns
- **Data Storage**: CSV files for persistence
- **Data Structures**: Queue (FIFO), Stack (LIFO), ArrayList, HashSet
- **Error Handling**: Comprehensive exception handling and input validation

### Performance Features
- **Memory Efficient**: Uses appropriate data structures for optimal memory usage
- **Fast Operations**: Efficient appointment booking and cancellation
- **Scalable**: Architecture supports easy extension and modification
- **Data Integrity**: Automatic data persistence with each operation

## 📊 System Statistics

### Latest Test Results
- **Total Tests**: 10
- **Passed**: 10
- **Failed**: 0
- **Success Rate**: 100%

### Key Metrics
- **Core Classes**: 6 (Main, AppointmentManager, Doctor, Patient, Appointment, CSVManager)
- **Data Structures**: 4 (Queue, Stack, ArrayList, HashSet)
- **CSV Files**: 4 (doctors.csv, patients.csv, appointments.csv, cancellations.csv)
- **Test Cases**: 10 comprehensive unit tests

## 🚀 Future Enhancements

### Possible Improvements
- **GUI Interface**: Convert to JavaFX or Swing GUI
- **Database Integration**: Replace CSV with database storage
- **Appointment Reminders**: Add email/SMS notification system
- **Multi-location Support**: Support for multiple hospital branches
- **Advanced Reporting**: Generate detailed reports and analytics

## � Notes

### Important Information
- **Data Backup**: All user data is automatically saved to CSV files
- **No Manual Editing**: Avoid manually editing CSV files - use the system interface
- **File Locations**: CSV files are stored in the `data/` directory
- **Test Data**: Use `demo_input.txt` for testing system functionality

### Development Notes
- **Clean Code**: Well-structured, commented code following Java best practices
- **Modular Design**: Easy to extend and maintain
- **Error Handling**: Robust error handling for all user inputs
- **Professional Output**: Clean, user-friendly console interface

---

**Author**: Hospital Appointment Management System  
**Version**: 1.0.0  
**Date**: July 2025  
**Status**: Complete and Tested
- **appointments.csv** - Active appointment records
- **cancellations.csv** - Cancelled appointment history for audit trail

### Data Security
- All data automatically saved after operations
- Organized file structure for easy backup
- Configuration documentation for data structure
- Plain text CSV format for universal compatibility

## Technical Architecture

### Core Classes
- **Main** - Application entry point and user interface
- **AppointmentManager** - Core business logic and appointment management
- **CSVManager** - Data persistence and CSV file operations
- **Doctor** - Doctor entity with specialization and time slots
- **Patient** - Patient entity with medical information
- **Appointment** - Appointment entity linking patients and doctors

### Data Structures
- **ArrayList** - Dynamic doctor and appointment lists
- **LinkedList** - Queue for appointment rescheduling
- **Stack** - Cancellation history tracking
- **HashMap** - Appointment grouping and organization

## Testing

### Unit Tests
```bash
# Compile and run tests
javac -cp . tests/AppointmentManagerTest.java
java -cp . tests.AppointmentManagerTest
```

### Integration Tests
```bash
# Test complete system workflow
java Main < tests/test_input.txt
```

## Documentation

- **docs/README_CSV_STORAGE.md** - Detailed CSV storage documentation
- **docs/README_INTERACTIVE.md** - Interactive system usage guide
- **tests/README.md** - Test files and testing procedures
- **data/README.txt** - Auto-generated data structure documentation

## Future Enhancements

- [ ] Data loading from CSV files on startup
- [ ] Database integration for enterprise deployment
- [ ] Web-based user interface
- [ ] Email notifications for appointments
- [ ] Report generation and analytics
- [ ] Multi-language support

## Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact

For questions or support, please contact the development team.

---

**Hospital Appointment Management System** - Making healthcare scheduling efficient and user-friendly.
