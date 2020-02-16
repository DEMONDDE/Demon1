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

    @Override
    public void delUserById(int id) {
         dao.delUserById(id);
    }

    @Override
    public User findUserById(String id) {
        return dao.findUserById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public void delSelectUser(String[] ids) {
        for(int i = 0;i < ids.length; i++){
            dao.delUserById(Integer.parseInt(ids[i]));
        }
    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }


}
