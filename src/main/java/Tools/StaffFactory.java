package Tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Staff.Employee;
import Staff.Leader;
import Staff.Superuser;
import Tools.Enums.Position;
import Tools.Enums.Sex;

public class StaffFactory {
    private Employee CreateStaff(String require_id, Position require_position) {
        Employee staff = null;
        JDBC jdbc = JDBC.GetJDBC();
        try (Connection conn = jdbc.GetConnection()) {
            // Staff's attributes: id, name, sex, age, leader_id, position, sales, salary,
            // password
            try (PreparedStatement prepared_statement = conn.prepareStatement("SELECT * FROM Staff Where id=?")) {
                prepared_statement.setObject(1, require_id);
                try (ResultSet result_set = prepared_statement.executeQuery()) {
                    while (result_set.next()) {
                        String id = result_set.getString("id");
                        String name = result_set.getString("name");
                        Sex sex = Sex.valueOf(result_set.getString("sex"));
                        int age = result_set.getInt("age");
                        String leader_id = result_set.getString("leader_id");
                        Position position = Position.valueOf(result_set.getString("position"));
                        double sales = result_set.getDouble("sales");
                        double salary = result_set.getDouble("salary");
                        String password = result_set.getString("password");
                        switch (require_position) {
                            case EMPLOYEE -> staff = new Employee(name, sex, age, id, leader_id, position, sales,
                                    salary, password);
                            case LEADER -> staff = new Leader(name, sex, age, id, leader_id, position, sales, salary,
                                    password);
                            case SUPERUSER -> staff = new Superuser(name, sex, age, id, leader_id, position, sales,
                                    salary, password);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Database connect failed");
        }
        return staff;
    }

    public Employee CreateEmployee(String require_id) {
        return CreateStaff(require_id, Position.EMPLOYEE);
    }

    public Leader CreateLeader(String require_id) {
        return (Leader) CreateStaff(require_id, Position.LEADER);
    }

    public Superuser CreateSuperuser(String require_id) {
        return (Superuser) CreateStaff(require_id, Position.SUPERUSER);
    }
}
