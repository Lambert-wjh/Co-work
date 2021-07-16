INSERT INTO Team
(leader_id, member_count)
VALUES
('AAA00000', 5);

INSERT INTO Team
(leader_id, member_count)
VALUES
('AAA00001', 8);

INSERT INTO Team
(leader_id, member_count)
VALUES
('AAA00009', 7);

INSERT INTO Team
(leader_id, member_count)
VALUES
('AAA00016', 6);

INSERT INTO Team
(leader_id, member_count)
VALUES
('AAA00022', 6);

INSERT INTO Team
(leader_id, member_count)
VALUES
('AAA00028', 7);


INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00000', 'ROOT', 'MALE', 24, NULL, 'SUPERUSER', 0, 0, '123456');


INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00001', 'Mike', 'MALE', 30, 'AAA00000', 'LEADER', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00002', 'Aron', 'MALE', 35, 'AAA00001', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00003', 'Zhang', 'MALE', 45, 'AAA00001', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00004', 'Shankar', 'FEMALE', 28, 'AAA00001', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00005', 'Brandt', 'MALE', 54, 'AAA00001', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00006', 'Chavez', 'FEMALE', 37, 'AAA00001', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00007', 'Peltier', 'MALE', 28, 'AAA00001', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00008', 'Levy', 'FEMALE', 24, 'AAA00001', 'EMPLOYEE', 0, 0, '123456');


INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00009', 'Williams', 'FEMALE', 34, 'AAA00000', 'LEADER', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00010', 'Snow', 'FEMALE', 35, 'AAA00009', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00011', 'Brown', 'MALE', 62, 'AAA00009', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00012', 'Aoi', 'FEMALE', 28, 'AAA00009', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00013', 'Bourikas', 'MALE', 33, 'AAA00009', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00014', 'Tanaka', 'MALE', 32, 'AAA00009', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00015', 'Srinivasan', 'MALE', 29, 'AAA00009', 'EMPLOYEE', 0, 0, '123456');


INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00016', 'Wu', 'MALE', 42, 'AAA00000', 'LEADER', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00017', 'Mozart', 'MALE', 29, 'AAA00016', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00018', 'Einstein', 'MALE', 19, 'AAA00016', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00019', 'El Said', 'FEMALE', 41, 'AAA00016', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00020', 'Gold', 'MALE', 37, 'AAA00016', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00021', 'Katz', 'FEMALE', 51, 'AAA00016', 'EMPLOYEE', 0, 0, '123456');


INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00022', 'Califieri', 'MALE', 27, 'AAA00000', 'LEADER', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00023', 'Singh', 'FEMALE', 22, 'AAA00022', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00024', 'Crick', 'MALE', 25, 'AAA00022', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00025', 'Kim', 'MALE', 42, 'AAA00022', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00026', 'Osinski', 'MALE', 34, 'AAA00022', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00027', 'Skeen', 'FEMALE', 36, 'AAA00022', 'EMPLOYEE', 0, 0, '123456');


INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00028', 'Juan', 'MALE', 34, 'AAA00000', 'LEADER', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00029', 'Sagawe', 'FEMALE', 28, 'AAA00028', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00030', 'Yen', 'FEMALE', 21, 'AAA00028', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00031', 'Drews', 'MALE', 45, 'AAA00028', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00032', 'Isogai', 'FEMALE', 32, 'AAA00028', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00033', 'Baroni', 'MALE', 22, 'AAA00028', 'EMPLOYEE', 0, 0, '123456');

INSERT INTO Employee
(id, name, sex, age, leader_id, position, sales, salary, password)
VALUES
('AAA00034', 'Neru', 'MALE', 20, 'AAA00028', 'EMPLOYEE', 0, 0, '123456');


INSERT INTO Project
(code_a, code_b, start, amount, status, leader_id)
VALUES
('AAPL', 'AMZN', '2021-07-01', 30000, 'COMPLETED', 'AAA00001');

INSERT INTO Project
(code_a, code_b, start, amount, status, leader_id)
VALUES
('0312', '9988', '2021-07-05', 80000, 'COMPLETED', 'AAA00001');

INSERT INTO Project
(code_a, code_b, start, amount, status, leader_id)
VALUES
('MSFT', 'TSLA', '2021-06-15', 80000, 'IN_PROGRESS', 'AAA00001');

INSERT INTO Project
(code_a, code_b, start, amount, status, leader_id)
VALUES
('NVDA', 'INTC', '2021-07-04', 60000, 'PAUSED', 'AAA00001');

INSERT INTO Project
(code_a, code_b, start, amount, status, leader_id)
VALUES
('AMD', 'INTC', '2021-07-04', 100000, 'IN_PROGRESS', 'AAA00009');

INSERT INTO Project
(code_a, code_b, start, amount, status, leader_id)
VALUES
('0312', '0700', '2021-07-06', 120000, 'COMPLETED', 'AAA00009');

INSERT INTO Project
(code_a, code_b, start, amount, status, leader_id)
VALUES
('0700', '9988', '2021-07-04', 120000, 'IN_PROGRESS', 'AAA00016');

INSERT INTO Project
(code_a, code_b, start, amount, status, leader_id)
VALUES
('INTC', 'AMD', '2021-05-04', 70000, 'ARCHIVED', 'AAA00001');

INSERT INTO Project
(code_a, code_b, start, amount, status, leader_id)
VALUES
('0700', 'AAPL', '2021-03-04', 90000, 'REVOKED', 'AAA00001');
