package Staff;

import Permission.Empolyee.*;

public class Empolyee extends Person implements Staff_Checkable,
    Staff_Modifiable,
    Project_Checkable {
    private String number;
    private Account account;
    private String leader_number;
    private Position position;
    private double sales;
    private double salary;

    public Empolyee(String name, Sex sex, int age, String number,
                    String leader_number, Position position, double sales, double salary) {
        super(name, sex, age);
        this.number = number;
        this.account = new Account(number);
        this.leader_number = leader_number;
        this.position = position;
        this.sales = sales;
        this.salary = salary;
    }

    public void SetLeaderNumber(String leader_number) {
        this.leader_number = leader_number;
    }

    public String GetLeaderNumber() {
        return this.leader_number;
    }

    public void SetPosition(Position position, String leader_number) {
        this.position = position;
        this.SetLeaderNumber(leader_number);
    }

    public Position GetPosition() {
        return this.position;
    }

    public void SetSales(double sales) {
        this.sales = sales;
    }

    public double GetSales() {
        return this.sales;
    }

    public void SetSalary(double salary) {
        this.salary = salary;
    }

    public double GetSalary() {
        return this.salary;
    }
}

enum Position {
    EMPOLYEE, LEADER, SUPERUSER;
}
