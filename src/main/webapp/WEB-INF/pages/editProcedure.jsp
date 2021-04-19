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
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand">Medical Cabinet</a>
    <ul class="navbar-nav">
        <li class="nav-item active">
            <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/procedures">Procedures</a>
        </li>
    </ul>
</nav>
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
                <label for="patient_id">Patient ID</label>
                <c:if test="${!empty procedure.patient_id}">
                    <input type="text" class="form-control" name="patient" id="patient_id" value="${procedure.patient_id}">
                </c:if>
                <c:if test="${empty procedure.patient_id}">
                    <input type="text" class="form-control" name="patient" id="patient_id">
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
                <label for="time">Time</label>
                <input type="text" id="time" name="time" class="form-control">
            </div>
            <div class="form-group">
                <label for="status">Status</label>
                <select id="status" name="status" class="form-control">
                    <option selected>scheduled</option>
                    <option>canceled</option>
                    <option>done</option>
                </select>
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