# SQL Interview Questions 🗄️

Essential SQL interview preparation covering all critical database concepts and queries.

## 🎯 Most Important SQL Concepts

### 1. **Basic Queries**
```sql
-- SELECT with conditions
SELECT name, salary FROM employees WHERE salary > 50000;

-- ORDER BY and LIMIT
SELECT * FROM employees ORDER BY salary DESC LIMIT 5;

-- DISTINCT values
SELECT DISTINCT department FROM employees;
```

### 2. **Joins**
```sql
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

-- FULL OUTER JOIN
SELECT e.name, d.department_name 
FROM employees e 
FULL OUTER JOIN departments d ON e.dept_id = d.id;
```

### 3. **Aggregate Functions**
```sql
-- COUNT, SUM, AVG, MIN, MAX
SELECT 
    COUNT(*) as total_employees,
    AVG(salary) as avg_salary,
    MAX(salary) as max_salary,
    MIN(salary) as min_salary,
    SUM(salary) as total_salary
FROM employees;

-- GROUP BY
SELECT department, COUNT(*), AVG(salary)
FROM employees 
GROUP BY department;

-- HAVING clause
SELECT department, AVG(salary)
FROM employees 
GROUP BY department 
HAVING AVG(salary) > 60000;
```

### 4. **Subqueries**
```sql
-- Scalar subquery
SELECT name FROM employees 
WHERE salary = (SELECT MAX(salary) FROM employees);

-- Correlated subquery
SELECT name, salary FROM employees e1
WHERE salary > (SELECT AVG(salary) FROM employees e2 WHERE e2.dept_id = e1.dept_id);

-- EXISTS
SELECT name FROM employees e
WHERE EXISTS (SELECT 1 FROM departments d WHERE d.id = e.dept_id);
```

### 5. **Window Functions**
```sql
-- ROW_NUMBER
SELECT name, salary, ROW_NUMBER() OVER (ORDER BY salary DESC) as rank
FROM employees;

-- RANK and DENSE_RANK
SELECT name, salary, 
       RANK() OVER (ORDER BY salary DESC) as rank,
       DENSE_RANK() OVER (ORDER BY salary DESC) as dense_rank
FROM employees;

-- Partition by department
SELECT name, department, salary,
       ROW_NUMBER() OVER (PARTITION BY department ORDER BY salary DESC) as dept_rank
FROM employees;
```

## 🔥 Top 25 SQL Interview Questions

### **Basic Level (1-10)**

**1. What is SQL?**
- Structured Query Language for managing relational databases

**2. Difference between DELETE, DROP, and TRUNCATE?**
```sql
DELETE FROM table WHERE condition;  -- Removes rows, can rollback
DROP TABLE table;                   -- Removes entire table structure
TRUNCATE TABLE table;              -- Removes all rows, faster than DELETE
```

**3. What are primary and foreign keys?**
```sql
-- Primary key
CREATE TABLE employees (
    id INT PRIMARY KEY,
    name VARCHAR(100)
);

-- Foreign key
CREATE TABLE orders (
    id INT PRIMARY KEY,
    employee_id INT,
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);
```

**4. What is normalization?**
- 1NF: Atomic values, no repeating groups
- 2NF: 1NF + no partial dependencies
- 3NF: 2NF + no transitive dependencies

**5. Difference between UNION and UNION ALL?**
```sql
-- UNION removes duplicates
SELECT name FROM employees UNION SELECT name FROM contractors;

-- UNION ALL keeps duplicates
SELECT name FROM employees UNION ALL SELECT name FROM contractors;
```

**6. What is an index?**
```sql
-- Create index for faster queries
CREATE INDEX idx_employee_name ON employees(name);
CREATE UNIQUE INDEX idx_employee_email ON employees(email);
```

**7. Difference between WHERE and HAVING?**
```sql
-- WHERE filters rows before grouping
SELECT department, COUNT(*) FROM employees WHERE salary > 50000 GROUP BY department;

-- HAVING filters groups after grouping
SELECT department, COUNT(*) FROM employees GROUP BY department HAVING COUNT(*) > 5;
```

