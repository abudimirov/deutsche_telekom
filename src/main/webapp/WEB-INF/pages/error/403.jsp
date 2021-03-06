<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<head>
    <title>Error 403 - You don't have permission to access this page.</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/res/style.css" />">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://use.fontawesome.com/e324a589d0.js"></script>
</head>
<body>
<%@ include file="../components/nav.jsp" %>
    <div class="container-wide">
        <div class="container">
            <div class="row my-5">
                <div class="d-flex flex-wrap justify-content-center mx-auto">
                    <img src="<c:url value="/res/page-forbidden-icon.svg" />" alt="page forbidden">
                    <h1>You don't have permission to access this page</h1>
                </div>
            </div>
        </div>
    </div>
</body>
</html>