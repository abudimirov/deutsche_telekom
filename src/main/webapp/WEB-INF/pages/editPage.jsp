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
<body style="background: #F2F2F2;">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/"><img src="<c:url value="/res/logo.png" />" alt="Medical Cabinet" style="height: 40px;" /></a>
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
    <div class="my-5 p-3" style="background: #fff;">
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
                    <input class="form-control" name="diagnosis" id="diagnosis" value="${patient.diagnosis}">
                </c:if>
                <c:if test="${empty patient.diagnosis}">
                    <input class="form-control" name="diagnosis" id="diagnosis">
                </c:if>
            </div>
            <div class="form-group">
                <label for="cured">Cured</label>
                <select id="cured" name="cured" class="form-control">
                    <option selected>false</option>
                    <option>true</option>
                </select>
            </div>

            <c:if test="${!empty patient.procedures}">
                <div class="my-5">
                    <h2 class="my-3">Patient procedures</h2>
                    <c:forEach var="procedure" items="${patient.procedures}" varStatus="i">
                        <div class="row g-3 my-3">
                            <div class="col-sm-7">
                                <input type="text" class="form-control" placeholder="Title" aria-label="Title" value="${procedure.title}">
                            </div>
                            <div class="col-sm">
                                <input type="date" class="form-control" placeholder="Date" aria-label="Date" value="${procedure.date}">
                            </div>
                            <div class="col-sm">
                                <input type="time" class="form-control" placeholder="Time" aria-label="Time" value="${procedure.time}">
                            </div>
                            <div class="col-sm">
                                <a href="/procedures/edit/${procedure.id}" class="btn btn-link"><i class="fa fa-pencil" aria-hidden="true"></i> edit</a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>

            <c:if test="${!empty patient.name}">
                <div class="mb-5">
                    <a href="<c:url value="/procedures/add"/>" class="btn btn-success btn-block" role="button" aria-pressed="true"><i class="fa fa-plus-circle" aria-hidden="true"></i> Add new procedure</a>
                </div>
            </c:if>

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