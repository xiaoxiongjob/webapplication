package cn.itcast.web.request;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/requestDemo1")
public class RequestDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //获取虚拟目录
//        String contextPath = request.getContextPath();
//        System.out.println(contextPath);
        //获取请求头数据
        String referer = request.getHeader("referer");
        System.out.println(referer);
        if(referer != null) {
            if(referer.contains("/day2_servlet")){
                //正常访问
                System.out.println("播放电影");
            } else {
                //盗链
                System.out.println("想看电影吗?来优酷吧....");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
