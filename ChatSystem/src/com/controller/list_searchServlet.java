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

public class list_searchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userId");
        String target = req.getParameter("target");

        if(target.length() != 0) {
            user_relationshipDao daoR = null;
            try {
                daoR = new user_relationshipDao();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            List<user_information> list = daoR.find(Integer.valueOf(userId), target);

            group_relationshipDao daoG = null;
            try {
                daoG = new group_relationshipDao();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            List<groupRelationship_information> list3 = daoG.find(Integer.valueOf(userId), target);
            req.setAttribute("keyG", list3);

            req.setAttribute("key", list);
            req.getRequestDispatcher("/index_search.jsp").forward(req, resp);
        }

        else {
            req.getRequestDispatcher("/relationshipList/refresh").forward(req, resp);
        }
    }
}
