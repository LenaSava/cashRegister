<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : \"en\"}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="properties"/>

<html>
<head>
    <title>login</title>
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
            <h2><fmt:message key="please.login"/></h2>
        </div>
        <div class="w3-red">
            <c:if test="${wrongPassOrLogin.equals(true)}">
                <fmt:message key="wrong.pass.or.login"/>
            </c:if>
        </div>
        <form method="post" action="/api/login_submit" class="w3-selection w3-light-grey w3-padding">
            <label><fmt:message key="registration.button"/>
                <input type="text" required placeholder="<fmt:message key="registration.button"/>" name="name" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <label><fmt:message key="password.input"/>
                <input type="password" required placeholder="<fmt:message key="registration.password"/>" name="pass" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom"><fmt:message key="submit"/></button>
        </form>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='../..'"><fmt:message key="back.to.main"/></button>
</div>
<jsp:include page="parts/footer.jsp"></jsp:include>
</body>
</html>
