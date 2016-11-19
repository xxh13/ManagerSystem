<%--
  Created by IntelliJ IDEA.
  User: cristph
  Date: 2016/11/17
  Time: 9:48
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

    <title>ManageSystem | 风险条目列表</title>

    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/reset.css" rel="stylesheet">
    <link href="../../css/store.css" rel="stylesheet">
    <link href="../../css/usersetting.css" rel="stylesheet">
    <link href="../../css/buttons.css" rel="stylesheet">
    <link href="../../css/footer.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../../css/sweetalert.css">
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
            <a class="navbar-brand" href="#">ManageSystem</a>
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

            <div class="col-sm-11 frame-middle">
                <div class="title-box">

                    风险计划——[${rid} ]：
                    <a href="${pageContext.request.contextPath}/riskPlan" class="button button-primary button-rounded button-small" style="display: block;float: right;margin-right: 10px">返回</a>
                </div>
                <div class="white-box">
                    <form class="form-horizontal form-wrapper"  style="padding-right: 6%;padding-top: 1%" accept-charset="utf-8">
                        <p style="color: black;font-size: 15px;font-weight: bolder">风险条目列表：</p>
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>内容</th>
                                <th>可能性</th>
                                <th>影响程度</th>
                                <th>触发器/阈值</th>
                                <th>提交者</th>
                                <th>状态描述</th>
                                <th>描述信息</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:choose>
                                <c:when test="${dangerList.size()==0}">
                                    <tr>
                                        <td style="font-weight: bolder;color: black">暂无风险条目！</td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${dangerList}" var="item" varStatus="i">
                                        <tr>
                                            <td style="width: 35%">${item.content}</td>
                                            <td>${item.possibility}</td>
                                            <td>${item.effect}</td>
                                            <td>${item.threshold}</td>
                                            <td>${item.poster}</td>
                                            <td>${item.condition}</td>
                                            <td>${item.description}</td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/view/editDanger?did=${item.did}" class="button button-primary button-rounded button-small" style="display: block;margin-bottom: 10px;padding: 0 15px 0 15px">编辑</a>
                                                <a href="javascript:del(${item.did})" class="button button-primary button-rounded button-small button-caution" style="display: block;margin-bottom: 10px;padding: 0 15px 0 15px">删除</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>

                            </tbody>
                        </table>

                        <div class="form-group form-line">
                            <div class="col-sm-10">
                                <a href="${pageContext.request.contextPath}/view/addDanger?rid=${rid}" class="button button-uppercase button-primary">增加风险条目</a>
                                <input type="hidden"  value="${rid}" id="rid">
                                <input type="hidden" name="_token" value="{{ csrf_token() }}" />
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
<script src="../../js/sweetalert.min.js"></script>
<script>

    function del(id){
        swal({
                    title: "Are you sure?",
                    text: "You will not be able to recover this imaginary file!",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Yes, delete it!",
                    closeOnConfirm: false
                },
                function(){
                    var rid=document.getElementById("rid").value;
//                    alert(id);
                    $.post(
                            "/ManageSystem/danger/del",
                            {
                                "did":id
                            },
                            function(data){
//                                alert(data);
                                if(data=="success"){
                                    swal("Deleted!", "Your imaginary file has been deleted.", "success");
                                    location.href="/ManageSystem/view/editRiskPlan?rid="+rid;
                                }
                            }
                    );

                });
    }
</script>
</body>
</html>