**8. What are constraints?**
```sql
CREATE TABLE employees (
    id INT NOT NULL,
    email VARCHAR(100) UNIQUE,
    age INT CHECK (age >= 18),
    salary DECIMAL DEFAULT 0
);
```

**9. Difference between CHAR and VARCHAR?**
- CHAR: Fixed length, padded with spaces
- VARCHAR: Variable length, no padding

**10. What is a view?**
```sql
CREATE VIEW high_earners AS
SELECT name, salary FROM employees WHERE salary > 100000;

SELECT * FROM high_earners;
```

### **Intermediate Level (11-20)**

**11. What are different types of joins?**
- INNER: Returns matching records
- LEFT: All from left + matching from right
- RIGHT: All from right + matching from left
- FULL OUTER: All records from both tables

**12. What is a stored procedure?**
```sql
DELIMITER //
CREATE PROCEDURE GetEmployeesByDept(IN dept_name VARCHAR(50))
BEGIN
    SELECT * FROM employees WHERE department = dept_name;
END //
DELIMITER ;

CALL GetEmployeesByDept('IT');
```

**13. What are triggers?**
```sql
CREATE TRIGGER update_modified_date
BEFORE UPDATE ON employees
FOR EACH ROW
SET NEW.modified_date = NOW();
```

**14. Difference between clustered and non-clustered index?**
- Clustered: Physical order of data, one per table
- Non-clustered: Logical order, multiple per table

**15. What is a CTE (Common Table Expression)?**
```sql
WITH dept_avg AS (
    SELECT department, AVG(salary) as avg_sal
    FROM employees GROUP BY department
)
SELECT e.name, e.salary, d.avg_sal
FROM employees e
JOIN dept_avg d ON e.department = d.department;
```

**16. How to find duplicate records?**
```sql
-- Find duplicates
SELECT email, COUNT(*) FROM employees GROUP BY email HAVING COUNT(*) > 1;

-- Remove duplicates
DELETE e1 FROM employees e1
INNER JOIN employees e2 
WHERE e1.id > e2.id AND e1.email = e2.email;
```

**17. What is the difference between RANK() and DENSE_RANK()?**
```sql
-- RANK: 1,2,2,4 (skips 3)
-- DENSE_RANK: 1,2,2,3 (no skip)
SELECT name, salary,
       RANK() OVER (ORDER BY salary DESC) as rank_val,
       DENSE_RANK() OVER (ORDER BY salary DESC) as dense_rank_val
FROM employees;
```

**18. How to find Nth highest salary?**
```sql
-- Using LIMIT and OFFSET
SELECT DISTINCT salary FROM employees ORDER BY salary DESC LIMIT 1 OFFSET 2;

-- Using window function
SELECT salary FROM (
    SELECT salary, DENSE_RANK() OVER (ORDER BY salary DESC) as rank_val
    FROM employees
) ranked WHERE rank_val = 3;
```

**19. What is ACID properties?**
- **Atomicity**: All or nothing
- **Consistency**: Valid state transitions
- **Isolation**: Concurrent transactions don't interfere
- **Durability**: Committed changes persist

**20. What are aggregate functions?**
```sql
SELECT 
    COUNT(*) as total,
    SUM(salary) as total_salary,
    AVG(salary) as avg_salary,
    MIN(salary) as min_salary,
    MAX(salary) as max_salary,
    STRING_AGG(name, ', ') as all_names
FROM employees;
```

### **Advanced Level (21-25)**

**21. What is a deadlock?**
- Two transactions waiting for each other's locks
- Prevention: Consistent lock ordering, timeouts

**22. Explain query execution plan**
```sql
EXPLAIN SELECT * FROM employees WHERE department = 'IT';
-- Shows how database executes the query
```

**23. What are window functions?**
```sql
SELECT name, salary,
       LAG(salary) OVER (ORDER BY salary) as prev_salary,
       LEAD(salary) OVER (ORDER BY salary) as next_salary,
       FIRST_VALUE(salary) OVER (ORDER BY salary) as min_salary,
       LAST_VALUE(salary) OVER (ORDER BY salary) as max_salary
FROM employees;
```

