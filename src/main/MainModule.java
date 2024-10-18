package main;

import dao.HospitalServiceImpl;
import entity.Appointment;
import exception.AppointmentNotFoundException;
import exception.InvalidInputException;
import exception.PatientNumberNotFoundException;
import exception.DatabaseConnectionException;

import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Main module for the Hospital Management System.
 * Provides a menu-driven interface for users to manage appointments,
 * including adding, viewing, updating, and canceling appointments.
*/
public class MainModule {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        HospitalServiceImpl hospitalService = new HospitalServiceImpl();

        while (true) {

            System.out.println("\n=== Hospital Management System ===");
            System.out.println("1. Add New Appointment");
            System.out.println("2. View All Appointments");
            System.out.println("3. View a Particular Appointment by ID");
            System.out.println("4. Search Appointments by Patient ID");
            System.out.println("5. Search Appointments by Doctor ID");
            System.out.println("6. Update Appointment");
            System.out.println("7. Cancel Appointment");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice;

            try {

                choice = Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 7.");
                continue;
            }

            try {

                switch (choice) {

                    case 1:
                        // Add New Appointment with Validation
                        int nextId = hospitalService.getNextAppointmentId();

                        System.out.println("New Appointment ID: " + nextId);

                        System.out.print("Enter Patient ID: ");
                        int patientId = validateIntegerInput(scanner);

                        System.out.print("Enter Doctor ID: ");
                        int doctorId = validateIntegerInput(scanner);

                        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                        String appointmentDate = validateDateInput(scanner);
                        scanner.nextLine();

                        System.out.print("Enter Description: ");
                        String description = scanner.nextLine();

                        Appointment newAppointment = new Appointment(nextId, patientId, doctorId, appointmentDate, description);

                        if (hospitalService.scheduleAppointment(newAppointment)) {

                            System.out.println("Appointment added successfully.");
                        }
                        else {

                            System.out.println("Failed to add appointment. Please check if the Patient ID and Doctor ID are correct.");
                        }
                        break;

                    case 2:
                        // View All Appointments
                        List<Appointment> allAppointments = hospitalService.getAllAppointments();

                        if (allAppointments.isEmpty()) {

                            System.out.println("No appointments found.");
                        }
                        else {

                            for (Appointment appointment : allAppointments) {

                                System.out.println(appointment);
                            }
                        }
                        break;

                    case 3:
                        // View a Particular Appointment by ID
                        System.out.print("Enter Appointment ID: ");
                        int appointmentId = validateIntegerInput(scanner);

                        Appointment specificAppointment = hospitalService.getAppointmentById(appointmentId);

                        if (specificAppointment == null) {

                            System.out.println("Appointment not found.");
                        }
                        else {

                            System.out.println(specificAppointment);
                        }
                        break;

                    case 4:
                        // Search Appointments by Patient ID
                        System.out.print("Enter Patient ID: ");
                        int searchPatientId = validateIntegerInput(scanner);

                        try {

                            List<Appointment> appointmentsByPatient = hospitalService.getAppointmentsForPatient(searchPatientId);

                            if (appointmentsByPatient.isEmpty()) {

                                System.out.println("No appointments found for this patient.");
                            }
                            else {

                                for (Appointment appointment : appointmentsByPatient) {

                                    System.out.println(appointment);
                                }
                            }
                        }
                        catch (PatientNumberNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 5:
                        // Search Appointments by Doctor ID
                        System.out.print("Enter Doctor ID: ");
                        int searchDoctorId = validateIntegerInput(scanner);

                        List<Appointment> appointmentsByDoctor = hospitalService.getAppointmentsForDoctor(searchDoctorId);

                        if (appointmentsByDoctor.isEmpty()) {

                            System.out.println("No appointments found for this doctor.");
                        }
                        else {

                            for (Appointment appointment : appointmentsByDoctor) {

                                System.out.println(appointment);
                            }
                        }
                        break;

                    case 6:
                        // Update Appointment
                        System.out.print("Enter Appointment ID to Update: ");
                        int updateId = validateIntegerInput(scanner);

                        Appointment existingAppointment = hospitalService.getAppointmentById(updateId);

                        if (existingAppointment == null) {
                            throw new AppointmentNotFoundException("Appointment not found for ID: " + updateId);
                        }

                        System.out.print("Enter New Patient ID: ");
                        int newPatientId = validateIntegerInput(scanner);

                        System.out.print("Enter New Doctor ID: ");
                        int newDoctorId = validateIntegerInput(scanner);

                        System.out.print("Enter New Appointment Date (YYYY-MM-DD): ");
                        String newAppointmentDate = validateDateInput(scanner);
                        scanner.nextLine();

                        System.out.print("Enter New Description: ");
                        String newDescription = scanner.nextLine();

                        Appointment updatedAppointment = new Appointment(updateId, newPatientId, newDoctorId, newAppointmentDate, newDescription);

                        if (hospitalService.updateAppointment(updatedAppointment)) {

                            System.out.println("Appointment updated successfully.");
                        }
                        else {

                            System.out.println("Failed to update appointment.");
                        }
                        break;

                    case 7:
                        // Cancel Appointment
                        System.out.print("Enter Appointment ID to Cancel: ");
                        int cancelId = validateIntegerInput(scanner);

                        if (hospitalService.cancelAppointment(cancelId)) {

                            System.out.println("Appointment canceled successfully.");
                        }
                        else {

                            System.out.println("Failed to cancel appointment.");
                        }
                        break;

                    case 8:
                        System.out.println("Exiting...");

                        // Close the scanner and exit the program
                        scanner.close();
                        System.exit(0);

                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
            catch (AppointmentNotFoundException e) {
                System.out.println(e.getMessage());
            }
            catch (InvalidInputException e) {
                System.out.println("Invalid input! " + e.getMessage());
            }
            catch (DatabaseConnectionException e) {
                System.out.println("Database error: " + e.getMessage());
            }
            catch (Exception e) {
                System.out.println("An unexpected error occurred.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Helper method to validate integer input from the user.
     * Ensures that the user provides a valid integer.
     * 
     * @param scanner the Scanner object for reading user input
     * @return a valid integer input from the user
    */
    private static int validateIntegerInput(Scanner scanner) {

        while (true) {

            try {

                return Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a valid number: ");
            }
        }
    }

    /**
     * Helper method to validate date input from the user.
     * Ensures that the user provides a valid date in the format YYYY-MM-DD.
     * 
     * @param scanner the Scanner object for reading user input
     * @return a valid date string in the format YYYY-MM-DD
    */
    private static String validateDateInput(Scanner scanner) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        while (true) {

            String dateStr = scanner.nextLine();

            try {

                Date date = dateFormat.parse(dateStr);
                return dateFormat.format(date);
            }
            catch (Exception e) {
                System.out.print("Invalid date! Please enter a date in the format YYYY-MM-DD: ");
            }
        }
    }
}