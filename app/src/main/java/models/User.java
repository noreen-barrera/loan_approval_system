/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

public class User {
    private int userId;
    private String fullName;
    private String email;
    private String role;
    private String clientType;

    // Constructor
    public User(int userId, String fullName, String email, String role, String clientType) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.clientType = clientType;
    }

    // Getters
    public int getUserId() { return userId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getClientType() { return clientType; }
}