**24. How to optimize SQL queries?**
- Use indexes on WHERE/JOIN columns
- Avoid SELECT *
- Use LIMIT for large datasets
- Proper JOIN order
- Analyze execution plans

**25. What is database sharding?**
- Horizontal partitioning across multiple databases
- Distributes load and improves performance

## 🚀 Multi-Row INSERT Techniques

### **1. Basic Multi-Row INSERT**
```sql
-- Insert multiple rows in single statement
INSERT INTO employees (id, name, department, salary) VALUES
    (1, 'John Doe', 'IT', 75000),
    (2, 'Jane Smith', 'HR', 65000),
    (3, 'Bob Johnson', 'Finance', 70000),
    (4, 'Alice Brown', 'IT', 80000),
    (5, 'Charlie Wilson', 'Marketing', 55000);
```

### **2. INSERT with Generated Values**
```sql
-- Auto-increment with multi-row insert
INSERT INTO products (name, category, price) VALUES
    ('Laptop', 'Electronics', 999.99),
    ('Mouse', 'Electronics', 29.99),
    ('Keyboard', 'Electronics', 79.99),
    ('Monitor', 'Electronics', 299.99);

-- With functions and expressions
INSERT INTO orders (customer_id, order_date, total, status) VALUES
    (101, NOW(), 1500.00, 'PENDING'),
    (102, NOW(), 750.50, 'CONFIRMED'),
    (103, CURDATE(), 2200.75, 'SHIPPED');
```

### **3. Multi-Table INSERT**
```sql
-- Insert into multiple tables simultaneously
START TRANSACTION;

INSERT INTO customers (name, email) VALUES
    ('John Customer', 'john@email.com'),
    ('Jane Buyer', 'jane@email.com');

INSERT INTO orders (customer_id, product_id, quantity) VALUES
    (LAST_INSERT_ID(), 1, 2),
    (LAST_INSERT_ID(), 2, 1);

COMMIT;
```

### **4. Conditional Multi-INSERT**
```sql
-- INSERT with conditional logic
INSERT INTO employee_bonuses (emp_id, bonus_amount, bonus_type)
SELECT 
    id,
    CASE 
        WHEN salary > 100000 THEN salary * 0.15
        WHEN salary > 75000 THEN salary * 0.10
        ELSE salary * 0.05
    END as bonus,
    CASE 
        WHEN department = 'Sales' THEN 'COMMISSION'
        ELSE 'PERFORMANCE'
    END as type
FROM employees
WHERE hire_date >= '2024-01-01';
```

### **5. Batch INSERT with Variables**
```sql
-- Using variables for dynamic multi-insert
SET @dept_id = (SELECT id FROM departments WHERE name = 'IT');

INSERT INTO employees (name, dept_id, salary, hire_date) VALUES
    ('Developer 1', @dept_id, 85000, CURDATE()),
    ('Developer 2', @dept_id, 90000, CURDATE()),
    ('Developer 3', @dept_id, 95000, CURDATE());
```

### **6. INSERT from Multiple Sources**
```sql
-- Combine data from different tables
INSERT INTO employee_summary (name, department, total_sales, performance_rating)
SELECT 
    e.name,
    d.department_name,
    COALESCE(s.total_sales, 0),
    COALESCE(p.rating, 'Not Rated')
FROM employees e
LEFT JOIN departments d ON e.dept_id = d.id
LEFT JOIN sales_summary s ON e.id = s.emp_id
LEFT JOIN performance p ON e.id = p.emp_id;
```

### **7. Prepared Statement Multi-INSERT**
```sql
-- Efficient for repeated multi-inserts
PREPARE stmt FROM 'INSERT INTO logs (user_id, action, timestamp) VALUES (?, ?, NOW())';

SET @user1 = 101, @action1 = 'LOGIN';
EXECUTE stmt USING @user1, @action1;

SET @user2 = 102, @action2 = 'LOGOUT';
EXECUTE stmt USING @user2, @action2;

DEALLOCATE PREPARE stmt;
```

