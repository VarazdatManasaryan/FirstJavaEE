<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forgot Password</title>
</head>
<body>
<form method="get" action="/email-verify">

    <%
        String message = (String) request.getAttribute("errorMessage");
        if (message != null) {
            response.getWriter().print(message);
        }
    %>
    <input type="text" placeholder="email" name="email"><br><br>
    <input type="submit" name="submit"><br><br>

</form>
</body>
</html>
