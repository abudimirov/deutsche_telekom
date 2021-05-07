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
    <title>All patients</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/res/style.css" />">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://use.fontawesome.com/e324a589d0.js"></script>
</head>

<body style="background: #F2F2F2;">
<%@ include file="components/nav.jsp" %>
<div class="container-wide">
    <div class="container my-5">
        <div class="d-flex flex-wrap justify-content-around mx-auto">
            <a href="<c:url value="/patients" />">
                <div class="card px-5 py-3" style="width: 18rem;">
                    <img src="<c:url value="/res/doctor-svgrepo-com.svg" />" alt="For doctors">
                    <div class="card-body">
                        <p class="card-text">For doctors</p>
                    </div>
                </div>
            </a>
            <a href="<c:url value="/procedures" />">
                <div class="card px-5 py-3" style="width: 18rem;">
                    <img src="<c:url value="/res/nurse-svgrepo-com.svg" />" alt="For medical personal">
                    <div class="card-body">
                        <p class="card-text">For medical personal</p>
                    </div>
                </div>
            </a>
        </div>
    </div>
</div>
</body>
</html>