/*
 * This file is part of the Staff in the HeadhunterMS project. It contains all Employee's data
 * structures and related functions listed in Employee's menu.
 */
package ssdut.cli.headhunterms.staff;

import java.util.ArrayList;
import java.util.List;
import ssdut.cli.headhunterms.Factory;
import ssdut.cli.headhunterms.MethodSet;
import ssdut.cli.headhunterms.business.Project;
import ssdut.cli.headhunterms.enums.Position;
import ssdut.cli.headhunterms.enums.Sex;

public class Employee extends Person {
    protected String id;
    protected Account account;
    protected String leader_id;
    protected Position position;
    protected double sales;
    protected double salary;

    public Employee(
            String id,
            String name,
            Sex sex,
            int age,
            String leader_id,
            Position position,
            double sales,
            double salary,
            String password) {
        super(name, sex, age);
        this.id = id;
        this.account = new Account(id, password);
        this.leader_id = leader_id;
        this.position = position;
        this.sales = sales;
        this.salary = salary;
    }

    /*
     * All get functions :
     *
     * @getId()
     *
     * @getAccount()
     *
     * @getPosition()
     *
     * @getSales()
     */
    public String getId() {
        return this.id;
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

    /*
     * Format the employee's information to a String :
     *
     * @toString()
     *
     * @getFieldName()
     *
     * @getFieldValue()
     */
    @Override
    public String toString() {
        List<List<String>> rows = new ArrayList<>();
        rows.addAll(List.of(Employee.getFieldName(), this.getFieldValue()));

        return MethodSet.formatAsTable(rows);
    }

    public static List<String> getFieldName() {
        return List.of("ID", "Name", "Sex", "Age", "Position", "Sales", "Salary");
    }

    public List<String> getFieldValue() {
        return List.of(
                this.id,
                this.name,
                this.sex.name(),
                String.valueOf(this.age),
                this.position.name(),
                String.valueOf(this.sales),
                String.valueOf(this.salary));
    }

    // Modify Employee's password
    public void changePassword() {
        this.account.setPassword();
    }

    // Call the toString() @override to print employee's information
    public void checkStaffInfo() {
        System.out.print(this);
    }

    // Check the projects information as employee privileges.
    public void checkProjectInfo() {
        String select_clause = null;
        List<String> parameters = new ArrayList<>();
        switch (this.position) {
            case EMPLOYEE -> {
                select_clause =
                        "SELECT * FROM Project WHERE leader_id IN (SELECT leader_id FROM Employee WHERE id=?) AND status!='ARCHIVED' AND status !='REVOKED'";
                parameters.add(this.id);
            }
            case LEADER -> {
                select_clause = "SELECT * FROM Project WHERE leader_id=?";
                parameters.add(this.id);
            }
            case SUPERUSER -> {
                select_clause = "SELECT * FROM Project";
            }
        }
        List<Project> projects = Factory.getFactory().getProjects(select_clause, parameters);

        if (projects.size() == 0) {
            System.err.println("You have not taken over any projects");
            return;
        }

        List<List<String>> rows = new ArrayList<>();
        rows.add(Project.getFieldName());
        for (Project project : projects) {
            rows.add(project.getFieldValue());
        }
        System.out.print(MethodSet.formatAsTable(rows));
    }
}
