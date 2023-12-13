package com.dao;

import com.bean.groupRelationship_information;
import com.bean.group_information;
import com.util.JDBCUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class group_relationshipDao {

    private JDBCUtil util = new JDBCUtil();

    public group_relationshipDao() throws SQLException {
    }

    //查询账号的群聊
    public List find(int userId) {
        String sql = "select * from group_relationship natural join group_information where userId = ?";
        ResultSet rs = null;
        List list = new ArrayList();
        PreparedStatement ps = util.createPs(sql);
        try {
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int groupId = rs.getInt("groupId");
                String groupName = rs.getString("groupName");
                groupRelationship_information Information = new groupRelationship_information(userId, groupId, groupName);
                list.add(Information);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs);
        }
        return list;
    }

    //查询用户账号和群聊账号对应的关系id
    public int getId(int userId, int groupId) {
        String sql = "select groupRelationshipId from group_relationship where userId = ? and groupId = ?";
        ResultSet rs = null;
        int result = 0;
        PreparedStatement ps = util.createPs(sql);
        try {
            ps.setInt(1, userId);
            ps.setInt(2, groupId);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt("groupRelationshipId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs);
        }
        return result;
    }

    //查询没有加入的群聊信息
    public List search(int userId) {
        String sql = "select * from group_information where groupId not in " +
                "(select groupId from group_relationship where userId = ?)";
        ResultSet rs = null;
        List list = new ArrayList();
        PreparedStatement ps = util.createPs(sql);
        try {
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int groupId = rs.getInt("groupId");
                String groupName = rs.getString("groupName");
                int createUserId = rs.getInt("createUserId");
                String groupCreateTime = rs.getString("groupCreateTime");
                group_information groupInformation = new group_information(groupId, groupName, createUserId, groupCreateTime);
                list.add(groupInformation);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close(rs);
        }
        return list;
    }

    //加入群聊
    public int add(int userId, int groupId) {
        PreparedStatement ps = null;
        String sql1 = "select max(groupRelationshipId) from group_relationship";
        ResultSet rs = null;
        int result = 0;
        int rId = 1;
        ps = util.createPs(sql1);
        try {
            rs = ps.executeQuery();
            while(rs.next()) {
                rId = rs.getInt("max(groupRelationshipId)") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql2 = "insert into group_relationship(groupRelationshipId, userId, groupId, joinTime) value(?, ?, ?, NOW())";
        ps = util.createPs(sql2);
        try {
            ps.setInt(1, rId);
            ps.setInt(2, userId);
            ps.setInt(3, groupId);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }
        return result;
    }

    //查询群聊信息
    public List query(int userId, String target) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        boolean flag = pattern.matcher(target).matches();
        if(!flag) {
            String sql = "select * from group_information where groupId not in " +
                    "(select groupId from group_relationship where userId = ?) " +
                    "and groupName like ?";
            ResultSet rs = null;
            List list = new ArrayList();
            PreparedStatement ps = util.createPs(sql);
            try {
                ps.setInt(1, userId);
                ps.setString(2, "%" + target + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int groupId = rs.getInt("groupId");
                    String groupName = rs.getString("groupName");
                    int createUserId = rs.getInt("createUserId");
                    String groupCreateTime = rs.getString("groupCreateTime");
                    group_information groupInformation = new group_information(groupId, groupName, createUserId, groupCreateTime);
                    list.add(groupInformation);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                util.close(rs);
            }
            return list;
        }
        else {
            int targetId = Integer.valueOf(target);
            String sql = "select * from group_information where groupId not in " +
                    "(select groupId from group_relationship where userId = ?) " +
                    "and (groupId = ? or groupName like ?)";
            ResultSet rs = null;
            List list = new ArrayList();
            PreparedStatement ps = util.createPs(sql);
            try {
                ps.setInt(1, userId);
                ps.setInt(2, targetId);
                ps.setString(3, "%" + target + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int groupId = rs.getInt("groupId");
                    String groupName = rs.getString("groupName");
                    int createUserId = rs.getInt("createUserId");
                    String groupCreateTime = rs.getString("groupCreateTime");
                    group_information groupInformation = new group_information(groupId, groupName, createUserId, groupCreateTime);
                    list.add(groupInformation);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                util.close(rs);
            }
            return list;
        }
    }

    //搜索群聊列表
    public List find(int userId, String target) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        boolean flag = pattern.matcher(target).matches();
        if (!flag) {
            String sql = "select * from group_relationship natural join group_information " +
                    "where userId = ? and groupName like ?";
            ResultSet rs = null;
            List list = new ArrayList();
            PreparedStatement ps = util.createPs(sql);
            try {
                ps.setInt(1, userId);
                ps.setString(2, "%" + target + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int groupId = rs.getInt("groupId");
                    String groupName = rs.getString("groupName");
                    groupRelationship_information Information = new groupRelationship_information(userId, groupId, groupName);
                    list.add(Information);
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
            String sql = "select * from group_relationship natural join group_information " +
                    "where userId = ? and (groupId = ? or groupName like ?)";
            ResultSet rs = null;
            List list = new ArrayList();
            PreparedStatement ps = util.createPs(sql);
            try {
                ps.setInt(1, userId);
                ps.setInt(2, targetId);
                ps.setString(3, "%" + target + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int groupId = rs.getInt("groupId");
                    String groupName = rs.getString("groupName");
                    groupRelationship_information Information = new groupRelationship_information(userId, groupId, groupName);
                    list.add(Information);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                util.close(rs);
            }
            return list;
        }
    }

    //删除用户和群聊的关系
    public int delete(int userId, int groupId) {
        PreparedStatement ps = null;

        String sql1 = "delete from group_record " +
                "where groupRelationshipId in " +
                "(select groupRelationshipId from group_relationship " +
                "where userId = ? and groupId = ?)";
        int result1 = 0;
        ps = util.createPs(sql1);
        try {
            ps.setInt(1, userId);
            ps.setInt(2, groupId);
            result1 = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }

        String sql2 = "delete from group_relationship " +
                "where userId = ? and groupId = ?";
        int result2 = 0;
        ps = util.createPs(sql2);
        try {
            ps.setInt(1, userId);
            ps.setInt(2, groupId);
            result2 = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }
        return result2;
    }

}