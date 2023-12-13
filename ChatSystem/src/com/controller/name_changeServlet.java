package com.controller;

import com.dao.user_informationDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class name_changeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int userId = (int)session.getAttribute("userId");
        String newName = req.getParameter("name");
        int result = 0;

        user_informationDao dao = null;
        try {
            dao = new user_informationDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        result = dao.changeName(userId, newName);

        PrintWriter out = null;
        resp.setContentType("text/html; charset = utf-8");
        out = resp.getWriter();
        if(result != 0) {
            session.removeAttribute("userName");
            session.setAttribute("userName", newName);
            resp.sendRedirect("/ChatSystem/relationshipList/refresh");
        }
        else {
            out.print("修改失败！");
        }
    }
}
