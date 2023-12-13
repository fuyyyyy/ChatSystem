package com.controller;

import com.bean.user_information;
import com.dao.group_informationDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class createRequest_sendServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        boolean flag = pattern.matcher(req.getParameter("groupId")).matches();
        if(req.getParameter("groupId") == "" || req.getParameter("groupName") == "" || !flag){
            resp.sendRedirect("/ChatSystem/createGroup/error");
        }
        else {
            int groupId;
            String groupName;
            HttpSession session = req.getSession();
            int userId = (int)session.getAttribute("userId");
            group_informationDao dao = null;
            try {
                dao = new group_informationDao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int result = 0;

            groupId = Integer.valueOf(req.getParameter("groupId"));
            groupName = req.getParameter("groupName");

            result = dao.insert(groupId, groupName, userId);

            if (result == 1) {
                resp.sendRedirect("/ChatSystem/relationshipList/refresh");
            } else {
                resp.sendRedirect("/ChatSystem/createGroup/error");
            }
        }
    }
}
