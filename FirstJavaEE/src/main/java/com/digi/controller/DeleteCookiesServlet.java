package com.digi.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DeleteCookiesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie cookie = new Cookie("some_id", "");
        Cookie cookie1 = new Cookie("JSESSIONID", "");

        cookie.setMaxAge(0);
        cookie1.setMaxAge(0);

        response.addCookie(cookie);
        response.addCookie(cookie1);
    }
}
