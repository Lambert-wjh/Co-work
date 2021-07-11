package Tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Staff.Employee;
import Staff.Leader;
import Staff.Superuser;
import Tools.Enums.Position;
import Tools.Enums.Sex;

public class StaffFactory {
    private Employee GetStaff(String require_id, Position require_position) {
        Employee staff = null;
        JDBC jdbc = JDBC.GetJDBC();

        try (Connection conn = jdbc.GetConnection()) {
            // Staff's attributes: id, name, sex, age, leader_id, position, sales, salary,
            // password
            try (PreparedStatement prst = conn.prepareStatement("SELECT * FROM Staff WHERE id=?")) {
                prst.setObject(1, require_id);
                try (ResultSet rese = prst.executeQuery()) {
                    if (rese.next()) {
                        String id = rese.getString("id");
                        String name = rese.getString("name");
                        Sex sex = Sex.valueOf(rese.getString("sex"));
                        int age = rese.getInt("age");
                        String leader_id = rese.getString("leader_id");
                        Position position = Position.valueOf(rese.getString("position"));
                        double sales = rese.getDouble("sales");
                        double salary = rese.getDouble("salary");
                        String password = rese.getString("password");
                        switch (require_position) {
                            case EMPLOYEE -> {
                                staff = new Employee(name, sex, age, id, leader_id, position, sales, salary, password);
                            }
                            case LEADER -> {
                                staff = new Leader(name, sex, age, id, leader_id, position, sales, salary, password);
                            }
                            case SUPERUSER -> {
                                staff = new Superuser(name, sex, age, id, leader_id, position, sales, salary, password);
                            }
                        }
                    } else {
                        throw new IllegalArgumentException("No such staff");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staff;
    }

    public List<Employee> GetEmployees(String require_leader_id) {
        List<Employee> employees = new ArrayList<>();
        JDBC jdbc = JDBC.GetJDBC();

        try (Connection conn = jdbc.GetConnection()) {
            try (PreparedStatement prst = conn.prepareStatement("SELECT * FROM Staff Where leader_id=?")) {
                prst.setObject(1, require_leader_id);
                try (ResultSet rese = prst.executeQuery()) {
                    while (rese.next()) {
                        String id = rese.getString("id");
                        String name = rese.getString("name");
                        Sex sex = Sex.valueOf(rese.getString("sex"));
                        int age = rese.getInt("age");
                        String leader_id = rese.getString("leader_id");
                        Position position = Position.valueOf(rese.getString("position"));
                        double sales = rese.getDouble("sales");
                        double salary = rese.getDouble("salary");
                        String password = rese.getString("password");
                        Employee employee = new Employee(name, sex, age, id, leader_id, position, sales, salary,
                                password);
                        employees.add(employee);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public Employee GetEmployee(String require_id) {
        return GetStaff(require_id, Position.EMPLOYEE);
    }

    public Leader GetLeader(String require_id) {
        return (Leader) GetStaff(require_id, Position.LEADER);
    }

    public Superuser GetSuperuser(String require_id) {
        return (Superuser) GetStaff(require_id, Position.SUPERUSER);
    }
}
