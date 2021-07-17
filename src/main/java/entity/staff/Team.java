/*
 * This file is part of the Team in the HeadhunterMS project. It contains all Team-like data
 * structures and functions.
 */
package entity.staff;

import java.util.ArrayList;
import java.util.List;
import entity.MethodSet;

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

    public int getMemberCount() {
        return this.member_count;
    }

    public double getSalesTotal() {
        return this.sales_total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Team headcount: ").append(this.member_count).append("\n").append("Team sales: ")
                .append(sales_total).append("\n")
                .append("The team member's information is as follows\n");

        List<List<String>> rows = new ArrayList<>();
        rows.addAll(List.of(Employee.getFieldName(), this.leader.getFieldValue()));
        for (Employee employee : this.employees) {
            rows.add(employee.getFieldValue());
        }

        return (sb.toString() + MethodSet.formatAsTable(rows));
    }
}
