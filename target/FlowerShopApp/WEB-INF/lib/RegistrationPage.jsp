<%--
  Created by IntelliJ IDEA.
  User: ilya.porochkin
  Date: 13.08.2019
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration page</title>
</head>
<body>
<h1>Welcome to registration page</h1>
<form action="RegistrationServlet" method="post">
    Login:        <input type="text" name="login" size="10"><br>
    First name:   <input type="text" name="FirstName" size="15"><br>
    Last name:    <input type="text" name="LastName" size="20"><br>
    Middle name:  <input type="text" name="MiddleName" size="15"><br>
    Password:     <input type="password" name="Password" size="20"><br>
    Email:        <input type="email" name="Email"><br>
    Address:      <input type="text" name="Address"><br>
    Phone number: <input type="number" name="PhoneNumber"><br>

    <p>
    <table>
        <tr>
            <th><small>
                <input type="submit" name="save" value="Save">

            </small></th>
        </tr>
    </table>
</form>
<h1>Back to login page</h1>
<form action="LoginServlet" method="get">
    <p>
    <table>
        <tr>
            <th><small>
                <input type="submit" name="To login" value="Come back">
            </small></th>
        </tr>
    </table>
</form>
</body>
</html>