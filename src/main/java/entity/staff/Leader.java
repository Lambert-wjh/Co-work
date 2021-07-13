package entity.staff;

import entity.Factory;
import entity.enums.Position;
import entity.enums.Sex;

public class Leader extends Employee {
    public Leader(String id, String name, Sex sex, int age, String leader_id, Position position,
            double sales, double salary, String password) {
        super(id, name, sex, age, leader_id, position, sales, salary, password);
    }

    public void checkTeamInfo() {
        Factory factory = new Factory();
        Team team = factory.getTeam(this.id);

        System.out.print(team);
    }
}
