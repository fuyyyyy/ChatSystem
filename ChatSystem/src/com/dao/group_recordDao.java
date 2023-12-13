package com.dao;

import com.bean.groupSend_information;
import com.util.JDBCUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class group_recordDao {

    private JDBCUtil util = new JDBCUtil();

    public group_recordDao() throws SQLException {
    }

    public int insert(int groupRelationshipId, String groupRecordContent) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rId = 1;
        String sql1 = "select max(groupRecordId) from group_record";
        ps = util.createPs(sql1);
        try {
            rs = ps.executeQuery();
            while(rs.next()) {
                rId = rs.getInt("max(groupRecordId)") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql2 = "insert into group_record(groupRecordId, groupRelationshipId, groupRecordContent, groupSendTime) value(?, ?, ?, NOW())";
        int result = 0;
        ps = util.createPs(sql2);
        try {
            ps.setInt(1, rId);
            ps.setInt(2, groupRelationshipId);
            ps.setString(3, groupRecordContent);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }
        return result;
    }

    public List find(int groupId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "select userId, userName, groupId, groupRecordContent, groupSendTime " +
                "from user_information NATURAL JOIN group_relationship NATURAL JOIN group_record " +
                "where groupId = ? ";
        ps = util.createPs(sql);
        try {
            ps.setInt(1, groupId);
            rs = ps.executeQuery();
            while(rs.next()) {
                int groupSendId = rs.getInt("userId");
                String groupSendName = rs.getString("userName");
                String groupRecordContent = rs.getString("groupRecordContent");
                String groupSendTime = rs.getString("groupSendTime");
                groupSend_information groupSendInformation = new groupSend_information(groupSendId, groupSendName, groupId, groupRecordContent, groupSendTime);
                list.add(groupSendInformation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs);
        }
        return list;
    }

    //查询历史记录
    public List find(int groupId, String target) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "select userId, userName, groupId, groupRecordContent, groupSendTime " +
                "from user_information NATURAL JOIN group_relationship NATURAL JOIN group_record " +
                "where (userName like ? or groupRecordContent like ?) " +
                "and groupId = ? ";
        ps = util.createPs(sql);
        try {
            ps.setString(1, "%" + target + "%");
            ps.setString(2, "%" + target + "%");
            ps.setInt(3, groupId);
            rs = ps.executeQuery();
            while(rs.next()) {
                int groupSendId = rs.getInt("userId");
                String groupSendName = rs.getString("userName");
                String groupRecordContent = rs.getString("groupRecordContent");
                String groupSendTime = rs.getString("groupSendTime");
                groupSend_information groupSendInformation = new groupSend_information(groupSendId, groupSendName, groupId, groupRecordContent, groupSendTime);
                list.add(groupSendInformation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs);
        }
        return list;
    }

}
