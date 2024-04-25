package com.myproject.myapplication;

import com.myproject.myapplication.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private String url = "jdbc:mysql://192.168.1.8:3306/moveit";
    private String user = "root";
    private String pass = "Natal1_Pa55";
    private Statement statement;

    public DBManager(){
        try
        {
            Connection connection = DriverManager.getConnection(url, user, pass);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            this.onCreate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCreate() {
        String CREATE_USER_TABLE =
                "CREATE TABLE IF NOT EXISTS moveit.users ( " +
                        "id INTEGER PRIMARY KEY auto_increment," +
                        "username VARCHAR(255) NOT NULL," +
                        "email VARCHAR(255) NOT NULL UNIQUE," +
                        "password VARCHAR(30) NOT NULL)";

        try {
            statement.execute(CREATE_USER_TABLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser(String username,String email, String password) {
        try {
            String insert = "INSERT INTO `users`(`username`,`email`,`password`) VALUES ('"+
                    username +"','"+email+"','"+password+"');";
            statement.execute(insert);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeUsername(int id, String username){
        try {
            String update = "UPDATE moveit.users SET username='" + username + "' WHERE id=" + id + ";";
            statement.execute(update);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeEmail(int id, String email){
        try {
            String update = "UPDATE moveit.users SET email=" + email + " WHERE id=" + id + ";";
            statement.execute(update);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changePassword(int id, String password){
        try {
            String update = "UPDATE moveit.users SET password=" + password + " WHERE id=" + id + ";";
            statement.execute(update);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String select = "SELECT * FROM users;";
        ResultSet rs = null;

        try {
            rs = statement.executeQuery(select);
            while (rs.next()){
                User user = new User();
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");


                user.setId(id);
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(password);
                users.add(user);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return users;
    }
}
