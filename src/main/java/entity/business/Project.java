package entity.business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import database.dao.ProjectDAO;
import entity.enums.Position;
import entity.enums.Status;
import menu.MainMenu;

public class Project {
    private Company company_a;
    private Company company_b;
    private DateTimeFormatter date_formatter;
    private LocalDate start;
    private double amount;
    private Status status;

    public Project(Company company_a, Company company_b, LocalDate start, double amount,
            Status status) {
        this.company_a = company_a;
        this.company_b = company_b;
        this.date_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.start = start;
        this.amount = amount;
        this.status = status;
    }

    @Override
    public String toString() {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Project.getFieldName());
        rows.add(this.getFieldValue());
        return MainMenu.formatAsTable(rows);
    }

    public static List<String> getFieldName() {
        return Arrays.asList("Company_A_Code", "Name", "Company_B_Code", "Name", "Start date",
                "Amount", "Status");
    }

    public List<String> getFieldValue() {
        return Arrays.asList(this.company_a.getCode(), this.company_a.getName(),
                this.company_b.getCode(), this.company_b.getName(),
                this.date_formatter.format(start).toString(),
                Double.valueOf(this.amount).toString(), this.status.name());
    }

    public static List<Project> getProjects(String require_id, Position position) {
        ProjectDAO project_dao = new ProjectDAO();
        String select_clause = null;
        List<String> parameters = new ArrayList<>();

        switch (position) {
            case EMPLOYEE -> {
                select_clause =
                        "SELECT * FROM Project WHERE leader_id IN (SELECT leader_id FROM Employee WHERE id=?)";
                parameters.add(require_id);
            }
            case LEADER -> {
                select_clause = "SELECT * FROM Project WHERE leader_id=?";
                parameters.add(require_id);
            }
            case SUPERUSER -> {
                select_clause = "SELECT * FROM Project";
            }
        }
        return project_dao.createProjects(select_clause, parameters);
    }
}
