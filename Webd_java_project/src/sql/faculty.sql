-- Shan Patel
-- 2nd Feb, 2024
-- This is the faculty.sql file for the faculty table in the database. It is connected to the user table through the faculty_id via foreign key constraint.

-- Shan Patel
-- 100900902
-- 2024-02-02
-- This is the faculty.sql file for the faculty table.


-- Drop the table faculty if it exists
DROP TABLE IF EXISTS faculty;

-- Create the table faculty
CREATE TABLE faculty(
    id INT PRIMARY KEY NOT NULL,
    schoolCode VARCHAR(10) NOT NULL,
    schoolDescription VARCHAR(255) NOT NULL,
    office VARCHAR(6) NOT NULL,
    extension INT NOT NULL,
    CONSTRAINT fk_faculty_user FOREIGN KEY (id) REFERENCES users(id)
);

-- Insert data into the table faculty
INSERT INTO faculty (faculty_id, schoolCode, schoolDescription, office, extension) 
VALUES 
(100333333, 'DC','Durham College','A123', 1234),
(100444444, 'UIT','Ontario Tech University','B123', 5678),
(100555555, 'TU','Trent University','C123', 9101);
