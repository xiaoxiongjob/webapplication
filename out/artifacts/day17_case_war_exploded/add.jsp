<%--
  Created by IntelliJ IDEA.
  User: TXX
  Date: 2022/11/8
  Time: 8:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加联系人</title>

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
        <h3 style = "..." align = "center">添加联系人页面</h3>
        <form action = "${pageContext.request.contextPath}/addUserServlet" method = "post">
            <div class = "form-group">
                <label for = "name">姓名:</label>
                <input type = "text" id = "name" name = "name" placeholder="请输入姓名" class = "form-control"/>
            </div>

            <div class = "form-group">
                <label>性别:</label>
                <label>
                    <input type = "radio" name = "gender" value = "男" checked = "checked"/>
                </label>男
                <label>
                    <input type = "radio" name = "gender" value = "女"/>
                </label>女
            </div>

            <div class = "form-group">
                <label for = "age">年龄:</label>
                <input type = "text" id = "age" name = "age" placeholder="请输入年龄" class = "form-control"/>
            </div>

            <div class = "form-group">
                <label for = "address">籍贯:</label>
                <select id = "address" name = "address" class = "form-control">
                    <option value = "陕西">陕西</option>
                    <option value = "北京">北京</option>
                    <option value = "上海">上海</option>
                </select>
            </div>

            <div class = "form-group">
                <label for = "qq">QQ:</label>
                <input type = "text" id = "qq" name = "qq" placeholder="请输入QQ号码" class = "form-control"/>
            </div>

            <div class = "form-group">
                <label for = "email">邮箱:</label>
                <input type = "email" class = "form-control" id = "email" name = "email" placeholder="请输入邮箱地址"/>
            </div>

            <div class = "form-group" style = "..." align = "center">
                <input type = "submit" class = "btn btn-primary" value = "提交"/>
                <input type = "reset" class = "btn btn-primary" value = "重置"/>
                <input type = "button" class = "btn btn-primary" value = "返回"/>
            </div>
        </form>
    </div>
</body>
</html>
