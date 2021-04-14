<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty patient.name}">
        <title>Add patient</title>
    </c:if>
    <c:if test="${!empty patient.name}">
        <title>Edit patient</title>
    </c:if>
    <link href="<c:url value="/res/font-awesome.min.css"/>" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
</head>
<body>
<c:if test="${empty patient.name}">
    <c:url value="/add" var="var"/>
</c:if>
<c:if test="${!empty patient.name}">
    <c:url value="/edit" var="var"/>
</c:if>
<div class="container">
    <div class="my-5">
        <c:if test="${!empty patient.name}">
            <h1>Edit patient - ${patient.name} ${patient.surname}</h1>
        </c:if>
        <c:if test="${empty patient.name}">
            <h1>Add new patient</h1>
        </c:if>
        <form action="${var}" method="POST">
            <c:if test="${!empty patient.name}">
                <input type="hidden" name="id" value="${patient.id}">
            </c:if>
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" name="name" id="name">
            </div>
            <div class="form-group">
                <label for="surname">Surname</label>
                <input type="text" class="form-control" name="surname" id="surname">
            </div>
            <div class="form-group">
                <label for="yearOfBirth">Year of birth</label>
                <input type="text" class="form-control" name="yearOfBirth" id="yearOfBirth">
            </div>
            <div class="form-group">
                <label for="sex">Sex</label>
                <select id="sex" name="sex" class="form-control">
                    <option selected>Male</option>
                    <option>Female</option>
                </select>
            </div>
            <div class="form-group">
                <label for="cured">Cured</label>
                <select id="cured" name="cured" class="form-control">
                    <option selected>false</option>
                    <option>true</option>
                </select>
            </div>
            <c:if test="${empty patient.name}">
                <button type="submit" class="btn btn-success">Save</button>
            </c:if>
            <c:if test="${!empty patient.name}">
                <button type="submit" class="btn btn-success">Save changes</button>
            </c:if>
        </form>
    </div>
</div>
</body>
</html>