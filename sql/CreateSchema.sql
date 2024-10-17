CREATE DATABASE hospital_db;

USE hospital_db;


CREATE TABLE Appointment (
    appointmentId INT PRIMARY KEY,
    patientId INT NOT NULL,
    doctorId INT NOT NULL,
    appointmentDate DATE NOT NULL,
    description TEXT
);

CREATE TABLE Patient (
    patientID INT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    dateOfBirth DATE NOT NULL,
    gender ENUM('M', 'F') NOT NULL,
    contactNumber VARCHAR(10) NOT NULL,
    address TEXT NOT NULL
)

CREATE TABLE Doctor (
    doctorID INT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    specialization VARCHAR(50) NOT NULL,
    contactNumber VARCHAR(10) NOT NULL
);

DROP TABLE Appointment;
DROP TABLE Patient;
DROP TABLE Doctor;

SELECT * FROM Appointment;
SELECT * FROM Patient;
SELECT * FROM Doctor;