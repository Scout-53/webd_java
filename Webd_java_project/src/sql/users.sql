-- Shan Patel
-- 100900902
-- 2024-02-02
-- This is the SQL file for the user table. It creates the table and inserts the values into the table.



-- Creating an extension of pgcrypto to use the encode function.
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Dropping the sequence if it exists
DROP SEQUENCE IF EXISTS user_id_seq CASCADE;

-- Just in case, we will start a user id sequence at 100000000 to not create any error later.
CREATE SEQUENCE user_id_seq START 100000000;


-- Create the user table.
DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id INT PRIMARY KEY DEFAULT nextval('user_id_seq'),
  password VARCHAR(40) NOT NULL,
  firstName VARCHAR(255) NOT NULL,  
  lastName VARCHAR(255) NOT NULL,
  emailAddress VARCHAR(255) UNIQUE NOT NULL,    
  lastAccess DATE NOT NULL,
  enrolDate DATE NOT NULL,
  enabled BOOLEAN NOT NULL,
  type CHAR(1) NOT NULL
);

-- Insert values to the table.
INSERT INTO users (id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type) 
    VALUES 
(100111111, ENCODE(DIGEST('password','sha1'), 'hex'), 'Mike', 'Jones', 'mike.jones@dcmail.ca', '2024-02-02', '2015-09-11', true, 's'),
(100222222, ENCODE(DIGEST('11111111','sha1'), 'hex'), 'John', 'Doe', 'john.doe@dcmail.ca', '2024-02-02', '2024-02-01', true, 's'),
(100900902, ENCODE(DIGEST('12345678','sha1'), 'hex'), 'Shan', 'Patel', 'shan.patel@dcmail.ca', '2024-02-02', '2024-01-28', true, 's'),
(100333333, ENCODE(DIGEST('22222222','sha1'), 'hex'), 'Lewis', 'Hamilton', 'Lewis.hamilton@dcmail.ca', '2024-02-02', '2024-01-27', true, 'f'),
(100444444, ENCODE(DIGEST('33333333','sha1'), 'hex'), 'Linda', 'Smiths', 'Linda.smiths@dcmail.ca', '2024-02-02', '2024-01-26', true, 'f'),
(100555555, ENCODE(DIGEST('44444444','sha1'), 'hex'), 'Laila', 'Johnson', 'Laila.johnson@dcmail.ca', '2024-02-02', '2024-01-25', true, 'f');


