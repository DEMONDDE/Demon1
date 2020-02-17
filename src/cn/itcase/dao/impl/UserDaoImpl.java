package cn.itcase.dao.impl;

import cn.itcase.dao.UserDao;
import cn.itcase.domain.User;
import cn.itcase.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    //获取所有
    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    //通过用户名和密码获取
    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        try{
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    //通过id删除
    @Override
    public void delUserById(int id) {
        String sql = "delete  from user where id = ?";
        template.update(sql,id);
    }

    //通过id获取用户信息
    @Override
    public User findUserById(int id) {
        String sql = "select * from user where id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
    }

    //更新数据
    @Override
    public void update(User user) {
        String sql = "update user set name = ?, gender = ?, age = ?, address = ?, qq = ?, email = ?";
        template.update(sql, user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());
    }

    //添加用户
    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO USER VALUE( null, ?, ?, ?, ?, ?, ?, null, null)";
        template.update(sql,user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql = "select count(*) from user where 1=1 ";
        String s = getSQL(condition);
        sql = sql + s;
        return template.queryForObject(sql, Integer.class);
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user where 1=1 " ;
        String s = getSQL(condition);
        sql = sql + s;
        sql += " limit ?, ? ";
        List<User> list;
        try{
            list = template.query(sql, new BeanPropertyRowMapper<User>(User.class), start, rows);
        }catch (Exception e){
            list = null;
        }
        return list;
    }

    //获取模板sql语句
    private String getSQL(Map<String, String[]> condition){
        StringBuilder sql = new StringBuilder();
        //遍历map
        Set<String> k = condition.keySet();
        for(String key:k){
            if(key.equals("rows") || key.equals("currentPage")){
                continue;
            }
            String value = condition.get(key)[0];
            if(value != null && !"".equals(value)){
                sql.append(" and "+key+" like '%"+value+"%' ");
            }
        }
        return  sql.toString();
    }


}
