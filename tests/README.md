# Test Files Directory

This directory contains essential test files for the Hospital Appointment Management System.

## Directory Structure

```
tests/
├── AppointmentManagerTest.java         # Comprehensive unit test suite
├── AppointmentManagerTestDemo.java     # Demo test runner
├── demo_input.txt                      # Clean demo input for system testing
└── README.md                          # This documentation file
```

## Test Files Description

### Unit Tests
- **`AppointmentManagerTest.java`** - Comprehensive unit tests covering:
  - Doctor registration and management
  - Patient appointment booking
  - Time slot conflict handling
  - Queue and stack operations
  - CSV data persistence
  - Appointment cancellation and rescheduling

- **`AppointmentManagerTestDemo.java`** - Demo test runner for development testing

### Test Data
- **`demo_input.txt`** - Clean, production-ready demo input data for system testing

## Running Tests

### Compile and Run Unit Tests
```bash
# From the main project directory
./build.sh run-tests
```

### Run System Demo
```bash
# Run with demo data
./build.sh compile
java -cp bin Main < tests/demo_input.txt
```

### Manual Testing
```bash
# Run interactive system
./build.sh run
```

## Test Coverage

The test suite covers:
- ✅ Doctor registration and management
- ✅ Patient appointment booking  
- ✅ Time slot conflict handling
- ✅ Appointment cancellation
- ✅ Reschedule queue management
- ✅ Cancellation history tracking
- ✅ CSV data persistence
- ✅ System status reporting

## Demo Input Data

The `demo_input.txt` includes:
- **2 Doctors** with different specializations
- **2 Patient appointments** with realistic medical data
- **Complete workflow** from registration to appointment viewing

## Notes

- All test files are production-ready and clean
- Test data uses realistic medical scenarios
- Unit tests validate both success and error conditions
- Demo input provides a complete system walkthrough
