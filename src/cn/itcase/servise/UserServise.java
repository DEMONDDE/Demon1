package cn.itcase.servise;

import cn.itcase.domain.User;

import java.util.List;

/**
 * 用户管理的业务接口
 */
public interface UserServise {

    //查询所有用户信息
    public List<User> findAll();
}