### **8. Multi-INSERT with Duplicate Handling**
```sql
-- Handle duplicates during multi-insert
INSERT INTO users (email, name, status) VALUES
    ('john@email.com', 'John Doe', 'ACTIVE'),
    ('jane@email.com', 'Jane Smith', 'ACTIVE'),
    ('bob@email.com', 'Bob Johnson', 'PENDING')
ON DUPLICATE KEY UPDATE
    name = VALUES(name),
    status = VALUES(status),
    updated_at = NOW();

-- Or use INSERT IGNORE
INSERT IGNORE INTO categories (name, description) VALUES
    ('Electronics', 'Electronic devices'),
    ('Clothing', 'Apparel and accessories'),
    ('Books', 'Books and literature');
```

### **9. Performance-Optimized Multi-INSERT**
```sql
-- Disable autocommit for better performance
SET autocommit = 0;

-- Insert in batches
INSERT INTO large_table (col1, col2, col3) VALUES
    (1, 'data1', 100), (2, 'data2', 200), (3, 'data3', 300),
    (4, 'data4', 400), (5, 'data5', 500), (6, 'data6', 600),
    (7, 'data7', 700), (8, 'data8', 800), (9, 'data9', 900),
    (10, 'data10', 1000);

COMMIT;
SET autocommit = 1;
```

### **10. Multi-INSERT with JSON Data**
```sql
-- Insert JSON data (MySQL 5.7+)
INSERT INTO user_profiles (user_id, profile_data) VALUES
    (1, JSON_OBJECT('age', 25, 'city', 'New York', 'skills', JSON_ARRAY('Java', 'SQL'))),
    (2, JSON_OBJECT('age', 30, 'city', 'London', 'skills', JSON_ARRAY('Python', 'MongoDB'))),
    (3, JSON_OBJECT('age', 28, 'city', 'Tokyo', 'skills', JSON_ARRAY('JavaScript', 'React')));
```

### **Performance Tips for Multi-INSERT:**
- **Batch Size**: Use 100-1000 rows per INSERT statement
- **Disable Indexes**: Drop non-essential indexes during bulk operations
- **Use Transactions**: Wrap large multi-inserts in transactions
- **Prepared Statements**: For repeated operations with different data
- **Memory Settings**: Increase `bulk_insert_buffer_size` for MyISAM
- **Lock Tables**: Use `LOCK TABLES` for exclusive access during bulk operations

## 📊 Multi-Data & Big Data Insertion

### **Bulk Insert Methods**

**1. Multiple Row INSERT**
```sql
-- Single statement multiple rows
INSERT INTO employees (name, department, salary) VALUES
    ('John Doe', 'IT', 75000),
    ('Jane Smith', 'HR', 65000),
    ('Bob Johnson', 'Finance', 70000),
    ('Alice Brown', 'IT', 80000);

-- Using SELECT for bulk insert
INSERT INTO employees_backup 
SELECT * FROM employees WHERE department = 'IT';
```

**2. LOAD DATA INFILE (MySQL)**
```sql
-- Load from CSV file
LOAD DATA INFILE '/path/to/employees.csv'
INTO TABLE employees
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(name, department, salary, hire_date);

-- Load with data transformation
LOAD DATA INFILE '/path/to/data.csv'
INTO TABLE employees
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
(name, dept, @salary, @date)
SET salary = @salary * 1.1,
    hire_date = STR_TO_DATE(@date, '%m/%d/%Y');
```

**3. BULK INSERT (SQL Server)**
```sql
-- Basic bulk insert
BULK INSERT employees
FROM 'C:\data\employees.csv'
WITH (
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '\n',
    FIRSTROW = 2
);

-- Bulk insert with format file
BULK INSERT employees
FROM 'C:\data\employees.txt'
WITH (FORMATFILE = 'C:\format\employees.fmt');
```

**4. INSERT INTO SELECT Pattern**
```sql
-- Copy data between tables
INSERT INTO employees_2024
SELECT * FROM employees 
WHERE YEAR(hire_date) = 2024;

-- Aggregate and insert
INSERT INTO department_summary (dept, total_employees, avg_salary)
SELECT department, COUNT(*), AVG(salary)
FROM employees
GROUP BY department;
```

