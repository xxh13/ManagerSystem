<%--
  Created by IntelliJ IDEA.
  User: cristph
  Date: 2016/11/17
  Time: 11:51
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
    <link href="css/reset.css" rel="stylesheet">
    <link href="css/store.css" rel="stylesheet">
    <link href="css/usersetting.css" rel="stylesheet">
    <link href="css/buttons.css" rel="stylesheet">
    <link href="css/footer.css" rel="stylesheet">
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

            <div class="col-sm-1 frame-left">
                <a href="${pageContext.request.contextPath}/riskAnalyse"><div class="setting-left">风险库统计</div></a>
            </div>

            <div class="col-sm-11 frame-middle">
                <div class="title-box">风险查询</div>
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
                                    <div style="padding-top: 5px;font-weight: bolder;font-size: medium;margin-left: 20px;margin-right: 5px" >结束于</div>
                                </li>
                                <li role="presentation" class="dropdown">
                                    <input type="date" class="form-control" value="2016-11-16" id="enddate"/>
                                </li>
                            </ul> <!-- /pills -->
                        </div>
                        <div style="min-height: 50px"></div>
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>内容</th>
                                <th>可能性</th>
                                <th>影响程度</th>
                                <th>触发器/阈值</th>
                                <th>提交者</th>
                                <th>追踪者</th>
                                <th>状态描述</th>
                                <th>描述信息</th>
                                <th>导入选中</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:choose>
                                <c:when test="${dangerList.size()==0}">
                                    <tr>
                                        <td style="font-weight: bolder;color: black">暂无结果</td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${dangerList}" var="item" varStatus="i">
                                        <tr>
                                            <td style="width: 35%">${item.content}</td>
                                            <td>${item.possiblity}</td>
                                            <td>${item.effect}</td>
                                            <td>${item.threshold}</td>
                                            <td>${item.poster}</td>
                                            <td>${item.tracer}</td>
                                            <td>${item.conditiondescription}</td>
                                            <td>${item.description}</td>
                                            <td>
                                                <div  style="text-align: center">
                                                    <input name="import" type="checkbox" value="${item.did}" />
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>

                            </tbody>
                        </table>

                        <div class="form-group form-line">
                            <div class="col-sm-10">
                                <a href="javascript:startModal()" class="button button-uppercase button-primary">导入至风险计划</a>
                                <input type="hidden" name="_token" value="{{ csrf_token() }}" />
                            </div>
                        </div>

                        <!-- Modal -->
                        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                        <h4 class="modal-title" id="myModalLabel">请输入风险计划名称</h4>
                                    </div>
                                    <div class="modal-body">
                                        <input type="text" class="form-control" id="importRiskPlanName">
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                        <button type="button" class="btn btn-primary" onclick="javascript:importDanger()">导入至该计划</button>
                                    </div>
                                </div>
                            </div>
                        </div>

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
<script src="js/echarts.min.js"></script>
<script>

    function startModal(){
        $('#myModal').modal();
    }

    function importDanger(){
        var str="";
        $(":checkbox[name='import']").each(function(){
            if($(this).is(":checked")){
                str=str+$(this).val()+";"
            }
        });
        var importRiskPlanName=document.getElementById("importRiskPlanName").value;
        alert(importRiskPlanName+"|"+str);
        $.post(
                "/importDanger",
                {
                    "dangerList":str,
                    "riskPlanName":importRiskPlanName
                },
                function(data){
                    location.href="/editRiskPlan?rid="+data;
                }
        );
    }

    function lookup(){
        var startdate=document.getElementById("startdate").value;
        var enddate=document.getElementById("enddate").value;
        alert(startdate+"|"+enddate);
        location.href="/dangerLookUp?startdate="+startdate+"&enddate="+enddate;
    }

</script>
</body>
</html>
</body>
</html>