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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://use.fontawesome.com/e324a589d0.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script>
</head>

<body style="background: #F2F2F2;">
<%@ include file="components/nav.jsp" %>
<div class="container-wide">
    <div class="container">
        <div class="row my-2 mx-auto">
            <div class="col-xs-4">
                <div id="canvas-holder" style="width: 300px;">
                    <canvas id="chart-area" width="150" height="150" />
                </div>

                <div id="chartjs-tooltip"></div>
            </div>
            <div class="col-xs-4"></div>
        </div>
    </div>
    <div class="row my-3 mx-auto">
        <div class="container p-3" style="background: #FFF;">
            <div class="d-flex justify-content-between align-items-center py-3">
                <h1 class="main">Patients</h1>
                <div class="date">
                    <jsp:useBean id="now" class="java.util.Date"/>
                    <fmt:formatDate value="${now}" dateStyle="long"/>
                    <fmt:formatDate value="${now}" pattern="HH:mm" />
                </div>
                <a href="<c:url value="/patients/add"/>" class="btn btn-success" role="button" aria-pressed="true"><i class="fa fa-plus-circle" aria-hidden="true"></i> Add new patient</a>
            </div>
            <table class="table">
                <c:if test="${patientCount > 0}">
                    <thead>
                        <tr>
                            <th scope="col" class="text-left">#</th>
                            <th scope="col">Name</th>
                            <th scope="col">Surname</th>
                            <th scope="col">Insurance №</th>
                            <th scope="col">Doctor</th>
                            <th scope="col">Cured</th>
                            <th scope="col" class="right-side"></th>
                        </tr>
                    </thead>

                    <c:forEach var="patient" items="${patientsList}" varStatus="i">
                        <tr>
                            <td class="left-side">${i.index + 1 + (page - 1) * 10}</td>
                            <td class="name">${patient.name}</td>
                            <td class="surname">${patient.surname}</td>
                            <td>${patient.insuranceNum}</td>
                            <td>${patient.doctor}</td>
                            <td>
                                <c:if test="${patient.cured == true}">
                                    <span>yes</span>
                                </c:if>
                                <c:if test="${patient.cured == false}">
                                    <span>no</span>
                                </c:if>
                            </td>
                            <td>
                                <a href="/patients/edit/${patient.id}">
                                    <i class="fa fa-user" aria-hidden="true"></i> view
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${patientsCount == 0}">
                    <tr>
                        <td colspan="7" style="font-size: 150%" class="left-side right-side">
                            The list is empty but you can add a new patient
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td colspan="8" class="left-side link right-side">
                        <span class="text-right">Total number of patients: ${patientCount}</span>
                        <nav aria-label="Page navigation example">
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
                    </td>
                </tr>
            </table>
        </div>
        </div>
    </div>
</div>


<script>
    window.chartColors = {
        red: 'rgb(255, 99, 132)',
        green: 'rgb(75, 192, 192)',
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
                data: [${patientCount}, 100 - ${patientCount}],
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