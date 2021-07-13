package entity.staff;

import java.util.List;
import entity.Account;
import entity.business.Project;
import entity.enums.Position;
import entity.enums.Sex;

public class Employee extends Person {
    protected String id;
    protected Account account;
    protected String leader_id;
    protected Position position;
    protected double sales;
    protected double salary;

    public Employee(String id, String name, Sex sex, int age, String leader_id, Position position,
            double sales, double salary, String password) {
        super(name, sex, age);
        this.id = id;
        this.account = new Account(id, password);
        this.leader_id = leader_id;
        this.position = position;
        this.sales = sales;
        this.salary = salary;
    }

    public Position getPosition() {
        return this.position;
    }

    public Account getAccount() {
        return this.account;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append("\nName: ").append(name).append("\nSex: ").append(sex)
                .append("\nAge: ").append(age).append("\nPosition: ").append(position)
                .append("\nSales: ").append(sales).append("\nSalary: ").append(salary);

        return sb.toString();
    }

    public void changePassword() {
        this.account.setPassword();
    }

    public void checkStaffInfo() {
        System.out.println(this);
    }

    public void checkProjectInfo() {
        List<Project> projects = Project.getProjects(this.id, this.position);
        if (projects.size() != 0) {
            for (Project project : projects) {
                System.out.println(project);
            }
        } else {
            System.err.println("You have not taken over any project yet");
        }
    }
}
