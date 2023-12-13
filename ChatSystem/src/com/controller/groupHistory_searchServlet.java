package com.controller;

import com.bean.groupRelationship_information;
import com.bean.groupSend_information;
import com.bean.user_information;
import com.dao.group_recordDao;
import com.dao.group_relationshipDao;
import com.dao.user_relationshipDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class groupHistory_searchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int groupId = Integer.valueOf(req.getParameter("groupId"));
        String target = req.getParameter("target");
        String groupName = req.getParameter("groupName");
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        int userId = (int)session.getAttribute("userId");
        user_relationshipDao daoR = null;
        try {
            daoR = new user_relationshipDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        List<user_information> list1 = daoR.find(Integer.valueOf(userId));
        req.setAttribute("key", list1);
        req.setAttribute("groupId", groupId);
        req.setAttribute("groupName", groupName);

        group_recordDao daoC = null;
        try {
            daoC = new group_recordDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        List<groupSend_information> list2 = daoC.find(groupId, target);
        req.setAttribute("key2", list2);

        group_relationshipDao daoG = null;
        try {
            daoG= new group_relationshipDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        List<groupRelationship_information> list3 = daoG.find(userId);
        req.setAttribute("keyG", list3);

        req.getRequestDispatcher("/group_history.jsp").forward(req, resp);
    }

}
