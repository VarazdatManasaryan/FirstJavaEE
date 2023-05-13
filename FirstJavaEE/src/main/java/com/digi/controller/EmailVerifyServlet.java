package com.digi.controller;

import com.digi.model.User;
import com.digi.repository.impl.UserRepositoryImplJDBC;
import com.digi.repository.impl.UserRepositoryImplJPA;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class EmailVerifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserRepositoryImplJDBC jdbc = new UserRepositoryImplJDBC();
        UserRepositoryImplJPA jpa = new UserRepositoryImplJPA();

        String email = request.getParameter("email");
        //User user = jdbc.getByUsername(email);
        User user = jpa.getByUsername(email);

        if (user != null) {
            session.setAttribute("verify-email", email);
            response.sendRedirect("/forgot-password.jsp");
        } else {
            request.setAttribute("errorMessage", "wrong email");
            request.getRequestDispatcher("/email-verify.jsp").forward(request, response);
        }
    }
}
