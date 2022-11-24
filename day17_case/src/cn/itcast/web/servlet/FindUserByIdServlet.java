package cn.itcast.web.servlet;

import cn.itcast.domain.User;
import cn.itcast.service.Impl.UserServiceImpl;
import cn.itcast.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 获取用户输入id,查找唯一用户,在update.jsp中实现回显信息的功能
 */
@WebServlet("/findUserByIdServlet")
public class FindUserByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置参数
        request.setCharacterEncoding("utf-8");
        //2.获取数据
        String id = request.getParameter("id");
        //3.调用service的findUserById()方法
        UserService service = new UserServiceImpl();
        User user = service.findUserById(id);
        //4.将查找出来的user存入到request域中
        request.setAttribute("user", user);
        //5.转发到update.jsp
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
