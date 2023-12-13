package com.dao;

import com.bean.user_information;
import com.util.JDBCUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class user_informationDao {

    private JDBCUtil util = new JDBCUtil();

    public user_informationDao() throws SQLException {
    }

    //用户注册
    public int insert(user_information userInformation) {
        String sql = "insert into user_information(userId, userName, password) value(?, ?, ?)";
        int result = 0;
        PreparedStatement ps = util.createPs(sql);
        try {
            ps.setInt(1, Integer.valueOf(userInformation.getUserId()));
            ps.setString(2, userInformation.getUserName());
            ps.setString(3, userInformation.getPassword());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }
        return result;
    }

    //用户登录验证
    public user_information find(String userId, String password) {
        String sql = "select * from user_information where userId = ? and password = ?";
        ResultSet rs = null;
        user_information userInformation = null;
        PreparedStatement ps = util.createPs(sql);
        try {
            ps.setInt(1, Integer.valueOf(userId));
            ps.setString(2, password);
            rs = ps.executeQuery();
            if(rs.next()) {
                String userName = rs.getString("userName");
                userInformation = new user_information(Integer.valueOf(userId), userName, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs);
        }
        return userInformation;
    }

    //查询id对应昵称
    public user_information search(int thisId) {
        String sql = "select * from user_information where userId = ?";
        ResultSet rs = null;
        user_information userInformation = null;
        PreparedStatement ps = util.createPs(sql);
        try {
            ps.setInt(1, thisId);
            rs = ps.executeQuery();
            if(rs.next()) {
                userInformation = new user_information(thisId, rs.getString("userName"), rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs);
        }
        return userInformation;
    }

    //修改昵称
    public int changeName(int userId, String newName) {
        String sql = "update user_information set userName = ? where userId = ?";
        int result = 0;
        PreparedStatement ps = util.createPs(sql);
        try {
            ps.setString(1, newName);
            ps.setInt(2, userId);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }
        return result;
    }

    //修改密码
    public int changePassword(int userId, String password) {
        String sql = "update user_information set password = ? where userId = ?";
        int result = 0;
        PreparedStatement ps = util.createPs(sql);
        try {
            ps.setString(1, password);
            ps.setInt(2, userId);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }
        return result;
    }

}
