package entity;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import database.DAO;

public class Account {
    private String account;
    private String password;

    public Account(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean verifyPassword(String input_password) {
        return this.password.equals(input_password);
    }

    public void setPassword() {
        Console console = System.console();
        System.out.print("Enter original password: ");
        String formal_password = new String(console.readPassword());
        if (!password.equals(formal_password)) {
            System.err.println("The original password is wrong");
            return;
        }

        System.out.print("Enter new password: ");
        String new_password = new String(console.readPassword());
        System.out.print("Enter the new password again: ");
        String verify_password = new String(console.readPassword());
        if (!new_password.equals(verify_password)) {
            System.err.println("The two password do not match");
            return;
        }
        this.password = new_password;

        String update_clause = "UPDATE Employee SET password=? WHERE id=?";
        List<String> parameters = new ArrayList<>();
        parameters.add(new_password);
        parameters.add(this.account);

        if (DAO.getDAO().updateTable(update_clause, parameters) == 0) {
            System.err.println("Failed to change password");
        } else {
            System.out.println("Change password successfully");
        }
    }
}
