package test;

import dao.HospitalServiceImpl;
import entity.Appointment;

import java.util.List;

public class HospitalServiceTest {
    public static void main(String[] args) {
        HospitalServiceImpl service = new HospitalServiceImpl();

        int nextId = service.getNextAppointmentId();
    
        // Test adding an appointment
        Appointment newAppointment = new Appointment(nextId, 1, 2, "2024-10-20 14:00", "Check-up");
        if (service.scheduleAppointment(newAppointment)) {
            System.out.println("Appointment scheduled successfully.");
        } else {
            System.out.println("Failed to schedule appointment.");
        }

        // Test fetching an appointment by ID
        Appointment appointment = service.getAppointmentById(1);
        if (appointment != null) {
            System.out.println("Fetched Appointment: " + appointment);
        }

        // Test fetching appointments for a patient
        List<Appointment> patientAppointments = service.getAppointmentsForPatient(1);
        for (Appointment appt : patientAppointments) {
            System.out.println(appt);
        }

        // Test updating an appointment
        newAppointment.setDescription("Updated description");
        newAppointment.setAppointmentId(1);  // Assuming this ID exists
        if (service.updateAppointment(newAppointment)) {
            System.out.println("Appointment updated successfully.");
        }

        // Test deleting an appointment
        if (service.cancelAppointment(1)) {
            System.out.println("Appointment canceled successfully.");
        }
    }
}