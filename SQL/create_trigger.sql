delimiter ||
CREATE TRIGGER Tri_Insert 
    BEFORE INSERT ON Employee
    FOR EACH ROW
BEGIN
    IF new.position = 'EMPLOYEE' THEN
         SET new.salary = 6000 + 0.15 * new.sales;
    END IF;
    IF new.position = 'LEADER' THEN
         SET new.salary = 8000 + 0.15 * new.sales;
    END IF;
    IF new.position = 'SUPERUSER' THEN
         SET new.salary = 12000 + 0.15 * new.sales;
    END IF;
END; ||

delimiter ||
CREATE TRIGGER Tri_Update
    BEFORE UPDATE ON Employee
    FOR EACH ROW
BEGIN
    IF new.position = 'EMPLOYEE' THEN
        SET new.salary = 6000 + 0.15 * new.sales;
    END IF;
    IF new.position = 'LEADER' THEN
        SET new.salary = 8000 + 0.15 * new.sales;
    END IF;
    IF new.position = 'SUPERUSER' THEN
        SET new.salary = 12000 + 0.15 * new.sales;
    END IF;
END; ||