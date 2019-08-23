<%--
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
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href=<c:url value = "main.css"/>
</head>
<body>

<div class="row">
    <h1>MAIN PAGE</h1>
    <form>
        <div class="account">
            <p>Name: ${Users.getName()}  </p>
            <p>Wallet_Score: ${Users.getBalance()}</p>
            <p>Discount: ${Users.getDiscount}% </p>
        </div>
    </form>

    <form action="LogoutServlet" method="get">
        <p>
        <table>
            <tr>
                <th><small>
                    <input type="submit" name="To login" value="Come back">
                </small></th>
            </tr>
        </table>
    </form>
</div>


<div class="row">
    <form method="post" action="SearchFlower">
        <h2>CATALOG</h2>
        <h3>Filter for search</h3>
        <div class="panel">
            <input type="text" name="from" placeholder="from"></input>
            <input type="text" name="to" placeholder="to"></input>
            <input type="text" name="name" placeholder="name"></input>
            <button type="submit"> Search </button>
        </div>
    </form>

    <form method="post" action="AddToBascket">
        <div class="row catalog">
            <table>
                <tbody>
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                </tr>
                <c:choose>
                    <c:when  test="${FLOWERS.isEmpty()}">
                        <tr><td colspan="3" id="empty">Nothing not found</td></tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items = "${FLOWERS}" var="iterator" varStatus="rowStatus">
                            <tr>
                                <td>${iterator.nameFlower}</td>
                                <td>${iterator.price}</td>
                                <td>${iterator.quantity}</td>
                                <td>
                                    <c:choose>
                                        <c:when  test="${iterator.quantity > 0}">
                                            <button type="submit" name="idFlower" value="${iterator.idFlower}">add to basket</button>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="submit" name="idFlower" value="${iterator.idFlower}" disabled="disabled"> Get</button>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>

                </tbody>
            </table>
        </div>
        <div class="panel">
            <input type="text" name="quantity" placeholder="quantity"></input>
            <p class="err" name="err">${ctlg_err}</p>
            <p class="msg" name="msg">${ctlg_msg}</p>
        </div>
    </form>
</div>

<div class="row">
    <form method="post" action="RemoveFromBascket">
        <div class="basket">
            <h2>MY BASKET</h2>
            <table>
                <tr>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
                <c:choose>
                    <c:when  test="${BASKET.orderPositions.isEmpty()}">
                        <tr><td colspan="3" id="empty">Here is Empty</td></tr>
                    </c:when>
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
                </c:choose>
            </table>
            <p>Total Price: ${BASKET.totalPrice} </p>
        </div>
    </form>

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
            <p class="err" name="err">${bskt_err}</p>
            <p class="msg" name="msg">${bskt_msg}</p>
        </div>
    </form>
</div>

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
            <p class="err" name="err">${order_err}</p>
            <p class="msg" name="msg">${order_msg}</p>
        </div>
    </form>
</div>
</body>
</html>
