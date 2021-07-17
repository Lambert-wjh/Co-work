package ssdut.cli.headhunterms.entity.staff;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ssdut.cli.headhunterms.database.DAO;
import ssdut.cli.headhunterms.entity.Factory;
import ssdut.cli.headhunterms.entity.business.Project;
import ssdut.cli.headhunterms.entity.enums.Position;
import ssdut.cli.headhunterms.entity.enums.Sex;
import ssdut.cli.headhunterms.entity.enums.Status;

public class Leader extends Employee {
  public Leader(
      String id,
      String name,
      Sex sex,
      int age,
      String leader_id,
      Position position,
      double sales,
      double salary,
      String password) {
    super(id, name, sex, age, leader_id, position, sales, salary, password);
  }

  public void checkTeamInfo() {
    Team team = Factory.getFactory().getTeam(this.id);

    System.out.print(team);
  }

  public void updateTheProjectStatus() {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter company A's code of the project: ");
    String code_a = input.nextLine();
    System.out.print("Enter company B's code of the project: ");
    String code_b = input.nextLine();
    System.out.print("Enter start date of the project (yyyy-MM-dd) : ");
    String start = input.nextLine();

    Project project =
        Factory.getFactory()
            .getProject(code_a, code_b, LocalDate.parse(start, Project.getDateFormatter()));

    if (project == null) {
      System.err.println("No such a project");
      return;
    } else if (!project.getLeaderId().equals(this.id)
        || project.getStatus() == Status.ARCHIVED
        || project.getStatus() == Status.REVOKED) {
      System.err.println("You can't change the project");
      return;
    }

    System.out.println("The current status of the project is " + project.getStatus());
    System.out.print(
        "You can change it to 1. COMPLETED or 2. PAUSED or 3. IN_PROGRESS. Now enter your selection: ");
    int selection = Integer.valueOf(input.nextLine());

    String update_clause = "UPDATE Project SET status=? WHERE code_a=? AND code_b=? AND start=?";
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
      default -> {
        System.err.println("An error was entered and the modification failed");
        return;
      }
    }
    parameters.add(code_a);
    parameters.add(code_b);
    parameters.add(start);

    if (DAO.getDAO().updateTable(update_clause, parameters) == 0) {
      System.err.println("Failed to update status of the project");
    } else {
      System.out.println("The project status was modified successfully");
    }
  }
}