### **Big Data Insertion Strategies**

**5. Batch Processing**
```sql
-- Disable autocommit for batch
SET autocommit = 0;

START TRANSACTION;

-- Insert in batches of 1000
INSERT INTO large_table (col1, col2, col3) VALUES
-- ... 1000 rows ...
;

COMMIT;

-- Re-enable autocommit
SET autocommit = 1;
```

**6. Temporary Disable Constraints**
```sql
-- Disable foreign key checks
SET FOREIGN_KEY_CHECKS = 0;

-- Disable unique checks
SET UNIQUE_CHECKS = 0;

-- Bulk insert operations
LOAD DATA INFILE 'large_dataset.csv' INTO TABLE big_table;

-- Re-enable checks
SET FOREIGN_KEY_CHECKS = 1;
SET UNIQUE_CHECKS = 1;
```

**7. Partitioned Table Insertion**
```sql
-- Create partitioned table
CREATE TABLE sales_data (
    id INT,
    sale_date DATE,
    amount DECIMAL(10,2)
) PARTITION BY RANGE (YEAR(sale_date)) (
    PARTITION p2020 VALUES LESS THAN (2021),
    PARTITION p2021 VALUES LESS THAN (2022),
    PARTITION p2022 VALUES LESS THAN (2023),
    PARTITION p2023 VALUES LESS THAN (2024)
);

-- Insert will automatically go to correct partition
INSERT INTO sales_data VALUES (1, '2022-01-15', 1500.00);
```

**8. Parallel Insertion**
```sql
-- Use multiple connections for parallel insert
-- Connection 1:
INSERT INTO employees SELECT * FROM temp_employees WHERE id BETWEEN 1 AND 10000;

-- Connection 2:
INSERT INTO employees SELECT * FROM temp_employees WHERE id BETWEEN 10001 AND 20000;

-- Connection 3:
INSERT INTO employees SELECT * FROM temp_employees WHERE id BETWEEN 20001 AND 30000;
```

### **Performance Optimization for Large Inserts**

**9. Index Management**
```sql
-- Drop indexes before bulk insert
DROP INDEX idx_employee_name ON employees;
DROP INDEX idx_employee_dept ON employees;

-- Perform bulk insert
LOAD DATA INFILE 'million_employees.csv' INTO TABLE employees;

-- Recreate indexes after insert
CREATE INDEX idx_employee_name ON employees(name);
CREATE INDEX idx_employee_dept ON employees(department);
```

**10. Memory Configuration**
```sql
-- Increase bulk insert buffer
SET GLOBAL bulk_insert_buffer_size = 256*1024*1024; -- 256MB

-- Increase key buffer for MyISAM
SET GLOBAL key_buffer_size = 512*1024*1024; -- 512MB

-- Increase InnoDB buffer pool
SET GLOBAL innodb_buffer_pool_size = 2*1024*1024*1024; -- 2GB
```

### **Error Handling in Bulk Operations**

**11. INSERT IGNORE and ON DUPLICATE KEY**
```sql
-- Ignore duplicate key errors
INSERT IGNORE INTO employees (id, name, email) VALUES
    (1, 'John Doe', 'john@company.com'),
    (2, 'Jane Smith', 'jane@company.com');

-- Handle duplicates with update
INSERT INTO employees (id, name, salary) VALUES
    (1, 'John Doe', 75000),
    (2, 'Jane Smith', 65000)
ON DUPLICATE KEY UPDATE
    salary = VALUES(salary),
    updated_at = NOW();
```

**12. REPLACE Statement**
```sql
-- Replace existing records
REPLACE INTO employees (id, name, department, salary) VALUES
    (1, 'John Updated', 'IT', 80000),
    (2, 'Jane Updated', 'HR', 70000);
```

### **Monitoring Large Insertions**

