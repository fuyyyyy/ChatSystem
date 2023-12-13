package com.controller;

import com.dao.group_informationDao;
import com.dao.group_relationshipDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class group_deleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int thisId = (int) session.getAttribute("userId");
        int groupId = Integer.valueOf(req.getParameter("groupId"));

        group_informationDao daoI = null;
        try {
            daoI = new group_informationDao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        int createUserId = daoI.getCreateUserId(groupId);
        if(createUserId == thisId) {
            int rs = daoI.delete(groupId);

            PrintWriter out = null;
            resp.setContentType("text/html; charset = utf-8");
            out = resp.getWriter();
            if(rs != 0) {
                resp.sendRedirect("/ChatSystem/relationshipList/refresh");
            }
            else {
                out.print("删除失败！");
            }
        }

        else {
            group_relationshipDao daoR = null;
            try {
                daoR = new group_relationshipDao();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            int rs = daoR.delete(thisId, groupId);

            PrintWriter out = null;
            resp.setContentType("text/html; charset = utf-8");
            out = resp.getWriter();
            if(rs != 0) {
                resp.sendRedirect("/ChatSystem/relationshipList/refresh");
            }
            else {
                out.print("删除失败！");
            }
        }
    }
}
