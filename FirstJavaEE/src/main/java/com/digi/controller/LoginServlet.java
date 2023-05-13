package com.digi.controller;

import com.digi.enums.Status;
import com.digi.model.Address;
import com.digi.model.User;
import com.digi.repository.impl.AddressRepositoryImplJDBC;
import com.digi.repository.impl.AddressRepositoryImplJPA;
import com.digi.repository.impl.UserRepositoryImplJDBC;
import com.digi.repository.impl.UserRepositoryImplJPA;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserRepositoryImplJDBC jdbc = new UserRepositoryImplJDBC();
        UserRepositoryImplJPA jpa = new UserRepositoryImplJPA();
        AddressRepositoryImplJDBC addressJdbc = new AddressRepositoryImplJDBC();
        AddressRepositoryImplJPA addressJpa = new AddressRepositoryImplJPA();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //User user = jdbc.getByUsername(username);
        User user = jpa.getByUsername(username);

        if (user != null && user.getPassword().equals(password) && user.getStatus().equals(Status.ACTIVE)) {
            //Address address = addressJdbc.getByUserId(user.getId());
            Address address = addressJpa.getByUserId(user.getId());
            session.setAttribute("userInfo", user.getName() + " " + user.getSurname() + " " + user.getAge());
            session.setAttribute("user", user);
            session.setAttribute("addressInfo", address);
            request.getRequestDispatcher("/homePage.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "wrong email or password");
            request.getRequestDispatcher("/loginPage.jsp").forward(request, response);
        }
    }
}
