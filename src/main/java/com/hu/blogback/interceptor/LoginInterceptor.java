package com.hu.blogback.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession();
        if (session.getAttribute("usr")  != null || session.getAttribute("user") != null) {
            return true;
        } else if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/admin");
            return false;
        } else {
            response.sendRedirect("/usr");
            return false;
        }

    }
}
