<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : \"en\"}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="properties"/>

<div style="padding: 5px;">

    <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='/api/info_page'"><fmt:message key="about.hotel"/></button>
    |
    <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='/api/room_list'"><fmt:message key="room.list"/></button>
    |
    <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='/api/admin_home_list'"><fmt:message key="admin.room.list"/></button>
    |
    <%--<a href="${pageContext.request.contextPath}/userInfo">My Account Info</a>--%>
    <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='/api/user_home_page'"><fmt:message key="my.account.info"/></button>

    |
    <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='/api/log_out'"><fmt:message key="exit-button"/></button>

</div>
