package com.digi.controller;

import com.digi.enums.Status;
import com.digi.model.User;
import com.digi.repository.impl.UserRepositoryImplJDBC;
import com.digi.repository.impl.UserRepositoryImplJPA;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class VerificationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserRepositoryImplJDBC jdbc = new UserRepositoryImplJDBC();
        UserRepositoryImplJPA jpa = new UserRepositoryImplJPA();

        String verifyCode = request.getParameter("verifyCode");
        String email = (String) session.getAttribute("username");

        //User user = jdbc.getByUsername(email);
        User user = jpa.getByUsername(email);

        String verifyCodeDB = user.getVerifyCode();

        if (verifyCode.equals(verifyCodeDB)) {
            user.setVerifyCode(null);
            user.setStatus(Status.ACTIVE);
            //jdbc.updateUser(user);
            jpa.updateUser(user);
            session.removeAttribute("username");
            response.sendRedirect("/loginPage.jsp");
        } else {
            request.setAttribute("verifyCodeDB", verifyCodeDB);
            request.setAttribute("errorMessage", "wrong verification code");
            request.getRequestDispatcher("/verification.jsp").forward(request, response);
        }
    }
}
