<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Verification Page</title>
</head>
<body>
<form method="post" action="/verification">

    <%
        PrintWriter writer = response.getWriter();
        String verifyCode = (String) request.getAttribute("verify_code");
        if (verifyCode != null) {
            writer.print(verifyCode);
        }
        String message = (String) request.getAttribute("errorMessage");
        if (message != null) {
            writer.print("<html>");
            writer.print(message);
            writer.print("<br><br>");
            writer.print((String) request.getAttribute("verifyCodeDB"));
            writer.print("</html>");
        }
    %>

    <input type="text" placeholder="verification code" name="verifyCode"><br><br>
    <input type="submit" name="submit">

</form>
</body>
</html>
