package cn.itcast.web.request;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/requestDemo2")
public class RequestDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        System.out.println("method为get(post)");
        System.out.println(username + "的爱好有:");
        //获取爱好复选框的值
        String[] hobbies = request.getParameterValues("hobby");
        for (String hobby : hobbies) {
            System.out.println(hobby);
        }
        System.out.println("============================================");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //获取请求体数据
//        BufferedReader br = request.getReader();
//        String line = null;
//        while((line = br.readLine()) != null) {
//            System.out.println(line);
//        }
//        System.out.println("============================");
        //根据参数名获取参数值
        this.doGet(request,response);

    }
}
