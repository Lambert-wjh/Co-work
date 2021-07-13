package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import database.JDBC;
import entity.enums.Position;
import entity.enums.Sex;
import entity.staff.Employee;
import entity.staff.Leader;
import entity.staff.Superuser;

public class StaffDAO {
    private JDBC jdbc = JDBC.getJDBC();

    public List<Employee> createStaff(String select_clause, List<String> parameters) {
        List<Employee> staff = new ArrayList<>();

        try (Connection connection = jdbc.getConnection()) {
            try (PreparedStatement prepared_statement =
                    connection.prepareStatement(select_clause)) {
                for (int i = 0; i < parameters.size(); i++) {
                    prepared_statement.setObject(i + 1, parameters.get(i));
                }
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

                        switch (position) {
                            case EMPLOYEE -> {
                                staff.add(new Employee(id, name, sex, age, leader_id, position,
                                        sales, salary, password));
                            }
                            case LEADER -> {
                                staff.add(new Leader(id, name, sex, age, leader_id, position, sales,
                                        salary, password));
                            }
                            case SUPERUSER -> {
                                staff.add(new Superuser(id, name, sex, age, leader_id, position,
                                        sales, salary, password));
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staff;
    }

    public int updateStaff(String update_clause, List<String> parameters) {
        int update_count = 0;

        try (Connection connection = jdbc.getConnection()) {
            try (PreparedStatement prepared_statement =
                    connection.prepareStatement(update_clause)) {
                for (int i = 0; i < parameters.size(); i++) {
                    prepared_statement.setObject(i + 1, parameters.get(i));
                }
                update_count = prepared_statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return update_count;
    }
}
