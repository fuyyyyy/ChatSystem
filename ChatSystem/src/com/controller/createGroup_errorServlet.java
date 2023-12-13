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

public class createGroup_errorServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int userId = (int)session.getAttribute("userId");
        user_relationshipDao daoR = null;
        try {
            daoR = new user_relationshipDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        List<user_information> list = daoR.find(Integer.valueOf(userId));

        req.setAttribute("key", list);

        group_relationshipDao daoG = null;
        try {
            daoG= new group_relationshipDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        List<groupRelationship_information> list3 = daoG.find(Integer.valueOf(userId));
        req.setAttribute("keyG", list3);

        req.getRequestDispatcher("/create_group_error.jsp").forward(req, resp);
    }
}
