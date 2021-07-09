package Staff;

import java.util.List;
import java.util.ArrayList;

import Business.Project;

public class Team {
    private Leader leader;
    private List<Empolyee> employees;
    private Project project;

    public Team(Leader leader, Empolyee[] empolyees, Project project) {
        this.leader = leader;
        this.employees = new ArrayList<>();
        for (Empolyee empolyee : empolyees) {
            this.employees.add(empolyee);
        }
        this.project = project;
    }
}
