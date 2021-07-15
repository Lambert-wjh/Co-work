package entity.business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import entity.MethodSet;
import entity.enums.Status;

public class Project {
    private String code_a;
    private String code_b;
    private DateTimeFormatter date_formatter;
    private LocalDate start;
    private double amount;
    private Status status;
    private String leader_id;

    public Project(String code_a, String code_b, LocalDate start, double amount, Status status,
            String leader_id) {
        this.code_a = code_a;
        this.code_b = code_b;
        this.date_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.start = start;
        this.amount = amount;
        this.status = status;
        this.leader_id = leader_id;
    }

    public String getCodeA() {
        return this.code_a;
    }

    public String getCodeB() {
        return this.code_b;
    }

    public static DateTimeFormatter getDateFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public LocalDate getStartDate() {
        return this.start;
    }

    public double getAmount() {
        return this.amount;
    }

    public Status getStatus() {
        return this.status;
    }

    public String getLeaderId() {
        return this.leader_id;
    }

    @Override
    public String toString() {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Project.getFieldName());
        rows.add(this.getFieldValue());

        return MethodSet.formatAsTable(rows);
    }

    public static List<String> getFieldName() {
        return Arrays.asList("Company_A_Code", "Company_B_Code", "Start date", "Amount", "Status",
                "Leader's ID");
    }

    public List<String> getFieldValue() {
        return Arrays.asList(this.code_a, this.code_b, this.date_formatter.format(start).toString(),
                Double.valueOf(this.amount).toString(), this.status.name(), this.leader_id);
    }
}
