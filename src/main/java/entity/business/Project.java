package entity.business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import entity.MethodSet;
import entity.enums.Status;

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

    public Status getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        List<List<String>> rows = new ArrayList<>();
        rows.add(Project.getFieldName());
        rows.add(this.getFieldValue());
        return MethodSet.formatAsTable(rows);
    }

    public static List<String> getFieldName() {
        return Arrays.asList("Company_A_Code", "Name", "Company_B_Code", "Name", "Start date",
                "Amount", "Status");
    }

    public static DateTimeFormatter getDateFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public List<String> getFieldValue() {
        return Arrays.asList(this.company_a.getCode(), this.company_a.getName(),
                this.company_b.getCode(), this.company_b.getName(),
                this.date_formatter.format(start).toString(),
                Double.valueOf(this.amount).toString(), this.status.name());
    }
}
