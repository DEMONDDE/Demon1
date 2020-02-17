package cn.itcase.servise;

import cn.itcase.domain.PageBean;
import cn.itcase.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */
public interface UserServise {

    //查询所有用户信息
    public List<User> findAll();
    public User findUserByUsernameAndPassword(String username, String password);
    public void delUserById(int id);
    public User findUserById(String id);

    public void updateUser(User user);

    public void delSelectUser(String[] ids);

    public void addUser(User user);

    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
