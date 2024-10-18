package entity;

/**
 * Represents an appointment in the hospital system.
 * Contains information such as appointment ID, patient ID, doctor ID,
 * date of the appointment, and a description of the appointment.
*/
public class Appointment {
    private int appointmentId;
    private int patientId;
    private int doctorId;
    private String appointmentDate;
    private String description;

    // Default constructor
    public Appointment() {}

    // Parameterized constructor
    public Appointment(int appointmentId, int patientId, int doctorId, String appointmentDate, String description) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.description = description;
    }

    /******************************* Getters and Setters *******************************/

    // Getter and Setter for appointmentId
    public int getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    // Getter and Setter for patientId
    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    // Getter and Setter for doctorId
    public int getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    // Getter and Setter for appointmentDate
    public String getAppointmentDate() {
        return appointmentDate;
    }
    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    // Getter and Setter for description
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // toString() method
    @Override
    public String toString() {
        return "Appointment [appointmentId=" + appointmentId + ", patientId=" + patientId +
               ", doctorId=" + doctorId + ", appointmentDate=" + appointmentDate +
               ", description=" + description + "]";
    }
}