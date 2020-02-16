package cn.itcase.web;

import cn.itcase.domain.User;
import cn.itcase.servise.UserServise;
import cn.itcase.servise.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取用户信息,以便回显
 */
@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        //查找用户信息
        UserServise servise = new UserServiceImpl();
        User user = servise.findUserById(id);
        //存入域中转发
        request.setAttribute("user",user);
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
