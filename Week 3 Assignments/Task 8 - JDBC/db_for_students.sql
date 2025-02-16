-- create a database
CREATE DATABASE IF NOT EXISTS jdbc_tasks;
USE jdbc_tasks;

-- creating table to add data
CREATE TABLE IF NOT EXISTS students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    course VARCHAR(100) NOT NULL
);

-- inserting data
INSERT INTO students (name, age, course) VALUES 
('Leon', 21, 'AI'),
('Ada', 20, 'CSBS'),
('Jill', 20, 'CSE');
