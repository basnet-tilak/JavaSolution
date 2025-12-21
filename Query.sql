-- =====================================================
-- SQL Interview Questions - Complete Implementation
-- =====================================================

-- Create Database
DROP DATABASE IF EXISTS interview_db;
CREATE DATABASE interview_db;
USE interview_db;

-- =====================================================
-- 1. DATABASE SCHEMA CREATION
-- =====================================================

-- Departments Table
CREATE TABLE departments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(100) NOT NULL,
    location VARCHAR(100),
    budget DECIMAL(15,2)
);

-- Employees Table
CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    department VARCHAR(50),
    dept_id INT,
    salary DECIMAL(10,2),
    hire_date DATE,
    manager_id INT,
    age INT CHECK (age >= 18),
    status VARCHAR(20) DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (dept_id) REFERENCES departments(id),
    FOREIGN KEY (manager_id) REFERENCES employees(id)
);

-- Products Table
CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    price DECIMAL(10,2),
    stock_quantity INT DEFAULT 0
);

-- Customers Table
CREATE TABLE customers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(20),
    city VARCHAR(50)
);

-- Orders Table
CREATE TABLE orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    order_date DATE,
    total DECIMAL(10,2),
    status VARCHAR(20) DEFAULT 'PENDING',
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

-- Order Items Table
CREATE TABLE order_items (
    order_id INT,
    product_id INT,
    quantity INT,
    price DECIMAL(10,2),
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Sales Table (for partitioning example)
CREATE TABLE sales_data (
    id INT AUTO_INCREMENT,
    sale_date DATE,
    amount DECIMAL(10,2),
    region VARCHAR(50),
    PRIMARY KEY (id, sale_date)
) PARTITION BY RANGE (YEAR(sale_date)) (
    PARTITION p2020 VALUES LESS THAN (2021),
    PARTITION p2021 VALUES LESS THAN (2022),
    PARTITION p2022 VALUES LESS THAN (2023),
    PARTITION p2023 VALUES LESS THAN (2024),
    PARTITION p2024 VALUES LESS THAN (2025)
);

-- =====================================================
-- 2. SAMPLE DATA INSERTION
-- =====================================================

-- Insert Departments
INSERT INTO departments (department_name, location, budget) VALUES
    ('IT', 'New York', 500000.00),
    ('HR', 'Chicago', 200000.00),
    ('Finance', 'Boston', 300000.00),
    ('Marketing', 'Los Angeles', 250000.00),
    ('Sales', 'Miami', 400000.00);

-- Insert Employees (Multi-row INSERT)
INSERT INTO employees (name, email, department, dept_id, salary, hire_date, manager_id, age) VALUES
    ('John Doe', 'john.doe@company.com', 'IT', 1, 85000, '2020-01-15', NULL, 35),
    ('Jane Smith', 'jane.smith@company.com', 'HR', 2, 65000, '2019-03-20', NULL, 32),
    ('Bob Johnson', 'bob.johnson@company.com', 'Finance', 3, 75000, '2021-06-10', NULL, 28),
    ('Alice Brown', 'alice.brown@company.com', 'IT', 1, 90000, '2018-11-05', 1, 30),
    ('Charlie Wilson', 'charlie.wilson@company.com', 'Marketing', 4, 60000, '2022-02-14', NULL, 26),
    ('Diana Prince', 'diana.prince@company.com', 'IT', 1, 95000, '2017-09-12', 1, 33),
    ('Edward Davis', 'edward.davis@company.com', 'Sales', 5, 70000, '2020-08-22', NULL, 29),
    ('Fiona Green', 'fiona.green@company.com', 'HR', 2, 55000, '2023-01-10', 2, 25),
    ('George Miller', 'george.miller@company.com', 'Finance', 3, 80000, '2019-12-03', 3, 31),
    ('Helen Taylor', 'helen.taylor@company.com', 'IT', 1, 88000, '2021-04-18', 4, 27);

-- Insert Products
INSERT INTO products (name, category, price, stock_quantity) VALUES
    ('Laptop', 'Electronics', 999.99, 50),
    ('Mouse', 'Electronics', 29.99, 200),
    ('Keyboard', 'Electronics', 79.99, 150),
    ('Monitor', 'Electronics', 299.99, 75),
    ('Desk Chair', 'Furniture', 199.99, 30),
    ('Coffee Mug', 'Office Supplies', 12.99, 500);

-- Insert Customers
INSERT INTO customers (name, email, phone, city) VALUES
    ('Customer One', 'customer1@email.com', '555-0101', 'New York'),
    ('Customer Two', 'customer2@email.com', '555-0102', 'Chicago'),
    ('Customer Three', 'customer3@email.com', '555-0103', 'Boston'),
    ('Customer Four', 'customer4@email.com', '555-0104', 'Los Angeles'),
    ('Customer Five', 'customer5@email.com', '555-0105', 'Miami');

-- Insert Orders
INSERT INTO orders (customer_id, order_date, total, status) VALUES
    (1, '2024-01-15', 1329.97, 'COMPLETED'),
    (2, '2024-01-20', 109.98, 'SHIPPED'),
    (3, '2024-02-05', 299.99, 'PENDING'),
    (4, '2024-02-10', 42.98, 'COMPLETED'),
    (5, '2024-02-15', 199.99, 'CANCELLED');

-- Insert Order Items
INSERT INTO order_items (order_id, product_id, quantity, price) VALUES
    (1, 1, 1, 999.99),
    (1, 4, 1, 299.99),
    (1, 6, 2, 12.99),
    (2, 2, 1, 29.99),
    (2, 3, 1, 79.99),
    (3, 4, 1, 299.99),
    (4, 2, 1, 29.99),
    (4, 6, 1, 12.99),
    (5, 5, 1, 199.99);

-- Insert Sales Data
INSERT INTO sales_data (sale_date, amount, region) VALUES
    ('2020-06-15', 15000.00, 'North'),
    ('2021-03-20', 22000.00, 'South'),
    ('2022-09-10', 18500.00, 'East'),
    ('2023-12-05', 31000.00, 'West'),
    ('2024-01-15', 25000.00, 'North');

-- =====================================================
-- 3. BASIC SQL QUERIES (Questions 1-10)
-- =====================================================

-- Q1: Basic SELECT with conditions
SELECT name, salary FROM employees WHERE salary > 70000;

-- Q2: ORDER BY and LIMIT
SELECT * FROM employees ORDER BY salary DESC LIMIT 5;

-- Q3: DISTINCT values
SELECT DISTINCT department FROM employees;

-- Q4: Primary and Foreign Key demonstration
SHOW CREATE TABLE employees;

-- Q5: UNION vs UNION ALL
SELECT name FROM employees WHERE department = 'IT'
UNION
SELECT name FROM employees WHERE department = 'HR';

SELECT name FROM employees WHERE department = 'IT'
UNION ALL
SELECT name FROM employees WHERE department = 'HR';

-- Q6: Index creation
CREATE INDEX idx_employee_name ON employees(name);
CREATE INDEX idx_employee_dept ON employees(department);
CREATE UNIQUE INDEX idx_employee_email ON employees(email);

-- Q7: WHERE vs HAVING
SELECT department, COUNT(*) FROM employees WHERE salary > 60000 GROUP BY department;
SELECT department, COUNT(*) FROM employees GROUP BY department HAVING COUNT(*) > 2;

-- Q8: Constraints demonstration (already in table creation)

-- Q9: CHAR vs VARCHAR (demonstration)
CREATE TABLE char_varchar_demo (
    fixed_char CHAR(10),
    variable_char VARCHAR(10)
);

-- Q10: View creation
CREATE VIEW high_earners AS
SELECT name, department, salary FROM employees WHERE salary > 80000;

SELECT * FROM high_earners;

-- =====================================================
-- 4. INTERMEDIATE QUERIES (Questions 11-20)
-- =====================================================

-- Q11: Different types of JOINs
-- INNER JOIN
SELECT e.name, d.department_name 
FROM employees e 
INNER JOIN departments d ON e.dept_id = d.id;

-- LEFT JOIN
SELECT e.name, d.department_name 
FROM employees e 
LEFT JOIN departments d ON e.dept_id = d.id;

-- RIGHT JOIN
SELECT e.name, d.department_name 
FROM employees e 
RIGHT JOIN departments d ON e.dept_id = d.id;

-- Q12: Stored Procedure
DELIMITER //
CREATE PROCEDURE GetEmployeesByDept(IN dept_name VARCHAR(50))
BEGIN
    SELECT * FROM employees WHERE department = dept_name;
END //
DELIMITER ;

-- Call stored procedure
CALL GetEmployeesByDept('IT');

-- Q13: Trigger
CREATE TRIGGER update_employee_timestamp
BEFORE UPDATE ON employees
FOR EACH ROW
SET NEW.updated_at = NOW();

-- Q14: Clustered vs Non-clustered index (conceptual - MySQL uses clustered by default on PRIMARY KEY)

-- Q15: CTE (Common Table Expression)
WITH dept_avg AS (
    SELECT department, AVG(salary) as avg_salary
    FROM employees GROUP BY department
)
SELECT e.name, e.salary, d.avg_salary
FROM employees e
JOIN dept_avg d ON e.department = d.department;

-- Q16: Find and remove duplicates
-- Find duplicates
SELECT email, COUNT(*) FROM employees GROUP BY email HAVING COUNT(*) > 1;

-- Q17: RANK vs DENSE_RANK
SELECT name, salary,
       RANK() OVER (ORDER BY salary DESC) as rank_val,
       DENSE_RANK() OVER (ORDER BY salary DESC) as dense_rank_val
FROM employees;

-- Q18: Nth highest salary (3rd highest)
SELECT DISTINCT salary FROM employees ORDER BY salary DESC LIMIT 1 OFFSET 2;

-- Using window function
SELECT salary FROM (
    SELECT salary, DENSE_RANK() OVER (ORDER BY salary DESC) as rank_val
    FROM employees
) ranked WHERE rank_val = 3;

-- Q19: ACID properties (conceptual)

-- Q20: Aggregate functions
SELECT 
    COUNT(*) as total_employees,
    SUM(salary) as total_salary,
    AVG(salary) as avg_salary,
    MIN(salary) as min_salary,
    MAX(salary) as max_salary
FROM employees;

-- =====================================================
-- 5. ADVANCED QUERIES (Questions 21-25)
-- =====================================================

-- Q21: Deadlock prevention (conceptual)

-- Q22: Query execution plan
EXPLAIN SELECT * FROM employees WHERE department = 'IT';

-- Q23: Window functions
SELECT name, salary,
       LAG(salary) OVER (ORDER BY salary) as prev_salary,
       LEAD(salary) OVER (ORDER BY salary) as next_salary,
       FIRST_VALUE(salary) OVER (ORDER BY salary) as min_salary,
       LAST_VALUE(salary) OVER (ORDER BY salary ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING) as max_salary
FROM employees;

-- Q24: Query optimization examples
-- Before optimization
SELECT * FROM employees WHERE UPPER(name) = 'JOHN DOE';

-- After optimization
SELECT * FROM employees WHERE name = 'John Doe';

-- Q25: Database sharding (conceptual)

-- =====================================================
-- 6. COMMON SQL PROBLEMS
-- =====================================================

-- Second highest salary
SELECT MAX(salary) as second_highest
FROM employees 
WHERE salary < (SELECT MAX(salary) FROM employees);

-- Employees with no manager
SELECT * FROM employees WHERE manager_id IS NULL;

-- Department with highest average salary
SELECT department FROM employees 
GROUP BY department 
ORDER BY AVG(salary) DESC 
LIMIT 1;

-- Employees earning more than their manager
SELECT e.name as employee, m.name as manager, e.salary as emp_salary, m.salary as mgr_salary
FROM employees e
JOIN employees m ON e.manager_id = m.id
WHERE e.salary > m.salary;

-- Running total
SELECT name, salary,
       SUM(salary) OVER (ORDER BY id ROWS UNBOUNDED PRECEDING) as running_total
FROM employees;

-- =====================================================
-- 7. MULTI-ROW INSERT EXAMPLES
-- =====================================================

-- Basic multi-row insert (already demonstrated above)

-- INSERT with generated values
INSERT INTO products (name, category, price) VALUES
    ('Tablet', 'Electronics', 599.99),
    ('Headphones', 'Electronics', 149.99),
    ('Webcam', 'Electronics', 89.99);

-- Multi-table insert with transaction
START TRANSACTION;

INSERT INTO customers (name, email, city) VALUES ('New Customer', 'new@email.com', 'Seattle');
SET @customer_id = LAST_INSERT_ID();

INSERT INTO orders (customer_id, order_date, total) VALUES (@customer_id, CURDATE(), 599.99);
SET @order_id = LAST_INSERT_ID();

INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (@order_id, 7, 1, 599.99);

COMMIT;

-- Create employee_bonuses table first
CREATE TABLE employee_bonuses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    emp_id INT,
    bonus_amount DECIMAL(10,2),
    bonus_type VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (emp_id) REFERENCES employees(id)
);

