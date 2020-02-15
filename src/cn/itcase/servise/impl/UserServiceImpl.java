package cn.itcase.servise.impl;

import cn.itcase.dao.UserDao;
import cn.itcase.dao.impl.UserDaoImpl;
import cn.itcase.domain.User;
import cn.itcase.servise.UserServise;

import java.util.List;

public class UserServiceImpl implements UserServise {
    private UserDao dao = new UserDaoImpl();
    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        return dao.findUserByUsernameAndPassword(username, password);
    }
}
