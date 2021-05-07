<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 28.04.2021
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand"><img src="<c:url value="/res/logo.png" />" alt="Medical Cabinet" style="height: 40px;" /></a>
    <sec:authorize access="isAuthenticated()">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/" />">Home</a>
            </li>
            <sec:authorize access="hasRole('DOCTOR')">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/patients" />">Patients</a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasRole('NURSE')">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/procedures" />">Procedures</a>
                </li>
            </sec:authorize>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/logout" />"><i class="fa fa-sign-out" aria-hidden="true"></i> Sign out</a>
            </li>
        </ul>
    </sec:authorize>
</nav>