-- Conditional multi-insert
INSERT INTO employee_bonuses (emp_id, bonus_amount, bonus_type)
SELECT 
    id,
    CASE 
        WHEN salary > 90000 THEN salary * 0.15
        WHEN salary > 70000 THEN salary * 0.10
        ELSE salary * 0.05
    END as bonus,
    CASE 
        WHEN department = 'Sales' THEN 'COMMISSION'
        ELSE 'PERFORMANCE'
    END as type
FROM employees;

-- Handle duplicates
INSERT INTO customers (name, email, city) VALUES
    ('Duplicate Test', 'test@email.com', 'Test City'),
    ('Another Customer', 'another@email.com', 'Another City')
ON DUPLICATE KEY UPDATE
    name = VALUES(name),
    city = VALUES(city);

-- =====================================================
-- 8. ADMINISTRATION QUERIES
-- =====================================================

-- User management
CREATE USER 'developer'@'localhost' IDENTIFIED BY 'dev_password';
GRANT SELECT, INSERT, UPDATE ON interview_db.* TO 'developer'@'localhost';
SHOW GRANTS FOR 'developer'@'localhost';

-- Performance monitoring
SHOW PROCESSLIST;
SHOW STATUS LIKE 'Connections';
SHOW STATUS LIKE 'Threads_connected';

