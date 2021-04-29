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
<html>
<head>
    <title>Filtered procedures</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://use.fontawesome.com/e324a589d0.js"></script>
</head>

<body style="background: #F2F2F2;">
<%@ include file="components/nav.jsp" %>
<div class="container-wide">
    <div class="row my-5 mx-auto">
        <div class="col-lg-10 p-3 mx-auto" style="background: #FFF;">
            <div class="d-flex justify-content-between align-items-center py-3">
                <h1>Filtered procedures</h1>
                <div>
                    <jsp:useBean id="now" class="java.util.Date"/>
                    <fmt:formatDate value="${now}" dateStyle="long"/>
                    <fmt:formatDate value="${now}" pattern="HH:mm" />
                </div>
                <a href="<c:url value="/procedures/add"/>" class="btn btn-success" role="button" aria-pressed="true"><i class="fa fa-plus-circle" aria-hidden="true"></i> Add new procedure</a>
            </div>
            <table class="table">
                    <thead>
                    <tr>
                        <th class="text-left">#</th>
                        <th>Procedure</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Patient</th>
                        <th>Status</th>
                        <th colspan="2" class="right-side"></th>
                    </tr>
                    </thead>

                    <c:forEach var="procedure" items="${proceduresList}" varStatus="i">
                        <tr>
                            <td class="left-side">${i.index + 1}</td>
                            <td class="name">${procedure.title}</td>
                            <td class="surname">${procedure.date}</td>
                            <td>${procedure.time}</td>
                            <td><a href="<c:url value="/procedures/patient/${procedure.patient.id}"/>">${procedure.patient.name} ${procedure.patient.surname}</a></td>
                            <td>${procedure.status}</td>
                            <td>
                                <a href="/procedures/edit/${procedure.id}">
                                    <i class="fa fa-pencil" aria-hidden="true"></i> edit
                                </a>
                            </td>
                            <td class="right-side">
                                <a href="/procedures/delete/${procedure.id}">
                                    <i class="fa fa-trash" aria-hidden="true"></i> delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>