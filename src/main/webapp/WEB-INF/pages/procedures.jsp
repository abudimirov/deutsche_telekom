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
    <title>All procedures</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/res/style.css" />">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://use.fontawesome.com/e324a589d0.js"></script>
</head>

<body style="background: #F2F2F2;">
<%@ include file="components/nav.jsp" %>
<div class="container-wide">
    <div class="row my-5 mx-auto">
        <div class="container p-3 mx-auto" style="background: #FFF;">
            <div class="d-flex justify-content-between align-items-center py-3">
                <h1 class="main">Events</h1>
                <div class="date">
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
                <c:if test="${proceduresCount > 0}">
                    <thead>
                        <tr>
                            <th scope="col" class="text-left">#</th>
                            <th scope="col">Event</th>
                            <th scope="col">Type</th>
                            <th scope="col">Date</th>
                            <th scope="col">Time</th>
                            <th scope="col">Patient</th>
                            <th scope="col">Status</th>
                            <th scope="col" class="right-side"></th>
                        </tr>
                    </thead>

                    <c:forEach var="procedure" items="${proceduresList}" varStatus="i">
                        <tr>
                            <td class="left-side">${i.index + 1 + (page - 1) * 10}</td>
                            <td class="name">${procedure.title}</td>
                            <td>${procedure.type}</td>
                            <td class="surname">${procedure.date}</td>
                            <td>${procedure.time}</td>
                            <td><a href="<c:url value="/procedures/patient/${procedure.patient.id}"/>">${procedure.patient.name} ${procedure.patient.surname}</a></td>
                            <td>${procedure.status}</td>
                            <td>
                                <c:if test="${procedure.status == 'scheduled'}">
                                    <a href="/procedures/edit/${procedure.id}">
                                        <i class="fa fa-pencil" aria-hidden="true"></i> edit
                                    </a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${proceduresCount == 0}">
                    <tr>
                        <td colspan="7" style="font-size: 150%" class="left-side right-side">
                            The list is empty but you can add a new procedure
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td colspan="8" class="left-side link right-side">
                        <span class="text-right">Total number of procedures: ${proceduresCount}</span>
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-center">
                        <c:if test="${pagesCount > 1}">
                            <c:set value="disabled" var="disabled"/>
                            <c:set value="" var="active"/>
                            <li class="${page == 1 ? disabled : active} page-item">

                                <c:url value="/procedures" var="url">
                                    <c:param name="page" value="1"/>
                                </c:url>
                                <a class=" page-link" href="${url}">
                                    &nbsp<i class="fa fa-fast-backward" aria-hidden="true"></i>&nbsp
                                </a>
                            </li>
                            <li class="${page == 1 ? disabled : active} page-item">
                                <c:url value="/procedures" var="url">
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
                                    <c:url value="/procedures" var="url">
                                        <c:param name="page" value="${i.index}"/>
                                    </c:url>
                                    <c:set value="current-page" var="current"/>
                                    <c:set value="" var="perspective"/>
                                    <a class="${page == i.index ? current : perspective} page-link" href="${url}">${i.index}</a>
                                </li>
                            </c:forEach>

                            <li class="${page == pagesCount ? disabled : active} page-item">
                                <c:url value="/procedures" var="url">
                                    <c:param name="page" value="${page + 1}"/>
                                </c:url>
                                <a class="page-link" href="${url}">
                                    &nbsp<i class="fa fa-step-forward" aria-hidden="true"></i>&nbsp
                                </a>
                            </li>
                            <li class="${page == pagesCount ? disabled : active} page-item">
                                <c:url value="/procedures" var="url">
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