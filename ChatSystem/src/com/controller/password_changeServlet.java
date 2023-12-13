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
import java.util.Objects;

public class password_changeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int userId = (int)session.getAttribute("userId");
        String password = (String) session.getAttribute("password");
        String oPassword = req.getParameter("password");
        String newPassword = req.getParameter("newPassword");
        String againPassword = req.getParameter("againPassword");
        int result = 0;

        if(Objects.equals(password, oPassword) && Objects.equals(newPassword, againPassword) && !Objects.equals(newPassword, "")) {
            user_informationDao dao = null;
            try {
                dao = new user_informationDao();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            result = dao.changePassword(userId, newPassword);

            PrintWriter out = null;
            resp.setContentType("text/html; charset = utf-8");
            out = resp.getWriter();
            if (result != 0) {
                session.removeAttribute("userName");
                session.setAttribute("userName", newPassword);
                resp.sendRedirect("/ChatSystem/user/quit");
            } else {
                out.print("修改失败！");
            }
        }
        else {
            resp.sendRedirect("/ChatSystem/informationChange/error");
        }
    }
}
