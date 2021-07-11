package Tools;

import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Staff.Employee;
import Tools.Enums.Position;

public class Account {
    private String account;
    private String password;

    public Account(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public void SetPassword() {
    }

    public static Position Verify(String input_account, String input_password) {
        Position position = null;
        JDBC jdbc = JDBC.GetJDBC();

        try (Connection conn = jdbc.GetConnection()) {
            try (PreparedStatement prst = conn.prepareStatement("SELECT password, position FROM Staff WHERE id=?")) {
                prst.setObject(1, input_account);
                try (ResultSet rese = prst.executeQuery()) {
                    if (rese.next()) {
                        String password = rese.getString("password");
                        if (password.equals(input_password)) {
                            position = Position.valueOf(rese.getString("position"));
                        } else {
                            throw new IllegalAccessError("Password is not correct");
                        }
                    } else {
                        throw new IllegalArgumentException("No such staff");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return position;
    }

    public static Employee Login() {
        Scanner input = new Scanner(System.in);
        Console console = System.console();
        System.out.println("Welcome to Headhunter Management System");
        System.out.print("Account: ");
        String input_account = input.nextLine();
        System.out.print("Password: ");
        String input_password = new String(console.readPassword());

        Position position = Account.Verify(input_account, input_password);
        StaffFactory staff_factory = new StaffFactory();
        Employee user = null;
        switch (position) {
            case EMPLOYEE -> {
                user = staff_factory.GetEmployee(input_account);
            }
            case LEADER -> {
                user = staff_factory.GetLeader(input_account);
            }
            case SUPERUSER -> {
                user = staff_factory.GetSuperuser(input_account);
            }
        }

        return user;
    }
}
