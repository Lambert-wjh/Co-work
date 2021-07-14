CREATE TABLE IF NOT EXISTS Team (
    leader_id VARCHAR(15) PRIMARY KEY,
    member_count INTEGER NOT NULL,
    sales_total DOUBLE DEFAULT 0.0
)CHARSET=utf8;

CREATE TABLE IF NOT EXISTS Employee (
    id VARCHAR(15) PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    sex ENUM('MALE', 'FEMALE') NOT NULL DEFAULT 'MALE',
    age INTEGER NOT NULL CHECK (age >= 18 AND age <= 65),
    leader_id VARCHAR(15),
    position ENUM('EMPLOYEE', 'LEADER', 'SUPERUSER') NOT NULL DEFAULT 'EMPLOYEE',
    sales DOUBLE DEFAULT 0.0,
    salary DOUBLE,
    password VARCHAR(20) NOT NULL DEFAULT '123456',
    FOREIGN KEY(leader_id) REFERENCES Team(leader_id)
) CHARSET = utf8;

CREATE TABLE IF NOT EXISTS Project(
    code_a VARCHAR(30),
    code_b VARCHAR(30),
    start DATE,
    PRIMARY KEY (code_a, code_b, start),
    amount DOUBLE NOT NULL,
    status ENUM('IN_PROGRESS', 'COMPLETED', 'PAUSED', 'ARCHIVED', 'REVOKED') DEFAULT 'IN_PROGRESS' NOT NULL,
    leader_id VARCHAR(15) NOT NULL,
    FOREIGN KEY(leader_id) REFERENCES Team(leader_id)
)CHARSET=utf8;
