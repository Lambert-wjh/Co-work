package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import database.JDBC;
import entity.business.Company;
import entity.business.Project;
import entity.enums.Status;

public class ProjectDAO {
    private JDBC jdbc = JDBC.getJDBC();

    public List<Project> createProjects(String select_clause, List<String> parameters) {
        List<Project> projects = new ArrayList<Project>();

        try (Connection connection = jdbc.getConnection()) {
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
                        String name_a = result_set.getString("name_a");
                        String name_b = result_set.getString("name_b");
                        double amount = result_set.getDouble("amount");
                        Status status = Status.valueOf(result_set.getString("status"));

                        Company company_a = new Company(code_a, name_a);
                        Company company_b = new Company(code_b, name_b);
                        projects.add(new Project(company_a, company_b, start, amount, status));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }
}
