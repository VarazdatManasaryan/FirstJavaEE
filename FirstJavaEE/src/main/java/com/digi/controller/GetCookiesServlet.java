package com.digi.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class GetCookiesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            response.getWriter().println(cookie.getName() + " : " + cookie.getValue());
        }
    }
}
