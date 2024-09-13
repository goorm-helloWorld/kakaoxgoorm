CREATE TABLE IF NOT EXISTS BOOK (
                                    id VARCHAR(255) PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(255),
    publisher VARCHAR(255),
    published_date DATE
    );


INSERT INTO BOOK (id, title, author, publisher, published_date)
VALUES
    ('1', 'Effective Java', 'Joshua Bloch', 'Addison-Wesley', '2018-05-08'),
    ('2', 'Clean Code', 'Robert C. Martin', 'Prentice Hall', '2008-08-01');
