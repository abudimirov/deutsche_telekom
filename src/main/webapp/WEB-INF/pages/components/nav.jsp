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
    <div class="container">
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
            <sec:authorize access="hasRole('NURSE')">
                <div class="d-flex flex-row mr-3">
                    <div class="avatar mr-2">
                        <img src="https://cdn.dribbble.com/users/4559563/avatars/small/d00e4816f973719dacccec0bfd31be70.jpg" style="height: 48px;" class="img-fluid rounded-circle" alt="doctor">
                    </div>
                    <div class="d-flex flex-column">
                        <span class="profile-name">James Hunt</span>
                        <span class="text-secondary">Nurse</span>
                    </div>
                </div>
            </sec:authorize>
            <sec:authorize access="hasRole('DOCTOR')">
                <div class="d-flex flex-row mr-3">
                    <div class="avatar mr-2">
                        <img src="https://cdn.dribbble.com/users/303276/avatars/small/11b212567a91f07cfc843ab6c75066c9.jpg" style="height: 48px;" class="img-fluid rounded-circle" alt="doctor">
                    </div>
                    <div class="d-flex flex-column">
                        <span class="profile-name">Dr. Alex Ivanov</span>
                        <span class="text-secondary">Cardiologist</span>
                    </div>
                </div>
            </sec:authorize>
                <li class="nav-item">
                    <a class="nav-link" id="logout" href="<c:url value="/logout" />"><i class="fa fa-sign-out" aria-hidden="true"></i> Sign out</a>
                </li>
            </ul>
        </sec:authorize>
    </div>
</nav>
