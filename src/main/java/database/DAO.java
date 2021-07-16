package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import entity.business.Project;
import entity.enums.Position;
import entity.enums.Sex;
import entity.enums.Status;
import entity.staff.Employee;
import entity.staff.Leader;
import entity.staff.Superuser;
import entity.staff.Team;

public class DAO {
    private HikariDataSource data_source;
    private static final String JDBC_URL = "jdbc:mysql://localhost/HeadhunterMS";
    private static final String JDBC_USER = "jdbc";
    private static final String JDBC_PASSWORD = "jdbcpassword";
    private static final String CONNECTION_TIMEOUT = "1000";
    private static final String IDLE_TIMEOUT = "60000";
    private static final String MAX_POOL_SIZE = "15";
    private static final DAO INSTANCE = new DAO();

    private DAO() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(JDBC_URL);
        config.setUsername(JDBC_USER);
        config.setPassword(JDBC_PASSWORD);
        config.addDataSourceProperty("connectionTimeout", CONNECTION_TIMEOUT);
        config.addDataSourceProperty("idleTimeout", IDLE_TIMEOUT);
        config.addDataSourceProperty("maximumPoolSize", MAX_POOL_SIZE);
        data_source = new HikariDataSource(config);
    }

    public static DAO getDAO() {
        return INSTANCE;
    }

    public List<Team> createTeams(String select_clause, List<String> parameters) {
        List<Team> teams = new ArrayList<>();

        try (Connection connection = this.data_source.getConnection()) {
            try (PreparedStatement prepared_statement =
                    connection.prepareStatement(select_clause)) {
                for (int i = 0; i < parameters.size(); i++) {
                    prepared_statement.setObject(i + 1, parameters.get(i));
                }
                try (ResultSet result_set = prepared_statement.executeQuery()) {
                    while (result_set.next()) {
                        String leader_id = result_set.getString("leader_id");
                        int member_count = result_set.getInt("member_count");
                        double sales_total = result_set.getDouble("sales_total");

                        Leader leader = (Leader) createStaff("SELECT * FROM Employee WHERE id=?",
                                Arrays.asList(leader_id)).get(0);
                        List<Employee> employees =
                                createStaff("SELECT * FROM Employee WHERE leader_id=?",
                                        Arrays.asList(leader_id));

                        teams.add(new Team(leader, employees, member_count, sales_total));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teams;
    }

    public List<Employee> createStaff(String select_clause, List<String> parameters) {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = this.data_source.getConnection()) {
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
                                employees.add(new Employee(id, name, sex, age, leader_id, position,
                                        sales, salary, password));
                            }
                            case LEADER -> {
                                employees.add(new Leader(id, name, sex, age, leader_id, position,
                                        sales, salary, password));
                            }
                            case SUPERUSER -> {
                                employees.add(new Superuser(id, name, sex, age, leader_id, position,
                                        sales, salary, password));
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public List<Project> createProjects(String select_clause, List<String> parameters) {
        List<Project> projects = new ArrayList<Project>();

        try (Connection connection = this.data_source.getConnection()) {
            try (PreparedStatement prepared_statement =
                    connection.prepareStatement(select_clause)) {
                for (int i = 0; i < parameters.size(); i++) {
                    prepared_statement.setObject(i + 1, parameters.get(i));
                }
                try (ResultSet result_set = prepared_statement.executeQuery()) {
                    while (result_set.next()) {
                        String code_a = result_set.getString("code_a");
                        String code_b = result_set.getString("code_b");
                        LocalDate start = result_set.getDate("start").toLocalDate();
                        double amount = result_set.getDouble("amount");
                        Status status = Status.valueOf(result_set.getString("status"));
                        String leader_id = result_set.getString("leader_id");

                        projects.add(new Project(code_a, code_b, start, amount, status, leader_id));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }

    public List<Object> createAttributes(String select_clause, List<String> parameters) {
        List<Object> attributes = new ArrayList<>();

        try (Connection connection = this.data_source.getConnection()) {
            try (PreparedStatement prepared_statement =
                    connection.prepareStatement(select_clause)) {
                for (int i = 0; i < parameters.size(); i++) {
                    prepared_statement.setObject(i + 1, parameters.get(i));
                }
                try (ResultSet result_set = prepared_statement.executeQuery()) {
                    ResultSetMetaData result_set_meta = result_set.getMetaData();
                    while (result_set.next()) {
                        for (int i = 0; i < result_set_meta.getColumnCount(); i++) {
                            attributes.add(result_set.getObject(i + 1));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attributes;
    }

    public int updateTable(String update_clause, List<String> parameters) {
        int update_count = 0;

        try (Connection connection = this.data_source.getConnection()) {
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
