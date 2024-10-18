-- Inserted Sample Data into 'Patient' Table
INSERT INTO Patient (patientID, firstName, lastName, dateOfBirth, gender, contactNumber, address)
VALUES
(1, 'John', 'Doe', '1990-05-14', 'M', '1234567890', '123 Elm St'),
(2, 'Jane', 'Smith', '1985-08-23', 'F', '2345678901', '456 Oak St'),
(3, 'Michael', 'Johnson', '1978-01-30', 'M', '3456789012', '789 Pine St'),
(4, 'Emily', 'Brown', '1992-12-15', 'F', '4567890123', '321 Maple St'),
(5, 'Chris', 'Davis', '1988-04-22', 'M', '5678901234', '654 Birch St'),
(6, 'Sarah', 'Miller', '1995-03-10', 'F', '6789012345', '987 Cedar St'),
(7, 'James', 'Wilson', '1980-11-05', 'M', '7890123456', '147 Willow St'),
(8, 'Jessica', 'Taylor', '1993-07-19', 'F', '8901234567', '258 Poplar St'),
(9, 'David', 'Anderson', '1987-09-02', 'M', '9012345678', '369 Spruce St'),
(10, 'Laura', 'Thomas', '1991-06-25', 'F', '0123456789', '123 Walnut St');

-- Inserted Sample Data into 'Doctor' Table
INSERT INTO Doctor (doctorID, firstName, lastName, specialization, contactNumber)
VALUES
(1, 'Dr. Alice', 'Carter', 'Cardiology', '1122334455'),
(2, 'Dr. Bob', 'Evans', 'Dermatology', '2233445566'),
(3, 'Dr. Carol', 'Foster', 'Pediatrics', '3344556677'),
(4, 'Dr. Daniel', 'Green', 'Neurology', '4455667788'),
(5, 'Dr. Eva', 'Hill', 'Orthopedics', '5566778899'),
(6, 'Dr. Frank', 'Irving', 'Oncology', '6677889900'),
(7, 'Dr. Grace', 'Jones', 'Gynecology', '7788990011'),
(8, 'Dr. Henry', 'King', 'Psychiatry', '8899001122'),
(9, 'Dr. Irene', 'Lee', 'Urology', '9900112233'),
(10, 'Dr. Jack', 'Martin', 'General Surgery', '1011223344');

-- Inserted Sample Data into 'Appointment' Table
INSERT INTO Appointment (appointmentId, patientId, doctorId, appointmentDate, description)
VALUES
(1, 1, 2, '2024-10-21', 'Skin check-up'),
(2, 3, 1, '2024-10-22', 'Cardiology consultation'),
(3, 4, 3, '2024-10-23', 'Pediatric follow-up'),
(4, 5, 4, '2024-10-24', 'Neurology consultation'),
(5, 2, 5, '2024-10-25', 'Orthopedic assessment'),
(6, 6, 6, '2024-10-26', 'Oncology treatment discussion'),
(7, 8, 7, '2024-10-27', 'Gynecological check-up'),
(8, 7, 8, '2024-10-28', 'Psychiatric evaluation'),
(9, 10, 9, '2024-10-29', 'Urology appointment'),
(10, 9, 10, '2024-10-30', 'Pre-surgery consultation');