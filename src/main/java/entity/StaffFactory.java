package entity;

import java.util.ArrayList;
import java.util.List;
import database.dao.StaffDAO;
import entity.staff.Employee;
import entity.staff.Leader;
import entity.staff.Superuser;
import entity.staff.Team;

public class StaffFactory {
    private StaffDAO staff_dao;

    public StaffFactory() {
        this.staff_dao = new StaffDAO();
    }

    public Employee getStaff(String require_id) {
        String select_clause = "SELECT * FROM Employee WHERE id=?";
        List<String> parameters = new ArrayList<>();
        parameters.add(require_id);
        return this.staff_dao.createStaff(select_clause, parameters).get(0);
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

    public Team getTeam(String require_leader_id) {
        String select_clause = "SELECT * FROM Employee WHERE leader_id=?";
        List<String> parameters = new ArrayList<>();
        parameters.add(require_leader_id);

        Leader leader = this.getLeader(require_leader_id);
        List<Employee> employees = this.staff_dao.createStaff(select_clause, parameters);
        int member_count = employees.size() + 1;
        double sales_total = leader.getSales();
        for (Employee employee : employees) {
            sales_total += employee.getSales();
        }

        return new Team(leader, employees, member_count, sales_total);
    }
}
