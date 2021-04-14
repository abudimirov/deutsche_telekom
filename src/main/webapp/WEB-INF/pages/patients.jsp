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
    <title>Patients</title>
</head>
<body>

<h2>Patients</h2>
<table>
    <caption class="heading">Patients</caption>
    <c:if test="${patientCount > 0}">
        <tr>
            <th class="left-side">№</th>
            <th style="width: 100%">name</th>
            <th style="width: 100%">surname</th>
            <th>year of birth</th>
            <th>sex</th>
            <th>cured</th>
            <th colspan="2" class="right-side">action</th>
        </tr>
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
        <td colspan="7" class="left-side link right-side">
            <a style="margin-right: 70px; font-size: 100%" href="<c:url value="/add"/>">
                <span class="icon icon-add"></span>Add new patient
            </a>
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
            <span style="margin-left: 70px;">Total number of patients: ${patientCount}</span>
        </td>
    </tr>
</table>

<h2>Add</h2>
<c:url value="/add" var="add"/>
<a href="${add}">Add new patient</a>
</body>
</html>