-- Table maintenance
CHECK TABLE employees;
ANALYZE TABLE employees;
OPTIMIZE TABLE employees;

-- Index information
SHOW INDEX FROM employees;

-- Table status
SHOW TABLE STATUS LIKE 'employees';

-- =====================================================
-- 9. BULK DATA OPERATIONS
-- =====================================================

-- Bulk insert with LOAD DATA (file-based)
-- LOAD DATA INFILE '/path/to/employees.csv'
-- INTO TABLE employees
-- FIELDS TERMINATED BY ','
-- LINES TERMINATED BY '\n'
-- IGNORE 1 ROWS;

-- Batch processing example
SET autocommit = 0;

INSERT INTO sales_data (sale_date, amount, region) VALUES
    ('2024-03-01', 5000.00, 'North'),
    ('2024-03-02', 7500.00, 'South'),
    ('2024-03-03', 6200.00, 'East'),
    ('2024-03-04', 8100.00, 'West'),
    ('2024-03-05', 5800.00, 'North');

COMMIT;
SET autocommit = 1;

-- =====================================================
-- 10. PERFORMANCE OPTIMIZATION
-- =====================================================

-- Create additional indexes for performance
CREATE INDEX idx_employee_salary ON employees(salary);
CREATE INDEX idx_employee_hire_date ON employees(hire_date);
CREATE INDEX idx_order_date ON orders(order_date);
CREATE INDEX idx_order_status ON orders(status);

