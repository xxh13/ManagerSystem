<%--
  Created by IntelliJ IDEA.
  User: cristph
  Date: 2016/11/16
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="Ease Project">
    <meta name="author" content="cristph">
    <link rel="icon" href="../../favicon.ico">

    <title>Dessert | 对比分析</title>

    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/reset.css" rel="stylesheet">
    <link href="../css/store.css" rel="stylesheet">
    <link href="../css/usersetting.css" rel="stylesheet">
    <link href="../css/buttons.css" rel="stylesheet">
    <link href="../css/footer.css" rel="stylesheet">
</head>

<body>

<nav class="navbar navbar-fixed-top navbar-custom">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Dessert</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/logout">登出</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="outer-frame">
    <div class="container inner-frame">
        <div class="row main-frame">

            <%--<div class="col-sm-1 frame-left">--%>
                <%--<a href="${pageContext.request.contextPath}/riskAnalyse"><div class="setting-left">风险库统计</div></a>--%>
            <%--</div>--%>

            <div class="col-sm-11 frame-middle">
                <div class="title-box">风险库统计</div>
                <div class="white-box" style="padding: 5%">
                    <form class="form-horizontal form-wrapper"  style="padding:0" accept-charset="utf-8">
                        <div style="min-height: 15px"></div>
                        <div class="bs-example">
                            <ul class="nav nav-pills" role="tablist">
                                <li id="ApplyHistogramFilter" role="presentation" class="active">
                                    <a href="javascript:lookup()"  style="background-color: #4cb0f9;border-color: #4cb0f9;color: #FFF;border-radius: 0;padding: 10px 20px 10px 20px;font-weight: bolder">查询</a>
                                </li>
                                <li role="presentation" class="dropdown">
                                    <div style="padding-top: 5px;font-weight: bolder;font-size: medium;margin-left: 20px;margin-right: 5px">开始于</div>
                                </li>
                                <li role="presentation" class="dropdown">
                                   <input type="date" class="form-control" value="2016-11-15" id="startdate"/>
                                </li>
                                <li role="presentation" class="dropdown">
                                    <div style="padding-top: 5px;font-weight: bolder;font-size: medium;margin-left: 20px;margin-right: 5px">结束于</div>
                                </li>
                                <li role="presentation" class="dropdown">
                                    <input type="date" class="form-control" value="2016-11-16" id="enddate"/>
                                </li>
                            </ul> <!-- /pills -->
                        </div>
                        <div style="min-height: 50px"></div>
                        <div id="histogramChart" style="width: 800px;height:400px;"></div>
                        <div style="min-height: 100px"></div>
                        <div id="pieChart1" style="width: 800px;height:400px;"></div>
                        <div style="min-height: 100px"></div>
                        <div id="pieChart2" style="width: 800px;height:400px;"></div>
                    </form>
                </div>

            </div>

        </div>
    </div>
</div>

<footer class="footer">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 text-center">
                <a href="/">Home</a>
                <a href="/terms" target="_blank">Terms</a>
                <a href="/privacy" target="_blank">Privacy</a>
                <a href="https://twitter.com/wiplohq" target="_blank" rel="nofollow">Twitter</a>
                <a href="mailto:team@wiplo.com?subject=Hi Wiplo Team!" rel="nofollow">Contact</a>
                <small>Architect @ Cristph</small>
            </div>
        </div>
    </div>
</footer>


