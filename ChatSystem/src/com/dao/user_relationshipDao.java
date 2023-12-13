package com.dao;

import com.bean.relationship_add;
import com.util.JDBCUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class user_relationshipDao {

    private JDBCUtil util = new JDBCUtil();

    public user_relationshipDao() throws SQLException {
    }

    //添加用户1对用户2的好友记录
    public int insert(int userId1, int userId2) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rId = 1;
        String sql1 = "select max(relationshipId) from user_relationship";
        ps = util.createPs(sql1);
        try {
            rs = ps.executeQuery();
            while(rs.next()) {
                rId = rs.getInt("max(relationshipId)") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql2 = "insert into user_relationship(relationshipId, userId1, userId2, createTime) value(?, ?, ?, NOW())";
        int result = 0;
        ps = util.createPs(sql2);
        try {
            ps.setInt(1, rId);
            ps.setInt(2, userId1);
            ps.setInt(3, userId2);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }
        return result;
    }

    //判断申请列表：0-未申请 1-已申请
    public int find(int userId1, int userId2) {
        String sql = "select count(*) from user_relationship where userId1 = ? and userId2 = ?";
        ResultSet rs = null;
        int result = 0;
        PreparedStatement ps = util.createPs(sql);
        try {
            ps.setInt(1, userId1);
            ps.setInt(2, userId2);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs);
        }
        if(result == 0) return 0;
        return 1;
    }

    //判断好友关系：0-未添加 1-用户1发出申请 2-用户2发出申请 3-已添加
    public int search(int userId1, int userId2) {
        int r1 = find(userId1, userId2);
        int r2 = find(userId2, userId1);
        if(r1 == 0 && r2 == 0) return 0;
        if(r1 == 1 && r2 == 0) return 1;
        if(r1 == 0 && r2 == 1) return 2;
        return 3;
    }

    //显示除自己外的用户信息
    public List find(int thisId) {
        String sql = "select * from user_information where userId <> ?";
        ResultSet rs = null;
        List list = new ArrayList();
        PreparedStatement ps = util.createPs(sql);
        try {
            ps.setInt(1, thisId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userId");
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                int r = search(thisId, userId);
                relationship_add userInformation = new relationship_add(userId, userName, password, r);
                list.add(userInformation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs);
        }
        return list;
    }

    //获取用户1对用户2的关系id
    public int getId(int userId1, int userId2) {
        String sql = "select relationshipId from user_relationship where userId1 = ? and userId2 = ?";
        ResultSet rs = null;
        int result = 0;
        PreparedStatement ps = util.createPs(sql);
        try {
            ps.setInt(1, userId1);
            ps.setInt(2, userId2);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt("relationshipId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs);
        }
        return result;
    }

    //通过id和name查询用户
    public List query(int thisId, String target) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        boolean flag = pattern.matcher(target).matches();
        if(!flag){
            String sql = "select * from user_information " +
                    "where userId <> ? and userName like ?";
            ResultSet rs = null;
            List list = new ArrayList();
            user_relationshipDao dao = null;
            try {
                dao = new user_relationshipDao();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            PreparedStatement ps = util.createPs(sql);
            try {
                ps.setInt(1, thisId);
                ps.setString(2, "%" + target + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int userId = rs.getInt("userId");
                    String userName = rs.getString("userName");
                    String password = rs.getString("password");
                    int r = dao.search(thisId, userId);
                    relationship_add userInformation = new relationship_add(userId, userName, password, r);
                    list.add(userInformation);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                util.close(rs);
            }
            return list;
        }
        else {
            int targetId = Integer.valueOf(target);
            String sql = "select * from user_information " +
                    "where userId <> ? and (userId = ? or userName like ?)";
            ResultSet rs = null;
            List list = new ArrayList();
            user_relationshipDao dao = null;
            try {
                dao = new user_relationshipDao();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            PreparedStatement ps = util.createPs(sql);
            try {
                ps.setInt(1, thisId);
                ps.setInt(2, targetId);
                ps.setString(3, "%" + target + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int userId = rs.getInt("userId");
                    String userName = rs.getString("userName");
                    String password = rs.getString("password");
                    int r = dao.search(thisId, userId);
                    relationship_add userInformation = new relationship_add(userId, userName, password, r);
                    list.add(userInformation);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                util.close(rs);
            }
            return list;
        }
    }

    //删除好友
    public int delete(int userId1, int userId2) {
        PreparedStatement ps;

        String sql = "select relationshipId from user_relationship " +
                "where userId1 = ? and userId2 = ?";
        ResultSet rs = null;
        int relationshipId = 0;
        ps = util.createPs(sql);
        try {
            ps.setInt(1, userId1);
            ps.setInt(2, userId2);
            rs = ps.executeQuery();
            while (rs.next()) {
                relationshipId = rs.getInt("relationshipId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs);
        }

        String sql1 = "delete from user_relationship where userId1 = ? and userId2 = ?";
        int result1 = 0;
        ps = util.createPs(sql1);
        try {
            ps.setInt(1, userId1);
            ps.setInt(2, userId2);
            result1 = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }

        if (result1 != 0) {
            String sql2 = "delete from chat_record where relationshipId = ?";
            int result2 = 0;
            ps = util.createPs(sql2);
            try {
                ps.setInt(1, relationshipId);
                result2 = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                util.close();
            }
            return 1;
        }
        return 0;
    }

    //搜索好友列表
    public List find(int thisId, String target) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        boolean flag = pattern.matcher(target).matches();
        if(!flag) {
            String sql = "select * from user_information " +
                    "where userId <> ? and userName like ?";
            ResultSet rs = null;
            List list = new ArrayList();
            PreparedStatement ps = util.createPs(sql);
            try {
                ps.setInt(1, thisId);
                ps.setString(2, "%" + target + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int userId = rs.getInt("userId");
                    String userName = rs.getString("userName");
                    String password = rs.getString("password");
                    int r = search(thisId, userId);
                    relationship_add userInformation = new relationship_add(userId, userName, password, r);
                    list.add(userInformation);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                util.close(rs);
            }
            return list;
        }
        else {
            int targetId = Integer.valueOf(target);
            String sql = "select * from user_information " +
                    "where userId <> ? and (userId = ? or userName like ?)";
            ResultSet rs = null;
            List list = new ArrayList();
            PreparedStatement ps = util.createPs(sql);
            try {
                ps.setInt(1, thisId);
                ps.setInt(2, targetId);
                ps.setString(3, "%" + target + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int userId = rs.getInt("userId");
                    String userName = rs.getString("userName");
                    String password = rs.getString("password");
                    int r = search(thisId, userId);
                    relationship_add userInformation = new relationship_add(userId, userName, password, r);
                    list.add(userInformation);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                util.close(rs);
            }
            return list;
        }
    }
}
