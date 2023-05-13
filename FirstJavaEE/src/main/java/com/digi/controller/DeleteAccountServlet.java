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

public class DeleteAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserRepositoryImplJPA jpa = new UserRepositoryImplJPA();
        UserRepositoryImplJDBC jdbc = new UserRepositoryImplJDBC();
        AddressRepositoryImplJDBC addressJdbc = new AddressRepositoryImplJDBC();
        AddressRepositoryImplJPA addressJpa = new AddressRepositoryImplJPA();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //User user = jdbc.getByUsername(username);
        User user = jpa.getByUsername(username);

        if (user != null && user.getPassword().equals(password) && user.getStatus().equals(Status.ACTIVE)) {
            //Address address = addressJdbc.getByUserId(user.getId());
            Address address = addressJpa.getByUserId(user.getId());
            if (address != null) {
                //addressJdbc.deleteAddress(address);
                addressJpa.deleteAddress(address);
            }
            //jdbc.deleteAccount(user);
            jpa.deleteAccount(user);
            response.sendRedirect("/loginPage.jsp");
        } else {
            request.setAttribute("errorMessage", "wrong email or password");
            request.getRequestDispatcher("/delete-account.jsp").forward(request, response);
        }
    }
}
