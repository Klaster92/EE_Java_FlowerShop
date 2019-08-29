<%@ page import="com.accenture.flowershop.fe.dto.FlowerDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.accenture.flowershop.fe.enums.SessionAttribute" %>
<%@ page import="com.accenture.flowershop.fe.dto.OrderDto" %>
<%@ page import="com.accenture.flowershop.fe.dto.OrderPosDto" %>
<%@ page import="org.springframework.data.domain.jaxb.SpringDataJaxb" %>
<%@ page import="com.accenture.flowershop.be.Entity.Order.Order" %>
<%@ page import="com.accenture.flowershop.fe.enums.OrderStatus" %>
<%@ page import="java.math.BigDecimal" %><%--
  Created by IntelliJ IDEA.
  User: aleksandr.serykh
  Date: 19.08.2019
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Main page</title>
</head>
<body>
<div class="row">
    <h1>MAIN PAGE</h1>
    <div>
        Hello,<%= request.getParameter("login")%>!<br>
        Your balance:<%=session.getAttribute("balance")%><br>
        Discount:<%=session.getAttribute("discount")%><br>
        </div>
    </div>
<form action = "LogoutServlet" method = "get" >
    <button type = "submit"> Logout </button>
</form>
<div class="row">
    <form action="SearchFlower" method="post">
        <h2>CATALOG</h2>
        <h3>Filter for search</h3>
        <div class="panel">
            <input type="text" name="from" placeholder="from"></input>
            <input type="text" name="to" placeholder="to"></input>
            <input type="text" name="name" placeholder="name"></input>
            <button type="submit"> Search </button>
        </div>
    </form>
    <form action="AddToBascket" method="post" >
        <div class="row catalog">
            <table  border =""  width="600" align="left">
                <tr bgcolor="">
                    <th><b>Name</b></th>
                    <th><b>Price</b></th>
                    <th><b>Amount</b></th>
                    <th><b>Input quantity</b></th>
                </tr>
                <%List<FlowerDto> flower = (List<FlowerDto>) request.getAttribute(SessionAttribute.FLOWERS.toString());
                    for(FlowerDto flowerItem: flower){ %>
                <tr>
                    <td><%=flowerItem.getNameFlower()%></td>
                    <td><%=flowerItem.getPrice()%></td>
                    <td><%=flowerItem.getNumber()%></td>
                    <td><input type="text" name="number" placeholder="number"></input></td>
                    <td>
                        <button type="submit" name="idFlower" value="<%=flowerItem.getIdFlower()%>">add to basket</button>
                    </td>
                </tr>
                <%}%>
            </table>
            <div class="panel">
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
            </div>
        </div>
    </form>
    <div>
        <form action="RemoveFromBascket" method="post">
            <h2>MY BASKET</h2>
            <table  border =""  width="600" align="left">
                <tr>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Delete</th>
                </tr>
                <% OrderDto orderDto = (OrderDto) session.getAttribute(SessionAttribute.BASKET.toString());
                    List<OrderPosDto> orderPosDtos = orderDto.getOrderPositions();
                    if(orderPosDtos.isEmpty()){   %>
                <tr><td colspan="3" id="empty">Here is Empty</td>
                    <%}else {%>
                        <% for (OrderPosDto orderPosDto: orderPosDtos){%>
                    <td><%=orderPosDto.getFlower().getNameFlower()%></td>
                    <td><%=orderPosDto.getNumber()%></td>
                    <td><%=orderPosDto.getPrice().multiply(BigDecimal.valueOf(orderPosDto.getNumber()))%></td>
                    <td><button type="submit" name="idFlower" value="<%=orderPosDto.getFlower().getIdFlower()%>">remove</button></td>
                </tr>
                <%}%>
                <%}%>
            </table>
        </form>
    </div>
    <form action="CreateOrder" method="post">
        <%OrderDto dto = (OrderDto) session.getAttribute(SessionAttribute.BASKET.toString());
            if (!dto.getOrderPositions().isEmpty()){%>
        <button type="submit" id="buttoncreate"> create order </button>
        <%}%>
    </form>
    <br>
    <br>
    <br>
    <div>
        <form action="PayOrder" method="post">

            <% List<OrderDto> created = (List<OrderDto>) session.getAttribute(SessionAttribute.ORDERS.toString());
                if (!created.isEmpty()){%>
            <%for (OrderDto toPay: created){
                if (toPay.getStatus().equals(OrderStatus.CREATED)){%>
            <table border ="" width="700" align="left">
                <tr>
                    <th>id</th>
                    <th>Flower and Quantity</th>
                    <th>Price</th>
                </tr>
                <th> <%=toPay.getIdOrder() %></th>
                    <%for (OrderPosDto pos: toPay.getOrderPositions()){%>
                <th> <%=pos.getFlower().getNameFlower()%>
                    <p> </p>
                    <%=pos.getNumber()%>
                </th>
                    <%}%>
                <th> <%=toPay.getTotalPrice()%></th>
                <th><button type="submit" name="idOrder" value="<%=toPay.getIdOrder()%>"> to pay </button></th>
                    <%}%>
                    <%}%>
                    <%}%>
        </form>
    </div>

</div>
</body>
</html>