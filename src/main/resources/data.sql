CREATE TABLE IF NOT EXISTS book (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    isbn VARCHAR(17) UNIQUE NOT NULL,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    stock INT
    );

INSERT INTO book (isbn, title, author, price, stock) VALUES
('978-0134685991', 'Effective Java', 'Joshua Bloch', 45.00, 10),
('978-0136083238', 'Clean Code', 'Robert C. Martin', 40.00, 5),
('978-0201616224', 'The Pragmatic Programmer', 'Andrew Hunt', 50.00, 7),
('978-0596009205', 'Head First Java', 'Kathy Sierra', 35.00, 15),
('978-1617294945', 'Spring in Action', 'Craig Walls', 30.00, 8);