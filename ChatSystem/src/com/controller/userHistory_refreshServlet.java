package com.controller;

import com.bean.send_information;
import com.bean.user_information;
import com.bean.groupRelationship_information;
import com.dao.chat_recordDao;
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

public class userHistory_refreshServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int Id = Integer.valueOf(req.getParameter("id"));
        req.setCharacterEncoding("UTF-8");
        String userName = req.getParameter("name");

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
        req.setAttribute("id", Id);
        req.setAttribute("name", userName);

        int relationshipId1 = daoR.getId(userId, Id);
        int relationshipId2 = daoR.getId(Id, userId);

        chat_recordDao daoC = null;
        try {
            daoC = new chat_recordDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        List<send_information> list2 = daoC.find(relationshipId1, relationshipId2);
        req.setAttribute("key2", list2);

        group_relationshipDao daoG = null;
        try {
            daoG= new group_relationshipDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        List<groupRelationship_information> list3 = daoG.find(userId);
        req.setAttribute("keyG", list3);

        req.getRequestDispatcher("/user_history.jsp").forward(req, resp);
    }
}
