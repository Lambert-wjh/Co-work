package entity.staff;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import database.DAO;
import entity.Factory;
import entity.MethodSet;
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

        String new_id = staff.getId();
        String new_name = staff.getName();
        Sex new_sex = staff.getSex();
        int new_age = staff.getAge();
        String new_password = staff.getAccount().getPassword();

        System.out.print("Enter new ID (Please enter N if you don't want to change it) : ");
        String temp_input = input.nextLine();
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
        List<String> parameters = List.of(new_id, new_name, new_sex.name(), String.valueOf(new_age),
                new_password, staff.getId());
        if (DAO.getDAO().updateTable(update_clause, parameters) == 0) {
            System.err.println("Failed to modify the staff information");
        } else {
            System.out.println(
                    "Modified the staff information successfully, the staff's information is as follows");
            staff = Factory.getFactory().getStaff(id);
            System.out.println(staff);
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
        List<String> parameters = List.of(id, name, sex.name(), String.valueOf(age));

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
            List<String> parameters = List.of(id);

            if (DAO.getDAO().updateTable(update_clause, parameters) == 0) {
                System.err.println("Failed to delete the staff's record");
            } else {
                System.out.println("Delete the staff's record successfully");
            }
        } else {
            System.out.println("You have cancelled the deletion");
        }
    }

    public void updateSales() {
        String select_clause = "SELECT leader_id FROM Project WHERE status='COMPLETED'";
        List<String> parameters = List.of();
        List<String> leader_ids = Factory.getFactory().getAttributes(select_clause, parameters)
                .stream().map(Object::toString).collect(Collectors.toList());

        for (String leader_id : leader_ids) {
            select_clause = "SELECT member_count FROM Team WHERE leader_id=?";
            parameters = List.of(leader_id);
            int member_count =
                    (Integer) Factory.getFactory().getAttribute(select_clause, parameters);


            select_clause =
                    "SELECT SUM(amount) FROM Project WHERE leader_id=? AND status='COMPLETED' GROUP BY leader_id";
            double amount = (Double) Factory.getFactory().getAttribute(select_clause, parameters);

            select_clause = "UPDATE Team SET sales_total=? WHERE leader_id=?";
            parameters = List.of(String.valueOf(amount), leader_id);
            if (DAO.getDAO().updateTable(select_clause, parameters) == 0) {
                System.err.println("Failed to update Team's sales");
                return;
            }

            double leader_sales = (amount / member_count) * 1.15;
            double employee_sales = (amount / member_count);

            select_clause = "UPDATE Employee SET sales=? WHERE id=?";
            parameters = List.of(String.valueOf(leader_sales), leader_id);
            if (DAO.getDAO().updateTable(select_clause, parameters) == 0) {
                System.err.println("Failed to update leader's sales");
                return;
            }

            select_clause = "UPDATE Employee SET sales=? WHERE leader_id=?";
            parameters = List.of(String.valueOf(employee_sales), leader_id);
            if (DAO.getDAO().updateTable(select_clause, parameters) == 0) {
                System.err.println("Failed to update employee's sales");
                return;
            }
        }

        select_clause = "SELECT SUM(amount) FROM Project WHERE status='COMPLETED'";
        parameters = List.of();
        double amount = (Double) Factory.getFactory().getAttribute(select_clause, parameters);
        select_clause = "UPDATE Employee SET sales=? WHERE id='AAA00000'";
        parameters = List.of(String.valueOf(amount));
        if (DAO.getDAO().updateTable(select_clause, parameters) == 0) {
            System.err.println("Failed to update superuser's sales");
            return;
        }

        System.out.println("Update sales successfully, all employees's information is as follows");
        select_clause = "SELECT * FROM Employee";
        parameters = List.of();
        List<Employee> employees = Factory.getFactory().getStaff(select_clause, parameters);
        List<List<String>> rows = new ArrayList<>();
        rows.add(Employee.getFieldName());
        for (Employee employee : employees) {
            rows.add(employee.getFieldValue());
        }
        System.out.println(MethodSet.formatAsTable(rows));
    }

    private void updateMemberCount() {
        String select_clause = "SELECT id FROM Employee WHERE position='LEADER'";
        List<String> parameters = List.of();
        List<String> leader_ids = Factory.getFactory().getAttributes(select_clause, parameters)
                .stream().map(Object::toString).collect(Collectors.toList());

        for (String leader_id : leader_ids) {
            select_clause = "SELECT * FROM Employee WHERE leader_id=?";
            parameters = List.of(leader_id);
            List<Employee> employees = Factory.getFactory().getStaff(select_clause, parameters);

            select_clause = "UPDATE Team SET member_count=? WHERE leader_id=?";
            parameters = List.of(String.valueOf(employees.size() + 1), leader_id);
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
        }

        String update_clause = "INSERT INTO Team (leader_id, member_count) VALUES (?, ?)";
        List<String> parameters = List.of(leader_id, String.valueOf(1));
        DAO.getDAO().updateTable(update_clause, parameters);

        update_clause = "UPDATE Employee SET leader_id='AAA00000', position='LEADER' WHERE id=?";
        parameters = List.of(leader_id);
        DAO.getDAO().updateTable(update_clause, parameters);

        String temp_input = "Y";
        while (temp_input.equals("Y")) {
            System.out.print("Enter the employee's id: ");
            String id = input.nextLine();
            Employee employee = Factory.getFactory().getEmployee(id);
            if (employee == null) {
                System.err.println("No such staff");
                continue;
            } else {
                update_clause = "UPDATE Employee SET leader_id=? WHERE id=?";
                parameters = List.of(leader_id, id);

                DAO.getDAO().updateTable(update_clause, parameters);
            }
            System.out.print("Continue adding employees (Y or N) : ");
            temp_input = input.nextLine();
        }

        Team team = Factory.getFactory().getTeam(leader_id);
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
            List<String> parameters = List.of(leader_id);
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

    public void changeTeamPosition() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the team leader's ID: ");
        String leader_id = input.nextLine();

        Employee original_leader = Factory.getFactory().getLeader(leader_id);
        if (original_leader == null) {
            System.err.println("Not such leader");
            return;
        }

        Team team = Factory.getFactory().getTeam(leader_id);
        System.out.println("The team's information is as follows");
        System.out.println(team);

        System.out.print("Enter the ID of the new leader among the above employees: ");
        String new_leader_id = input.nextLine();

        String select_clause = "SELECT * FROM Employee WHERE id=? AND leader_id=?";
        List<String> parameters = List.of(new_leader_id, leader_id);
        List<Employee> employees = Factory.getFactory().getStaff(select_clause, parameters);
        if (employees.size() == 0) {
            System.err.println("Not such employee");
            return;
        }

        select_clause = "INSERT INTO Team (leader_id, member_count, sales_total) VALUES (?, ?, ?)";
        parameters = List.of(new_leader_id, String.valueOf(team.getMemberCount()),
                String.valueOf(team.getSalesTotal()));
        if (DAO.getDAO().updateTable(select_clause, parameters) == 0) {
            System.err.println("Failed to insert new team information");
            return;
        }

        select_clause = "UPDATE Employee SET leader_id=?, position='EMPLOYEE' WHERE id=?";
        parameters = List.of(new_leader_id, leader_id);
        if (DAO.getDAO().updateTable(select_clause, parameters) == 0) {
            System.err.println("Failed to modify original leader's information");
            return;
        }

        select_clause = "UPDATE Employee SET leader_id='AAA00000', position='LEADER' WHERE id=?";
        parameters = List.of(new_leader_id);
        if (DAO.getDAO().updateTable(select_clause, parameters) == 0) {
            System.err.println("Failed to modify new leader's information");
            return;
        }

        select_clause = "UPDATE Employee SET leader_id=? WHERE leader_id=?";
        parameters = List.of(new_leader_id, leader_id);
        if (DAO.getDAO().updateTable(select_clause, parameters) == 0) {
            System.err.println("Failed to modify other employees' information");
            return;
        }

        select_clause = "UPDATE Project SET leader_id=? WHERE leader_id=?";
        parameters = List.of(new_leader_id, leader_id);
        if (DAO.getDAO().updateTable(select_clause, parameters) == 0) {
            System.err.println("Failed to modify project's information");
            return;
        }

        select_clause = "DELETE FROM Team WHERE leader_id=?";
        parameters = List.of(leader_id);
        if (DAO.getDAO().updateTable(select_clause, parameters) == 0) {
            System.err.println("Failed to delete original team's record");
            return;
        }

        System.out.println("Now the team's information is as follows");
        Team new_team = Factory.getFactory().getTeam(new_leader_id);
        System.out.println(new_team);
    }

    public void transferEmployee() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the employee's ID: ");
        String id = input.nextLine();

        Employee employee = Factory.getFactory().getEmployee(id);
        if (employee == null) {
            System.err.println("No such employee");
            return;
        }

        String select_clause = "SELECT id FROM Employee WHERE position='LEADER'";
        List<String> parameters = List.of();
        List<String> leader_ids = Factory.getFactory().getAttributes(select_clause, parameters)
                .stream().map(Object::toString).collect(Collectors.toList());

        List<Leader> leaders = new ArrayList<>();
        for (String leader_id : leader_ids) {
            leaders.add(Factory.getFactory().getLeader(leader_id));
        }
        System.out.println("All leaders' information is as follows");
        List<List<String>> rows = new ArrayList<>();
        rows.add(Leader.getFieldName());
        for (Leader leader : leaders) {
            rows.add(leader.getFieldValue());
        }
        System.out.println(MethodSet.formatAsTable(rows));

        System.out.print("Enter the ID of the leader among the above leaders: ");
        String leader_id = input.nextLine();

        Leader leader = Factory.getFactory().getLeader(leader_id);
        if (leader == null) {
            System.out.println("No such leader");
            return;
        }

        select_clause = "UPDATE Employee SET leader_id=? WHERE id=?";
        parameters = List.of(leader_id, id);
        if (DAO.getDAO().updateTable(select_clause, parameters) == 0) {
            System.err.println("Failed to update the employee's information");
            return;
        }

        this.updateMemberCount();
        System.out.println("Transfer successfully, Now the team's information is as follows");
        Team new_team = Factory.getFactory().getTeam(leader_id);
        System.out.println(new_team);
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
        List<String> parameters = List.of(code_a, code_b, start, amount, leader_id);

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
            List<String> parameters = List.of(code_a, code_b, start);

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

        String new_code_a = project.getCodeA();
        String new_code_b = project.getCodeB();
        LocalDate new_start = project.getStartDate();
        double new_amount = project.getAmount();
        String new_leader_id = project.getLeaderId();

        System.out.print(
                "Enter new company A's code (Please enter N if you don't want to change it) : ");
        String temp_input = input.nextLine();
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
        List<String> parameters = List.of(new_code_a, new_code_b,
                new_start.format(Project.getDateFormatter()), String.valueOf(new_amount),
                new_leader_id, project.getCodeA(), project.getCodeB(),
                project.getStartDate().format(Project.getDateFormatter()));

        if (DAO.getDAO().updateTable(update_clause, parameters) == 0) {
            System.err.println("Failed to update the project's information");
        } else {
            System.out.println(
                    "Update specified project's information successfully, the project's information is as follows");
            project = Factory.getFactory().getProject(new_code_a, new_code_b, new_start);
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
