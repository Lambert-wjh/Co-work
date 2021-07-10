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
    private Superuser CreateStaff(String require_id) {
        Superuser staff = null;
        JDBC jdbc = JDBC.GetJDBC();
        try (Connection conn = jdbc.GetConnection()) {
            // Staff's attributes: id, name, sex, age, leader_id, position, sales, salary,
            // password
            try (PreparedStatement prepared_statement = conn.prepareStatement("SELECT * FROM staff Where id=?")) {
                prepared_statement.setObject(1, require_id);
                try (ResultSet result_set = prepared_statement.executeQuery()) {
                    while (result_set.next()) {
                        String id = result_set.getString("id");
                        String name = result_set.getString("name");
                        Sex sex = (Sex) result_set.getObject("sex");
                        int age = result_set.getInt("age");
                        String leader_id = result_set.getString("leader_id");
                        Position position = (Position) result_set.getObject("position");
                        double sales = result_set.getDouble("sales");
                        double salary = result_set.getDouble("salary");
                        String password = result_set.getString("password");
                        staff = new Superuser(name, sex, age, id, leader_id, position, sales, salary, password);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Database connect failed");
        }
        return staff;
    }

    public Employee CreateEmployee(String require_id) {
        return CreateStaff(require_id);
    }

    public Leader CreateLeader(String require_id) {
        return CreateStaff(require_id);
    }

    public Superuser CreateSuperuser(String require_id) {
        return CreateStaff(require_id);
    }
}
