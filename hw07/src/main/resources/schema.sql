DROP TABLE IF EXISTS Books;
DROP TABLE IF EXISTS Authors;
DROP TABLE IF EXISTS Genres;
DROP TABLE IF EXISTS Comments;
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
CREATE TABLE Comments(
                         ID BIGINT auto_increment PRIMARY KEY,
                         Content VARCHAR(255),
                         BookID int NOT NULL,
                         FOREIGN KEY (BookID) REFERENCES Books(ID)
);