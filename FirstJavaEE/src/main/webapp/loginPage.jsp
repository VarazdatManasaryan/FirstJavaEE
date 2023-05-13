<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<form method="get" action="/login">

    <%
        PrintWriter writer = response.getWriter();
        String message = (String) request.getAttribute("errorMessage");
        if (message != null) {
            writer.print(message);
        }
    %>

    <input type="text" placeholder="username" name="username"><br><br>
    <input type="text" placeholder="password" name="password"><br><br>
    <input type="submit" name="submit"><br><br>

</form>
</body>
<a href="registration.jsp">registration</a><br><br>
<a href="email-verify.jsp">forgot-password?</a><br><br>
</html>
