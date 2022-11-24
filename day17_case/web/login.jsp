<%--
  Created by IntelliJ IDEA.
  User: TXX
  Date: 2022/11/7
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>管理员登录</title>

    <!-- 1.加载全局css文件 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2.jQuery导入，建议使用1.9以上的版本-->
    <script src="js/jquery-3.2.1.min.js"></script>
    <!-- 3.导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>

    <!--    验证码相关操作-->
    <script type = "text/javascript">
        function refreshCode() {
            //刷新验证码
            /*
                1.获取验证码图片对象
                2.设置其src
             */
            let vCode = document.getElementById('vCode');
            vCode.src = "${pageContext.request.contextPath}/checkCodeServlet?time=" + new Date().getTime();
        }

    </script>
</head>
<body>
<div class = "container" style = "width: 400px">
    <h3 style = "text-align: center;">管理员登录</h3>
    <form action = "${pageContext.request.contextPath}/loginServlet" method = "post">
        <div class = "form-group">
            <label for = "user">用户名:</label>
            <input type = "text" name = "username" class = "form-control" id = "user" placeholder="请输入用户名">
        </div>
        <div class = "form-group">
            <label for = "password">密码:</label>
            <input type = "password" name = "password" class = "form-control" id = "password" placeholder="请输入密码">
        </div>
        <div class = "form-inline">
            <label for = "verifyCode">验证码:</label>
            <input type="text" name = "verifyCode" class = "form-control" id = "verifyCode" maxlength="4" placeholder="请输入验证码">
            <a href = "javascript:refreshCode();">
                <img src = "${pageContext.request.contextPath}/checkCodeServlet" title = "看不清点击刷新" id="vCode"/>
            </a>
        </div>
        <hr/>
        <div class = "form-group" style = "text-align: center;">
            <input type = "submit" class = "btn btn btn-primary" value = "登录">
        </div>
    </form>
<%--    出错显示的信息框--%>
    <div class = "alert alert-warning alert-dismissible" role = "alert">
        <button type = "button" class = "close" data-dismiss = "alert">
            <span>x</span>
        </button>
        <strong>${login_msg}</strong>
    </div>
</div>
</body>
</html>