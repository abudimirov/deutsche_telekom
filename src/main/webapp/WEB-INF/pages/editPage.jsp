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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://use.fontawesome.com/e324a589d0.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand">Medical Cabinet</a>
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/procedures">Procedures</a>
        </li>
    </ul>
</nav>
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
                <c:if test="${!empty patient.name}">
                    <input type="text" class="form-control" name="name" id="name" value="${patient.name}">
                </c:if>
                <c:if test="${empty patient.name}">
                    <input type="text" class="form-control" name="name" id="name">
                </c:if>
            </div>
            <div class="form-group">
                <label for="surname">Surname</label>
                <c:if test="${!empty patient.surname}">
                    <input type="text" class="form-control" name="surname" id="surname" value="${patient.surname}">
                </c:if>
                <c:if test="${empty patient.surname}">
                    <input type="text" class="form-control" name="surname" id="surname">
                </c:if>
            </div>
            <div class="form-group">
                <label for="insuranceNum">Insurance Number</label>
                <c:if test="${!empty patient.insuranceNum}">
                    <input type="number" min="1000" max="9999" class="form-control" name="insuranceNum" id="insuranceNum" value="${patient.insuranceNum}">
                </c:if>
                <c:if test="${empty patient.insuranceNum}">
                    <input type="number" min="1000" max="9999" class="form-control" name="insuranceNum" id="insuranceNum">
                </c:if>
            </div>
            <div class="form-group">
                <label for="doctor">Doctor</label>
                <c:if test="${!empty patient.doctor}">
                    <input type="text" class="form-control" name="doctor" id="doctor" value="${patient.doctor}">
                </c:if>
                <c:if test="${empty patient.doctor}">
                    <input type="text" class="form-control" name="doctor" id="doctor">
                </c:if>
            </div>
            <div class="form-group">
                <label for="diagnosis">Diagnosis</label>
                <c:if test="${!empty patient.diagnosis}">
                    <input type="text" class="form-control" name="diagnosis" id="diagnosis" value="${patient.diagnosis}">
                </c:if>
                <c:if test="${empty patient.diagnosis}">
                    <input type="text" class="form-control" name="diagnosis" id="diagnosis">
                </c:if>
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