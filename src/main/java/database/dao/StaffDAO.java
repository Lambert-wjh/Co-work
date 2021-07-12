package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.JDBC;
import entity.enums.Position;
import entity.enums.Sex;
import entity.staff.Employee;
import entity.staff.Leader;
import entity.staff.Superuser;

public class StaffDAO {
    private JDBC jdbc = JDBC.getJDBC();

    public Employee createStaff(String require_id, Position require_position) {
        Employee staff = null;

        try (Connection connection = jdbc.getConnection()) {
            try (PreparedStatement prepared_statement =
                    connection.prepareStatement("SELECT * FROM Employee WHERE id=?")) {
                prepared_statement.setObject(1, require_id);
                try (ResultSet result_set = prepared_statement.executeQuery()) {
                    if (result_set.next()) {
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
                            case EMPLOYEE -> {
                                staff = new Employee(id, name, sex, age, leader_id, position, sales,
                                        salary, password);
                            }
                            case LEADER -> {
                                staff = new Leader(id, name, sex, age, leader_id, position, sales,
                                        salary, password);
                            }
                            case SUPERUSER -> {
                                staff = new Superuser(id, name, sex, age, leader_id, position,
                                        sales, salary, password);
                            }
                        }
                    } else {
                        System.err.println("No such staff");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staff;
    }

    public Position verifyPassword(String input_account, String input_password) {
        Position position = null;

        try (Connection connection = jdbc.getConnection()) {
            try (PreparedStatement prepared_statement = connection
                    .prepareStatement("SELECT password, position FROM Employee WHERE id=?")) {
                prepared_statement.setObject(1, input_account);
                try (ResultSet result_set = prepared_statement.executeQuery()) {
                    if (result_set.next()) {
                        String password = result_set.getString("password");
                        if (password.equals(input_password)) {
                            position = Position.valueOf(result_set.getString("position"));
                        } else {
                            System.err.println("Password is not correct");
                        }
                    } else {
                        System.err.println("No such staff");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return position;
    }

    public void updatePassword(String account, String new_password) throws SQLException {
        try (Connection connection = jdbc.getConnection()) {
            try (PreparedStatement prepared_statement =
                    connection.prepareStatement("UPDATE Employee SET password=? WHERE id=?")) {
                prepared_statement.setObject(1, new_password);
                prepared_statement.setObject(2, account);
                if (prepared_statement.executeUpdate() != 1) {
                    throw new SQLException("Update password failed");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
