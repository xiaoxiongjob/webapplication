package cn.itcast.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 程序生成验证码,存入session中,在LoginServlet中使用
 */
@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {

    private int width = 100;
    private int height = 50;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 创建一个对象，用来在内存中画图
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        StringBuilder sb = new StringBuilder();

        // 美化图片
        // 填充矩形
        Graphics g = image.getGraphics();   // 获取画笔
        g.setColor(Color.PINK);             // 设置画笔颜色
        g.fillRect(0,0,width,height);
        // 画边宽
        g.setColor(Color.BLUE);
        g.drawRect(0,0,width-1,height-1);
        // 随机画字符
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rd = new Random();
        // 画干扰线
        for (int i = 1; i <8 ; i++) {
            int x1 = rd.nextInt(width);
            int x2 = rd.nextInt(width);
            int y1 = rd.nextInt(height);
            int y2 = rd.nextInt(height);
            g.drawLine(x1,x2,y1,y2);
        }
        // 画验证码
        g.setColor(Color.BLACK);
        for (int i = 1; i <=4; i++) {
            int index = rd.nextInt(str.length());
            char c = str.charAt(index);
            sb.append(c);
            g.drawString(c+"",width/5*i,height/2);
        }
        // 将验证码保存到session中
        String checkCode_session = sb.toString();
        req.getSession().setAttribute("checkCode_session",checkCode_session);
        // 输出图片到页面展示
        ImageIO.write(image,"jpg",resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}

