package menu;

import java.io.Console;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Scanner;
import entity.StaffFactory;
import entity.staff.Employee;
import entity.staff.Leader;
import entity.staff.Superuser;

public class MainMenu {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void postSwitch() {
        Console console = System.console();
        Reader reader = console.reader();

        try {
            System.out.print("Enter any key to return...");
            int single_char = reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String formatAsTable(List<List<String>> rows) {
        int[] max_lengths = new int[rows.get(0).size()];
        for (List<String> row : rows) {
            for (int i = 0; i < row.size(); i++) {
                max_lengths[i] = Math.max(max_lengths[i], row.get(i).length());
            }
        }

        StringBuilder formatter = new StringBuilder();
        for (int max_length : max_lengths) {
            formatter.append("%-").append(max_length + 2).append("s");
        }

        StringBuilder table = new StringBuilder();
        for (List<String> row : rows) {
            table.append(String.format(formatter.toString(), row.toArray(new Object[0])))
                    .append("\n");
        }
        return table.toString();
    }

    public void start() {
        Employee user = Login();
        if (user == null) {
            return;
        }

        switch (user.getPosition()) {
            case EMPLOYEE -> {
                EmployeeMenu menu = new EmployeeMenu();
                menu.start(user);
            }
            case LEADER -> {
                LeaderMenu menu = new LeaderMenu();
                menu.start((Leader) user);
            }
            case SUPERUSER -> {
                SuperuserMenu menu = new SuperuserMenu();
                menu.start((Superuser) user);
            }
        }
    }

    private Employee Login() {
        Scanner input = new Scanner(System.in);
        Console console = System.console();

        MainMenu.clearScreen();
        System.out.println("Welcome to use headhunter management system");
        System.out.print("Account: ");
        String input_account = input.nextLine();
        System.out.print("Password: ");
        String input_password = new String(console.readPassword());

        StaffFactory staff_factory = new StaffFactory();
        Employee user = staff_factory.getStaff(input_account);

        if (!user.getAccount().verifyPassword(input_password)) {
            System.err.println("The password is wrong");
            return null;
        }

        return user;
    }
}
