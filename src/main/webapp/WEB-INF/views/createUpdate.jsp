<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : \"en\"}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="properties"/>

<html>
<head>
    <title>CreateOrUpdate</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1><fmt:message key="room.booking"/></h1>
</div>

<div class="w3-container w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2><fmt:message key="сreate.or.update.room"/></h2>
        </div>
        <form method="post" action="/api/create_update" class="w3-selection w3-light-grey w3-padding">
            <input type="hidden" name="id" value="${product.id}"/>
            <input type="hidden" name="id" value="${product.invoiceId}"/>
            <label>Code:
                <input type="number" required maxlength="3" value="${product.code}" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <label>Product's name:
                <input type="text" required name="type" maxlength="50" value="${product.name}" name="capacity" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <label>Cost:
                <input type="number" required name="cost" value="${product.cost}" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <label>Quantity:
                <input type="number" required name="quantity" value="${product.quantity}" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Submit</button>
        </form>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/api/manager_page'"><fmt:message key="back.to.main"/></button>
</div>
</body>
</html>
