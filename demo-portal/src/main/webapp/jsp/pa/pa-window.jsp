<%--
  Created by IntelliJ IDEA.
  User: bqhuy
  Date: 11/8/2016
  Time: 11:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:include page="/jsp/common/datetime.jsp"/>
<html>
<head>
    <script src="http://code.jquery.com/jquery-git2.min.js"></script>
    <script src="http://code.highcharts.com/highcharts.js"></script>
    <script src="http://code.highcharts.com/modules/exporting.js"></script>
    <script src="http://code.highcharts.com/modules/offline-exporting.js"></script>
    <title>M2M BasicMeterig PA Chart</title>
    <style>
        .highcharts-tooltip span {
            width: 200px;
        }
    </style>
</head>
<body>
<div>
    <table width="100%" height="100%">
        <tr>
            <td width="15%" style="background-color: #94CDDD;" valign="top" align=center>
                <table width="90%">
                    <tr>
                        <td style="border-bottom: 1px solid #808080">
                            <h2 style="padding-top: 10px; font-family: 'Calibri'; margin-bottom: 5px;"
                                align=center>
                                Modules
                            </h2>
                        </td>
                    </tr>
                    <tr>
                        <td align=center>
                            <table style="padding-top: 10px;">
                                <tr>
                                    <td>
                                        <s:checkboxlist theme="vertical-checkbox"
                                                        id="chkModule"
                                                        name="modules"
                                                        list="{'ENR062A3','ENR04CA0','ENR077A9'}"
                                                        value="{'ENR062A3','ENR04CA0','ENR077A9'}"
                                                        onchange="changeModule(this)"
                                                />
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
            <td width="85%">
                <div id="container" style="min-width: 310px; height: 45%; margin: 0 auto;; margin-top:0px;"></div>
                <div id="container_subcat" style="min-width: 310px; height: 45%; margin: 0 auto; margin-top:10px;"
                     hidden="true"></div>
            </td>
        </tr>
    </table>
</div>
<script language="JavaScript" type="text/javascript">
var moduleHourlyCur;
$(document).ready(function () {
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });
    drawChartWeekly();
});

function drawChartWeekly() {
    var toDate = new Date(2016, 09, 24);
    var fromDate = new Date(2016, 09, 30);
    $.getJSON("getDataWeeky.action", function (res) {
        var data = convertDataWeeklyForChart(res);

        var chart = {
            type: 'column'
        };
        var title = {
            text: 'Weekly PA'
        };
        var subtitle = {
            text: createSubtitle(toDate, fromDate)
        };
        var yAxis = {
            min: 0,
            title: {
                text: 'Puissance appelée (kW)'
            },
            labels: {
                formatter: function () {
                    return this.value;
                }
            }
        };
        var tooltip = {
            headerFormat: '<span style="font-size:13px">{point.key:%a (%d/%m/%Y)}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.2f} kW</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        };
        var plotOptions = {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            },
            series: {
                cursor: 'pointer',
                point: {
                    events: {
                        click: function () {
                            drawChartDetail(this.category, this.series.name);
                            console.log(this.category + " " + this.series.name);
                        }
                    }
                }
            }
        };
        var credits = {
            enabled: false
        };

        var json = {};
        json.chart = chart;
        json.title = title;
        json.subtitle = subtitle;
        json.tooltip = tooltip;
        json.yAxis = yAxis;
        json.plotOptions = plotOptions;
        json.credits = credits;
        json.series = data.seriesData;
        json.xAxis = createXAxisForWeeky(data.xCategories);
        $('#container').highcharts(json);
    });
}

function createXAxisForWeeky(xCategories) {
    var xAxis = { categories: xCategories,
        type: 'datetime',
        labels: {
            formatter: function () {
                return formatDate(this.value);
            }
        }  };
    return xAxis;
}

function createXAxisForHourly() {
    var xAxis = { tickmarkPlacement: 'between',
        type: 'datetime',
        showLastLabel: false,
        labels: {
            formatter: function () {
                return formatHour(this.value)
            }
        },
        tickInterval: 60 * 60 * 1000
    };
    return xAxis;
}

function createSubtitle(toDate, fromDate) {
    return "(W" + toDate.getWeek() + " " + toDate.ddmmyyyy() + " - " + fromDate.ddmmyyyy() + ")";
}

function getColorOfSeries(moduleName) {
    if (moduleName == "ENR062A3") {
        return '#7DB5EC'
    } else {
        if (moduleName == "ENR04CA0") {
            return '#444449'
        } else {
            return '#91ED7E'
        }
    }
}

