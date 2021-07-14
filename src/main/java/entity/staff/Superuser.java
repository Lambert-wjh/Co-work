package entity.staff;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import database.DAO;
import entity.Factory;
import entity.business.Project;
import entity.enums.Position;
import entity.enums.Sex;
import entity.enums.Status;

public class Superuser extends Leader {
    public Superuser(String id, String name, Sex sex, int age, String leader_id, Position position,
            double sales, double salary, String password) {
        super(id, name, sex, age, leader_id, position, sales, salary, password);
    }

    @Override
    public void updateProjectStatus() {
        Scanner input = new Scanner(System.in);
        System.out.println(
                "Enter company A's, company B's code and start date of the project in order: ");
        String code_a = input.next();
        String code_b = input.next();
        String start = input.next();

        Factory factory = new Factory();
        List<Project> projects = factory.getProjects(code_a, code_b,
                LocalDate.parse(start, Project.getDateFormatter()), this.id, this.position);

        if (projects.size() == 0) {
            System.err.println("You can't change the project");
            return;
        }
        Project project = projects.get(0);
        System.out.println("The current status of the project is " + project.getStatus());
        System.out.println(
                "You can change it to 1. COMPLETED or 2. PAUSED or 3. IN_PROGRESS or 4. ARCHIVED or 5. REVOKED");
        System.out.print("Now enter your selection: ");
        int selection = input.nextInt();
        String update_clause =
                "UPDATE Project SET status=? WHERE code_a=? AND code_b=? AND start=?";
        List<String> parameters = new ArrayList<>();
        switch (selection) {
            case 1 -> {
                parameters.add(Status.COMPLETED.name());
            }
            case 2 -> {
                parameters.add(Status.PAUSED.name());
            }
            case 3 -> {
                parameters.add(Status.IN_PROGRESS.name());
            }
            case 4 -> {
                parameters.add(Status.ARCHIVED.name());
            }
            case 5 -> {
                parameters.add(Status.REVOKED.name());
            }
            default -> {
                System.err.println("An error was entered and the modification failed");
                return;
            }
        }
        parameters.add(code_a);
        parameters.add(code_b);
        parameters.add(start);
        DAO dao = new DAO();
        dao.updateTable(update_clause, parameters);
        System.out.println("The project status was modified successfully");
    }
}
