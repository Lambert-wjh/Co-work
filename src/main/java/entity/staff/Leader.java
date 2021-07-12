package entity.staff;

import entity.enums.Position;
import entity.enums.Sex;

public class Leader extends Employee {
    public Leader(String id, String name, Sex sex, int age, String leader_id, Position position,
            double sales, double salary, String password) {
        super(id, name, sex, age, leader_id, position, sales, salary, password);
    }
}
