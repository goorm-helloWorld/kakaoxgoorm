DROP TABLE IF EXISTS book;

CREATE TABLE book
(
    id LONG PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    publisher VARCHAR(100) NOT NULL
);
