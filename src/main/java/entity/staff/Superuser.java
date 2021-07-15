package entity.staff;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
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

        Employee staff = Factory.getFactory().getStaff(id);
        if (staff == null) {
            System.err.println("No such staff");
            return;
        }
        System.out.println("The staff's information is as follows");
        System.out.println(staff);

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
        List<String> parameters = Arrays.asList(new_id, new_name, new_sex.name(),
                Integer.valueOf(new_age).toString(), new_password, staff.getId());
        if (DAO.getDAO().updateTable(update_clause, parameters) == 0) {
            System.err.println("Failed to modify the staff information");
            staff = Factory.getFactory().getStaff(id);
            System.out.println("The staff's information is as follows");
            System.out.println(staff);
        } else {
            System.out.println("Modified the staff information successfully");
        }
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
        List<String> parameters =
                Arrays.asList(id, name, sex.name(), Integer.valueOf(age).toString());

        if (DAO.getDAO().updateTable(update_clause, parameters) == 0) {
            System.err.println("Failed to create a new staff");
        } else {
            System.out.println(
                    "Create new staff successfully, the staff's information is as follows");
            Employee staff = Factory.getFactory().getStaff(id);
            System.out.println(staff);
        }
    }

    public void deleteStaffRecord() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter staff's ID: ");
        String id = input.nextLine();

        Employee staff = Factory.getFactory().getStaff(id);
        if (staff == null) {
            System.err.println("No such staff");
            return;
        }
        System.out.println("The staff's information is as follows");
        System.out.println(staff);

        System.out.print("Confirm deletion (Y or N) : ");
        String deletion = input.nextLine();
        if (deletion.equals("Y")) {
            String update_clause = "DELETE FROM Employee WHERE id=?";
            List<String> parameters = Arrays.asList(id);

            if (DAO.getDAO().updateTable(update_clause, parameters) == 0) {
                System.err.println("Failed to delete the staff's record");
            } else {
                System.out.println("Delete the staff's record successfully");
            }
        } else {
            System.out.println("You have cancelled the deletion");
        }
    }

    private void updateMemberCount() {
        String select_clause = "SELECT leader_id FROM Team";
        List<String> parameters = Arrays.asList();
        List<String> leader_ids = Factory.getFactory().getAttributes(select_clause, parameters)
                .stream().map(Object::toString).collect(Collectors.toList());

        for (String leader_id : leader_ids) {
            select_clause = "SELECT * FROM Employee WHERE leader_id=?";
            parameters = Arrays.asList(leader_id);
            List<Employee> employees = Factory.getFactory().getStaff(select_clause, parameters);

            select_clause = "UPDATE Team SET member_count=? WHERE leader_id=?";
            parameters = Arrays.asList(Integer.valueOf(employees.size() + 1).toString(), leader_id);
            DAO.getDAO().updateTable(select_clause, parameters);
        }
    }

    public void createNewTeam() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter new team leader's ID: ");
        String leader_id = input.nextLine();

        Employee pre_leader = Factory.getFactory().getEmployee(leader_id);
        if (pre_leader == null) {
            System.err.println("No such staff");
            return;
        } else if (pre_leader.getPosition() == Position.LEADER) {
            System.err.println("This staff has led a team");
            return;
        }

        String update_clause = "INSERT INTO Team (leader_id, member_count) VALUES (?, ?)";
        List<String> parameters = Arrays.asList(pre_leader.getId(), Integer.valueOf(1).toString());
        DAO.getDAO().updateTable(update_clause, parameters);

        update_clause = "UPDATE Employee SET leader_id='AAA00000', position='LEADER' WHERE id=?";
        parameters = Arrays.asList(pre_leader.getId());
        DAO.getDAO().updateTable(update_clause, parameters);

        String temp_input = "Y";
        while (temp_input.equals("Y")) {
            System.out.print("Enter the employee's id: ");
            String id = input.nextLine();
            Employee employee = Factory.getFactory().getEmployee(id);
            if (employee == null) {
                System.err.println("No such staff");
                continue;
            } else if (employee.getPosition() != Position.EMPLOYEE) {
                System.err.println("Not a employee and not been added");
                continue;
            } else {
                update_clause = "UPDATE Employee SET leader_id=? WHERE id=?";
                parameters = Arrays.asList(pre_leader.getId(), employee.getId());

                DAO.getDAO().updateTable(update_clause, parameters);
            }
            System.out.print("Continue adding employees (Y or N) : ");
            temp_input = input.nextLine();
        }

        Team team = Factory.getFactory().getTeam(pre_leader.getId());
        System.out.println("The team's information is as follows");
        System.out.println(team);

        this.updateMemberCount();
    }

    public void deleteTeamRecord() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the team leader's ID: ");
        String leader_id = input.nextLine();

        Employee staff = Factory.getFactory().getLeader(leader_id);
        if (staff == null) {
            System.err.println("Not such leader");
            return;
        }

        Team team = Factory.getFactory().getTeam(leader_id);
        System.out.println("The team's information is as follows");
        System.out.println(team);

        System.out.print("Confirm deletion (Y or N) : ");
        String deletion = input.nextLine();
        if (deletion.equals("Y")) {
            String update_clause = "UPDATE Employee SET leader_id=null WHERE leader_id=?";
            List<String> parameters = Arrays.asList(leader_id);
            DAO.getDAO().updateTable(update_clause, parameters);

            update_clause = "UPDATE Employee SET leader_id=null, position='EMPLOYEE' WHERE id=?";
            DAO.getDAO().updateTable(update_clause, parameters);

            update_clause = "DELETE FROM Team WHERE leader_id=?";
            DAO.getDAO().updateTable(update_clause, parameters);
            System.out.println("Delete the team's record successfully");
        } else {
            System.out.println("You have cancelled the deletion");
        }
    }

    public void createNewProject() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter company A's code: ");
        String code_a = input.nextLine();
        System.out.print("Enter company B's code: ");
        String code_b = input.nextLine();
        System.out.print("Enter start date of the project (yyyy-MM-dd) : ");
        String start = input.nextLine();
        System.out.print("Enter amount of the project: ");
        String amount = input.nextLine();
        System.out.print("Enter leader's id of the assigned team: ");
        String leader_id = input.nextLine();

        String update_clause =
                "INSERT INTO Project (code_a, code_b, start, amount, leader_id) VALUES (?, ?, ?, ?, ?)";
        List<String> parameters = Arrays.asList(code_a, code_b, start, amount, leader_id);

        if (DAO.getDAO().updateTable(update_clause, parameters) == 0) {
            System.err.println("Failed to create a new project");
        } else {
            System.out.println(
                    "Create new project successfully, the project's information is as follows");
            Project project = Factory.getFactory().getProject(code_a, code_b,
                    LocalDate.parse(start, Project.getDateFormatter()));
            System.out.println(project);
        }

    }

    public void deleteProjectRecord() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter company A's code of the project: ");
        String code_a = input.nextLine();
        System.out.print("Enter company B's code of the project: ");
        String code_b = input.nextLine();
        System.out.print("Enter start date of the project (yyyy-MM-dd) : ");
        String start = input.nextLine();

        Project project = Factory.getFactory().getProject(code_a, code_b,
                LocalDate.parse(start, Project.getDateFormatter()));

        if (project == null) {
            System.err.println("No such project");
            return;
        }

        System.out.println("The project's information is as follows");
        System.out.println(project);

        System.out.print("Confirm deletion (Y or N) : ");
        String deletion = input.nextLine();
        if (deletion.equals("Y")) {
            String update_clause = "DELETE FROM Project WHERE code_a =? AND code_b=? AND start=?";
            List<String> parameters = Arrays.asList(code_a, code_b, start);

            if (DAO.getDAO().updateTable(update_clause, parameters) == 0) {
                System.err.println("Failed to delete the project's record");
            } else {
                System.out.println("Delete the project's record successfully");
            }
        } else {
            System.out.println("You have cancelled the deletion");
        }
    }

    public void checkTheProjectInfo() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter company A's code of the project: ");
        String code_a = input.nextLine();
        System.out.print("Enter company B's code of the project: ");
        String code_b = input.nextLine();
        System.out.print("Enter start date of the project (yyyy-MM-dd) : ");
        String start = input.nextLine();

        Project project = Factory.getFactory().getProject(code_a, code_b,
                LocalDate.parse(start, Project.getDateFormatter()));

        if (project == null) {
            System.err.println("No such project");
            return;
        }

        System.out.println("The project's information is as follows");
        System.out.println(project);
    }

    public void updateTheProjectInfo() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter company A's code of the project: ");
        String code_a = input.nextLine();
        System.out.print("Enter company B's code of the project: ");
        String code_b = input.nextLine();
        System.out.print("Enter start date of the project (yyyy-MM-dd) : ");
        String start = input.nextLine();

        Project project = Factory.getFactory().getProject(code_a, code_b,
                LocalDate.parse(start, Project.getDateFormatter()));

        if (project == null) {
            System.err.println("No such project");
            return;
        }

        System.out.println("The project's information is as follows");
        System.out.println(project);

        String temp_input = null;
        String new_code_a = project.getCodeA();
        String new_code_b = project.getCodeB();
        LocalDate new_start = project.getStartDate();
        double new_amount = project.getAmount();
        String new_leader_id = project.getLeaderId();

        System.out.print(
                "Enter new company A's code (Please enter N if you don't want to change it) : ");
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
        System.out
                .print("Enter new leader's ID (Please enter N if you don't want to change it) : ");
        temp_input = input.nextLine();
        new_leader_id = temp_input.equals("N") ? new_leader_id : temp_input;

        String update_clause =
                "UPDATE Project SET code_a=?, code_b=?, start=?, amount=?, leader_id=? WHERE code_a=? AND code_b=? AND start=?";
        List<String> parameters = Arrays.asList(new_code_a, new_code_b,
                Project.getDateFormatter().format(new_start), Double.valueOf(new_amount).toString(),
                new_leader_id, project.getCodeA(), project.getCodeB(),
                Project.getDateFormatter().format(project.getStartDate()));

        if (DAO.getDAO().updateTable(update_clause, parameters) == 0) {
            System.err.println("Failed to update the project's information");
        } else {
            System.out.println("Update specified project's information successfully");
            project = Factory.getFactory().getProject(new_code_a, new_code_b, new_start);
            System.out.println("The project's information is as follows");
            System.out.println(project);
        }
    }

    @Override
    public void updateTheProjectStatus() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter company A's code of the project: ");
        String code_a = input.nextLine();
        System.out.print("Enter company B's code of the project: ");
        String code_b = input.nextLine();
        System.out.print("Enter start date of the project (yyyy-MM-dd) : ");
        String start = input.nextLine();

        Project project = Factory.getFactory().getProject(code_a, code_b,
                LocalDate.parse(start, Project.getDateFormatter()));

        if (project == null) {
            System.err.println("No such project");
            return;
        }

        System.out.println("The current status of the project is " + project.getStatus());
        System.out.println(
                "You can change it to 1. COMPLETED or 2. PAUSED or 3. IN_PROGRESS or 4. ARCHIVED or 5. REVOKED");
        System.out.print("Now enter your selection: ");
        int selection = Integer.valueOf(input.nextLine());

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

        if (DAO.getDAO().updateTable(update_clause, parameters) == 0) {
            System.err.println("Failed to update status of the project");
        } else {
            System.out.println("The project status was modified successfully");
        }
    }
}
