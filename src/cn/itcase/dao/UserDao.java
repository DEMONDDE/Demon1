package cn.itcase.dao;

import cn.itcase.domain.User;

import java.util.List;

/**
 * 用户操作的Dao
 */
public interface UserDao {

    public List<User> findAll();
    public User findUserByUsernameAndPassword(String username, String password);
}
