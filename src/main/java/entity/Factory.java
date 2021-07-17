/*
 * This file is part of the Factory Pattern in the HeadhunterMS project. It contains all Factory
 * Pattern related functions.
 */
package entity;

import java.time.LocalDate;
import java.util.List;
import database.DAO;
import entity.business.Project;
import entity.enums.Position;
import entity.staff.Employee;
import entity.staff.Leader;
import entity.staff.Superuser;
import entity.staff.Team;

/*
 * In the class Factory, we provide all different get functions to get object.
 */
public class Factory {
    private static final Factory INSTANCE = new Factory();

    private Factory() {}

    public static Factory getFactory() {
        return INSTANCE;
    }

    public Team getTeam(String leader_id) {
        String select_clause = "SELECT * FROM Team WHERE leader_id=?";
        List<String> parameters = List.of(leader_id);
        List<Team> teams = DAO.getDAO().createTeams(select_clause, parameters);

        if (teams.size() != 0) {
            return teams.get(0);
        } else {
            return null;
        }
    }

    public List<Team> getTeams(String select_clause, List<String> parameters) {
        return DAO.getDAO().createTeams(select_clause, parameters);
    }

    public Employee getStaff(String id) {
        String select_clause = "SELECT * FROM Employee WHERE id=?";
        List<String> parameters = List.of(id);
        List<Employee> staff = DAO.getDAO().createStaff(select_clause, parameters);

        if (staff.size() != 0) {
            return staff.get(0);
        } else {
            return null;
        }
    }

    public List<Employee> getStaff(String select_clause, List<String> parameters) {
        return DAO.getDAO().createStaff(select_clause, parameters);
    }

    public Employee getEmployee(String id) {
        Employee employee = this.getStaff(id);

        if (employee != null && employee.getPosition() == Position.EMPLOYEE) {
            return employee;
        } else {
            return null;
        }
    }

    public Leader getLeader(String id) {
        Leader leader = (Leader) this.getStaff(id);

        if (leader != null && leader.getPosition() == Position.LEADER) {
            return leader;
        } else {
            return null;
        }
    }

    public Superuser getSuperuser(String id) {
        Superuser superuser = (Superuser) this.getStaff(id);

        if (superuser != null && superuser.getPosition() == Position.SUPERUSER) {
            return superuser;
        } else {
            return null;
        }
    }

    public Project getProject(String code_a, String code_b, LocalDate start) {
        String select_clause = "SELECT * FROM Project WHERE code_a =? AND code_b=? AND start=?";
        List<String> parameters = List.of(code_a, code_b, start.format(Project.getDateFormatter()));
        List<Project> projects = DAO.getDAO().createProjects(select_clause, parameters);

        if (projects.size() != 0) {
            return projects.get(0);
        } else {
            return null;
        }
    }

    public List<Project> getProjects(String select_clause, List<String> parameters) {
        return DAO.getDAO().createProjects(select_clause, parameters);
    }

    public Object getAttribute(String select_clause, List<String> parameters) {
        List<Object> attributes = DAO.getDAO().createAttributes(select_clause, parameters);

        if (attributes.size() != 0) {
            return attributes.get(0);
        } else {
            return null;
        }
    }

    public List<Object> getAttributes(String select_clause, List<String> parameters) {
        return DAO.getDAO().createAttributes(select_clause, parameters);
    }
}
