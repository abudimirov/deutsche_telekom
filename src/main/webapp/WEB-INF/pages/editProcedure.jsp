<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit procedure</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://use.fontawesome.com/e324a589d0.js"></script>
</head>
<body>
<%@ include file="components/nav.jsp" %>
<c:url value="/procedures/edit" var="var"/>
<div class="container">
    <div class="my-5">
        <h1>Edit procedure - ${procedure.title}</h1>
        <form action="${var}" method="POST">
            <input type="hidden" name="id" value="${procedure.id}">
            <div class="form-group">
                <label for="title">Title</label>
                <input type="text" class="form-control" name="title" id="title" value="${procedure.title}">
            </div>
            <div class="form-group">
                <label for="patient.id">Patient ID</label>
                <input type="text" class="form-control" name="patient.id" id="patient.id" value="${procedure.patient.id}">
            </div>
            <div class="form-group">
                <label for="date">Date</label>
                <input type="date" class="form-control" name="date" id="date" value="${procedure.date}">
            </div>
            <div class="form-group">
                <label for="time">Time</label>
                <input type="time" class="form-control" name="time" id="time" value="${procedure.time}">
            </div>
            <div class="form-group">
                <label for="type">Type</label>
                <select id="type" name="type" class="form-control">
                    <option value="procedure">procedure</option>
                    <option value="medicine" selected>medicine</option>
                </select>
            </div>
            <div class="form-group" id="dosage">
                <label for="type">Dosage</label>
                <input type="number" id="dose" name="dose" class="form-control" value="${procedure.dose}">
            </div>
            <div class="form-group">
                <label for="status">Current status is <strong>${procedure.status}</strong>. Set status</label>
                <select id="status" name="status" class="form-control">
                    <option selected>scheduled</option>
                    <option>canceled</option>
                    <option>done</option>
                </select>
            </div>
            <button type="submit" class="btn btn-success">Save changes</button>
        </form>
    </div>
</div>
</body>
<script>
    $(function () {
        $("#type").change(function() {
            var val = $(this).val();
            if(val === "medicine") {
                $("#dosage").show();
            }
            else if(val === "procedure") {
                $("#dosage").hide();
            }
        });
    });
</script>
</html>