<%--
  Created by IntelliJ IDEA.
  User: TXX
  Date: 2022/11/8
  Time: 11:20
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
    <title>用户修改页面</title>

    <!-- 1.加载全局css文件 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2.jQuery导入，建议使用1.9以上的版本-->
    <script src="js/jquery-3.2.1.min.js"></script>
    <!-- 3.导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script>

    </script>
</head>
<body>
    <div class = "container">
        <h3 style = "..." align = "center">用户修改页面</h3>
        <form action = "${pageContext.request.contextPath}/updateUserServlet" method = "post">
<%--            隐藏域提交id,用来锁定唯一记录,定向修改--%>
            <input type = "hidden" name = "id" value = "${user.id}"/>
            <div class = "form-group">
                <label for = "name">姓名:</label>
                <input type = "text" class = "form-control" id = "name" value = "${user.name}" name = "name" placeholder="请输入姓名"/>
            </div>

            <div class = "form-group">
                <label>性别:</label>
                <c:if test = "${user.gender == '男'}">
                    <label>
                        <input type = "radio"  name = "gender" value = "男" checked/>
                    </label>男
                    <label>
                        <input type = "radio"  name = "gender" value = "女" />
                    </label>女
                </c:if>
                <c:if test = "${user.gender == '女'}">
                    <label>
                        <input type = "radio"  name = "gender" value = "男" />
                    </label>男
                    <label>
                        <input type = "radio"  name = "gender" value = "女" checked/>
                    </label>女
                </c:if>
            </div>

            <div class = "form-group">
                <label for = "age">年龄:</label>
                <input type = "text" class = "form-control" id = "age" value = "${user.age}" name = "age" placeholder="请输入年龄"/>
            </div>

            <div class = "form-group">
                <label for="address">籍贯:</label>
                <select id = "address" name = "address" class = "form-control">
                    <c:if test="${user.address == '陕西'}">
                        <option value = "陕西" selected>陕西</option>
                        <option value="北京">北京</option>
                        <option value="上海">上海</option>
                    </c:if>
                    <c:if test="${user.address == '北京'}">
                        <option value = "陕西" >陕西</option>
                        <option value="北京" selected>北京</option>
                        <option value="上海">上海</option>
                    </c:if>
                    <c:if test="${user.address == '上海'}">
                        <option value = "陕西" >陕西</option>
                        <option value="北京">北京</option>
                        <option value="上海" selected>上海</option>
                    </c:if>
                </select>
            </div>

            <div class = "form-group">
                <label for = "qq">QQ:</label>
                <input type = "text" class = "form-control" id = "qq" value = "${user.qq}" name = "qq" placeholder="请输入QQ号码"/>
            </div>

            <div class = "form-group">
                <label for = "email">Email:</label>
                <input type = "email" class = "form-control" id = "email" value = "${user.email}" name = "email" placeholder="请输入邮箱地址"/>
            </div>

            <div class = "form-group" style = "..." align = "center">
                <input type = "submit" class = "btn btn-primary" value = "提交"/>
                <input type = "reset" class = "btn btn-primary" value = "重置"/>
                <input type = "button" class = "btn btn-primary" onclick="javascript:window.history.go(-1)" value = "返回"/>
            </div>
        </form>
    </div>
</body>
</html>