**13. Progress Tracking**
```sql
-- Check insertion progress
SHOW PROCESSLIST;

-- Monitor table size growth
SELECT 
    table_name,
    table_rows,
    ROUND(((data_length + index_length) / 1024 / 1024), 2) AS 'Size (MB)'
FROM information_schema.TABLES 
WHERE table_schema = 'your_database'
AND table_name = 'your_table';

-- Check InnoDB status
SHOW ENGINE INNODB STATUS;
```

**14. Transaction Log Management**
```sql
-- For large transactions, monitor log size
SHOW VARIABLES LIKE 'innodb_log_file_size';

-- Check binary log space
SHOW BINARY LOGS;

-- Purge old binary logs if needed
PURGE BINARY LOGS BEFORE '2024-01-01 00:00:00';
```

### **Best Practices for Big Data Insertion**

**15. Chunked Processing**
```sql
-- Process in chunks to avoid long-running transactions
DELIMITER //
CREATE PROCEDURE BulkInsertChunked()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE chunk_size INT DEFAULT 10000;
    DECLARE offset_val INT DEFAULT 0;
    
    WHILE NOT done DO
        INSERT INTO target_table
        SELECT * FROM source_table
        LIMIT chunk_size OFFSET offset_val;
        
        IF ROW_COUNT() < chunk_size THEN
            SET done = TRUE;
        END IF;
        
        SET offset_val = offset_val + chunk_size;
        COMMIT;
    END WHILE;
END //
DELIMITER ;
```

**Performance Tips:**
- Use `INSERT` with multiple VALUES for small batches (< 1000 rows)
- Use `LOAD DATA INFILE` for large CSV imports
- Disable indexes during bulk operations
- Use transactions for consistency
- Monitor memory usage and adjust buffer sizes
- Consider partitioning for very large tables
- Use parallel processing for massive datasets

## 💻 Common SQL Problems

### **Find Second Highest Salary**
```sql
SELECT MAX(salary) as second_highest
FROM employees 
WHERE salary < (SELECT MAX(salary) FROM employees);
```

### **Find Employees with No Manager**
```sql
SELECT * FROM employees WHERE manager_id IS NULL;
```

### **Department with Highest Average Salary**
```sql
SELECT department FROM employees 
GROUP BY department 
ORDER BY AVG(salary) DESC 
LIMIT 1;
```

### **Employees Earning More Than Their Manager**
```sql
SELECT e.name as employee, m.name as manager
FROM employees e
JOIN employees m ON e.manager_id = m.id
WHERE e.salary > m.salary;
```

### **Running Total**
```sql
SELECT name, salary,
       SUM(salary) OVER (ORDER BY id ROWS UNBOUNDED PRECEDING) as running_total
FROM employees;
```

## 🔧 SQL Administration Questions

### **User Management**

**1. How to create and manage users?**
```sql
-- Create user
CREATE USER 'developer'@'localhost' IDENTIFIED BY 'password123';

-- Grant privileges
GRANT SELECT, INSERT, UPDATE ON company.* TO 'developer'@'localhost';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'%';

-- Revoke privileges
REVOKE INSERT ON company.employees FROM 'developer'@'localhost';

-- Drop user
DROP USER 'developer'@'localhost';

-- Show grants
SHOW GRANTS FOR 'developer'@'localhost';
```

**2. What are database roles?**
```sql
-- Create role
CREATE ROLE 'app_developer';

-- Grant privileges to role
GRANT SELECT, INSERT, UPDATE ON app.* TO 'app_developer';

-- Assign role to user
GRANT 'app_developer' TO 'john'@'localhost';

-- Set default role
SET DEFAULT ROLE 'app_developer' TO 'john'@'localhost';
```

### **Backup and Recovery**

**3. How to backup and restore databases?**
```bash
# Full database backup
mysqldump -u root -p company > company_backup.sql

# Specific tables backup
mysqldump -u root -p company employees departments > tables_backup.sql

# Restore database
mysql -u root -p company < company_backup.sql

# Binary log backup
mysqlbinlog mysql-bin.000001 > binlog_backup.sql
```

