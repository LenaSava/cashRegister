<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : \"en\"}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="properties"/>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>main</title>
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
  <h1><fmt:message key="cash.register"/></h1>
</div>

<div class="w3-container w3-center">
  <div class="w3-bar w3-padding-large w3-padding-24">
    <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='/show_registration'"><fmt:message key="registration.title"/></button>
    <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='/login'"><fmt:message key="log-in.title"/></button>
    <div>
      <a href="${pageContext.request.contextPath}/views/language//?language=ua">
        <fmt:message key="ua"/>
      </a>
      <a href="${pageContext.request.contextPath}/views/language//?language=en">
        <fmt:message key="en"/>
      </a>
    </div>
  </div>
</div>


</body>
</html>