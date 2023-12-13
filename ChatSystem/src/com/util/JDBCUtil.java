package com.util;

import java.sql.*;

public class JDBCUtil {
    static final String url = "jdbc:mysql://localhost:3306/ChatSystem";
    static final String user = "root";
    static final String password = "fyy20010704";
    private Connection conn = null;
    private PreparedStatement ps = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection createConn() {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection对象创建失败");
        }
        return conn;
    }

    public PreparedStatement createPs(String sql) {
        createConn();
        try {
            ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("PrepareStatement对象创建失败");
        }
        return ps;
    }

    public void close() {
        if(ps != null) {
            try {
                ps.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close();
    }

}
