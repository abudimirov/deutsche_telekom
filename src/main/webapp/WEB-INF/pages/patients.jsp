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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>All patients</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/res/style.css" />">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://use.fontawesome.com/e324a589d0.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script>
</head>

<body>
    <div class="container-fluid">
        <div class="row">
            <%@ include file="components/sidebar.jsp" %>
            <main class="col-md-9 ml-sm-auto col-lg-10 px-md-4 bg-light">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-8">
                            <div class="card my-5">
                                <div class="card-body">
                                    <div class="d-flex justify-content-between align-items-center py-3">
                                        <h5>Patients <small class="text-muted">${patientCount} total</small></h5>
                                        <div>
                                            <i class="fa fa-calendar" aria-hidden="true"></i>
                                            <jsp:useBean id="now" class="java.util.Date"/>
                                            <fmt:formatDate value="${now}" dateStyle="long"/>
                                            <fmt:formatDate value="${now}" pattern="HH:mm" />
                                        </div>
                                        <a href="<c:url value="/patients/add"/>" class="btn btn-success" role="button" aria-pressed="true"><i class="fa fa-plus-circle" aria-hidden="true"></i> Add new patient</a>
                                    </div>
                                <c:if test="${patientsCount == 0}">
                                    <h2>The list is empty but you can add a new patient</h2>
                                </c:if>
                                <c:if test="${patientCount > 0}">
                                    <table class="table">
                                        <thead style="background: transparent;">
                                            <tr>
                                                <th scope="col" class="text-left">#</th>
                                                <th scope="col">Patient</th>
                                                <th scope="col">Status</th>
                                                <th scope="col">Insurance</th>
                                                <th scope="col">Doctor</th>
                                                <th scope="col" class="right-side"></th>
                                            </tr>
                                        </thead>

                                        <c:forEach var="patient" items="${patientsList}" varStatus="i">
                                            <tr>
                                                <td class="left-side">${i.index + 1 + (page - 1) * 10}</td>
                                                <td class="name">${patient.name} ${patient.surname}</td>
                                                <td>
                                                    <c:if test="${patient.cured == true}">
                                                        <span class="badge badge-pill badge-success">Cured</span>
                                                    </c:if>
                                                    <c:if test="${patient.cured == false}">
                                                        <span class="badge badge-pill badge-secondary">In treatment</span>
                                                    </c:if>
                                                </td>
                                                <td># ${patient.insuranceNum}</td>
                                                <td><i class="fa fa-user-md" aria-hidden="true"></i> ${patient.doctor}</td>
                                                <td>
                                                    <a href="/patients/edit/${patient.id}" class="text-dark" title="edit patient">
                                                        <i class="fa fa-ellipsis-h" aria-hidden="true"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                    <nav aria-label="Page navigation">
                                        <ul class="pagination justify-content-center">
                                            <c:if test="${pagesCount > 1}">
                                                <c:set value="disabled" var="disabled"/>
                                                <c:set value="" var="active"/>
                                                <li class="${page == 1 ? disabled : active} page-item">

                                                    <c:url value="/patients" var="url">
                                                        <c:param name="page" value="1"/>
                                                    </c:url>
                                                    <a class=" page-link" href="${url}">
                                                        &nbsp<i class="fa fa-fast-backward" aria-hidden="true"></i>&nbsp
                                                    </a>
                                                </li>
                                                <li class="${page == 1 ? disabled : active} page-item">
                                                    <c:url value="/patients" var="url">
                                                        <c:param name="page" value="${page - 1}"/>
                                                    </c:url>
                                                    <a class=" page-link" href="${url}">
                                                        &nbsp<i class="fa fa-step-backward" aria-hidden="true"></i>&nbsp
                                                    </a>
                                                </li>
                                                <c:if test="${pagesCount <= 5}">
                                                    <c:set var="begin" value="1"/>
                                                    <c:set var="end" value="${pagesCount}"/>
                                                </c:if>
                                                <c:if test="${pagesCount > 5}">
                                                    <c:choose>
                                                        <c:when test="${page < 3}">
                                                            <c:set var="begin" value="1"/>
                                                            <c:set var="end" value="5"/>
                                                        </c:when>
                                                        <c:when test="${page > pagesCount - 2}">
                                                            <c:set var="begin" value="${pagesCount - 4}"/>
                                                            <c:set var="end" value="${pagesCount}"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:set var="begin" value="${page - 2}"/>
                                                            <c:set var="end" value="${page + 2}"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:if>

                                                <c:forEach begin="${begin}" end="${end}" step="1" varStatus="i">
                                                    <li class="page-item">
                                                        <c:url value="/patients" var="url">
                                                            <c:param name="page" value="${i.index}"/>
                                                        </c:url>
                                                        <c:set value="current-page" var="current"/>
                                                        <c:set value="" var="perspective"/>
                                                        <a class="${page == i.index ? current : perspective} page-link" href="${url}">${i.index}</a>
                                                    </li>
                                                </c:forEach>

                                                <li class="${page == pagesCount ? disabled : active} page-item">
                                                    <c:url value="/patients" var="url">
                                                        <c:param name="page" value="${page + 1}"/>
                                                    </c:url>
                                                    <a class="page-link" href="${url}">
                                                        &nbsp<i class="fa fa-step-forward" aria-hidden="true"></i>&nbsp
                                                    </a>
                                                </li>
                                                <li class="${page == pagesCount ? disabled : active} page-item">
                                                    <c:url value="/patients" var="url">
                                                        <c:param name="page" value="${pagesCount}"/>
                                                    </c:url>
                                                    <a class="page-link" href="${url}">
                                                        &nbsp<i class="fa fa-fast-forward" aria-hidden="true"></i>&nbsp
                                                    </a>
                                                </li>
                                            </c:if>
                                        </ul>
                                    </nav>
                                </c:if>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="card mt-5">
                                <div class="card-body">
                                    <h5 class="card-title">Hospital capacity</h5>
                                    <div id="canvas-holder" class="mx-auto" style="width: 300px;">
                                        <canvas id="chart-area" width="150" height="150" />
                                    </div>

                                    <div id="chartjs-tooltip"></div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>


            </main>
        </div>
    </div>


