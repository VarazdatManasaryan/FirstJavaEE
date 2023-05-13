package com.digi.controller;

import com.digi.model.User;
import com.digi.repository.impl.UserRepositoryImplJDBC;
import com.digi.repository.impl.UserRepositoryImplJPA;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ForgotPasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserRepositoryImplJDBC jdbc = new UserRepositoryImplJDBC();
        UserRepositoryImplJPA jpa = new UserRepositoryImplJPA();

        String new_password = request.getParameter("new_password");
        String confirm_password = request.getParameter("confirm_password");

        if (new_password.equals(confirm_password)) {
            String email = (String) session.getAttribute("verify-email");
            //User user = jdbc.getByUsername(email);
            User user = jpa.getByUsername(email);
            user.setPassword(new_password);
            //jdbc.updateUser(user);
            jpa.updateUser(user);
            response.sendRedirect("/loginPage.jsp");
        } else {
            request.setAttribute("errorMessage", "passwords don't match");
            request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserRepositoryImplJDBC jdbc = new UserRepositoryImplJDBC();
        UserRepositoryImplJPA jpa = new UserRepositoryImplJPA();

        String old_password = request.getParameter("old_password");
        String new_password = request.getParameter("new_password");
        String confirm_password = request.getParameter("confirm_password");

        User user = (User) session.getAttribute("user");
        String email = user.getEmail();

        if (new_password.equals(confirm_password)) {
            //User userDB = jdbc.getByUsername(email);
            User userDB = jpa.getByUsername(email);
            if (userDB.getPassword().equals(old_password)) {
                //jdbc.changePassword(userDB.getEmail(), new_password);
                jpa.changePassword(userDB.getEmail(), new_password);
                response.sendRedirect("/loginPage.jsp");
            } else {
                request.setAttribute("errorMessage", "wrong password");
                request.getRequestDispatcher("/change-password.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "passwords don't match");
            request.getRequestDispatcher("/change-password.jsp").forward(request, response);
        }
    }
}
