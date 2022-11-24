<jsp:useBean id="pb" scope="request" type="cn.itcast.domain.PageBean"/>
<%--
  Created by IntelliJ IDEA.
  User: TXX
  Date: 2022/11/7
  Time: 12:26
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
    <title>用户信息管理系统</title>

    <!-- 1.加载全局css文件 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2.jQuery导入，建议使用1.9以上的版本-->
    <script src="js/jquery-3.2.1.min.js"></script>
    <!-- 3.导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>

    <script>
        ////删除时提示
        function deleteUser(id) {
            if(confirm("您确定要删除吗?")){
                location.href = "${pageContext.request.contextPath}/delUserByIdServlet?id=" + id;
            }
        }

        //提示用户是否确定删除,用户确定删除后,在提交选中记录的表单
        window.onload = function(){
            document.getElementById("delSelected").onclick = function() {
                if(confirm("您确定要删除选中的条目吗?")) {
                    let cbs = document.getElementsByName("uid");
                    let flag = false;
                    //遍历
                    for (let i = 0; i < cbs.length; i++) {
                        if(cbs[i].checked) {//判断有无选中的,再删除
                            flag = true;
                        }
                    }
                    if(flag) {
                        //有条目被选中,可以提交了
                        document.getElementById("selectedUserForm").submit();
                    }
                }
            }
            //全选的实现
            document.getElementById("firstCb").onclick = function () {
                //获得firstCb下的已选项
                let cbs = document.getElementsByName("uid")
                //遍历
                for (let i = 0; i < cbs.length; i++) {
                    //设置每个cbs[i]的状态与firstCb一致
                    cbs[i].checked = this.checked;
                }
            }
        }

    </script>

</head>
<body>
    <div class = "container" >
        <h3 style = "..." align="center" >用户信息列表</h3>

        <div style = "float : right;margin : 5px">
            <a class = "btn btn-primary" href = "${pageContext.request.contextPath}/add.jsp">添加联系人</a>
            <a class = "btn btn-primary" href = "javascript:void(0);" id = "delSelected">删除选中</a>
        </div>

        <div style = "float : left">
            <form class="form-inline" action = "${pageContext.request.contextPath}/findUserByPageServlet" method = "post">
                <div class="form-group">
                    <label for="exampleInputName2">姓名</label>
                    <input type="text" class="form-control" name = "name" value = "${condition.name[0]}" id="exampleInputName2">
                </div>
                <div class="form-group">
                    <label for="exampleInputName3">籍贯</label>
                    <input type="text" class="form-control" name = "address" value = "${condition.address[0]}" id="exampleInputName3">
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail2">邮箱</label>
                    <input type="email" class="form-control" name = "email" value = "${condition.email[0]}" id="exampleInputEmail2">
                </div>
                <button type="submit" class="btn btn-default">查询</button>
            </form>
        </div>
        <form id = "selectedUserForm" action = "${pageContext.request.contextPath}/delSelectedServlet" method = "post">
            <table border = "1" class = "table table-bordered table-hover">
                <tr class = "success">
                    <th><label for="firstCb"></label><input type = "checkbox" id = "firstCb"></th>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>年龄</th>
                    <th>籍贯</th>
                    <th>QQ</th>
                    <th>邮箱</th>
                    <th>操作</th>
                </tr>
                <%--            循环users得到真实的用户信息--%>
                <c:forEach items = "${pb.list}" var = "user" varStatus="s">
                
                    <tr>
                        <td><label>
                            <input type = "checkbox" name = "uid" value = "${user.id}">
                        </label></td>
                        <td>${s.count}</td>
                        <td>${user.name}</td>
                        <td>${user.gender}</td>
                        <td>${user.age}</td>
                        <td>${user.address}</td>
                        <td>${user.qq}</td>
                        <td>${user.email}</td>
                        <td><a class = "btn btn-default btn-sm" href = "${pageContext.request.contextPath}/findUserByIdServlet?id=${id=user.id}">修改</a>&nbsp;
                            <a class = "btn btn-default btn-sm" href = "javascript:deleteUser(${user.id});">删除</a></td>
                    </tr>
                </c:forEach>
            </table>
        </form>
        <div>
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:if test="${pb.currentPage == 1}">
                        <li class = "disabled">
                    </c:if>
                    <c:if test="${pb.currentPage != 1}">
                        <li>
                    </c:if>
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage - 1}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>

                    <c:forEach var = "i" begin="1" end="${pb.totalPage}">
                        <c:if test="${pb.currentPage == i}">
                            <li class = "active"><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a></li>
                        </c:if>
                        <c:if test="${pb.currentPage != i}">
                            <li><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a></li>
                        </c:if>
                    </c:forEach>
                    <li>
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage + 1}&rows=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                    <span style = "font-size: 25px;margin-left : 5px">
                        共${pb.totalCount}条记录,共${pb.totalPage}页
                    </span>
                </ul>
            </nav>

        </div>
    </div>
</body>
</html>
