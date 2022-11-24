package cn.itcast.web.servlet;

import cn.itcast.domain.User;
import cn.itcast.service.Impl.UserServiceImpl;
import cn.itcast.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.util.List;

@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service完成查询
        UserService service = new UserServiceImpl();
        List<User> users = service.findAll();
        //2.将users存入到request域中
        request.setAttribute("users", users);
        //3.转发到list.jsp

//        response.sendRedirect(request.getContextPath() +"/findUserByPageServlet");
        request.getRequestDispatcher("/list.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
