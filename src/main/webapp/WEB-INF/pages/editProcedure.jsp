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
                <sec:authorize access="hasRole('NURSE')">
                    <input type="text" class="form-control-plaintext" name="title" id="title" value="${procedure.title}" readonly>
                </sec:authorize>
                <sec:authorize access="hasRole('DOCTOR')">
                    <input type="text" class="form-control" name="title" id="title" value="${procedure.title}">
                </sec:authorize>
            </div>
            <div class="form-group d-none">
                <label for="patient.id">Patient ID</label>
                <sec:authorize access="hasRole('NURSE')">
                    <input type="text" class="form-control-plaintext" name="patient.id" id="patient.id" value="${procedure.patient.id}" readonly>
                </sec:authorize>
                <sec:authorize access="hasRole('DOCTOR')">
                    <input type="text" class="form-control" name="patient.id" id="patient.id" value="${procedure.patient.id}" readonly>
                </sec:authorize>
            </div>
            <div class="form-group d-none">
                <label for="patient.id">Event ID</label>
                <sec:authorize access="hasRole('NURSE')">
                    <input type="text" class="form-control-plaintext" name="event.id" id="event.id" value="${procedure.event.id}" readonly>
                </sec:authorize>
                <sec:authorize access="hasRole('DOCTOR')">
                    <input type="text" class="form-control" name="event.id" id="event.id" value="${procedure.event.id}">
                </sec:authorize>
            </div>
            <div class="form-group">
                <label for="date">Date</label>
                <sec:authorize access="hasRole('NURSE')">
                    <input type="date" readonly class="form-control-plaintext" name="date" id="date" value="${procedure.date}">
                </sec:authorize>
                <sec:authorize access="hasRole('DOCTOR')">
                    <input type="date" class="form-control" name="date" id="date" value="${procedure.date}">
                </sec:authorize>
            </div>
            <div class="form-group">
                <label for="time">Time</label>
                <sec:authorize access="hasRole('NURSE')">
                    <input type="time" readonly class="form-control-plaintext" name="time" id="time" value="${procedure.time}">
                </sec:authorize>
                <sec:authorize access="hasRole('DOCTOR')">
                    <input type="time" class="form-control" name="time" id="time" value="${procedure.time}">
                </sec:authorize>
            </div>
            <div class="form-group">
                <label for="type">Type</label>
                <sec:authorize access="hasRole('NURSE')">
                    <input type="text" readonly class="form-control-plaintext" name="type" id="type" value="${procedure.type}">
                </sec:authorize>
                <sec:authorize access="hasRole('DOCTOR')">
                    <select id="type" name="type" class="custom-select">
                        <option value="procedure">procedure</option>
                        <option value="medicine" selected>medicine</option>
                    </select>
                </sec:authorize>
            </div>
            <div class="form-group" id="dosage">
                <label for="type">Dosage (mg)</label>
                <sec:authorize access="hasRole('NURSE')">
                    <input type="number" readonly id="dose" name="dose" class="form-control-plaintext" value="${procedure.dose}">
                </sec:authorize>
                <sec:authorize access="hasRole('DOCTOR')">
                    <input type="number" id="dose" name="dose" class="form-control" value="${procedure.dose}">
                </sec:authorize>
            </div>
            <div class="form-group">
                <label for="status">Current status is <strong>${procedure.status}</strong>. Set status</label>
                <select id="status" name="status" class="custom-select">
                    <sec:authorize access="hasRole('DOCTOR')">
                    <option selected>scheduled</option>
                    </sec:authorize>
                    <option>done</option>
                    <option>cancelled</option>
                </select>
            </div>
            <div class="form-group" id="comment-block" style="display: none;">
                <label for="comment">Please, provide explanation why it's cancelled</label>
                <input type="text" name="comment" id="comment">
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
<script>
    $(function () {
        $("#status").change(function() {
            var val = $(this).val();
            if(val === "cancelled") {
                $("#comment-block").show();
                $("#comment").prop('required', true);
            }
            else if(val !== "cancelled") {
                $("#comment-block").hide();
                $("#comment").prop('required', false)
            }
        });
    });
</script>

</html>