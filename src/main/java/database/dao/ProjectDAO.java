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

    public List<Project> createProjects(String require_id) {
        List<Project> projects = new ArrayList<Project>();

        try (Connection connection = jdbc.getConnection()) {
            try (PreparedStatement prepare_statement = connection.prepareStatement(
                    "SELECT * FROM Project WHERE (code_a, code_b, start) IN (SELECT code_a, code_b, start FROM Employee JOIN Project USING(leader_id) WHERE id=?)")) {
                prepare_statement.setObject(1, require_id);
                try (ResultSet result_set = prepare_statement.executeQuery()) {
                    while (result_set.next()) {
                        String code_a = result_set.getString("code_a");
                        String code_b = result_set.getString("code_b");
                        LocalDate start = result_set.getDate("start").toLocalDate();
                        String name_a = result_set.getString("name_a");
                        String name_b = result_set.getString("name_b");
                        LocalDate end = result_set.getDate("end").toLocalDate();
                        double amount = result_set.getDouble("amount");
                        Status status = Status.valueOf(result_set.getString("status"));

                        Company company_a = new Company(code_a, name_a);
                        Company company_b = new Company(code_b, name_b);
                        projects.add(new Project(company_a, company_b, start, end, amount, status));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }
}
