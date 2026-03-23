/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    public static ResultSet executeQuery(String sql, Object... params) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        setParameters(stmt, params);
        return stmt.executeQuery();
    }

    public static int executeUpdate(String sql, Object... params) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        setParameters(stmt, params);
        return stmt.executeUpdate();
    }

    private static void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
    }
}

