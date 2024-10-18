package dao;

import entity.Appointment;
import exception.AppointmentNotFoundException;
import exception.PatientNumberNotFoundException;
import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of hospital services, providing functionality
 * to manage appointments, including scheduling, retrieving,
 * updating, and canceling appointments.
*/
public class HospitalServiceImpl implements IHospitalService {
    
    /**
     * Retrieves the next available appointment ID.
     * @return the next appointment ID to be used.
    */
    public int getNextAppointmentId() {

        Connection connection = DBConnection.getDBConn();
        int nextId = 1; // Default to 1 if no appointments exist

        try {

            String query = "SELECT MAX(appointmentId) AS maxId FROM Appointment";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nextId = rs.getInt("maxId") + 1;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return nextId;
    }

    /**
     * Retrieves a list of all appointments in the system.
     * @return a list of all appointments.
    */
    public List<Appointment> getAllAppointments() {

        Connection connection = DBConnection.getDBConn();
        List<Appointment> appointments = new ArrayList<>();

        try {

            String query = "SELECT * FROM Appointment";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Appointment appointment = new Appointment(
                    rs.getInt("appointmentId"),
                    rs.getInt("patientId"),
                    rs.getInt("doctorId"),
                    rs.getString("appointmentDate"),
                    rs.getString("description")
                );
                appointments.add(appointment);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    /**
     * Retrieves an appointment based on the provided appointment ID.
     * @param appointmentId the ID of the appointment to retrieve.
     * @return the appointment if found, or throws an exception if not found.
     * @throws AppointmentNotFoundException if no appointment with the given ID exists.
     */
    @Override
    public Appointment getAppointmentById(int appointmentId) {

        Connection connection = DBConnection.getDBConn();
        Appointment appointment = null;

        try {

            String query = "SELECT * FROM Appointment WHERE appointmentId = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, appointmentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                appointment = new Appointment(
                    rs.getInt("appointmentId"),
                    rs.getInt("patientId"),
                    rs.getInt("doctorId"),
                    rs.getString("appointmentDate"),
                    rs.getString("description")
                );
            }
            else {
                throw new AppointmentNotFoundException("Appointment with ID " + appointmentId + " not found.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return appointment;
    }

    /**
     * Retrieves a list of appointments for a specific patient.
     * @param patientId the ID of the patient.
     * @return a list of appointments for the given patient ID.
     * @throws PatientNumberNotFoundException if the patient does not exist.
    */
    @Override
    public List<Appointment> getAppointmentsForPatient(int patientId) {

        Connection connection = DBConnection.getDBConn();
        List<Appointment> appointments = new ArrayList<>();

        try {

            // Check if the patient exists
            if (!patientExists(patientId)) {
                throw new PatientNumberNotFoundException("Patient with ID " + patientId + " does not exist.");
            }

            String query = "SELECT * FROM Appointment WHERE patientId = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Appointment appointment = new Appointment(
                    rs.getInt("appointmentId"),
                    rs.getInt("patientId"),
                    rs.getInt("doctorId"),
                    rs.getString("appointmentDate"),
                    rs.getString("description")
                );
                appointments.add(appointment);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    /**
     * Helper method to check if a patient exists in the system.
     * @param patientId the ID of the patient to check.
     * @return true if the patient exists, false otherwise.
    */
    private boolean patientExists(int patientId) {

        Connection connection = DBConnection.getDBConn();

        try {

            String query = "SELECT patientID FROM Patient WHERE patientID = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            return rs.next();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves a list of appointments for a specific doctor.
     * @param doctorId the ID of the doctor.
     * @return a list of appointments for the given doctor ID.
    */
    @Override
    public List<Appointment> getAppointmentsForDoctor(int doctorId) {

        Connection connection = DBConnection.getDBConn();
        List<Appointment> appointments = new ArrayList<>();

        try {

            String query = "SELECT * FROM Appointment WHERE doctorId = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Appointment appointment = new Appointment(
                    rs.getInt("appointmentId"),
                    rs.getInt("patientId"),
                    rs.getInt("doctorId"),
                    rs.getString("appointmentDate"),
                    rs.getString("description")
                );
                appointments.add(appointment);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    /**
     * Schedules a new appointment.
     * @param appointment the appointment details to schedule.
     * @return true if the appointment was successfully scheduled, false otherwise.
    */
    @Override
    public boolean scheduleAppointment(Appointment appointment) {

        Connection connection = DBConnection.getDBConn();

        try {

            String query = "INSERT INTO Appointment (appointmentId, patientId, doctorId, appointmentDate, description) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            
            // Set all parameters
            stmt.setInt(1, appointment.getAppointmentId());
            stmt.setInt(2, appointment.getPatientId());
            stmt.setInt(3, appointment.getDoctorId());
            stmt.setString(4, appointment.getAppointmentDate());
            stmt.setString(5, appointment.getDescription());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Updates an existing appointment.
     * @param appointment the appointment details with updated information.
     * @return true if the appointment was successfully updated, false otherwise.
    */
    @Override
    public boolean updateAppointment(Appointment appointment) {

        Connection connection = DBConnection.getDBConn();

        try {

            String query = "UPDATE Appointment SET patientId = ?, doctorId = ?, appointmentDate = ?, description = ? WHERE appointmentId = ?";
            PreparedStatement stmt = connection.prepareStatement(query);

            // Set all parameters
            stmt.setInt(1, appointment.getPatientId());
            stmt.setInt(2, appointment.getDoctorId());
            stmt.setString(3, appointment.getAppointmentDate());
            stmt.setString(4, appointment.getDescription());
            stmt.setInt(5, appointment.getAppointmentId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Cancels an appointment based on the provided appointment ID.
     * @param appointmentId the ID of the appointment to cancel.
     * @return true if the appointment was successfully canceled, false otherwise.
    */
    @Override
    public boolean cancelAppointment(int appointmentId) {

        Connection connection = DBConnection.getDBConn();

        try {

            String query = "DELETE FROM Appointment WHERE appointmentId = ?";
            PreparedStatement stmt = connection.prepareStatement(query);

            // Set the parameter
            stmt.setInt(1, appointmentId);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}