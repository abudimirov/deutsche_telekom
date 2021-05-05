<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:if test="${empty procedure.title}">
        <title>Add procedure</title>
    </c:if>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://use.fontawesome.com/e324a589d0.js"></script>
</head>
<body>
<%@ include file="components/nav.jsp" %>
<c:url value="/procedures/add" var="var"/>
<div class="container">
    <div class="my-5">
        <h1>Add new procedure</h1>
        <form action="${var}" method="POST">
            <div class="form-group">
                <label for="title">Title</label>
                <input type="text" class="form-control" name="title" id="title">
            </div>
            <div class="form-group">
                <label for="patient.id">Patient ID</label>
                <input type="text" class="form-control" name="patient.id" id="patient.id">
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
                <input type="number" id="dose" name="dose" class="form-control" value="0">
            </div>
            <div class="form-group">
                <label for="startDate">Start date</label>
                <input type="date" class="form-control" name="startDate" id="startDate">
            </div>
            <div class="form-group">
                <label for="endDate">End date</label>
                <input type="date" class="form-control" name="endDate" id="endDate">
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
                <label for="updated">Replace procedures</label>
                <input type="checkbox" id="updated" name="updated" /> Replace
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
                <input type="checkbox" name="weeklyPattern" value="2" /> Monday
                <input type="checkbox" name="weeklyPattern" value="3" /> Tuesday
                <input type="checkbox" name="weeklyPattern" value="4" /> Wednesday
                <input type="checkbox" name="weeklyPattern" value="5" /> Thursday
                <input type="checkbox" name="weeklyPattern" value="6" /> Friday
                <input type="checkbox" name="weeklyPattern" value="7" /> Saturday
                <input type="checkbox" name="weeklyPattern" value="1" /> Sunday
            </div>
            <button type="submit" class="btn btn-success">Save</button>
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