<script>
    window.chartColors = {
        red: '#c31d25',
        green: '#28a745',
    };

    Chart.defaults.global.tooltips.custom = function(tooltip) {
        // Tooltip Element
        var tooltipEl = document.getElementById('chartjs-tooltip');

        // Hide if no tooltip
        if (tooltip.opacity === 0) {
            tooltipEl.style.opacity = 0;
            return;
        }

        // Set Text
        if (tooltip.body) {
            var total = 0;

            // get the value of the datapoint
            var value = this._data.datasets[tooltip.dataPoints[0].datasetIndex].data[tooltip.dataPoints[0].index].toLocaleString();

            // calculate value of all datapoints
            this._data.datasets[tooltip.dataPoints[0].datasetIndex].data.forEach(function(e) {
                total += e;
            });

            // calculate percentage and set tooltip value
            tooltipEl.innerHTML = '<h1>' + (value / total * 100) + '%</h1>';
        }

        // calculate position of tooltip
        var centerX = (this._chartInstance.chartArea.left + this._chartInstance.chartArea.right) / 2;
        var centerY = ((this._chartInstance.chartArea.top + this._chartInstance.chartArea.bottom) / 2);

        // Display, position, and set styles for font
        tooltipEl.style.opacity = 1;
        tooltipEl.style.left = centerX + 'px';
        tooltipEl.style.top = centerY + 'px';
        tooltipEl.style.fontFamily = tooltip._fontFamily;
        tooltipEl.style.fontSize = tooltip.fontSize;
        tooltipEl.style.fontStyle = tooltip._fontStyle;
        tooltipEl.style.padding = tooltip.yPadding + 'px ' + tooltip.xPadding + 'px';
    };

    var config = {
        type: 'doughnut',
        data: {
            datasets: [{
                data: [${patientsInTreatmentCount}, 100 - ${patientsInTreatmentCount}],
                backgroundColor: [
                    window.chartColors.red,
                    window.chartColors.green,
                ],
            }],
            labels: [
                "Occupied beds",
                "Free beds",
            ]
        },
        options: {
            responsive: true,
            legend: {
                display: true,
                labels: {
                    padding: 20
                },
            },
            tooltips: {
                enabled: false,
            }
        }
    };

    window.onload = function() {
        var ctx = document.getElementById("chart-area").getContext("2d");
        window.myPie = new Chart(ctx, config);
    };
</script>
</body>
</html>