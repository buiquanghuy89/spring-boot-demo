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
    </div>
</div>
<%--<table width="100%" height="100%">--%>
<%--<tr>--%>
<%--<td width="15%" style="background-color: #94CDDD;" valign="top" align=center>--%>
<%--<table width="90%">--%>
<%--<tr>--%>
<%--<td style="border-bottom: 1px solid #808080">--%>
<%--<h2 style="padding-top: 10px; font-family: 'Calibri'; margin-bottom: 5px;"--%>
<%--align=center>--%>
<%--Modules--%>
<%--</h2>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td align=center>--%>
<%--<table style="padding-top: 10px;">--%>
<%--<tr>--%>
<%--<td>--%>
<%--<s:checkboxlist theme="vertical-checkbox"--%>
<%--id="chkModule"--%>
<%--name="modules"--%>
<%--list="{'ENR062A3','ENR04CA0','ENR077A9'}"--%>
<%--value="{'ENR062A3','ENR04CA0','ENR077A9'}"--%>
<%--onchange="changeModule(this)"--%>
<%--/>--%>
<%--</td>--%>
<%--</tr>--%>
<%--</table>--%>
<%--</td>--%>
<%--</tr>--%>
<%--</table>--%>
<%--</td>--%>
<%--<td width="85%">--%>
<%--<div id="container" style="min-width: 310px; height: 45%; margin: 0 auto;; margin-top:0px;"></div>--%>
<%--<div id="container_subcat" style="min-width: 310px; height: 45%; margin: 0 auto; margin-top:10px;"--%>
<%--hidden="true"></div>--%>
<%--</td>--%>
<%--</tr>--%>
<%--</table>--%>

</body>
<script src="js/pa/pa-stock.js"></script>
</html>