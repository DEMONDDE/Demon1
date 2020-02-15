package cn.itcase.web;

import cn.itcase.domain.User;
import cn.itcase.servise.UserServise;
import cn.itcase.servise.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取数据
        String verifycode = request.getParameter("verifycode");
        //验证码校验
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        request.removeAttribute("CHECKCODE_SERVER");
        if(!checkcode_server.equalsIgnoreCase(verifycode)){
            // 验证码不正确
            request.setAttribute("login_msg", "验证码错误!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        //获取用户名密码
        /**
         * 获取map 再获取set 再将set改为list以获取键，再根据键获取值
         */
        Map<String,String[]> map = request.getParameterMap();
        String username;
        String password;
        Set<String> keySet = map.keySet();
        List list = new ArrayList(keySet);
        username = map.get(list.get(0))[0];
        password = map.get(list.get(1))[0];
        //查询用户是否存在
        UserServise servise = new UserServiceImpl();
        User loginUser = servise.findUserByUsernameAndPassword(username, password);
        if( loginUser == null){
            //登陆失败
            request.setAttribute("login_msg", "用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }else{
            //登录成功
            request.getSession().setAttribute("user", loginUser);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
