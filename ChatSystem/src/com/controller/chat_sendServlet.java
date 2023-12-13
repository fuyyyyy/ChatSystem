package com.controller;

import com.dao.chat_recordDao;
import com.dao.user_relationshipDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class chat_sendServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int Id = Integer.valueOf(req.getParameter("id"));
        HttpSession session = req.getSession();
        int userId = (int)session.getAttribute("userId");

        user_relationshipDao daoR = null;
        try {
            daoR = new user_relationshipDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        int relationshipId = daoR.getId(userId, Id);

        chat_recordDao daoC = null;
        try {
            daoC = new chat_recordDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String content = req.getParameter("content");
        int rs = daoC.insert(relationshipId, content);

        PrintWriter out = null;
        resp.setContentType("text/html; charset = utf-8");
        out = resp.getWriter();
        if (rs == 1) {
            String userName = req.getParameter("name");
            userName= java.net.URLEncoder.encode(userName.toString(),"UTF-8");
            resp.sendRedirect("/ChatSystem/chat/refresh?id=" + req.getParameter("id") + "&name=" + userName);
        } else {
            out.print("发送失败！");
        }
    }

}