<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="../js/echarts.min.js"></script>
<script>

    function lookup(){
//        alert('hh');
        var startdate=document.getElementById("startdate").value;
        var enddate=document.getElementById("enddate").value;


//        alert(startdate+"|"+enddate);

        $.post(
                "/ManageSystem/danger/analyseDangerByTime",
                {
                    "date1":startdate,
                    "date2":enddate
                },
                function (data) {
                    var set=$.parseJSON(data);
                    var data1=set.data1;
                    var data2=set.data2;
                    var data3=set.data3;

//                    alert("data1="+data1);
//                    alert("data2="+data2);
//                    alert("data3="+data3);
                    //直方图
                    var histogramChart = echarts.init(document.getElementById('histogramChart'));
                    histogramChart.setOption({
                        title : {
                            text: '风险识别次数与演变成问题数'
                        },
                        tooltip : {
                            trigger: 'axis'
                        },
                        legend: {
                            data:['识别次数','演变成问题数']
                        },
                        toolbox: {
                            show : true,
                            feature : {
                                dataView : {show: true, readOnly: false},
                                magicType : {show: true, type: ['line', 'bar']},
                                restore : {show: true},
                                saveAsImage : {show: true}
                            }
                        },
                        calculable : true,
                        xAxis : [
                            {
                                type : 'category',
                                data : data1
                            }
                        ],
                        yAxis : [
                            {
                                type : 'value'
                            }
                        ],
                        series : [
                            {
                                name:'识别次数',
                                type:'bar',
                                data:data2,
                                markPoint : {
                                    data : [
                                        {type : 'max', name: '最多'},
                                        {type : 'min', name: '最少'}
                                    ]
                                },
                                markLine : {
                                    data : [
                                        {type : 'average', name: '平均值'}
                                    ]
                                }
                            },
                            {
                                name:'演变成问题数',
                                type:'bar',
                                data:data3,
                                markPoint : {
                                    data : [
                                        {name : '最多', value : 182.2, xAxis: 7, yAxis: 183},
                                        {name : '最少', value : 2.3, xAxis: 11, yAxis: 3}
                                    ]
                                },
                                markLine : {
                                    data : [
                                        {type : 'average', name : '平均值'}
                                    ]
                                }
                            }
                        ]
                    });


                    var data4=[];
                    for(i=0;i<data1.length;i++){
                        var unit = {};
                        unit["name"] = data1[i];
                        unit["value"] = data2[i];
                        data4.push(unit);
                    }
                    //饼图
                    var piechart1=echarts.init(document.getElementById('pieChart1'));
                    piechart1.setOption(
                            {
                                title : {
                                    text: '识别的风险来源占比',
                                    x:'center'
                                },
                                tooltip : {
                                    trigger: 'item',
                                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                                },
                                legend: {
                                    orient: 'vertical',
                                    left: 'left',
                                    data: data1
                                },
                                series : [
                                    {
                                        name: '风险来源',
                                        type: 'pie',
                                        radius : '55%',
                                        center: ['50%', '60%'],
                                        data:data4,
                                        itemStyle: {
                                            emphasis: {
                                                shadowBlur: 10,
                                                shadowOffsetX: 0,
                                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                                            }
                                        }
                                    }
                                ]
                            }
                    );


                    var data5=[];
                    for(i=0;i<data1.length;i++){
                        var unit = {};
                        unit["name"] = data1[i];
                        unit["value"] = data3[i];
                        data5.push(unit);
                    }
                    //饼图
                    var piechart2=echarts.init(document.getElementById('pieChart2'));
                    piechart2.setOption(
                            {
                                title : {
                                    text: '演变成问题数的风险来源占比',
                                    x:'center'
                                },
                                tooltip : {
                                    trigger: 'item',
                                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                                },
                                legend: {
                                    orient: 'vertical',
                                    left: 'left',
                                    data: data1
                                },
                                series : [
                                    {
                                        name: '风险来源',
                                        type: 'pie',
                                        radius : '55%',
                                        center: ['50%', '60%'],
                                        data:data5,
                                        itemStyle: {
                                            emphasis: {
                                                shadowBlur: 10,
                                                shadowOffsetX: 0,
                                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                                            }
                                        }
                                    }
                                ]
                            }
                    );



                }
        );
//        location.href="/danger/analyseDangerByTime?date1="+startdate+"&date2="+enddate;
    }

    //直方图
    var histogramChart = echarts.init(document.getElementById('histogramChart'));
    histogramChart.setOption({
        title : {
            text: '风险识别次数与演变成问题数'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['识别次数','演变成问题数']
        },
        toolbox: {
            show : true,
            feature : {
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : ['风险1','风险2','风险3','风险4','风险5','风险6','风险7','风险8','风险9','风险10','风险11','风险12']
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'识别次数',
                type:'bar',
                data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
                markPoint : {
                    data : [
                        {type : 'max', name: '最多'},
                        {type : 'min', name: '最少'}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
            },
            {
                name:'演变成问题数',
                type:'bar',
                data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
                markPoint : {
                    data : [
                        {name : '最多', value : 182.2, xAxis: 7, yAxis: 183},
                        {name : '最少', value : 2.3, xAxis: 11, yAxis: 3}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name : '平均值'}
                    ]
                }
            }
        ]
    });



    //饼图
    var piechart1=echarts.init(document.getElementById('pieChart1'));
    piechart1.setOption(
            {
                title : {
                    text: '识别的风险来源占比',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['风险1','风险2','风险3','风险4','风险5']
                },
                series : [
                    {
                        name: '风险来源',
                        type: 'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:[
                            {value:335, name:'风险1'},
                            {value:310, name:'风险2'},
                            {value:234, name:'风险3'},
                            {value:135, name:'风险4'},
                            {value:1548, name:'风险5'}
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            }
    );

    //饼图
    var piechart2=echarts.init(document.getElementById('pieChart2'));
    piechart2.setOption(
            {
                title : {
                    text: '演变成问题数的风险来源占比',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['风险1','风险2','风险3','风险4','风险5']
                },
                series : [
                    {
                        name: '风险来源',
                        type: 'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:[
                            {value:335, name:'风险1'},
                            {value:310, name:'风险2'},
                            {value:234, name:'风险3'},
                            {value:135, name:'风险4'},
                            {value:1548, name:'风险5'}
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            }
    );
</script>
</body>
</html>
</body>
</html>