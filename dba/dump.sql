-- Creating the Users Table
DROP TABLE IF EXISTS `users`;
CREATE TABLE users (
                       user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       first_name VARCHAR(50) NOT NULL UNIQUE,
                        last_name VARCHAR(50) NOT NULL UNIQUE,
                        user_name VARCHAR(15) DEFAULT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Creating the Expense Categories Table
DROP TABLE IF EXISTS `expense_categories`;
CREATE TABLE expense_categories (
                                    category_id INT AUTO_INCREMENT PRIMARY KEY,
                                    name VARCHAR(100) NOT NULL,
                                    description TEXT,
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Creating the Expenses Table
DROP TABLE IF EXISTS `expenses`;
CREATE TABLE expenses (
                          expense_id INT AUTO_INCREMENT PRIMARY KEY,
                          user_id INT,
                          category_id INT,
                          amount DECIMAL(10, 2) NOT NULL,
                          date DATE NOT NULL,
                          description TEXT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          FOREIGN KEY (user_id) REFERENCES users(user_id),
                          FOREIGN KEY (category_id) REFERENCES expense_categories(category_id)
);

-- Creating the Expense Reports Table
DROP TABLE IF EXISTS `expense_reports`;
CREATE TABLE expense_reports (
                                 report_id INT AUTO_INCREMENT PRIMARY KEY,
                                 user_id INT,
                                 start_date DATE,
                                 end_date DATE,
                                 total_expense DECIMAL(10, 2) NOT NULL,
                                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Inserting some sample data into Users table
INSERT INTO users (first_name, last_name, user_name, password, email)
VALUES
    ('Jenny', 'Smith', 'jsmith', 'jsmith01', 'jenny@gmail.com'),
    ('Princess', 'Adams', 'padams', 'padams11', 'adams@hotmail.com');

-- Inserting sample categories
INSERT INTO expense_categories (name, description)
VALUES
    ('Rent', 'Monthly rental payment'),
    ('Food', 'Groceries and meals'),
    ('Travel', 'Transportation and travel expenses'),
    ('Entertainment', 'Movies, games, and other leisure activities');

-- Inserting sample expenses
INSERT INTO expenses (user_id, category_id, amount, date, description)
VALUES
    (1, 1, 1200.00, '2025-02-01', 'February Rent'),
    (1, 2, 150.50, '2025-02-05', 'Groceries'),
    (2, 3, 100.00, '2025-02-10', 'Train fare to Chicago'),
    (2, 4, 50.00, '2025-02-12', 'Movie night with friends');

-- Inserting sample reports
INSERT INTO expense_reports (user_id, start_date, end_date, total_expense)
VALUES
    (1, '2025-02-01', '2025-02-28', 1350.50),
    (2, '2025-02-01', '2025-02-28', 250.00);