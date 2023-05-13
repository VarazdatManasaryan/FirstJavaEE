package com.digi.controller;

import com.digi.model.Address;
import com.digi.model.User;
import com.digi.repository.impl.AddressRepositoryImplJDBC;
import com.digi.repository.impl.AddressRepositoryImplJPA;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AddressServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        String home = request.getParameter("home");
        User user = (User) session.getAttribute("user");

        Address address = new Address(0, country, city, street, home, user.getId());

        AddressRepositoryImplJDBC jdbc = new AddressRepositoryImplJDBC();
        AddressRepositoryImplJPA jpa = new AddressRepositoryImplJPA();

        //jdbc.saveAddress(address);
        jpa.saveAddress(address);
        //Address addressDB = jdbc.getByUserId(user.getId());
        Address addressDB = jpa.getByUserId(user.getId());
        session.setAttribute("addressInfo", addressDB);
        response.sendRedirect("/homePage.jsp");
    }
}
