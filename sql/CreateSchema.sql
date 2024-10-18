-- Created the database 'hospital_db'
CREATE DATABASE hospital_db;


-- Switched to using the 'hospital_db' database
USE hospital_db;


/********************************************** Table Structure Definitions **********************************************/

-- Created the 'Appointment' table
CREATE TABLE Appointment (
    appointmentId INT PRIMARY KEY,
    patientId INT NOT NULL,
    doctorId INT NOT NULL,
    appointmentDate DATE NOT NULL,
    description TEXT
);

-- Create the 'Patient' table
CREATE TABLE Patient (
    patientID INT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    dateOfBirth DATE NOT NULL,
    gender ENUM('M', 'F') NOT NULL,
    contactNumber VARCHAR(10) NOT NULL,
    address TEXT NOT NULL
)

-- Create the 'Doctor' table
CREATE TABLE Doctor (
    doctorID INT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    specialization VARCHAR(50) NOT NULL,
    contactNumber VARCHAR(10) NOT NULL
);


-- Drop tables if needed to reset the schema
DROP TABLE Appointment;
DROP TABLE Patient;
DROP TABLE Doctor;

-- Retrieve all records from the tables
SELECT * FROM Appointment;
SELECT * FROM Patient;
SELECT * FROM Doctor;