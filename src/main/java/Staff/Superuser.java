package Staff;

import Permission.Superuser.*;

public class Superuser extends Leader implements Staff_Checkable,
    Staff_Modifiable,
    Project_Checkable,
    Project_Modifiable {

    public Superuser(String name, Sex sex, int age, String number,
                     String leader_number, Position position, double sales, double salary) {
        super(name, sex, age, number, leader_number, position, sales, salary);
    }
}
