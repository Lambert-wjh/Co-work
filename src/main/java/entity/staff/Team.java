package entity.staff;

import java.util.ArrayList;
import java.util.List;
import menu.MainMenu;

public class Team {
    private Leader leader;
    private List<Employee> employees;
    private int member_count;
    private double sales_total;

    public Team(Leader leader, List<Employee> employees, int member_count, double sales_total) {
        this.leader = leader;
        this.employees = employees;
        this.member_count = member_count;
        this.sales_total = sales_total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Team headcount: ").append(this.member_count).append("\n").append("Team sales: ")
                .append(sales_total).append("\n")
                .append("The team member's information is as follows\n");

        List<List<String>> rows = new ArrayList<>();
        rows.add(Employee.getFieldName());
        rows.add(this.leader.getFieldValue());
        for (Employee employee : this.employees) {
            rows.add(employee.getFieldValue());
        }

        return (sb.toString() + MainMenu.formatAsTable(rows));
    }
}
