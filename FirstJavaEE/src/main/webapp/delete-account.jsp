<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Account</title>
</head>
<body>
<form method="get" action="/delete-account">

    <%
        String message = (String) request.getAttribute("errorMessage");
        if (message != null) {
            response.getWriter().print(message);
        }
    %>

    <input type="text" placeholder="username" name="username"><br><br>
    <input type="text" placeholder="password" name="password"><br><br>
    <input type="submit" name="submit" value="delete account">

</form>
</body>
</html>
