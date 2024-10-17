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

public class HospitalServiceImpl implements IHospitalService {
    
    public int getNextAppointmentId() {
        Connection connection = DBConnection.getDBConn();
        int nextId = 1;
        try {
            String query = "SELECT MAX(appointmentId) AS maxId FROM Appointment";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                nextId = rs.getInt("maxId") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextId;
    }

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

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
        } else {
            throw new AppointmentNotFoundException("Appointment with ID " + appointmentId + " not found.");
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointment;
    }

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    // Helper method to check if a patient exists
    private boolean patientExists(int patientId) {
        Connection connection = DBConnection.getDBConn();
        try {
            String query = "SELECT patientID FROM Patient WHERE patientID = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    @Override
    public boolean scheduleAppointment(Appointment appointment) {
        Connection connection = DBConnection.getDBConn();
        try {
            // Now include appointmentId in the insert statement
            String query = "INSERT INTO Appointment (appointmentId, patientId, doctorId, appointmentDate, description) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            
            // Set all parameters including appointmentId
            stmt.setInt(1, appointment.getAppointmentId());
            stmt.setInt(2, appointment.getPatientId());
            stmt.setInt(3, appointment.getDoctorId());
            stmt.setString(4, appointment.getAppointmentDate());
            stmt.setString(5, appointment.getDescription());
            
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateAppointment(Appointment appointment) {
        Connection connection = DBConnection.getDBConn();
        try {
            String query = "UPDATE Appointment SET patientId = ?, doctorId = ?, appointmentDate = ?, description = ? WHERE appointmentId = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
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

    @Override
    public boolean cancelAppointment(int appointmentId) {
        Connection connection = DBConnection.getDBConn();
        try {
            String query = "DELETE FROM Appointment WHERE appointmentId = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, appointmentId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}