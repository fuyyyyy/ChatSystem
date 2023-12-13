package com.controller;

import com.dao.user_relationshipDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


public class addRequest_sendServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user_relationshipDao dao = null;
        int result = 0;
        PrintWriter out = null;

        try {
            dao = new user_relationshipDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        HttpSession session = req.getSession();
        int thisId = (int)session.getAttribute("userId");
        int userId = Integer.valueOf(req.getParameter("Id"));

        result = dao.insert(thisId, userId);

        resp.setContentType("text/html; charset = utf-8");
        out = resp.getWriter();
        if(result == 1) {
            resp.sendRedirect("/ChatSystem/relationship/add");
        }
        else {
            out.print("添加失败！");
        }
    }
}
