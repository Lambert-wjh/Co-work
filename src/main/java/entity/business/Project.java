package entity.business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import database.dao.ProjectDAO;
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
        sb.append("Company A: ").append(this.company_a).append("\nCompany B: ")
                .append(this.company_b).append("\nEstablishment date: ")
                .append(this.date_formatter.format(start));
        if (status == Status.COMPLETED || status == Status.ARCHIVED) {
            sb.append("\nCompletion date: ").append(this.date_formatter.format(end));
        }
        sb.append("\nAmount: ").append(this.amount).append("\nStatus: ").append(this.status);

        return sb.toString();
    }

    public static List<Project> getProjects(String require_id) {
        ProjectDAO project_dao = new ProjectDAO();
        return project_dao.createProjects(require_id);
    }
}