**4. What are different backup types?**
- **Full Backup**: Complete database copy
- **Incremental**: Changes since last backup
- **Differential**: Changes since last full backup
- **Point-in-time**: Restore to specific timestamp

### **Performance Monitoring**

**5. How to monitor database performance?**
```sql
-- Show running processes
SHOW PROCESSLIST;

-- Show slow queries
SHOW VARIABLES LIKE 'slow_query_log';
SET GLOBAL slow_query_log = 'ON';

-- Check table status
SHOW TABLE STATUS LIKE 'employees';

-- Analyze table performance
ANALYZE TABLE employees;

-- Check index usage
SHOW INDEX FROM employees;
```

**6. What are key performance metrics?**
```sql
-- Connection statistics
SHOW STATUS LIKE 'Connections';
SHOW STATUS LIKE 'Threads_connected';

-- Query statistics
SHOW STATUS LIKE 'Questions';
SHOW STATUS LIKE 'Slow_queries';

-- Buffer pool statistics
SHOW STATUS LIKE 'Innodb_buffer_pool%';

-- Lock statistics
SHOW STATUS LIKE 'Table_locks%';
```

### **Database Maintenance**

**7. How to maintain database health?**
```sql
-- Check table integrity
CHECK TABLE employees;

-- Repair corrupted table
REPAIR TABLE employees;

-- Optimize table
OPTIMIZE TABLE employees;

-- Update table statistics
ANALYZE TABLE employees;

-- Rebuild indexes
ALTER TABLE employees ENGINE=InnoDB;
```

**8. What is database partitioning?**
```sql
-- Range partitioning
CREATE TABLE sales (
    id INT,
    sale_date DATE,
    amount DECIMAL(10,2)
) PARTITION BY RANGE (YEAR(sale_date)) (
    PARTITION p2020 VALUES LESS THAN (2021),
    PARTITION p2021 VALUES LESS THAN (2022),
    PARTITION p2022 VALUES LESS THAN (2023)
);

-- Hash partitioning
CREATE TABLE customers (
    id INT PRIMARY KEY,
    name VARCHAR(100)
) PARTITION BY HASH(id) PARTITIONS 4;
```

### **Security Administration**

**9. How to secure a database?**
```sql
-- Enable SSL
SHOW VARIABLES LIKE 'have_ssl';

-- Create SSL user
CREATE USER 'secure_user'@'%' IDENTIFIED BY 'password' REQUIRE SSL;

-- Audit logging
SET GLOBAL general_log = 'ON';
SET GLOBAL log_output = 'TABLE';

-- Password validation
SHOW VARIABLES LIKE 'validate_password%';
```

**10. What are security best practices?**
- Use strong passwords and rotate regularly
- Implement principle of least privilege
- Enable SSL/TLS encryption
- Regular security audits
- Keep database software updated
- Monitor failed login attempts

### **Replication and High Availability**

**11. How to set up replication?**
```sql
-- Master configuration
CREATE USER 'repl'@'%' IDENTIFIED BY 'password';
GRANT REPLICATION SLAVE ON *.* TO 'repl'@'%';
SHOW MASTER STATUS;

-- Slave configuration
CHANGE MASTER TO
    MASTER_HOST='master_ip',
    MASTER_USER='repl',
    MASTER_PASSWORD='password',
    MASTER_LOG_FILE='mysql-bin.000001',
    MASTER_LOG_POS=154;

START SLAVE;
SHOW SLAVE STATUS\G;
```

**12. What are replication types?**
- **Master-Slave**: One master, multiple slaves
- **Master-Master**: Bidirectional replication
- **Statement-based**: Replicates SQL statements
- **Row-based**: Replicates data changes
- **Mixed**: Combination of both

### **Configuration Management**

**13. Important database parameters?**
```sql
-- Memory settings
SHOW VARIABLES LIKE 'innodb_buffer_pool_size';
SHOW VARIABLES LIKE 'key_buffer_size';

-- Connection settings
SHOW VARIABLES LIKE 'max_connections';
SHOW VARIABLES LIKE 'connect_timeout';

-- Query cache
SHOW VARIABLES LIKE 'query_cache%';

-- Log settings
SHOW VARIABLES LIKE 'log_bin';
SHOW VARIABLES LIKE 'expire_logs_days';
```

