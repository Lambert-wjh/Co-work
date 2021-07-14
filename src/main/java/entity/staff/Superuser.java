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

    public void modifyStaffInfo() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter staff's ID: ");
        String id = input.nextLine();

        Factory factory = new Factory();
        Employee staff = factory.getStaff(id);
        if (staff == null) {
            return;
        } else {
            System.out.println("The staff's information is as follows");
            System.out.println(staff);
        }

        String temp_input = null;
        String new_id = staff.getId();
        String new_name = staff.getName();
        Sex new_sex = staff.getSex();
        int new_age = staff.getAge();
        String new_password = staff.getAccount().getPassword();

        System.out.print("Enter new ID (Please enter N if you don't want to change it) : ");
        temp_input = input.nextLine();
        new_id = temp_input.equals("N") ? new_id : temp_input;
        System.out.print("Enter new name (Please enter N if you don't want to change it) : ");
        temp_input = input.nextLine();
        new_name = temp_input.equals("N") ? new_name : temp_input;
        System.out.print(
                "Enter new sex (MALE or FEMALE) (Please enter N if you don't want to change it) : ");
        temp_input = input.nextLine();
        new_sex = temp_input.equals("N") ? new_sex : Sex.valueOf(temp_input);
        System.out.print(
                "Enter new age (18 ~ 65) (Please enter N if you don't want to change it) : ");
        temp_input = input.nextLine();
        new_age = temp_input.equals("N") ? new_age : Integer.parseInt(temp_input);
        System.out.print("Reset password ? (Y or N) : ");
        temp_input = input.nextLine();
        new_password = temp_input.equals("N") ? new_password : "123456";

        String update_clause =
                "UPDATE Employee SET id=?, name=?, sex=?, age=?, password=? WHERE id=?";
        List<String> parameters = new ArrayList<>();
        parameters.add(new_id);
        parameters.add(new_name);
        parameters.add(new_sex.name());
        parameters.add(Integer.valueOf(new_age).toString());
        parameters.add(new_password);
        parameters.add(staff.getId());

        DAO.getDAO().updateTable(update_clause, parameters);
        System.out.println("Modified the staff information successfully");
    }

    public void createNewStaff() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter staff's ID: ");
        String id = input.nextLine();
        System.out.print("Enter staff's name: ");
        String name = input.nextLine();
        System.out.print("Enter staff's sex (MALE or FEMALE): ");
        Sex sex = Sex.valueOf(input.nextLine());
        System.out.print("Enter staff's age (18 ~ 65) : ");
        int age = input.nextInt();

        String update_clause = "INSERT INTO Employee (id, name, sex, age) VALUES (?, ?, ?, ?)";
        List<String> parameters = new ArrayList<>();
        parameters.add(id);
        parameters.add(name);
        parameters.add(sex.name());
        parameters.add(Integer.valueOf(age).toString());

        DAO.getDAO().updateTable(update_clause, parameters);
        System.out.println("Create new staff successfully, the staff's information is as follows");

        Factory factory = new Factory();
        Employee staff = factory.getStaff(id);
        System.out.println(staff);
    }

    public void deleteStaffRecord() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter staff's ID: ");
        String id = input.nextLine();

        Factory factory = new Factory();
        Employee staff = factory.getStaff(id);
        if (staff == null) {
            return;
        } else {
            System.out.println("The staff's information is as follows");
            System.out.println(staff);
        }

        System.out.print("Confirm deletion (Y or N) : ");
        String deletion = input.nextLine();
        if (deletion.equals("Y")) {
            String update_clause = "DELETE FROM Employee WHERE id=?";
            List<String> parameters = new ArrayList<>();
            parameters.add(id);

            DAO.getDAO().updateTable(update_clause, parameters);
            System.out.println("Delete staff's record successfully");
        } else {
            System.out.println("You have cancelled the deletion");
        }
    }

    public void checkSpecifiedProjectInfo() {
        Scanner input = new Scanner(System.in);
        System.out.println(
                "Enter company A's, company B's code and start date (yyyy-MM-dd) of the project in order: ");
        String code_a = input.next();
        String code_b = input.next();
        String start = input.next();

        Factory factory = new Factory();
        List<Project> projects = factory.getProjects(code_a, code_b,
                LocalDate.parse(start, Project.getDateFormatter()), this.id, this.position);

        if (projects.size() == 0) {
            System.err.println("No such project");
            return;
        }
        Project project = projects.get(0);
        System.out.println("The project's information is as follows");
        System.out.println(project);
    }

    public void updateSpecifiedProjectInfo() {
        Scanner input = new Scanner(System.in);
        System.out.println(
                "Enter company A's, company B's code and start date (yyyy-MM-dd) of the project in order: ");
        String code_a = input.next();
        String code_b = input.next();
        String start = input.next();

        Factory factory = new Factory();
        List<Project> projects = factory.getProjects(code_a, code_b,
                LocalDate.parse(start, Project.getDateFormatter()), this.id, this.position);

        if (projects.size() == 0) {
            System.err.println("No such project");
            return;
        }
        Project project = projects.get(0);
        System.out.println("The project's information is as follows");
        System.out.println(project);

        String temp_input = null;
        String new_code_a = project.getCodeA();
        String new_code_b = project.getCodeB();
        LocalDate new_start = project.getStartDate();
        double new_amount = project.getAmount();

        System.out.print(
                "Enter new company A's code (Please enter N if you don't want to change it) : ");
        input.nextLine();
        temp_input = input.nextLine();
        new_code_a = temp_input.equals("N") ? new_code_a : temp_input;
        System.out.print(
                "Enter new company B's code (Please enter N if you don't want to change it) : ");
        temp_input = input.nextLine();
        new_code_b = temp_input.equals("N") ? new_code_b : temp_input;
        System.out.print(
                "Enter new start date (yyyy-MM-dd) (Please enter N if you don't want to change it) : ");
        temp_input = input.nextLine();
        new_start = temp_input.equals("N") ? new_start
                : LocalDate.parse(temp_input, Project.getDateFormatter());
        System.out.print("Enter new amount (Please enter N if you don't want to change it) : ");
        temp_input = input.nextLine();
        new_amount = temp_input.equals("N") ? new_amount : Double.parseDouble(temp_input);

        String update_clause =
                "UPDATE Project SET code_a=?, code_b=?, start=?, amount=? WHERE code_a=? AND code_b=? AND start=?";
        List<String> parameters = new ArrayList<>();
        parameters.add(new_code_a);
        parameters.add(new_code_b);
        parameters.add(Project.getDateFormatter().format(new_start));
        parameters.add(Double.valueOf(new_amount).toString());
        parameters.add(project.getCodeA());
        parameters.add(project.getCodeB());
        parameters.add(Project.getDateFormatter().format(project.getStartDate()));

        DAO.getDAO().updateTable(update_clause, parameters);
        System.out.println("Update specified project's information successfully");
    }

    @Override
    public void updateProjectStatus() {
        Scanner input = new Scanner(System.in);
        System.out.println(
                "Enter company A's, company B's code and start date (yyyy-MM-dd) of the project in order: ");
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

        DAO.getDAO().updateTable(update_clause, parameters);
        System.out.println("The project status was modified successfully");
    }
}
