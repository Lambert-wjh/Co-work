package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import database.DAO;
import entity.business.Project;
import entity.enums.Position;
import entity.staff.Employee;
import entity.staff.Leader;
import entity.staff.Superuser;
import entity.staff.Team;

public class Factory {
    private DAO dao;

    public Factory() {
        this.dao = new DAO();
    }

    public Employee getEmployee(String require_id) {
        return this.getStaff(require_id);
    }

    public Leader getLeader(String require_id) {
        return (Leader) this.getStaff(require_id);
    }

    public Superuser getSuperuser(String require_id) {
        return (Superuser) this.getStaff(require_id);
    }

    public Employee getStaff(String require_id) {
        String select_clause = "SELECT * FROM Employee WHERE id=?";
        List<String> parameters = new ArrayList<>();
        parameters.add(require_id);
        List<Employee> staff = this.dao.createStaff(select_clause, parameters);

        if (staff.size() == 0) {
            System.err.println("No such staff");
            return null;
        }
        return staff.get(0);
    }

    public Team getTeam(String require_leader_id) {
        String select_clause = "SELECT * FROM Employee WHERE leader_id=?";
        List<String> parameters = new ArrayList<>();
        parameters.add(require_leader_id);

        Leader leader = this.getLeader(require_leader_id);
        List<Employee> employees = this.dao.createStaff(select_clause, parameters);
        int member_count = employees.size() + 1;
        double sales_total = leader.getSales();
        for (Employee employee : employees) {
            sales_total += employee.getSales();
        }

        return new Team(leader, employees, member_count, sales_total);
    }


    public List<Project> getProjects(String require_id, Position position) {
        String select_clause = null;
        List<String> parameters = new ArrayList<>();

        switch (position) {
            case EMPLOYEE -> {
                select_clause =
                        "SELECT * FROM Project WHERE leader_id IN (SELECT leader_id FROM Employee WHERE id=?) AND status!='ARCHIVED' AND status !='REVOKED'";
                parameters.add(require_id);
            }
            case LEADER -> {
                select_clause = "SELECT * FROM Project WHERE leader_id=?";
                parameters.add(require_id);
            }
            case SUPERUSER -> {
                select_clause = "SELECT * FROM Project";
            }
        }
        return this.dao.createProjects(select_clause, parameters);
    }

    public List<Project> getProjects(String code_a, String code_b, LocalDate start,
            String require_id, Position position) {
        String select_clause = null;
        List<String> parameters = new ArrayList<>();

        switch (position) {
            case EMPLOYEE -> {
            }
            case LEADER -> {
                select_clause =
                        "SELECT * FROM Project WHERE leader_id=? AND code_a=? AND code_b=? AND start=? AND status!='COMPLETED' AND status!='ARCHIVED' AND status!='REVOKED'";
                parameters.add(require_id);
                parameters.add(code_a);
                parameters.add(code_b);
                parameters.add(Project.getDateFormatter().format(start));
            }
            case SUPERUSER -> {
                select_clause = "SELECT * FROM Project WHERE code_a=? AND code_b=? AND start=?";
                parameters.add(code_a);
                parameters.add(code_b);
                parameters.add(Project.getDateFormatter().format(start));
            }
        }
        return this.dao.createProjects(select_clause, parameters);
    }
}
