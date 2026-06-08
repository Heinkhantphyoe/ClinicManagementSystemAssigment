package clinicmanagementsystem.model;

public class Patient {
    private String patientId;
    private String name;
    private String gender;
    private String phoneNumber;
    private String email;
    private int age;
    private String bloodType;
    private String address;
    private String emergencyContact;

    public Patient() {
    }

    public Patient(String patientId, String name, String gender, String phoneNumber, String email,
            int age, String bloodType, String address, String emergencyContact) {
        this.patientId = patientId;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.age = age;
        this.bloodType = bloodType;
        this.address = address;
        this.emergencyContact = emergencyContact;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    @Override
    public String toString() {
        return patientId + "," + name + "," + gender + "," + phoneNumber + "," + email + ","
                + age + "," + bloodType + "," + address + "," + emergencyContact;
    }
}
