package cn.itcast.web.servlet;

import cn.itcast.service.Impl.UserServiceImpl;
import cn.itcast.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 用户在list.jsp页面点击删除按钮(每条记录已经绑定了唯一id),获取id删除指定记录
 */
@WebServlet("/delUserByIdServlet")
public class DelUserByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取数据
        String id = request.getParameter("id");
        //3.调用service的delUser()方法
        UserService service = new UserServiceImpl();
        service.delUserById(id);
        //4.跳转到userListServlet
        response.sendRedirect(request.getContextPath()+"/findUserByPageServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
