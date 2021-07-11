package Tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
