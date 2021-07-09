package Staff;

import Permission.Superuser.*;

public class Superuser extends Leader implements Staff_Related,
    Project_Related {
    public Superuser(String name, Sex sex, int age, String number,
                     String leader_number, Position position, double sales, double salary) {
        super(name, sex, age, number, leader_number, position, sales, salary);
    }
}
