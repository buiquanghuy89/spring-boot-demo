<%--
  Created by IntelliJ IDEA.
  User: bqhuy
  Date: 11/8/2016
  Time: 11:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="no-js">
<head>
    <script src="http://code.jquery.com/jquery-git2.min.js"></script>
    <script src="http://code.highcharts.com/highcharts.js"></script>
    <script src="http://code.highcharts.com/modules/exporting.js"></script>
</head>
<body>
<svg xmlns="http://www.w3.org/2000/svg" version="1.1" height="190">
    <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
    <div id="container_subcat" style="min-width: 310px; height: 400px; margin: 0 auto; margin-top:100px;"></div>
</svg>
<script language="JavaScript">
    $(function () {
        $('#container').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'Monthly Average Rainfall'
            },
            subtitle: {
                text: 'Source: WorldClimate.com'
            },
            xAxis: {
                categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun']
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Rainfall (mm)'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                        '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                },
                series: {
                    cursor: 'pointer',
                    point: {
                        events: {
                            click: function () {//alert ('Category: '+ this.category +', value: '+ this.y);
                                draw_cat_chart(this.category);
                            }
                        }
                    }
                }
            },
            series: [
                {
                    name: 'Tokyo',
                    data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0]

                },
                {
                    name: 'New York',
                    data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5]

                },
                {
                    name: 'London',
                    data: [48.9, 38.8, 39.3, 41.4, 47.0, 48.3]

                },
                {
                    name: 'Berlin',
                    data: [42.4, 33.2, 34.5, 39.7, 52.6, 75.5]

                }
            ]
        });
    });

    // calling function to draw new chart onclick.
    function draw_cat_chart(catagory) {
        if (catagory == 'Jan') {
            var cats = ['subcat1', 'subcat2', 'subcat3', 'subcat4', 'subcat5'];
            var plseries = [20, 40, 50, 45, 80];
            var acseries = [30, 60, 80, 35, 70];
        }
        if (catagory == 'Feb') {
            var cats = ['home1', 'home2', 'home3', 'home4', 'home5'];
            var plseries = [30, 20, 60, 55, 80];
            var acseries = [30, 60, 80, 35, 70];
        }


        //exit;
        $('#container_subcat').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'Monthly Average Rainfall'
            },
            xAxis: {
                categories: cats
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:8px">{point.key}</span><table>',
                pointFormat: '<tr style="border:none;"><td style="color:{series.color};padding:0;border:none;">{series.name}: </td>' +
                        '<td style="padding:0;border:none;"><b>{point.y:1f} </b></td></tr>',
                footerFormat: '</table>',
                shared: false,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0,
                    borderWidth: 0
                }
            },
            series: [
                {
                    name: 'Monthly',
                    data: plseries
                },
                {
                    name: 'Yearly',
                    data: acseries
                }
            ]
        });
    }

    function test() {
        var data = [
            {"moduleName": "ENR062A3", "dt": 1477155600000, "pa": 30.17, "ps": null, "count": 1, "bDetail": false},
            {"moduleName": "ENR062A3", "dt": 1477242000000, "pa": 1605.17, "ps": null, "count": 1, "bDetail": false},
            {"moduleName": "ENR062A3", "dt": 1477328400000, "pa": 1468.83, "ps": null, "count": 1, "bDetail": false},
            {"moduleName": "ENR062A3", "dt": 1477414800000, "pa": 1366.0, "ps": null, "count": 1, "bDetail": false},
            {"moduleName": "ENR062A3", "dt": 1477501200000, "pa": 1456.0, "ps": null, "count": 1, "bDetail": false},
            {"moduleName": "ENR062A3", "dt": 1477587600000, "pa": 1355.33, "ps": null, "count": 1, "bDetail": false},
            {"moduleName": "ENR062A3", "dt": 1477674000000, "pa": 961.5, "ps": null, "count": 1, "bDetail": false},
            {"moduleName": "ENR062A3", "dt": 1477760400000, "pa": 749.83, "ps": null, "count": 1, "bDetail": false},
            {"moduleName": "ENR04CA0", "dt": 1477155600000, "pa": 9.5, "ps": null, "count": 1, "bDetail": false},
            {"moduleName": "ENR04CA0", "dt": 1477242000000, "pa": 369.33, "ps": null, "count": 1, "bDetail": false},
            {"moduleName": "ENR04CA0", "dt": 1477328400000, "pa": 352.83, "ps": null, "count": 1, "bDetail": false},
            {"moduleName": "ENR04CA0", "dt": 1477414800000, "pa": 331.33, "ps": null, "count": 1, "bDetail": false},
            {"moduleName": "ENR04CA0", "dt": 1477501200000, "pa": 370.33, "ps": null, "count": 1, "bDetail": false},
            {"moduleName": "ENR04CA0", "dt": 1477587600000, "pa": 404.17, "ps": null, "count": 1, "bDetail": false},
            {"moduleName": "ENR04CA0", "dt": 1477674000000, "pa": 304.67, "ps": null, "count": 1, "bDetail": false},
            {"moduleName": "ENR04CA0", "dt": 1477760400000, "pa": 274.67, "ps": null, "count": 1, "bDetail": false},
            {"moduleName": "ENR077A9", "dt": 1477155600000, "pa": 90.33, "ps": null, "count": 1, "bDetail": false},
            {"moduleName": "ENR077A9", "dt": 1477242000000, "pa": 3125.0, "ps": null, "count": 1, "bDetail": false},
            {"moduleName": "ENR077A9", "dt": 1477328400000, "pa": 3153.67, "ps": null, "count": 1, "bDetail": false},
            {"moduleName": "ENR077A9", "dt": 1477414800000, "pa": 3046.67, "ps": null, "count": 1, "bDetail": false},
            {"moduleName": "ENR077A9", "dt": 1477501200000, "pa": 3056.83, "ps": null, "count": 1, "bDetail": false},
            {"moduleName": "ENR077A9", "dt": 1477587600000, "pa": 3022.0, "ps": null, "count": 1, "bDetail": false},
            {"moduleName": "ENR077A9", "dt": 1477674000000, "pa": 2302.0, "ps": null, "count": 1, "bDetail": false},
            {"moduleName": "ENR077A9", "dt": 1477760400000, "pa": 2061.58, "ps": null, "count": 1, "bDetail": false}
        ];
        var seriesData = [];
        var xCategories = [];
        var i, cat;
        for (i = 0; i < data.length; i++) {
            cat = data[i].moduleName;
            if (xCategories.indexOf(cat) === -1) {
                xCategories[xCategories.length] = cat;
            }
        }
        for (i = 0; i < data.length; i++) {
            if (seriesData) {
                var currSeries = seriesData.filter(function (seriesObject) {
                    return seriesObject.name == data[i].dt;
                });
                if (currSeries.length === 0) {
                    currSeries = seriesData[seriesData.length] = {name: data[i].dt, data: []};
                } else {
                    currSeries = currSeries[0];
                }
                var index = currSeries.data.length;
                currSeries.data[index] = data[i].pa;
            } else {
                seriesData[0] = {name: data[i].dt, data: [data[i].pa]}
            }
        }
    }
</script>
</body>
</html>