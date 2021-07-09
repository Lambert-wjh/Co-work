package Staff;

import Permission.Superuser.*;
import Tools.Enums.Sex;
import Tools.Enums.Position;
import Tools.Enums.Status;

public class Superuser extends Leader implements Staff_Related,
    Project_Related {
    public Superuser(String name, Sex sex, int age, String id, String leader_id,
                     Position position, double sales, double salary, String password) {
        super(name, sex, age, id, leader_id, position, sales, salary, password);
    }
}
