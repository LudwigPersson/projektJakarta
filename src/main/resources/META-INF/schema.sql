CREATE DATABASE movies;

CREATE TABLE movie (
                     id INT AUTO_INCREMENT PRIMARY KEY,
                     uuid BINARY(16),
                     title VARCHAR(100),
                     releaseYear INT,
                     director VARCHAR(50),
                     rating FLOAT,
                     genre VARCHAR(20)
); 
DROP TABLE movie;
SELECT * from movie;
