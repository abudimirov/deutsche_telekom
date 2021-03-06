<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 13.04.2021
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Filtered procedures</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/res/style.css" />">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://use.fontawesome.com/e324a589d0.js"></script>
</head>

<body>
<div class="container-fluid">
    <div class="row">
        <%@ include file="components/sidebar.jsp" %>
        <main class="col-md-9 ml-sm-auto col-lg-10 px-md-4 bg-light">
            <div class="container-fluid">
                <div class="card my-5">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-center py-3">
                            <h5>Filtered procedures</h5>
                            <div>
                                <i class="fa fa-calendar" aria-hidden="true"></i>
                                <jsp:useBean id="now" class="java.util.Date"/>
                                <fmt:formatDate value="${now}" dateStyle="long"/>
                                <fmt:formatDate value="${now}" pattern="HH:mm" />
                                <jsp:useBean id="today" class="java.util.Date"/>
                            </div>
                            <div class="filters">
                                <a href="/procedures/date/<fmt:formatDate value="${today}" pattern="yyyy-MM-dd" />" class="btn btn-link">for today</a>
                                <a href="/procedures/nexthour" class="btn btn-link">for next hour</a>
                            </div>
                        </div>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col" class="text-left">#</th>
                                <th scope="col">Procedure</th>
                                <th scope="col">Date</th>
                                <th scope="col">Time</th>
                                <th scope="col">Patient</th>
                                <th scope="col">Status</th>
                                <th scope="col" class="right-side"></th>
                            </tr>
                            </thead>

                            <c:forEach var="procedure" items="${proceduresList}" varStatus="i">
                                <tr>
                                    <td class="left-side">${i.index + 1}</td>
                                    <td class="name">${procedure.title}</td>
                                    <td class="surname">${procedure.date}</td>
                                    <td>${procedure.time}</td>
                                    <td><a href="<c:url value="/procedures/patient/${procedure.patient.id}"/>">${procedure.patient.name} ${procedure.patient.surname}</a></td>
                                    <td>
                                        <c:if test="${procedure.status == 'scheduled'}">
                                            <span class="badge badge-pill badge-secondary">${procedure.status}</span>
                                        </c:if>
                                        <c:if test="${procedure.status == 'cancelled'}">
                                            <span class="badge badge-pill badge-danger">${procedure.status}</span>
                                        </c:if>
                                        <c:if test="${procedure.status == 'done'}">
                                            <span class="badge badge-pill badge-success">${procedure.status}</span>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${procedure.status == 'scheduled'}">
                                            <a href="/procedures/edit/${procedure.id}">
                                                <i class="fa fa-pencil" aria-hidden="true"></i> edit
                                            </a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
</body>
</html>