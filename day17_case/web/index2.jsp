<%--
  Created by IntelliJ IDEA.
  User: TXX
  Date: 2022/11/7
  Time: 10:09
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
    <title>分页后的数据</title>

    <!-- 1.加载全局css文件 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2.jQuery导入，建议使用1.9以上的版本-->
    <script src="js/jquery-3.2.1.min.js"></script>
    <!-- 3.导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>

    <script type="text/javascript">
    </script>

</head>
<body>
<%--<div align = "center" style = "font-size: 25px">${user.name},欢迎您~~~</div>--%>
<div align = "center">
    <a href = "${pageContext.request.contextPath}/findUserByPageServlet?currentPage=1&rows=5" style = "text-decoration: none;font-size: 33px">
        查询所有用户信息(分页效果)
    </a>
</div>
</body>
</html>
