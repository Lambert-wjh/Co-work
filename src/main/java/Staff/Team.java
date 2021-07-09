package Staff;

import java.util.List;
import java.util.ArrayList;

import Business.Project;

public class Team {
    private Leader leader;
    private List<Employee> employees;
    private Project project;

    public Team(Leader leader, Employee[] employees, Project project) {
        this.leader = leader;
        this.employees = new ArrayList<>();
        for (Employee employee : employees) {
            this.employees.add(employee);
        }
        this.project = project;
    }
}
