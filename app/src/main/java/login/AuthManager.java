/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import database.DatabaseHelper;
import models.User;

import java.sql.ResultSet;

public class AuthManager {

    public static User login(String email, String password) {
        try {
            ResultSet rs = DatabaseHelper.executeQuery(
                "SELECT * FROM users WHERE email = ? AND password = ?",
                email, password
            );

            if (rs.next()) {
                int id = rs.getInt("user_id");
                String fullName = rs.getString("full_name");
                String role = rs.getString("role");
                String clientType = rs.getString("client_type"); 

                return new User(id, fullName, email, role, clientType);
            } else {
                return null; // login failed
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
