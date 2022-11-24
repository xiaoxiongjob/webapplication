package cn.itcast.web.servlet;

import cn.itcast.domain.User;
import cn.itcast.service.Impl.UserServiceImpl;
import cn.itcast.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * login.jsp页面,判断用户是否登录成功(验证码校验,根据用户名和密码查询返回的对象是否为空)
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取数据
        String verifyCode = request.getParameter("verifyCode");
        //3.验证码的校验
        HttpSession session = request.getSession();
        String checkCode_session = (String)session.getAttribute("checkCode_session");
        //将验证码从session中删除,确保验证码的唯一性
        session.removeAttribute("checkCode_session");
        if(!checkCode_session.equalsIgnoreCase(verifyCode)){
            //验证码不正确
            //提示信息
            request.setAttribute("login_msg","验证码错误");
            //跳转登陆页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);

            return;
        }
        //4.封装User对象
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser,request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //5.调用Service查询
        UserService service = new UserServiceImpl();
        User user = service.login(loginUser);
        //6.判断是否登录成功
        if(user != null) {
            //登录成功
            //将用户存入session
            request.getSession().setAttribute("user", user);
            //跳转到主页index.jsp
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        } else {
            //登录失败,提示信息
            request.setAttribute("login_msg", "用户名或密码错误");
            //跳转登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
