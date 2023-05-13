package com.digi.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class SetCookiesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie cookie = new Cookie("some_id", "123");
        Cookie cookie1 = new Cookie("some_name", "Justin");

        cookie.setMaxAge(24 * 60 * 60);
        cookie.setMaxAge(24 * 60 * 60);

        response.addCookie(cookie);
        response.addCookie(cookie1);
    }
}
