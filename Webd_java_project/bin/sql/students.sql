-- Shan Patel
-- 100900902
-- 2024-02-02
-- This is the students.sql file for the student table.

-- Drop the table if it exists so that we can create a new one.
DROP TABLE IF EXISTS students;

-- Students table creation with foreign key reference to the users table.
-- Also can be done with just using this : studentID REFERENCES users(id) but using constraint is a better option.
CREATE TABLE students(
    id INT PRIMARY KEY NOT NULL,
    programCode VARCHAR(10) NOT NULL,
    programDescription VARCHAR(255) NOT NULL,
    year INT NOT NULL,
    CONSTRAINT fk_student_user FOREIGN KEY (student_id) REFERENCES users(id)
    -- question are we supposed to add marks value in this database or in the mark.sql file which we are not supposed to create for this existing assignment.
    -- marks DECIMAL(2,2) NOT NULL,
);

-- Inserting values into the students table.
INSERT INTO students (id, programCode, programDescription, year)
VALUES
(100111111, 'CSTY', 'Computer System Technology', '3'),
(100222222,'CLT','Chemical Lab Technician','2'),
(100900902, 'COMP','Computer Programming','2');



