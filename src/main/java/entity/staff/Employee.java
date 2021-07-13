package entity.staff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import entity.Account;
import entity.Factory;
import entity.MethodSet;
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

    public Account getAccount() {
        return this.account;
    }

    public Position getPosition() {
        return this.position;
    }

    public double getSales() {
        return this.sales;
    }

    @Override
    public String toString() {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Employee.getFieldName());
        rows.add(this.getFieldValue());
        return MethodSet.formatAsTable(rows);
    }

    public static List<String> getFieldName() {
        return Arrays.asList("ID", "Name", "Sex", "Age", "Position", "Sales", "Salary");
    }

    public List<String> getFieldValue() {
        return Arrays.asList(this.id, this.name, this.sex.name(),
                Integer.valueOf(this.age).toString(), this.position.name(),
                Double.valueOf(this.sales).toString(), Double.valueOf(this.salary).toString());
    }

    public void changePassword() {
        this.account.setPassword();
    }

    public void checkStaffInfo() {
        System.out.print(this);
    }

    public void checkProjectInfo() {
        Factory factory = new Factory();
        List<Project> projects = factory.getProjects(this.id, this.position);
        List<List<String>> rows = new ArrayList<>();

        rows.add(Project.getFieldName());
        if (projects.size() != 0) {
            for (Project project : projects) {
                rows.add(project.getFieldValue());
            }
        } else {
            System.err.println("You have not taken over any projects");
        }
        System.out.print(MethodSet.formatAsTable(rows));
    }
}
