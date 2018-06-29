<%--
  Created by IntelliJ IDEA.
  User: bqhuy
  Date: 11/8/2016
  Time: 11:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:include page="/jsp/common/datetime.jsp"/>
<link href="css/pa.css" rel="stylesheet">
<html>
<head>
    <script src="http://code.jquery.com/jquery-git2.min.js"></script>
    <script src="http://code.highcharts.com/highcharts.js"></script>
    <script src="http://code.highcharts.com/modules/exporting.js"></script>
    <title>PA Chart</title>
</head>
<body>
<div class="app-wrapper app-pa">
    <div class="left-wrapper">
        <div class="pa-header">
            <h3>Modules</h3>
        </div>
        <div class="checklist-wrapper" id="checklist-module-id">
        </div>
    </div>
    <div class="center-wrapper">
        <div class="chart-container" id="chart-total-container">
        </div>
        <div class="chart-detail-popup" id="chart-detail-container">
            <div id="show-chart-detail-container">
            </div>
        </div>
        <div class="jump-to-top">
            <button onclick="jumpToTop()" id="jump-to-top-btn" title="Go to top"/>
        </div>
    </div>
</div>
</body>
<script src="js/pa/pa-stock.js"></script>
</html>