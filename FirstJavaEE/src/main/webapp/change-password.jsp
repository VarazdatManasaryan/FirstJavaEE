<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Password</title>
</head>
<body>
<form method="get" action="/forgot-password">

    <%
        String message = (String) request.getAttribute("errorMessage");
        if (message != null) {
            response.getWriter().print(message);
        }
    %>

    <input type="text" placeholder="old password" name="old_password"><br><br>
    <input type="text" placeholder="new password" name="new_password"><br><br>
    <input type="text" placeholder="confirm password" name="confirm_password"><br><br>
    <input type="submit" name="submit">

</form>
</body>
</html>
