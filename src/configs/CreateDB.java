package configs;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateDB {
    public static void create() {
        try {
            String databaseName = "exam_test_program";
            String username = "root";
            String password = "host123";
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";

        Connection connection = DriverManager.getConnection(url,username, password);

        String sql = "CREATE DATABASE " + databaseName;

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        JOptionPane.showMessageDialog(null, databaseName + " Database has been created successfully", "System Message", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
        e.printStackTrace();
        }
    }
}



