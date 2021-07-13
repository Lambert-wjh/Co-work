package entity;

import java.io.Console;
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
        System.out.print("输入原密码: ");
        String formal_password = new String(console.readPassword());
        if (!password.equals(formal_password)) {
            System.err.println("原密码错误");
            return;
        }

        System.out.print("输入新密码: ");
        String new_password = new String(console.readPassword());
        System.out.print("再次输入新密码: ");
        String verify_password = new String(console.readPassword());
        if (!new_password.equals(verify_password)) {
            System.err.println("两次密码不一致");
            return;
        }
        this.password = new_password;

        StaffDAO staff_dao = new StaffDAO();
        String update_clause = "UPDATE Employee SET password=? WHERE id=?";
        List<String> parameters = new ArrayList<>();
        parameters.add(new_password);
        parameters.add(this.account);
        if (staff_dao.updateStaff(update_clause, parameters) == 0) {
            System.err.println("修改密码失败");
        }
    }
}
