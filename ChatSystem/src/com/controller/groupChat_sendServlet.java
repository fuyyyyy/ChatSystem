package com.controller;

import com.dao.chat_recordDao;
import com.dao.group_recordDao;
import com.dao.group_relationshipDao;
import com.dao.user_relationshipDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class groupChat_sendServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int groupId = Integer.valueOf(req.getParameter("groupId"));
        HttpSession session = req.getSession();
        int userId = (int)session.getAttribute("userId");

        group_relationshipDao daoR = null;
        try {
            daoR = new group_relationshipDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        int relationshipId = daoR.getId(userId, groupId);

        group_recordDao daoC = null;
        try {
            daoC = new group_recordDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String content = req.getParameter("content");
        int rs = daoC.insert(relationshipId, content);

        PrintWriter out = null;
        resp.setContentType("text/html; charset = utf-8");
        out = resp.getWriter();
        if (rs == 1) {
            String groupName = req.getParameter("groupName");
            groupName = java.net.URLEncoder.encode(groupName.toString(),"UTF-8");
            resp.sendRedirect("/ChatSystem/groupChat/refresh?groupId=" + groupId + "&groupName=" + groupName);
        } else {
            out.print("发送失败！");
        }
    }

}

