package cn.itcase.web;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 实现验证码
 */

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置验证码的大小
        int width = 100;
        int height = 50;
        StringBuffer code = new StringBuffer();

        //在内存中创建验证么图片对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //美化图片
        Graphics g = image.getGraphics();//画笔对象
        g.setColor(Color.pink);
        g.fillRect(0,0,width,height);

        //画边框
        g.setColor(Color.black);
        g.drawRect(0, 0, width-1, height-1);

        //写验证码
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        Random r = new Random();
        for(int i = 1;i <= 4; i++){
            int index = r.nextInt(str.length());
            char c = str.charAt(index);
            code.append(c);
            g.drawString(c+"", width/5*i, height/2);
        }

//        //增加干扰线
//        g.setColor(Color.green);
//        for(int i = 0; i < 10; i++){
//            int x1 = r.nextInt(width);
//            int x2 = r.nextInt(width);
//            int y1 = r.nextInt(height);
//            int y2 = r.nextInt(height);
//            g.drawLine(x1, y1,x2, y2);
//        }

        //将图片输出
        ImageIO.write(image, "jpg",response.getOutputStream());

        //将验证码存入session
        String checkcode = code.toString();
        request.getSession().setAttribute("CHECKCODE_SERVER", checkcode);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
