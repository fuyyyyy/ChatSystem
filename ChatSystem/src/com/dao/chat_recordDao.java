package com.dao;

import com.bean.chat_record;
import com.bean.send_information;
import com.util.JDBCUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class chat_recordDao {

    private JDBCUtil util = new JDBCUtil();

    public chat_recordDao() throws SQLException {
    }

    public int insert(int relationshipId, String recordContent) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rId = 1;
        String sql1 = "select max(recordId) from chat_record";
        ps = util.createPs(sql1);
        try {
            rs = ps.executeQuery();
            while(rs.next()) {
                rId = rs.getInt("max(recordId)") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql2 = "insert into chat_record(recordId, relationshipId, recordContent, sendTime) value(?, ?, ?, NOW())";
        int result = 0;
        ps = util.createPs(sql2);
        try {
            ps.setInt(1, rId);
            ps.setInt(2, relationshipId);
            ps.setString(3, recordContent);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }
        return result;
    }

    public List find(int thisId1, int thisId2) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "select userId1, userName, recordContent, sendTime " +
                "from user_information, user_relationship NATURAL JOIN chat_record " +
                "where (relationshipId = ? or relationshipId = ?) " +
                "and userId1 = userId ";
        ps = util.createPs(sql);
        try {
            ps.setInt(1, thisId1);
            ps.setInt(2, thisId2);
            rs = ps.executeQuery();
            while(rs.next()) {
                int sendId = rs.getInt("userId1");
                String sendName = rs.getString("userName");
                String recordContent = rs.getString("recordContent");
                String sendTime = rs.getString("sendTime");
                send_information userInformation = new send_information(recordContent, sendId, sendName, sendTime);
                list.add(userInformation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs);
        }
        return list;
    }

    //查询历史记录
    public List find(int thisId1, int thisId2, String target) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "select userId1, userName, recordContent, sendTime " +
                "from user_information, user_relationship NATURAL JOIN chat_record " +
                "where (relationshipId = ? or relationshipId = ?) " +
                "and recordContent like ? " +
                "and userId1 = userId";
        ps = util.createPs(sql);
        try {
            ps.setInt(1, thisId1);
            ps.setInt(2, thisId2);
            ps.setString(3, "%" + target + "%");
            rs = ps.executeQuery();
            while(rs.next()) {
                int sendId = rs.getInt("userId1");
                String sendName = rs.getString("userName");
                String recordContent = rs.getString("recordContent");
                String sendTime = rs.getString("sendTime");
                send_information userInformation = new send_information(recordContent, sendId, sendName, sendTime);
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
