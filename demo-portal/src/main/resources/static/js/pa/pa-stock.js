/**
 * Created by bqhuy on 6/18/2018.
 */
var moduleHourlyCur;
$(document).ready(function () {
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });
    fillModuleId();
//    $(".app-wrapper").not("#chart-detail-container").mouseup(function(e){
    $(".app-wrapper *:not(#chart-detail-container)").mouseup(function (e) {
        var container = $("#chart-detail-container");
        if (container.length > 0) {
            if (!container.is(e.target) && container.has(e.target).length === 0) {
                container.fadeOut("fast");
            }
        }
    })
});

function fillModuleId() {
    $.ajax({
        url: "pa/getModuleIds",
        dataType: "json",
        type: "GET",
        async: true,
        contentType: "application/json; charset=utf-8",
        success: function (data, textStatus, jqXHR) {
            if (data) {
                var $element = $("#checklist-module-id");
                data.forEach(function (moduleId) {
                    var html = '<div class="checklist-item"><div class="toggle-checkbox"><div class="checkbox-input">'
                        + '<input type="checkbox" class="checkbox-square" name="' + moduleId + '" id="checkbox-' + moduleId + '" checked>'
                        + '<label class="lbl-square"></label></div></div><p>' + moduleId + '</p></div>';
                    var $checkbox = $(html).appendTo($element).find("input");
                    $checkbox.attr('value', moduleId);
                });
                $(".checkbox-square").change(function () {
                    changeModule(this)
                });
                drawChartWeekly();
            }
        }
    });
}

function drawChartWeekly() {
    var fromDate = new Date(2016, 09, 24);
    var toDate = new Date(2016, 09, 30);
    $.getJSON("pa/getAllDataByDay?fromDate=2016/10/24&toDate=2016/10/30", function (res) {
        var data = convertDataWeeklyForChart(res);

        var chart = {
            type: 'column'
        };
        var title = {
            text: 'Weekly PA'
        };
        var subtitle = {
            text: createSubtitle(fromDate, toDate)
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
            pointFormat: '<tr><td style="color:{series.color};font-size:13px">{series.name}: </td>' +
                '<td style="text-align: right;font-size:13px"><b>{point.y:.2f} kW</b></td></tr>',
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
        $('#chart-total-container').highcharts(json);
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

function createXAxisForHourly1() {
    var xAxis = { tickmarkPlacement: 'on',
        type: 'datetime',
        labels: {
            formatter: function () {
                return formatHour(this.value)
            }
        },
        tickInterval: 60 * 60 * 1000
    };
    return xAxis;
}

function createXAxisForHourly(xCategories) {
    var xAxis = { categories: xCategories,
        tickmarkPlacement: 'on',
        type: 'datetime',
        labels: {
            formatter: function () {
                return formatHour(this.value)
            }
        },
        tickInterval: 60 * 60 * 1000
    };
    return xAxis;
}

function createSubtitle(fromDate, toDate) {
    return "(W" + fromDate.getWeek() + " " + fromDate.ddmmyyyy() + " - " + toDate.ddmmyyyy() + ")";
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
//    data = JSON.parse(data);
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
    $("#chart-detail-container").fadeIn("fast");
    moduleHourlyCur = name;
    var object = {dt: category, moduleName: name};
    $.getJSON("pa/getDataDetail", object, function (res) {
        var data = convertDataDetailForChart1(res);

        $('#show-chart-detail-container').show();
        $('#show-chart-detail-container').highcharts({
            chart: {
                zoomType: 'x'
            },
            title: {
                text: 'Hourly chart'
            },
            xAxis: createXAxisForHourly1(),
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
                spline: {
                    pointPlacement: 'between'
                },
                areaspline: {
                    pointPlacement: 'between'
                }
            },
            series: data.seriesData
        });
    });
}

function changeModule(module) {
    var moduleChange = module.name;
    var bChecked = module.checked;

    var chart = $('#chart-total-container').highcharts();
    if (chart) {
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
                addSeriesWeekyly(moduleChange, chart);
            }
        } else {
            if (bExist) {
                chart.series[idx].remove(true);
                if (moduleHourlyCur != null && moduleHourlyCur == moduleChange) {
                    $('#chart-detail-container').hide();
                }
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

function addSeriesWeekyly(moduleName, chart) {
    var object = {moduleName: moduleName, fromDate: "2016/10/24", toDate: "2016/10/30"};
    $.getJSON("pa/getDataByDay", object, function (res) {
        var data = convertDataWeeklyForChart(res);
        chart.addSeries({
            data: data.seriesData[0].data,
            name: data.seriesData[0].name,
            color: getColorOfSeries(data.seriesData[0].name)
        });
    });
}

function convertDataDetailForChart(data) {
    var result = {};
    data = JSON.parse(data);
    if (data != null && data != '') {
        var seriesData = [];
        var xCategories = [];
        var i, cat;
        for (i = 0; i < data.length; i++) {
            cat = data[i].dt;
            if (xCategories.indexOf(cat) === -1) {
                xCategories[xCategories.length] = cat;
            }
        }
        for (i = 0; i < data.length; i++) {
            if (seriesData.length > 0) {
                var index = seriesData[0].data.length;
                seriesData[0].data[index] = data[i].pa;
                seriesData[1].data[index] = data[i].ps;
            } else {
                seriesData[0] = {name: 'PA', type: 'areaspline', color: '#6495ED', data: [data[i].pa]};
                seriesData[1] = {name: 'PS', type: 'spline', color: '#FFA500', data: [data[i].ps]}
            }
        }
        result.xCategories = xCategories;
        result.seriesData = seriesData;
    }
    return result;
}

function convertDataDetailForChart1(data) {
    var result = {};
    var seriesData = [];
//    data = JSON.parse(data);
    if (data != null && data != '') {
        var i;
        for (i = 0; i < data.length; i++) {
            if (seriesData.length > 0) {
                var index = seriesData[0].data.length;
                seriesData[0].data[index] = [data[i].dt, data[i].pa];
                seriesData[1].data[index] = [data[i].dt, data[i].ps];
            } else {
                seriesData[0] = {name: 'PA', type: 'areaspline', color: '#9EC7F1', data: [
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
