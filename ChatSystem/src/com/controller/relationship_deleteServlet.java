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

public class relationship_deleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user_relationshipDao dao = null;
        int result1 = 0, result2 = 0;
        PrintWriter out = null;

        try {
            dao = new user_relationshipDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        HttpSession session = req.getSession();
        int thisId = (int)session.getAttribute("userId");
        int userId = Integer.valueOf(req.getParameter("id"));

        result1 = dao.delete(thisId, userId);
        result2 = dao.delete(userId, thisId);


        resp.setContentType("text/html; charset = utf-8");
        out = resp.getWriter();
        if((result1 == 1) && (result2 == 1)) {
            resp.sendRedirect("/ChatSystem/relationshipList/refresh");
        }
        else {
            out.print("删除失败！");
        }
    }
}
