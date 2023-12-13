package com.controller;

import com.bean.groupRelationship_information;
import com.bean.user_information;
import com.dao.group_relationshipDao;
import com.dao.user_informationDao;
import com.dao.user_relationshipDao;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class user_loginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        boolean flag = pattern.matcher(req.getParameter("userId")).matches();
        if(req.getParameter("userId") == "" || req.getParameter("password") == "" || !flag){
            resp.sendRedirect("/ChatSystem/user_login_error.jsp");
        }
        else {
            String userId, password;
            user_informationDao dao = null;
            try {
                dao = new user_informationDao();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            user_information result = null;

            userId = req.getParameter("userId");
            password = req.getParameter("password");
            result = dao.find(userId, password);
            if (result != null) {
                HttpSession informationSession = req.getSession();
                informationSession.setAttribute("userId", result.getUserId());
                informationSession.setAttribute("userName", result.getUserName());
                informationSession.setAttribute("password", result.getPassword());

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
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("/ChatSystem/user_login_error.jsp");
            }
        }
    }

}
