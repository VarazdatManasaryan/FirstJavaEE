<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.digi.model.Address" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>

<%
    HttpSession loginSession = request.getSession();
    String userInfo = (String) loginSession.getAttribute("userInfo");
    PrintWriter writer = response.getWriter();
    writer.print(userInfo);

    Address address = (Address) loginSession.getAttribute("addressInfo");
    if (address != null) {
        String country = address.getCountry();
        String city = address.getCity();
        if (!country.equals("")) {
            writer.print(country);
            if (!city.equals("")) {
                writer.print(city);
            }
        }
    }
%>
<br><br><br>
<form method="get" action="/logout">

    <input type="submit" name="submit" value="logout"><br><br>

</form>
</body>
<a href="address.jsp">add address</a><br><br>
<a href="change-password.jsp">change password</a><br><br>
<a href="delete-account.jsp">delete account</a><br><br>
</html>
