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
    <title>All patients</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://use.fontawesome.com/e324a589d0.js"></script>
</head>

<body style="background: #F2F2F2;">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/"><img src="<c:url value="/res/logo.png" />" alt="Medical Cabinet" style="height: 40px;" /></a>
    <ul class="navbar-nav">
        <li class="nav-item active">
            <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/procedures">Procedures</a>
        </li>
    </ul>
</nav>
<div class="container-wide">
    <div class="row my-5 mx-auto">
        <div class="col-lg-10 p-3 mx-auto" style="background: #FFF;">
            <div class="d-flex justify-content-between align-items-center py-3">
                <h1>Patients</h1>
                <div>
                    <jsp:useBean id="now" class="java.util.Date"/>
                    <fmt:formatDate value="${now}" dateStyle="long"/>
                    <fmt:formatDate value="${now}" pattern="HH:mm" />
                </div>
                <a href="<c:url value="/add"/>" class="btn btn-success" role="button" aria-pressed="true"><i class="fa fa-plus-circle" aria-hidden="true"></i> Add new patient</a>
            </div>
            <table class="table">
                <c:if test="${patientCount > 0}">
                    <thead>
                        <tr>
                            <th class="text-left">#</th>
                            <th>Name</th>
                            <th>Surname</th>
                            <th>Insurance №</th>
                            <th>Doctor</th>
                            <th>Cured</th>
                            <th colspan="3" class="right-side"></th>
                        </tr>
                    </thead>

                    <c:forEach var="patient" items="${patientsList}" varStatus="i">
                        <tr>
                            <td class="left-side">${i.index + 1 + (page - 1) * 10}</td>
                            <td class="name">${patient.name}</td>
                            <td class="surname">${patient.surname}</td>
                            <td>${patient.insuranceNum}</td>
                            <td>${patient.doctor}</td>
                            <td>
                                <c:if test="${patient.cured == true}">
                                    <span>yes</span>
                                </c:if>
                                <c:if test="${patient.cured == false}">
                                    <span>no</span>
                                </c:if>
                            </td>
                            <td>
                                <a href="/edit/${patient.id}">
                                    <i class="fa fa-pencil" aria-hidden="true"></i> edit
                                </a>
                            </td>
                            <td class="right-side">
                                <a href="/delete/${patient.id}">
                                    <i class="fa fa-trash" aria-hidden="true"></i> delete
                                </a>
                            </td>
                            <td class="right-side">
                                <a href="/discharge/${patient.id}">
                                    <i class="fa fa-flag-checkered" aria-hidden="true"></i> discharge
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${patientsCount == 0}">
                    <tr>
                        <td colspan="7" style="font-size: 150%" class="left-side right-side">
                            The list is empty but you can add a new patient
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td colspan="8" class="left-side link right-side">
                        <span class="text-right">Total number of patients: ${patientCount}</span>
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-center">
                        <c:if test="${pagesCount > 1}">
                            <c:set value="disabled" var="disabled"/>
                            <c:set value="" var="active"/>
                            <li class="${page == 1 ? disabled : active} page-item">

                                <c:url value="/" var="url">
                                    <c:param name="page" value="1"/>
                                </c:url>
                                <a class=" page-link" href="${url}">
                                    &nbsp<i class="fa fa-fast-backward" aria-hidden="true"></i>&nbsp
                                </a>
                            </li>
                            <li class="${page == 1 ? disabled : active} page-item">
                                <c:url value="/" var="url">
                                    <c:param name="page" value="${page - 1}"/>
                                </c:url>
                                <a class=" page-link" href="${url}">
                                    &nbsp<i class="fa fa-step-backward" aria-hidden="true"></i>&nbsp
                                </a>
                            </li>
                            <c:if test="${pagesCount <= 5}">
                                <c:set var="begin" value="1"/>
                                <c:set var="end" value="${pagesCount}"/>
                            </c:if>
                            <c:if test="${pagesCount > 5}">
                                <c:choose>
                                    <c:when test="${page < 3}">
                                        <c:set var="begin" value="1"/>
                                        <c:set var="end" value="5"/>
                                    </c:when>
                                    <c:when test="${page > pagesCount - 2}">
                                        <c:set var="begin" value="${pagesCount - 4}"/>
                                        <c:set var="end" value="${pagesCount}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="begin" value="${page - 2}"/>
                                        <c:set var="end" value="${page + 2}"/>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>

                            <c:forEach begin="${begin}" end="${end}" step="1" varStatus="i">
                                <li class="page-item">
                                    <c:url value="/" var="url">
                                        <c:param name="page" value="${i.index}"/>
                                    </c:url>
                                    <c:set value="current-page" var="current"/>
                                    <c:set value="" var="perspective"/>
                                    <a class="${page == i.index ? current : perspective} page-link" href="${url}">${i.index}</a>
                                </li>
                            </c:forEach>

                            <li class="${page == pagesCount ? disabled : active} page-item">
                                <c:url value="/" var="url">
                                    <c:param name="page" value="${page + 1}"/>
                                </c:url>
                                <a class="page-link" href="${url}">
                                    &nbsp<i class="fa fa-step-forward" aria-hidden="true"></i>&nbsp
                                </a>
                            </li>
                            <li class="${page == pagesCount ? disabled : active} page-item">
                                <c:url value="/" var="url">
                                    <c:param name="page" value="${pagesCount}"/>
                                </c:url>
                                <a class="page-link" href="${url}">
                                    &nbsp<i class="fa fa-fast-forward" aria-hidden="true"></i>&nbsp
                                </a>
                            </li>
                        </c:if>
                            </ul>
                        </nav>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>