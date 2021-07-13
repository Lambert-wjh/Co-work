package entity.staff;

import entity.StaffFactory;
import entity.enums.Position;
import entity.enums.Sex;

public class Leader extends Employee {
    public Leader(String id, String name, Sex sex, int age, String leader_id, Position position,
            double sales, double salary, String password) {
        super(id, name, sex, age, leader_id, position, sales, salary, password);
    }

    public void checkTeamInfo() {
        StaffFactory staff_factory = new StaffFactory();
        Team team = staff_factory.getTeam(this.id);

        System.out.print(team);
    }
}
