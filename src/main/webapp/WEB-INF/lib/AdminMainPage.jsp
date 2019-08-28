<%@ page import="com.accenture.flowershop.be.Entity.Order.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="com.accenture.flowershop.fe.dto.OrderDto" %><%--
  Created by IntelliJ IDEA.
  User: aleksandr.serykh
  Date: 19.08.2019
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FlowerShop</title>
</head>
<body>
<form>
    <h1> ADMIN MAIN PAGE</h1>
    <p>Hello, <%= request.getParameter("login")%>!<br></p>
    <p>Role: ADMIN<br></p>
</form>

<form method = "get" action = "LogoutServlet">
    <button type = "submit"> Logout </button>
</form>

<h2>ALL ORDERS</h2>
<form method="post" action="CloseOrderServlet">
    <div>
        <table>
            <tr>
                <td>Id Order</td>
                <td>Id User</td>
                <td>Description</td>
                <td>Total Price</td>
                <td>Status</td>
                <td>Date Create</td>
                <td>Date Close</td>
            </tr>
            <%
                List<OrderDto> orderList = (List<OrderDto>) session.getAttribute(se)

            <c:forEach items = "${ORDERS}" var="iterator" varStatus="rowStatus">
                <tr>
                    <td>${iterator.idOrder}</td>
                    <td>${iterator.user.idUser}</td>
                    <td>
                        <c:forEach items = "${iterator.orderPositions}" var="it" varStatus="rowStatus">
                            <p>${it.quantity}x ${it.flower.nameFlower}</p>
                        </c:forEach>
                    </td>
                    <td>${iterator.totalPrice}</td>
                    <td>${iterator.status}</td>
                    <td>${iterator.dateCreate}</td>
                    <td>${iterator.dateClose}</td>
                    <td>
                        <c:choose>
                            <c:when test="${iterator.status.toString() eq 'PAID'}">
                                <button type="submit"
                                        name="idOrder"
                                        value="${iterator.idOrder}"
                                > Close </button>
                            </c:when>
                            <c:otherwise>
                                <button type="submit"
                                        name="idOrder"
                                        value="${iterator.idOrder}"
                                        disabled="disabled"
                                > Close </button>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</form>

</body>
</html>
