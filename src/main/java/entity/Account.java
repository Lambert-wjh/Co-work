package entity;

import java.io.Console;
import java.sql.SQLException;
import database.dao.StaffDAO;

public class Account {
    private String account;
    private String password;

    public Account(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public void setPassword() {
        Console console = System.console();
        System.out.print("Enter formal password: ");
        String formal_password = new String(console.readPassword());
        if (!password.equals(formal_password)) {
            System.err.println("Formal password is not correct");
        }

        System.out.print("Enter new password: ");
        String new_password = new String(console.readPassword());
        System.out.print("Enter new password again: ");
        String verify_password = new String(console.readPassword());
        if (!new_password.equals(verify_password)) {
            System.err.println("The two passwords do not match");
        }
        this.password = new_password;

        StaffDAO staff_dao = new StaffDAO();
        try {
            staff_dao.updatePassword(this.account, new_password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
