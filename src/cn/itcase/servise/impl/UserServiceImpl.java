package cn.itcase.servise.impl;

import cn.itcase.dao.UserDao;
import cn.itcase.dao.impl.UserDaoImpl;
import cn.itcase.domain.PageBean;
import cn.itcase.domain.User;
import cn.itcase.servise.UserServise;

import java.util.List;
import java.util.Map;

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

    @Override
    public PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition) {
        int _currentPage = Integer.parseInt(currentPage);
        int _rows = Integer.parseInt(rows);
        PageBean<User> pb = new PageBean<User>();
        pb.setCurrentPage(_currentPage);
        pb.setRows(_rows);

        //查询总记录数
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);

        //计算总页码
        int totalPage;
        totalPage = (totalCount % _rows) == 0? (totalCount/_rows) : (totalCount/_rows)+1;
        pb.setTotalPage(totalPage);

        //判断页码是否超出范围
        if(_currentPage <= 0 ){
            _currentPage = 1;
        }else if (_currentPage > totalPage){
            _currentPage = totalPage;
        }

        //获得展示的记录
        int start = (_currentPage - 1) * _rows;
        List<User> list = dao.findByPage(start, _rows, condition);
        pb.setList(list);


        return pb;
    }


}
