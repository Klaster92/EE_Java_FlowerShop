<%@ page import="com.accenture.flowershop.be.Entity.Order.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="com.accenture.flowershop.fe.dto.OrderDto" %>
<%@ page import="com.accenture.flowershop.fe.enums.SessionAttribute" %>
<%@ page import="com.accenture.flowershop.fe.enums.OrderStatus" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="com.accenture.flowershop.fe.dto.OrderPosDto" %><%--
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
<div class="row">
    <h1>Admin page</h1>
    <div>Hello,<%=session.getAttribute("login") %>!
        Your balance:<%=session.getAttribute("balance")%>
        and discount:<%=session.getAttribute("discount")%></div>
</div>

<form action = "LogoutServlet" method = "get" >

    <button type = "submit"> Logout </button>
</form>
<form action="CloseOrderServlet" method="post">
    <div>
        <table border ="" width="900" align="left">
            <tr>
                <td>Order id</td>
                <td>User id</td>
                <td>Flowers and amount</td>
                <td>Price</td>
                <td>Status</td>
                <td>Date Create</td>
                <td>Date Close</td>
                <td>Action</td>
            </tr>
            <% List<OrderDto> orderDto = (List<OrderDto>) session.getAttribute(SessionAttribute.ORDERS.toString());
                orderDto.sort(Comparator.comparing(OrderDto::getIdOrder));
                for (OrderDto dto: orderDto){
                    if (dto.getStatus() == OrderStatus.CREATED){
            %>
            <tr>
                <td><%=dto.getIdOrder()%></td>
                <td><%=dto.getUser().getIdUser()%></td>

                <td><%for (OrderPosDto positionDTO: dto.getOrderPositions()){%>
                    <%=positionDTO.getFlower().getNameFlower() %>:
                    <%=positionDTO.getNumber()%>; <%}%></td>

                <td><%=dto.getTotalPrice()%></td>
                <td><%=dto.getStatus()%></td>
                <td><%=dto.getDateCreate()%></td>
                <td><%=dto.getDateClose()%></td>
                <td><button type="submit" name="idOrder" value="<%=dto.getIdOrder()%>"
                > Close </button></td>
            </tr>
            <%}%>
            <%}%>
            <% for (OrderDto dto: orderDto){
                if (dto.getStatus() == OrderStatus.PAID){
            %>
            <tr>
                <td><%=dto.getIdOrder()%></td>
                <td><%=dto.getUser().getIdUser()%></td>

                <td><%for (OrderPosDto positionDto: dto.getOrderPositions()){%>
                    <%=positionDto.getFlower().getNameFlower() %>:
                    <%=positionDto.getNumber()%>; <%}%></td>

                <td><%=dto.getTotalPrice()%></td>
                <td><%=dto.getStatus()%></td>
                <td><%=dto.getDateCreate()%></td>
                <td><%=dto.getDateClose()%></td>
                <td><button type="submit" name="idOrder" value="<%=dto.getIdOrder()%>"
                > Close </button></td>
            </tr>
            <%}%>
            <%}%>
            <% for (OrderDto dto: orderDto){
                if (dto.getStatus() == OrderStatus.CLOSED){
            %>
            <tr>
                <td><%=dto.getIdOrder()%></td>
                <td><%=dto.getUser().getIdUser()%></td>

                <td><%for (OrderPosDto positionDTO: dto.getOrderPositions()){%>
                    <%=positionDTO.getFlower().getNameFlower() %>:
                    <%=positionDTO.getNumber()%>;<%}%></td>


                <td><%=dto.getTotalPrice()%></td>
                <td><%=dto.getStatus()%></td>
                <td><%=dto.getDateCreate()%></td>
                <td><%=dto.getDateClose()%></td>
            </tr>
            <%}%>
            <%}%>
        </table>
    </div>
</form>
</body>
</html>
