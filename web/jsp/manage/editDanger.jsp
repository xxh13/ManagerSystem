<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cristph
  Date: 2016/11/17
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
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

    <title>ManageSystem | 输入风险条目</title>

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
            <a class="navbar-brand" href="#">ManageSystem</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
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
                <a href="${pageContext.request.contextPath}/editRiskPlan"><div class="setting-left">风险计划</div></a>
            </div>

            <div class="col-sm-10 frame-middle">

                <div class="title-box">追踪风险条目</div>

                <div class="white-box">

                    <form class="form-horizontal form-wrapper"  style="padding-right: 6%;padding-top: 1%" accept-charset="utf-8">

                        <div class="form-group form-line">
                            <label for="content" class="col-sm-2 control-label">风险内容：</label>
                            <div class="col-sm-10">
                                <textarea class="form-control from-input" id="content" name="content" rows="3" readonly>${danger.content}</textarea>
                            </div>
                        </div>

                        <div class="form-group form-line">
                            <label for="possiblity" class="col-sm-2 control-label">可能性：</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control from-input" id="possiblity" name="possiblity" rows="3" value="${danger.possibility}" readonly>
                            </div>
                        </div>

                        <div class="form-group form-line">
                            <label for="effect" class="col-sm-2 control-label">影响程度：</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control from-input" id="effect" name="effect" rows="3" value="${danger.effect}" readonly>
                            </div>
                        </div>

                        <div class="form-group form-line">
                            <label for="threshold" class="col-sm-2 control-label">触发器/阈值：</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control from-input" id="threshold" name="threshold" value="${danger.threshold}" readonly>
                            </div>
                        </div>

                        <div class="form-group form-line">
                            <label for="poster" class="col-sm-2 control-label">提交者：</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control from-input" id="poster" name="poster" value="${danger.poster}" readonly>
                            </div>
                        </div>
                        <div class="form-group form-line">
                            <label for="conditiondescription" class="col-sm-2 control-label">状态描述：</label>
                            <div class="col-sm-10">
                                <c:choose>
                                    <c:when test="${danger.condition=='风险'}">
                                        <label class="radio-inline">
                                            <input type="radio" name="conditiondescription" value="1" checked="true"> 风险
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="conditiondescription"  value="2"> 问题
                                        </label>
                                    </c:when>
                                    <c:otherwise>
                                        <label class="radio-inline">
                                            <input type="radio" name="conditiondescription" value="1"> 风险
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="conditiondescription"  value="2" checked="true"> 问题
                                        </label>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                        </div>

                        <div class="form-group form-line">
                            <label for="description" class="col-sm-2 control-label">描述信息：</label>
                            <div class="col-sm-10">
                                <textarea class="form-control from-input" id="description" name="description" rows="3">${danger.description}</textarea>
                            </div>
                        </div>


                        <div class="form-group form-line">
                            <div class="col-sm-offset-2 col-sm-10">
                                <input type="hidden" id="did" value="${danger.did}" />
                                <input type="button"  value="保存改动" class="btn btn-block post-btn" id="createwaiter" onclick="update()">
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
<script>
    function update(){
        var did=document.getElementById("did").value;

        var str=new Array('风险','问题');
        var conditiondescription='';
        var obj=document.getElementsByName("conditiondescription");
        for(var i=0;i<obj.length;i++) {
            if(obj[i].checked){
                conditiondescription=str[i];
            }
        }

        var desccription=document.getElementById("description").value;

        var rid=document.getElementById("rid").value;

        $.post(
                "/ManageSystem/danger/update",
                {   "did":did,
                    "condition":conditiondescription,
                    "description":desccription,
                    "rid":rid
                },
                function(data){

                    if(data=="success"){

                        alert("追踪风险条目成功！");
                        location.href="/ManageSystem/view/editRiskPlan?rid="+rid;
                    }else{
                        alert("未知错误，请重试！");
                    }
                });
    }
</script>
</body>
</html>
