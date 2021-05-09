<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:if test="${empty patient.name}">
        <title>Add patient</title>
    </c:if>
    <c:if test="${!empty patient.name}">
        <title>Edit patient</title>
    </c:if>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://use.fontawesome.com/e324a589d0.js"></script>
    <script src="https://unpkg.com/@popperjs/core@2"></script>
</head>
<body style="background: #F2F2F2;">
<%@ include file="components/nav.jsp" %>
<c:if test="${empty patient.name}">
    <c:url value="/patients/add" var="var"/>
</c:if>
<c:if test="${!empty patient.name}">
    <c:url value="/patients/edit" var="var"/>
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



            <c:if test="${empty patient.name}">
                <button type="submit" class="btn btn-success">Save</button>
            </c:if>
            <c:if test="${!empty patient.name}">
                <button type="submit" class="btn btn-success">Save changes</button>
            </c:if>
            <c:if test="${!empty patient.name}">

                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal">
                    Discharge patient
                </button>
                <!-- Modal -->
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Discharge ${patient.name} ${patient.surname}</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                Are you sure you want to discharge ${patient.name} ${patient.surname}? Patient status will be set to "Cured" and all scheduled events will be cancelled.
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <a href="/patients/discharge/${patient.id}" class="btn btn-danger">Discharge patient</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </form>
        <c:if test="${!empty eventsList}">
            <h2 class="my-3">Patient events</h2>
            <c:forEach var="event" items="${eventsList}" varStatus="i">
                <div class="card mb-3">
                    <div class="card-body">
                        <h5 class="card-title">Card title - ${event.status}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">From ${event.start_date} to ${event.end_date}</h6>
                        <p class="card-text">
                            <ul class="list-group list-group-flush">
                                <c:forEach var="procedure" items="${event.procedures}" varStatus="i">
                                    <li class="list-group-item">
                                            ${procedure.date} at ${procedure.time} - ${procedure.status}
                                        <a href="/procedures/edit/${procedure.id}" class="btn btn-link"><i class="fa fa-pencil" aria-hidden="true"></i> edit</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </p>
                        <a href="/events/cancel/${event.id}" class="card-link"><i class="fa fa-times" aria-hidden="true"></i> cancel event</a>
                    </div>
                </div>
            </c:forEach>
        </c:if>

        <c:if test="${!empty patient.name}">
            <div class="my-5">
                <a href="<c:url value="/procedures/add"/>" class="btn btn-success btn-block" role="button" aria-pressed="true"><i class="fa fa-plus-circle" aria-hidden="true"></i> Add new event</a>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>