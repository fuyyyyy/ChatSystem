package com.controller;

import com.dao.group_relationshipDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class groupAdd_sendServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        group_relationshipDao dao = null;
        int result = 0;
        PrintWriter out = null;

        try {
            dao = new group_relationshipDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        HttpSession session = req.getSession();
        int userId = (int)session.getAttribute("userId");
        int groupId = Integer.valueOf(req.getParameter("groupId"));

        result = dao.add(userId, groupId);

        resp.setContentType("text/html; charset = utf-8");
        out = resp.getWriter();
        if(result == 1) {
            resp.sendRedirect("/ChatSystem/group/add");
        }
        else {
            out.print("加入失败！");
        }
    }
}
