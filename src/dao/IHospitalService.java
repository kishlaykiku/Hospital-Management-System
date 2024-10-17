package dao;

import entity.Appointment;
import java.util.List;

public interface IHospitalService {

    // Retrieve appointment by appointment ID
    Appointment getAppointmentById(int appointmentId);

    // Retrieve all appointments for a specific patient by patient ID
    List<Appointment> getAppointmentsForPatient(int patientId);

    // Retrieve all appointments for a specific doctor by doctor ID
    List<Appointment> getAppointmentsForDoctor(int doctorId);

    // Schedule a new appointment
    boolean scheduleAppointment(Appointment appointment);

    // Update an existing appointment
    boolean updateAppointment(Appointment appointment);

    // Cancel an appointment by appointment ID
    boolean cancelAppointment(int appointmentId);
}