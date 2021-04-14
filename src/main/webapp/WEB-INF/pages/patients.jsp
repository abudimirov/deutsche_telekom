<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 13.04.2021
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All patients</title>
    <link href="<c:url value="/res/font-awesome.min.css"/>" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
</head>

<body>
<div class="container">
    <div class="my-5">
        <div class="d-flex justify-content-between align-items-center">
            <h1>Patients</h1>
            <a href="<c:url value="/add"/>" class="btn btn-success" role="button" aria-pressed="true">Add new patient</a>
        </div>
        <table class="table">
            <c:if test="${patientCount > 0}">
                <thead>
                    <tr>
                        <th class="text-left">#</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Year of birth</th>
                        <th>Sex</th>
                        <th>Cured</th>
                        <th colspan="2" class="right-side"></th>
                    </tr>
                </thead>

                <c:forEach var="patient" items="${patientsList}" varStatus="i">
                    <tr>
                        <td class="left-side">${i.index + 1 + (page - 1) * 10}</td>
                        <td class="name">${patient.name}</td>
                        <td class="surname">${patient.surname}</td>
                        <td>${patient.yearOfBirth}</td>
                        <td>${patient.sex}</td>
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
                                edit
                            </a>
                        </td>
                        <td class="right-side">
                            <a href="/delete/${patient.id}">
                                delete
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

                    <c:if test="${pagesCount > 1}">
                        <c:set value="disabled" var="disabled"/>
                        <c:set value="" var="active"/>
                        <c:url value="/" var="url">
                            <c:param name="page" value="1"/>
                        </c:url>
                        <a class="${page == 1 ? disabled : active}" href="${url}">
                            &nbsp<span class="icon icon-first"></span>&nbsp
                        </a>
                        <c:url value="/" var="url">
                            <c:param name="page" value="${page - 1}"/>
                        </c:url>
                        <a class="${page == 1 ? disabled : active}" href="${url}">
                            &nbsp<span class="icon icon-prev"></span>&nbsp
                        </a>

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
                            <c:url value="/" var="url">
                                <c:param name="page" value="${i.index}"/>
                            </c:url>
                            <c:set value="current-page" var="current"/>
                            <c:set value="" var="perspective"/>
                            <a class="${page == i.index ? current : perspective}" href="${url}">${i.index}</a>
                        </c:forEach>

                        <c:url value="/" var="url">
                            <c:param name="page" value="${page + 1}"/>
                        </c:url>
                        <a class="${page == pagesCount ? disabled : active}" href="${url}">
                            &nbsp<span class="icon icon-next"></span>&nbsp
                        </a>
                        <c:url value="/" var="url">
                            <c:param name="page" value="${pagesCount}"/>
                        </c:url>
                        <a class="${page == pagesCount ? disabled : active}" href="${url}">
                            &nbsp<span class="icon icon-last"></span>&nbsp
                        </a>
                    </c:if>
                    <span class="text-right">Total number of patients: ${patientCount}</span>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>