package cn.itcase.web;

import cn.itcase.domain.PageBean;
import cn.itcase.domain.User;
import cn.itcase.servise.UserServise;
import cn.itcase.servise.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 执行分页功能，并且也执行复杂条件查询
 */
@WebServlet( "/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");

        //获得查询条件
        Map<String, String[]> condition = request.getParameterMap();
        //通过service查询页面返回PageBean对象
        UserServise servise = new UserServiceImpl();
        PageBean<User> pb = servise.findUserByPage(currentPage, rows, condition);

        //将pageBean对象发送给页面
        request.setAttribute("pb", pb);
        request.setAttribute("condition",condition);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
