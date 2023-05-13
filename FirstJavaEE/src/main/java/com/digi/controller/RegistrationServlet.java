package com.digi.controller;

import com.digi.enums.Status;
import com.digi.model.User;
import com.digi.repository.impl.UserRepositoryImplJDBC;
import com.digi.repository.impl.UserRepositoryImplJPA;
import com.digi.util.GenerateToken;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserRepositoryImplJDBC jdbc = new UserRepositoryImplJDBC();
        UserRepositoryImplJPA jpa = new UserRepositoryImplJPA();

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String age = request.getParameter("age");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String verifyCode = GenerateToken.generateVerifyCode();

        User user = new User(0, name, surname, Integer.parseInt(age), email, password, verifyCode, Status.INACTIVE);
        //jdbc.save(user);
        jpa.save(user);

        session.setAttribute("username", email);

        request.setAttribute("verify_code", verifyCode);

        request.getRequestDispatcher("/verification.jsp").forward(request, response);
    }
}