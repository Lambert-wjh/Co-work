package Staff;

import Permission.Leader.*;

public class Leader extends Empolyee implements Staff_Checkable,
    Staff_Modifiable,
    Project_Checkable,
    Project_Modifiable {

    public Leader(String name, Sex sex, int age, String number,
                  String leader_number, Position position, double sales, double salary) {
        super(name, sex, age, number, leader_number, position, sales, salary);
    }
}
