<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : \"en\"}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="properties"/>

<div style="background: #E0E0E0; height: 55px; padding: 5px;">
    <div style="float: left">
        <h1><fmt:message key="shop.name"/></h1>
    </div>

    <div style="float: right; padding: 10px; text-align: right;">



    </div>

</div>
