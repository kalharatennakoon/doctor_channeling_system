public class Patient {
    private String patientId;
    private String name;
    private String mobile;
    private String email;
    private String city;
    private int age;
    private String medicalHistory;

    public Patient(String patientId, String name, String mobile, String email, String city, int age, String medicalHistory) {
        this.patientId = patientId;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.city = city;
        this.age = age;
        this.medicalHistory = medicalHistory;
    }

    public String getPatientId() { return patientId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getMobile() { return mobile; }
    public String getEmail() { return email; }
    public String getCity() { return city; }
    public String getMedicalHistory() { return medicalHistory; }

    @Override
    public String toString() {
        return name + " (" + age + ", " + city + ")";
    }
}