function convertDataWeeklyForChart(data) {
    var result = {};
    var seriesData = [];
    var xCategories = [];
    data = JSON.parse(data);
    if (data != null && data != '') {
        var i, cat;
        for (i = 0; i < data.length; i++) {
            cat = data[i].dt;
            if (xCategories.indexOf(cat) === -1) {
                xCategories[xCategories.length] = cat;
            }
        }
        for (i = 0; i < data.length; i++) {
            if (seriesData) {
                var currSeries = seriesData.filter(function (seriesObject) {
                    return seriesObject.name == data[i].moduleName;
                });
                if (currSeries.length === 0) {
                    currSeries = seriesData[seriesData.length] = {name: data[i].moduleName, color: getColorOfSeries(data[i].moduleName), data: []};
                } else {
                    currSeries = currSeries[0];
                }
                var index = currSeries.data.length;
                currSeries.data[index] = data[i].pa;
            } else {
                seriesData[0] = {name: data[i].moduleName, color: getColorOfSeries(data[i].moduleName), data: [data[i].pa]}
            }
        }
    }
    result.xCategories = xCategories;
    result.seriesData = seriesData;
    return result;
}

function formatDate(value) {
    var myDate = new Date(value);
//        console.log(value);
//        console.log(myDate);
//        console.log(Highcharts.dateFormat('%a', myDate, false));
    return Highcharts.dateFormat('%a', myDate);
}

function formatHour(value) {
    var myDate = new Date(value);
    return Highcharts.dateFormat('%H', myDate);
}

function drawChartDetail(category, name) {
    moduleHourlyCur = name;
    var object = {dt: category, module: name};
    $.getJSON("getDataDetail.action", object, function (res) {
        var data = convertDataDetailForChart(category, res);

        $('#container_subcat').show();
        $('#container_subcat').highcharts({
            chart: {
                zoomType: 'x'
            },
            title: {
                text: 'Hourly chart'
            },
            xAxis: createXAxisForHourly(category),
            yAxis: {
                min: 0,
                title: {
                    text: 'Puissance (kW)'
                }
            },
            legend: {
                align: 'right',
                verticalAlign: 'middle',
                layout: 'vertical'
            },
            tooltip: {
                headerFormat: '<span style="font-size:13px">{point.key:%a (%d/%m/%Y %H:%M)}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                        '<td style="padding:0"><b>{point.y:.2f} kW</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                series: {
                    pointPlacement: 'between',
                    connectNulls: true
                }
            },
            credits: {
                enabled: false
            },
            series: data.seriesData
        });
    });
}

function changeModule(module) {
    var moduleChange = module.value;
    var bChecked = module.checked;

    var chart = $('#container').highcharts();
    var seriesLength = chart.series.length;

    var bExist = false;
    var idx;
    for (var i = 0; i < seriesLength; i++) {
        if (chart.series[i].name == moduleChange) {
            idx = i;
            bExist = true;
        }
    }

    if (bChecked) {
        if (bExist) {
            chart.series[idx].show();
        } else {
            //Add series
            addSeriesWeekly(moduleChange, chart);
        }
    } else {
        if (bExist) {
            chart.series[idx].remove(true);
            if (moduleHourlyCur != null && moduleHourlyCur == moduleChange) {
                $('#container_subcat').hide();
            }
        }
    }


//    var checkboxes = document.getElementsByName('module');
//    var vals = "";
//    for (var i = 0, n = checkboxes.length; i < n; i++) {
//        if (checkboxes[i].checked) {
//            vals += "," + checkboxes[i].value;
//        }
//    }
//    vals += ",";
//    console.log(vals);
//    for (var i = seriesLength - 1; i > -1; i--) {
//        //chart.series[i].remove();
//        if (vals.indexOf("," + chart.series[i].name + ",") == -1) {
//            chart.series[i].remove();
//        } else {
//            chart.series[i].show();
//        }
//    }
}

function addSeriesWeekly(moduleName, chart) {
    var object = {module: moduleName};
    $.getJSON("getDataByDay.action", object, function (res) {
        var data = convertDataWeeklyForChart(res);
        chart.addSeries({
            data: data.seriesData[0].data,
            name: data.seriesData[0].name,
            color: getColorOfSeries(data.seriesData[0].name)
        });
    });
}

function convertDataDetailForChart(category, data) {
    var result = {};
    var seriesData = [];
    var dt1 = new Date(category);
    dt1.setHours(6);
    var dt2 = new Date(category);
    dt2.setHours(23);
    console.log(dt1 + " " + dt1.getTime())
    console.log(dt2 + " " + dt2.getTime())
    data = JSON.parse(data);
    if (data != null && data != '') {
        var i;
        for (i = 0; i < data.length; i++) {
            if (seriesData.length > 0) {
                var index = seriesData[0].data.length;
                seriesData[0].data[index] = [data[i].dt, data[i].pa];
                seriesData[1].data[index] = [data[i].dt, data[i].ps];
            } else {
                seriesData[0] = {name: 'PA', type: 'areaspline', color: '#9EC7F1',
                    zoneAxis: 'x',
                    zones: [
                        {
                            value: dt1.getTime()
                        },
                        {
                            value: dt2.getTime(),
                            fillColor: 'red'
                        }
                    ], data: [
                        [data[i].dt, data[i].pa]
                    ]};
                seriesData[1] = {name: 'PS', type: 'spline', color: '#F77C28', data: [
                    [data[i].dt, data[i].ps]
                ]}
            }
        }
    }
    result.seriesData = seriesData;
    return result;
}
</script>
</body>
</html>