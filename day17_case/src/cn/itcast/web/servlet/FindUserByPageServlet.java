package cn.itcast.web.servlet;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;
import cn.itcast.service.Impl.UserServiceImpl;
import cn.itcast.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置参数
        request.setCharacterEncoding("utf-8");

        //2.获取数据
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        //获取条件查询参数
        Map<String, String[]> condition = request.getParameterMap();

        //和index2一样,避免了点击"查询所有用户信息"后,因为没有提交currentPage和rows而返回null
        if(currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if(rows == null || "".equals(rows)) {
            rows = "5";
        }

        //3.调用service的findByPage()方法
        UserService service = new UserServiceImpl();
        PageBean<User> pb = service.findUserByPage(currentPage, rows, condition);

//        System.out.println(pb);

        //4.将pb存入到request域中
        request.setAttribute("pb",pb);
        //将查询条件condition插入到request中,用于回显信息,条件查询分页的关键
        request.setAttribute("condition", condition);

        //5.转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
