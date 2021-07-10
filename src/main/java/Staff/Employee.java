package Staff;

import Permission.Employee.*;
import Tools.Enums.Position;
import Tools.Enums.Sex;
import Tools.Account;

public class Employee extends Person implements Staff_Related, Project_Related {
    protected String id;
    protected Account account;
    protected String leader_id;
    protected Position position;
    protected double sales;
    protected double salary;

    public Employee(String name, Sex sex, int age, String id, String leader_id, Position position, double sales,
            double salary, String password) {
        super(name, sex, age);
        this.id = id;
        this.account = new Account(id, password);
        this.leader_id = leader_id;
        this.position = position;
        this.sales = sales;
        this.salary = salary;
    }

    public Account GetAccount() {
        return this.account;
    }

    public void SetLeaderNumber(String leader_id) {
        this.leader_id = leader_id;
    }

    public String GetLeaderNumber() {
        return this.leader_id;
    }

    public void SetPosition(Position position, String leader_id) {
        this.position = position;
        this.SetLeaderNumber(leader_id);
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

    @Override
    public void CheckEmployeeInfo() {
    }

    @Override
    public void ChangePassword() {
    }

    @Override
    public void CheckProjectInfo() {
    }
}
