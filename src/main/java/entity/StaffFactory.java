package entity;

import database.dao.StaffDAO;
import entity.enums.Position;
import entity.staff.Employee;
import entity.staff.Leader;
import entity.staff.Superuser;

public class StaffFactory {
    private StaffDAO staff_dao;

    public StaffFactory(StaffDAO staff_dao) {
        this.staff_dao = staff_dao;
    }

    public StaffFactory() {
        this(new StaffDAO());
    }

    private Employee getStaff(String require_id, Position require_position) {
        return this.staff_dao.createStaff(require_id, require_position);
    }

    public Employee getEmployee(String require_id) {
        return getStaff(require_id, Position.EMPLOYEE);
    }

    public Leader getLeader(String require_id) {
        return (Leader) getStaff(require_id, Position.LEADER);
    }

    public Superuser getSuperuser(String require_id) {
        return (Superuser) getStaff(require_id, Position.SUPERUSER);
    }
}