-- Composite index
CREATE INDEX idx_employee_dept_salary ON employees(department, salary);

-- =====================================================
-- 11. CLEANUP AND FINAL QUERIES
-- =====================================================

-- Show all tables
SHOW TABLES;

-- Show table structures
DESCRIBE employees;
DESCRIBE departments;
DESCRIBE orders;

-- Final statistics
SELECT 
    'employees' as table_name, COUNT(*) as record_count FROM employees
UNION ALL
SELECT 
    'departments' as table_name, COUNT(*) as record_count FROM departments
UNION ALL
SELECT 
    'orders' as table_name, COUNT(*) as record_count FROM orders
UNION ALL
SELECT 
    'customers' as table_name, COUNT(*) as record_count FROM customers
UNION ALL
SELECT 
    'employee_bonuses' as table_name, COUNT(*) as record_count FROM employee_bonuses;

-- Complex query combining multiple concepts
SELECT 
    e.name,
    e.department,
    e.salary,
    d.department_name,
    RANK() OVER (PARTITION BY e.department ORDER BY e.salary DESC) as dept_rank,
    CASE 
        WHEN e.salary > 85000 THEN 'High'
        WHEN e.salary > 65000 THEN 'Medium'
        ELSE 'Low'
    END as salary_grade,
    DATEDIFF(CURDATE(), e.hire_date) as days_employed
FROM employees e
LEFT JOIN departments d ON e.dept_id = d.id
WHERE e.status = 'ACTIVE'
ORDER BY e.department, e.salary DESC;

-- =====================================================
-- END OF QUERY FILE
-- =====================================================