**14. How to tune database performance?**
```sql
-- Increase buffer pool
SET GLOBAL innodb_buffer_pool_size = 1073741824; -- 1GB

-- Optimize query cache
SET GLOBAL query_cache_size = 268435456; -- 256MB

-- Adjust connection limits
SET GLOBAL max_connections = 500;

-- Configure slow query log
SET GLOBAL long_query_time = 2;
SET GLOBAL slow_query_log = 'ON';
```

### **Troubleshooting**

**15. Common database issues and solutions?**
```sql
-- Check disk space
SHOW TABLE STATUS;

-- Find blocking queries
SELECT * FROM information_schema.INNODB_TRX;

-- Kill long-running query
KILL QUERY 123;

-- Check error log
SHOW VARIABLES LIKE 'log_error';

-- Deadlock information
SHOW ENGINE INNODB STATUS;
```

## 📊 Database Design Questions

### **1. Design a Library Management System**
```sql
CREATE TABLE books (
    id INT PRIMARY KEY,
    title VARCHAR(200),
    author VARCHAR(100),
    isbn VARCHAR(20) UNIQUE
);

CREATE TABLE members (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE
);

CREATE TABLE loans (
    id INT PRIMARY KEY,
    book_id INT,
    member_id INT,
    loan_date DATE,
    return_date DATE,
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (member_id) REFERENCES members(id)
);
```

### **2. E-commerce Database Schema**
```sql
CREATE TABLE customers (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE
);

CREATE TABLE products (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    price DECIMAL(10,2)
);

CREATE TABLE orders (
    id INT PRIMARY KEY,
    customer_id INT,
    order_date DATE,
    total DECIMAL(10,2),
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE order_items (
    order_id INT,
    product_id INT,
    quantity INT,
    price DECIMAL(10,2),
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
```

## 🎯 Performance Optimization

### **Indexing Strategy**
```sql
-- Single column index
CREATE INDEX idx_employee_dept ON employees(department);

-- Composite index
CREATE INDEX idx_employee_dept_salary ON employees(department, salary);

-- Covering index
CREATE INDEX idx_employee_covering ON employees(department, salary) INCLUDE (name);
```

### **Query Optimization Tips**
1. **Use EXPLAIN** to analyze query plans
2. **Index frequently queried columns**
3. **Avoid functions in WHERE clauses**
4. **Use EXISTS instead of IN for subqueries**
5. **Limit result sets with WHERE and LIMIT**

## 📚 Key Concepts Summary

| Concept | Description | Use Case |
|---------|-------------|----------|
| **Joins** | Combine tables | Relational data retrieval |
| **Indexes** | Speed up queries | Performance optimization |
| **Views** | Virtual tables | Data abstraction |
| **Stored Procedures** | Reusable code blocks | Business logic |
| **Triggers** | Automatic actions | Data integrity |
| **Window Functions** | Advanced analytics | Ranking, running totals |
| **CTEs** | Temporary result sets | Complex queries |
| **Transactions** | ACID compliance | Data consistency |

## 🚀 Interview Tips

1. **Understand the problem** - Read requirements carefully
2. **Start simple** - Basic query first, then optimize
3. **Explain your approach** - Walk through your logic
4. **Consider edge cases** - NULL values, empty results
5. **Discuss performance** - Indexes, query optimization
6. **Know your database** - MySQL, PostgreSQL, SQL Server differences

## 📈 Study Plan

### **Week 1: Fundamentals**
- Basic SELECT, WHERE, ORDER BY
- Joins (INNER, LEFT, RIGHT)
- Aggregate functions

### **Week 2: Intermediate**
- Subqueries and CTEs
- Window functions
- Indexes and performance

### **Week 3: Advanced**
- Stored procedures and triggers
- Database design principles
- Query optimization

### **Week 4: Practice**
- Complex queries
- Performance tuning
- Mock interviews

---

**🎯 Master SQL for your next interview! Practice daily and query confidently! 💪**