package com.epam.filter;

import com.epam.entity.Role;
import com.epam.entity.User;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        doFilter(((HttpServletRequest) req), ((HttpServletResponse) resp), chain);
    }

    private void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute("client");
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            String id = session.getId();
            if (cookie.getValue().equals(id)) {
                chain.doFilter(req, resp);
                return;
            }
        }

        String pathInfo = req.getPathInfo();
        if (!pathInfo.equals("/home") || (!pathInfo.equals("/login")) || (!pathInfo.equals("/register"))){
            resp.sendRedirect(req.getContextPath()+"/do/login");
            return;
        }
        chain.doFilter(req, resp);
        return;
//        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {

    }
}
