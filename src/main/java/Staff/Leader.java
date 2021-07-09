package Staff;

import Permission.Leader.*;
import Tools.Enums.Position;
import Tools.Enums.Sex;

public class Leader extends Empolyee implements Staff_Related, Project_Related {
    public Leader(String name, Sex sex, int age, String number,
                  String leader_number, Position position, double sales, double salary) {
        super(name, sex, age, number, leader_number, position, sales, salary);
    }

    @Override
    public void CheckMembersInfo() {
    }

    @Override
    public void UpdateProjectStatus(Status status) {
    }
}
