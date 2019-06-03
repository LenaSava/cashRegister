<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : \"en\"}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="properties"/>

<div style="padding: 5px;">

    <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='/api/cashier_page'"><fmt:message key="cashier.list"/></button>
    |
    <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='/api/senior_cashier_list'"><fmt:message key="senior.cashier"/></button>
    |
    <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='/api/manager_page'"><fmt:message key="manager"/></button>
    |
    <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='/api/bills_page'"><fmt:message key="bills"/></button>
    |
    <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='/api/log_out'"><fmt:message key="exit-button"/></button>


</div>
