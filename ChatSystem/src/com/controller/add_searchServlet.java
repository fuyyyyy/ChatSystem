package com.controller;

import com.bean.groupRelationship_information;
import com.bean.user_information;
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

public class add_searchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user_relationshipDao dao = null;
        String target = req.getParameter("target");
        try {
            dao = new user_relationshipDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        HttpSession session = req.getSession();
        int userId = (int)session.getAttribute("userId");
        List<user_information> list = dao.find(userId);
        req.setAttribute("key", list);

        group_relationshipDao daoG = null;
        try {
            daoG= new group_relationshipDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        List<groupRelationship_information> list3 = daoG.find(Integer.valueOf(userId));
        req.setAttribute("keyG", list3);

        if(target.length() != 0) {
            List<user_information> list1 = dao.query(userId, target);

            req.setAttribute("key1", list1);
            req.getRequestDispatcher("/relationship_add.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("key1", list);
            req.getRequestDispatcher("/relationship_add.jsp").forward(req, resp);
        }
    }
}
