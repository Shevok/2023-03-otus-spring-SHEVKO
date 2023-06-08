DROP TABLE IF EXISTS Books;
DROP TABLE IF EXISTS Authors;
DROP TABLE IF EXISTS Genres;
CREATE TABLE Authors(ID BIGINT auto_increment PRIMARY KEY, Name VARCHAR(255));
CREATE TABLE Genres(ID BIGINT auto_increment PRIMARY KEY, Name VARCHAR(255));
CREATE TABLE Books (
                       ID BIGINT auto_increment  PRIMARY KEY,
                       Name VARCHAR(255),
                       AuthorID int NOT NULL,
                       GenreID int NOT NULL,
                       FOREIGN KEY (AuthorID) REFERENCES Authors(ID),
                       FOREIGN KEY (GenreID) REFERENCES Genres(ID)
);