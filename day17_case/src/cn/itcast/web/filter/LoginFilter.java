package cn.itcast.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        /**
         * 需求:用户登录后才能访问其他资源
         * 注意:要先将request和response的类型进行转换,才能获得参数
         */
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        //获取uri
        String uri = request.getRequestURI();

        //用户进入到login.jsp后,在里面访问其他资源时,也会经过此过滤器,所以还得确保必要资源的输出
        if(uri.contains("/login.jsp") || uri.contains("/loginServlet") || uri.contains("/css/") || uri.contains("/fonts/") || uri.contains("/js/") || uri.contains("/checkCodeServlet")) {

            //用户只是想登录,放行~~~~~
            chain.doFilter(req,resp);//放行的是Request还是req?req
        } else {
            //当用户登录成功了(即user不为null),此时用户得转到index.jsp,所以要判断用户是否登录成功,再决定放行
            Object user = request.getSession().getAttribute("user");

            if(user != null) {
                //用户是登录成功的,可以访问所有资源
                chain.doFilter(req,resp);
            } else {

                request.setAttribute("login_msg","您还未登录,请先进行登录!!!"); //如用户在浏览器直接访问update.jsp
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                
            }
        }
    }
}
