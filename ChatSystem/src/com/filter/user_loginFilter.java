package com.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class user_loginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session;
        String uri = request.getRequestURI();
        if(uri.indexOf("login") != -1 || uri.indexOf("register") != -1 || "/ChatSystem/".equals(uri)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        session = request.getSession(false);
        if(session.getAttribute("userId") != null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        request.getRequestDispatcher("/user_login.jsp").forward(servletRequest, servletResponse);
    }
}
