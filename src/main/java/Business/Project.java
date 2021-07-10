package Business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Tools.Enums.Status;

public class Project {
    private Company party_a;
    private Company party_b;
    private DateTimeFormatter date_formatter;
    private LocalDate establishment;
    private LocalDate completion;
    private double amount;
    private Status status;

    public Project(Company party_a, Company party_b, LocalDate establishment, LocalDate completion, double amount,
            Status status) {
        this.party_a = party_a;
        this.party_b = party_b;
        this.date_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.establishment = establishment;
        this.completion = completion;
        this.amount = amount;
        this.status = status;
    }
}
