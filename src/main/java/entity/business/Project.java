package entity.business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import database.dao.ProjectDAO;
import entity.enums.Position;
import entity.enums.Status;

public class Project {
    private Company company_a;
    private Company company_b;
    private DateTimeFormatter date_formatter;
    private LocalDate start;
    private LocalDate end;
    private double amount;
    private Status status;

    public Project(Company company_a, Company company_b, LocalDate start, LocalDate end,
            double amount, Status status) {
        this.company_a = company_a;
        this.company_b = company_b;
        this.date_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.start = start;
        this.end = end;
        this.amount = amount;
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("甲方: ").append(this.company_a).append("\n乙方: ").append(this.company_b)
                .append("\n立项日期: ").append(this.date_formatter.format(start));
        if (status == Status.COMPLETED || status == Status.ARCHIVED) {
            sb.append("\n结项日期: ").append(this.date_formatter.format(end));
        }
        sb.append("\n项目金额: ").append(this.amount).append("\n项目状态: ").append(this.status);

        return sb.toString();
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
