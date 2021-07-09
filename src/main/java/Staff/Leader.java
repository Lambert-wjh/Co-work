package Staff;

import Permission.Leader.*;
import Tools.Enums.Position;
import Tools.Enums.Sex;
import Tools.Enums.Status;

public class Leader extends Employee implements Staff_Related, Project_Related {
    public Leader(String name, Sex sex, int age, String id, String leader_id,
                  Position position, double sales, double salary, String password) {
        super(name, sex, age, id, leader_id, position, sales, salary, password);
    }


    @Override
    public void CheckMembersInfo() {
    }

    @Override
    public void UpdateProjectStatus(Status status) {
    }
}
