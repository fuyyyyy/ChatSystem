package com.dao;

import com.bean.groupSend_information;
import com.util.JDBCUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class group_informationDao {

    private JDBCUtil util = new JDBCUtil();

    public group_informationDao() throws SQLException {
    }

    //创建群聊
    public int insert(int groupId, String groupName, int createUserId) {
        PreparedStatement ps = null;
        String sql = "insert into group_information(groupId, groupName, createUserId, groupCreateTime) value(?, ?, ?, NOW())";
        int result = 0, result2 = 0;;
        ps = util.createPs(sql);
        try {
            ps.setInt(1, groupId);
            ps.setString(2, groupName);
            ps.setInt(3, createUserId);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }

        if(result != 0) {
            String sql1 = "select max(groupRelationshipId) from group_relationship";
            ResultSet rs = null;
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
                ps.setInt(2, createUserId);
                ps.setInt(3, groupId);
                result2 = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                util.close();
            }
        }
        return result2;
    }

    //获取创建人id
    public int getCreateUserId(int groupId) {
        String sql = "select createUserId from group_information where groupId = ?";
        ResultSet rs = null;
        int result = 0;
        PreparedStatement ps = util.createPs(sql);
        try {
            ps.setInt(1, groupId);
            rs = ps.executeQuery();
            while(rs.next()) {
                result = rs.getInt("createUserId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs);
        }
        return result;
    }

    //删除群聊
    public int delete(int groupId) {
        PreparedStatement ps = null;

        String sql1 = "delete from group_record " +
                "where groupRelationshipId in " +
                "(select groupRelationshipId from group_relationship " +
                "where groupId = ?)";
        int result1 = 0;
        ps = util.createPs(sql1);
        try {
            ps.setInt(1, groupId);
            result1 = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }

        String sql2 = "delete from group_relationship " +
                "where groupId = ?";
        int result2 = 0;
        ps = util.createPs(sql2);
        try {
            ps.setInt(1, groupId);
            result2 = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }

        String sql3= "delete from group_information " +
                "where groupId = ?";
        int result3 = 0;
        ps = util.createPs(sql3);
        try {
            ps.setInt(1, groupId);
            result3 = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }

        return result3;
    }

}
