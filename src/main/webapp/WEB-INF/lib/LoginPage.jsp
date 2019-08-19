<%--
  Created by IntelliJ IDEA.
  User: Aleksandr.serykh
  Date: 13.08.2019
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form of entering system</title>
</head>
<body>
<br>
<h1>Please enter the system</h1>

<form action="LoginServlet" method="post">
    Login:    <input type="text" name="login" size="10"><br>
    Password: <input type="password" name="password" size="20"><br>
    <p>
    <table>
        <tr>
            <th><small>
                <input type="submit" name="Enter system" value="login">
            </small></th>
        </tr>
    </table>
</form>
<h1>If you have no account</h1>
<form action="RegistrationServlet" method="get">
    <p>
    <table>
        <tr>
            <th><small>
                <input type="submit" name="To registration" value="registrate">
            </small></th>
        </tr>
    </table>
</form>
</body>
</html>