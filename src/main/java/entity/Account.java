package entity;

import java.io.Console;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import database.dao.StaffDAO;

public class Account {
    private String account;
    private String password;

    public Account(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public boolean verifyPassword(String input_password) {
        return this.password.equals(input_password);
    }

    public void setPassword() {
        Console console = System.console();
        System.out.print("Enter formal password: ");
        String formal_password = new String(console.readPassword());
        if (!password.equals(formal_password)) {
            System.err.println("Formal password is not correct");
            return;
        }

        System.out.print("Enter new password: ");
        String new_password = new String(console.readPassword());
        System.out.print("Enter new password again: ");
        String verify_password = new String(console.readPassword());
        if (!new_password.equals(verify_password)) {
            System.err.println("The two passwords do not match");
            return;
        }
        this.password = new_password;

        StaffDAO staff_dao = new StaffDAO();
        String update_clause = "UPDATE Employee SET password=? WHERE id=?";
        List<String> parameters = new ArrayList<>();
        parameters.add(new_password);
        parameters.add(this.account);
        if (staff_dao.updateStaff(update_clause, parameters) == 0) {
            System.err.println("Update password failed");
        }
    }
}
