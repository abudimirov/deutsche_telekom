<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty procedure.title}">
        <title>Add procedure</title>
    </c:if>
    <c:if test="${!empty procedure.title}">
        <title>Edit procedure</title>
    </c:if>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://use.fontawesome.com/e324a589d0.js"></script>
</head>
<body>
<%@ include file="components/nav.jsp" %>
<c:if test="${empty procedure.title}">
    <c:url value="/procedures/add" var="var"/>
</c:if>
<c:if test="${!empty procedure.title}">
    <c:url value="/procedures/edit" var="var"/>
</c:if>
<div class="container">
    <div class="my-5">
        <c:if test="${!empty procedure.title}">
            <h1>Edit procedure - ${procedure.title}</h1>
        </c:if>
        <c:if test="${empty procedure.title}">
            <h1>Add new procedure</h1>
        </c:if>
        <form action="${var}" method="POST">
            <c:if test="${!empty procedure.title}">
                <input type="hidden" name="id" value="${procedure.id}">
            </c:if>
            <div class="form-group">
                <label for="title">Title</label>
                <c:if test="${!empty procedure.title}">
                    <input type="text" class="form-control" name="title" id="title" value="${procedure.title}">
                </c:if>
                <c:if test="${empty procedure.title}">
                    <input type="text" class="form-control" name="title" id="title">
                </c:if>
            </div>
            <div class="form-group">
                <label for="patient.id">Patient ID</label>
                <c:if test="${!empty procedure.patient.id}">
                    <input type="text" class="form-control" name="patient.id" id="patient.id" value="${procedure.patient.id}">
                </c:if>
                <c:if test="${empty procedure.patient.id}">
                    <input type="text" class="form-control" name="patient.id" id="patient.id">
                </c:if>
            </div>
            <div class="form-group">
                <label for="date">Date</label>
                <c:if test="${!empty procedure.date}">
                    <input type="date" class="form-control" name="date" id="date" value="${procedure.date}">
                </c:if>
                <c:if test="${empty procedure.date}">
                    <input type="date" class="form-control" name="date" id="date">
                </c:if>
            </div>
            <div class="form-group">
                <label for="date">Start date</label>
                <c:if test="${!empty procedure.startDate}">
                    <input type="date" class="form-control" name="startDate" id="startDate" value="${procedure.startDate}">
                </c:if>
                <c:if test="${empty procedure.startDate}">
                    <input type="date" class="form-control" name="startDate" id="startDate">
                </c:if>
            </div>
            <div class="form-group">
                <label for="date">End date</label>
                <c:if test="${!empty procedure.endDate}">
                    <input type="date" class="form-control" name="endDate" id="endDate" value="${procedure.endDate}">
                </c:if>
                <c:if test="${empty procedure.endDate}">
                    <input type="date" class="form-control" name="endDate" id="endDate">
                </c:if>
            </div>
            <div class="form-group">
                <label for="time">Time</label>
                <c:if test="${!empty procedure.time}">
                    <input type="time" id="time" name="time" class="form-control" value="${procedure.time}">
                </c:if>
                <c:if test="${empty procedure.time}">
                    <input type="time" id="time" name="time" class="form-control">
                </c:if>
            </div>
            <div class="form-group">
                <label for="status">Status</label>
                <select id="status" name="status" class="form-control">
                    <option selected>scheduled</option>
                    <option>canceled</option>
                    <option>done</option>
                </select>
            </div>
            <div class="form-group">
                <label for="status">Daily pattern</label>
                <select id="dailyPattern" name="dailyPattern" class="form-control">
                    <option value="1" selected>once a day</option>
                    <option value="2">twice a day</option>
                    <option value="3">three times a day</option>
                </select>
            </div>
            <div class="form-group">
                <input type="checkbox" name="weeklyPattern" value="Monday" /> Monday
                <input type="checkbox" name="weeklyPattern" value="Tuesday" /> Tuesday
                <input type="checkbox" name="weeklyPattern" value="Wednesday" /> Wednesday
                <input type="checkbox" name="weeklyPattern" value="Thursday" /> Thursday
                <input type="checkbox" name="weeklyPattern" value="Friday" /> Friday
                <input type="checkbox" name="weeklyPattern" value="Saturday" /> Saturday
                <input type="checkbox" name="weeklyPattern" value="Sunday" /> Sunday
            </div>
            <c:if test="${empty procedure.title}">
                <button type="submit" class="btn btn-success">Save</button>
            </c:if>
            <c:if test="${!empty procedure.title}">
                <button type="submit" class="btn btn-success">Save changes</button>
            </c:if>
        </form>
    </div>
</div>
</body>
</html>