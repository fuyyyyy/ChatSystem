package com.controller;

import com.bean.user_information;
import com.dao.user_informationDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class user_registerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        boolean flag = pattern.matcher(req.getParameter("userId")).matches();
        if(req.getParameter("userId") == "" || req.getParameter("userName") == "" || req.getParameter("password") == "" || !flag){
            resp.sendRedirect("/ChatSystem/user_register_error.jsp");
        }
        else {
            String userId, userName, password;
            user_informationDao dao = null;
            try {
                dao = new user_informationDao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            user_information userInformation = null;
            int result = 0;
            PrintWriter out = null;

            userId = req.getParameter("userId");
            userName = req.getParameter("userName");
            password = req.getParameter("password");

            userInformation = new user_information(Integer.valueOf(userId), userName, password);
            result = dao.insert(userInformation);

            resp.setContentType("text/html; charset = utf-8");
            out = resp.getWriter();
            if (result == 1) {
                out.print("注册成功！点击<a href = http://localhost:8080/ChatSystem/user_login.jsp> 跳转 </a>到登录页面");
            } else {
                resp.sendRedirect("/ChatSystem/user_register_error.jsp");
            }
        }
    }
}
