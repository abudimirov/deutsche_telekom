<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add patient</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://use.fontawesome.com/e324a589d0.js"></script>
    <script src="https://unpkg.com/@popperjs/core@2"></script>
</head>
<body style="background: #F2F2F2;">
<%@ include file="components/nav.jsp" %>
<c:url value="/patients/add" var="var"/>

<div class="container">
    <div class="my-5 p-5" style="background: #fff;">
        <h1>Add new patient</h1>
        <form:form action="${var}" method="POST" modelAttribute="patient">
            <div class="form-group">
                <label for="name">Name</label>
                <c:if test="${!empty patient.name}">
                    <input type="text" class="form-control" name="name" id="name" value="${patient.name}" required />
                    <form:errors path="name" cssClass="text-danger" />
                </c:if>
                <c:if test="${empty patient.name}">
                    <input type="text" class="form-control" name="name" id="name" required>
                </c:if>
            </div>
            <div class="form-group">
                <label for="surname">Surname</label>
                <c:if test="${!empty patient.surname}">
                    <input type="text" class="form-control" name="surname" id="surname" value="${patient.surname}">
                    <form:errors path="surname" cssClass="text-danger" />
                </c:if>
                <c:if test="${empty patient.surname}">
                    <input type="text" class="form-control" name="surname" id="surname" required>
                </c:if>
            </div>
            <div class="form-group">
                <label for="insuranceNum">Insurance Number</label>
                <c:if test="${!empty patient.insuranceNum}">
                    <input type="number" min="1000" max="9999" class="form-control" name="insuranceNum" id="insuranceNum" value="${patient.insuranceNum}">
                    <form:errors path="insuranceNum" cssClass="text-danger" />
                </c:if>
                <c:if test="${empty patient.insuranceNum}">
                    <input type="number" min="1000" max="9999" class="form-control" name="insuranceNum" id="insuranceNum" required>
                </c:if>
            </div>
            <div class="form-group">
                <label for="doctor">Doctor</label>
                <c:if test="${!empty patient.doctor}">
                    <input type="text" class="form-control" name="doctor" id="doctor" value="${patient.doctor}">
                </c:if>
                <c:if test="${empty patient.doctor}">
                    <input type="text" class="form-control" name="doctor" id="doctor" required>
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
            <c:if test="${!empty patient.name}">
                <div class="form-group">
                    <label for="cured">Cured</label>
                    <select id="cured" name="cured" class="form-control">
                        <c:if test="${patient.cured == false}">
                            <option selected>false</option>
                            <option>true</option>
                        </c:if>
                        <c:if test="${patient.cured == true}">
                            <option>false</option>
                            <option selected>true</option>
                        </c:if>
                    </select>
                </div>
            </c:if>
            <button type="submit" class="btn btn-success">Save</button>
        </form:form>



    </div>
</div>
</body>
</html>