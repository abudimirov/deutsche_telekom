<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty patient.name}">
        <title>Add</title>
    </c:if>
    <c:if test="${!empty patient.name}">
        <title>Edit</title>
    </c:if>
</head>
<body>
<c:if test="${empty patient.name}">
    <c:url value="/add" var="var"/>
</c:if>
<c:if test="${!empty patient.name}">
    <c:url value="/edit" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <c:if test="${!empty patient.name}">
        <input type="hidden" name="id" value="${patient.id}">
    </c:if>
    <label for="name">Title</label>
    <input type="text" name="name" id="name">
    <label for="surname">Title</label>
    <input type="text" name="surname" id="surname">
    <label for="yearOfBirth">Year</label>
    <input type="text" name="yearOfBirth" id="yearOfBirth">
    <label for="sex">Genre</label>
    <input type="text" name="sex" id="sex">
    <label for="cured">Watched</label>
    <input type="text" name="cured" id="cured">
    <c:if test="${empty patient.name}">
        <input type="submit" value="Add new patient">
    </c:if>
    <c:if test="${!empty patient.name}">
        <input type="submit" value="Edit patient">
    </c:if>
</form>
</body>
</html>