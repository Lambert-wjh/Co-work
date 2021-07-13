package entity;

import java.util.ArrayList;
import java.util.List;
import database.dao.StaffDAO;
import entity.staff.Employee;
import entity.staff.Leader;
import entity.staff.Superuser;

public class StaffFactory {
    private StaffDAO staff_dao;

    public StaffFactory(StaffDAO staff_dao) {
        this.staff_dao = staff_dao;
    }

    public Employee getStaff(String require_id) {
        String select_clause = "SELECT * FROM Employee WHERE id=?";
        List<String> parameters = new ArrayList<>();
        parameters.add(require_id);
        return this.staff_dao.createStaff(select_clause, parameters).get(0);
    }

    public Employee getEmployee(String require_id) {
        return getStaff(require_id);
    }

    public Leader getLeader(String require_id) {
        return (Leader) getStaff(require_id);
    }

    public Superuser getSuperuser(String require_id) {
        return (Superuser) getStaff(require_id);
    }
}
