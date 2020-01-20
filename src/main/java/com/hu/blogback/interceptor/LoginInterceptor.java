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

        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();
        if (session.getAttribute("usr")  != null || session.getAttribute("user") != null) {
            return true;
        } else if (requestURI.contains("admin")) {
            response.sendRedirect("/admin");
            return false;
        } else if (requestURI.contains("usr")){
            response.sendRedirect("/usr");
            return false;
        } else {
            response.sendRedirect("/");
            return false;
        }

    }
}
