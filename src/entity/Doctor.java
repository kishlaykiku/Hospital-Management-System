package entity;

/**
 * Represents a doctor in the hospital system.
 * Contains information such as doctor ID, name, specialization,
 * and contact details.
*/
public class Doctor {
    private int doctorId;
    private String firstName;
    private String lastName;
    private String specialization;
    private String contactNumber;

    // Default constructor
    public Doctor() {}

    // Parameterized constructor
    public Doctor(int doctorId, String firstName, String lastName, String specialization, String contactNumber) {
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.contactNumber = contactNumber;
    }

    /******************************* Getters and Setters *******************************/

    // Getter and Setter for doctorId
    public int getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    // Getter and Setter for firstName
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter and Setter for lastName
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter and Setter for specialization
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    // Getter and Setter for contactNumber
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    // toString() method
    @Override
    public String toString() {
        return "Doctor [doctorId=" + doctorId + ", firstName=" + firstName + ", lastName=" + lastName +
               ", specialization=" + specialization + ", contactNumber=" + contactNumber + "]";
    }
}