# Hospital Management System

A simple Hospital Management System built in Java, designed to manage appointments for patients and doctors. It provides functionality for adding, viewing, updating, and canceling appointments using a menu-driven interface.

## Features
- **Add New Appointment**: Schedule new appointments by specifying patient and doctor details.
- **View All Appointments**: List all scheduled appointments.
- **View Appointment by ID**: Retrieve details of a specific appointment using its ID.
- **Search by Patient ID**: Find all appointments for a particular patient.
- **Search by Doctor ID**: Find all appointments for a particular doctor.
- **Update Appointment**: Modify details of an existing appointment.
- **Cancel Appointment**: Remove an appointment from the system.

## Prerequisites
- **Java 8+**: Make sure Java is installed and added to your system's PATH.
- **MySQL**: Set up a MySQL server to host the hospital database.
- **MySQL Connector/J**: Ensure `mysql-connector-j.jar` is added to your classpath.

## Setup
1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/HospitalManagementSystem.git
   cd 'Hospital Management System'
   ```

2. **Set Up Database**

    ```bash
    Execute the SQL scripts in the /sql folder:
    CreateSchema.sql to create tables.
    InsertData.sql to insert sample data.
    ```

3. **Configure Database Connection**

    ```bash
    hostname = localhost
    port = 3306
    dbname = hospital_db
    username = your_username
    password = your_password
    ```

4. Compile and Run the Project

    ```bash
    javac -cp .;mysql-connector-j.jar src/main/MainModule.java
    java -cp .;mysql-connector-j.jar src/main/MainModule
    ```
<hr>
<hr>