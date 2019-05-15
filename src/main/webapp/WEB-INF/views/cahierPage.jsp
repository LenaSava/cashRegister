<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: lenkasava
  Date: 2019-04-04
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>cashier page</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1><fmt:message key="cash.register"/></h1>
</div>

<div class="w3-container w3-padding">
    <div class="w3-card-4">
        <jsp:include page="parts/header.jsp"></jsp:include>
        <jsp:include page="parts/menu.jsp"></jsp:include>
        <div class="w3-container w3-center w3-green">
            <h2>
                <i>Product List</i>
            </h2>
            <table border="1" cellpadding="5" cellspacing="1" >
                <tr>
                    <th>Code</th>
                    <th>Product's name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Book</th>
                </tr>
                <c:forEach items="${products}" var="product" >
                    <tr>
                        <td>${product.code}</td>
                        <td>${product.name}</td>
                        <td>${product.cost}</td>
                        <td>${product.quantity}</td>
                        <td><input type="checkbox" name="canBooking" <c:if test="${Objects.isNull(product.invoice_id)}">checked="checked"</c:if> /> </td>
                    </tr>
                </c:forEach>
            </table>
            <br>
        </div>
    </div>
</div>
<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='/api/login'">back to user menu</button>
</div>

<jsp:include page="parts/footer.jsp"></jsp:include>
</body>
</html>
