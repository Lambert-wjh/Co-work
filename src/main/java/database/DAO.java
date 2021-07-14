package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import entity.business.Project;
import entity.enums.Position;
import entity.enums.Sex;
import entity.enums.Status;
import entity.staff.Employee;
import entity.staff.Leader;
import entity.staff.Superuser;

public class DAO {
    private static final DAO INSTANCE = new DAO();

    private DAO() {}

    public static DAO getDAO() {
        return INSTANCE;
    }

    private PreparedStatement getPreparedStatement(String sql_clause, List<String> parameters) {
        PreparedStatement prepared_statement = null;

        try {
            Connection connection = JDBC.getJDBC().getConnection();
            prepared_statement = connection.prepareStatement(sql_clause);
            for (int i = 0; i < parameters.size(); i++) {
                prepared_statement.setObject(i + 1, parameters.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prepared_statement;
    }

    public List<Employee> createStaff(String select_clause, List<String> parameters) {
        List<Employee> staff = new ArrayList<>();

        try (PreparedStatement prepared_statement =
                this.getPreparedStatement(select_clause, parameters)) {
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
                            staff.add(new Employee(id, name, sex, age, leader_id, position, sales,
                                    salary, password));
                        }
                        case LEADER -> {
                            staff.add(new Leader(id, name, sex, age, leader_id, position, sales,
                                    salary, password));
                        }
                        case SUPERUSER -> {
                            staff.add(new Superuser(id, name, sex, age, leader_id, position, sales,
                                    salary, password));
                        }
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staff;
    }

    public List<Project> createProjects(String select_clause, List<String> parameters) {
        List<Project> projects = new ArrayList<Project>();

        try (PreparedStatement prepared_statement =
                this.getPreparedStatement(select_clause, parameters)) {
            try (ResultSet result_set = prepared_statement.executeQuery()) {
                while (result_set.next()) {
                    String code_a = result_set.getString("code_a");
                    String code_b = result_set.getString("code_b");
                    LocalDate start = result_set.getDate("start").toLocalDate();
                    double amount = result_set.getDouble("amount");
                    Status status = Status.valueOf(result_set.getString("status"));

                    projects.add(new Project(code_a, code_b, start, amount, status));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }

    public int updateTable(String update_clause, List<String> parameters) {
        int update_count = 0;

        try (PreparedStatement prepared_statement =
                this.getPreparedStatement(update_clause, parameters)) {
            update_count = prepared_statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return update_count;
    }
}
