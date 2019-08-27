<%@ page import="com.accenture.flowershop.fe.dto.FlowerDto" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: aleksandr.serykh
  Date: 19.08.2019
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FlowerShop</title>
<%--    <link rel="stylesheet" type="text/css" href="<c:url value="main.css"/>"/>--%>
</head>
<body>

<div class="row">
    <h1>MAIN PAGE</h1>
    <form>
        User: <%= request.getParameter("login")%><br>
        Balance: <%= session.getAttribute("balance")%><br>
        Discount: <%= session.getAttribute("discount")%><br>
    </form>

    <form action="LogoutServlet" method="get">
        <p>
        <table>
            <tr>
                <th><small>
                    <input type="submit" name="To login" value="Logout">
                </small></th>
            </tr>
        </table>
    </form>
</div>

<%--/////////////////////--%>
<div class="row">
    <form method="post" action="SearchFlower">
        <h2>CATALOG</h2>
        <h3>Filter for search</h3>
        <div class="panel">
            <input type="text" name="from" placeholder="from">
            <input type="text" name="to" placeholder="to">
            <input type="text" name="name" placeholder="name">
            <button type="submit"> Search </button>
        </div>
    </form>
<%--///////////////////////////--%>
    <form action="AddToBascket" method="post" >
        <div class="row catalog">
            <table border ="" width="500" align="left">
                <tr bgcolor="">
                    <th><b>Name</b></th>
                    <th><b>Price</b></th>
                    <th><b>Amount</b></th>
                </tr>
                <%List<FlowerDto> flower = (List<FlowerDto>) request.getAttribute("FLOWERS");
                    for(FlowerDto flowerItem: flower){ %>
                <tr>
                    <td><%=flowerItem.getNameFlower()%></td>
                    <td><%=flowerItem.getPrice()%></td>
                    <td><%=flowerItem.getNumber()%></td>
                    <td><input type="text" name="number" placeholder="Input quantity"></input></td>
                    <td>
                        <button type="submit" name="idFlower" value="<%=flowerItem.getIdFlower()%>">add to basket</button>
                    </td>
                </tr>
                <%}%>
            </table>
        </div>
    </form>
</div>
<%--//////////////////////--%>
<div>
    <form action="RemoveFromBascket" method="post" >
        <div class="basket">
            <h2>SOME</h2>
            <h2>INFO</h2>
            <h2>ABOUT BASKET</h2>
            <h2>MY BASKET</h2>
            <table>
                <tr>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
                    <c:otherwise>
                        <c:forEach items = "${BASKET.orderPositions}" var="iterator" varStatus="rowStatus">
                            <tr>
                                <td>${iterator.flower.nameFlower}</td>
                                <td>${iterator.quantity}</td>
                                <td>${iterator.price}</td>
                                <td>
                                    <button type="submit"
                                            name="idFlower"
                                            value="${iterator.flower.idFlower}"
                                    > remove
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
            </table>
            <p>Total Price: ${BASKET.totalPrice} </p>
        </div>
    </form>
<%--///////////////////////--%>
    <form method="post" action="CreateOrder">
        <div class="panel">
            <c:choose>
                <c:when test="${BASKET.orderPositions.isEmpty()}">
                    <button type="submit" id="buttoncreate" disabled="disabled"> create order </button>
                </c:when>
                <c:otherwise>
                    <button type="submit" id="buttoncreate"> create order </button>
                </c:otherwise>
            </c:choose>
        </div>
    </form>
</div>
<%--//////////////////--%>
<div class="row">
    <h2>MY ORDERS</h2>
    <form method="post" action="PayOrder">
        <div class="myorders">
            <table>
                <tr>
                    <th>Id</th>
                    <th>Description</th>
                    <th>Total Price</th>
                    <th>Status</th>
                </tr>
                <c:choose>
                    <c:when  test="${ORDERS.isEmpty()}">
                        <tr><td colspan="4" id="empty">Here is Empty</td></tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items = "${ORDERS}" var="iterator" varStatus="rowStatus">
                            <tr>
                                <td>${iterator.idOrder}</td>
                                <td>
                                    <c:forEach items = "${iterator.orderPositions}" var="it" varStatus="rowStatus">
                                        <p>${it.quantity}-${it.flower.nameFlower}</p>
                                    </c:forEach>
                                </td>
                                <td>${iterator.totalPrice}</td>
                                <td>${iterator.status}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${iterator.status.toString() eq 'CREATED'}">
                                            <button type="submit"
                                                    name="idOrder"
                                                    value="${iterator.idOrder}"
                                            > to pay </button>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="submit"
                                                    name="idOrder"
                                                    value="${iterator.idOrder}"
                                                    disabled="disabled"
                                            > to pay </button>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>
    </form>
</div>
</body>
</html>
