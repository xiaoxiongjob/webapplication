package cn.itcast.dao.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.util.JDBCUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

public class UserDaoImpl implements UserDao {

        private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

        @Override
        public List<User> findAll() {
            //1.定义sql语句
            String sql = "select * from user";

            return template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        }

        @Override
        public User findUserByUsernameAndPassword(String username, String password) {
            User object = null;
            try {
                String sql = "select * from user where username = ? and password = ?";
                object = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            } catch (EmptyResultDataAccessException e) {
                return null;
            }
            return object;
        }

        @Override
        public void add(User user) {
            String sql = "insert into user values(null,?,?,?,?,?,?,null,null)";
            template.update(sql, user.getName(), user.getGender(), user.getAge(),
                    user.getAddress(), user.getQq(), user.getEmail());

        }

        @Override
        public void delUserById(int id) {
            String sql = "delete from user where id = ?";
            template.update(sql, id);
        }

        @Override
        public User findUserById(int id) {
            User user = null;
            try {
                String sql = "select * from user where id = ?";
                user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
            } catch(EmptyResultDataAccessException e) {
                return null;
            }
            return user;
        }

        @Override
        public void updateUser(User user) {
            String sql = "update user set name = ?, gender = ?, age = ?,address = ?, qq = ?, email = ? where id = ?";
            template.update(sql,user.getName(),user.getGender(),user.getAge(),
                    user.getAddress(),user.getQq(),user.getEmail(),user.getId());
        }


        @Override
        public int findTotalCount(Map<String, String[]> condition) {
            //1.定义模板初始化sql
            String sql = "select count(*) from user where 1 = 1";
            //2.定义一个StringBuilder拼接复杂查询语句
            StringBuilder sb = new StringBuilder(sql);

            //3.获取condition中的参数
            Set<String> keySet = condition.keySet();
            //4.定义参数的集合,存储name对应的value或者address对应的value或者邮箱对应的value
            List<Object> params = new ArrayList<>();
            for (String key : keySet) {

                if("currentPage".equals(key) || "rows".equals(key)) {
                    continue;
                }

                //获取value
                String value = condition.get(key)[0];
                //判断value是否有值
                if(value != null && !"".equals(value)) {
                    sb.append(" and "+key+" like ? ");
                    params.add("%"+value+"%");
                }
            }

//            System.out.println(sb.toString());
//            System.out.println(params);

            sql = sb.toString();
            Integer object = null;
            object = template.queryForObject(sql, Integer.class, params.toArray());
            if(object == null) {
                return 0;
            } else {
                return object;
            }
        }

        @Override
        public List<User> findUserByPage(int start, int rows, Map<String, String[]> condition) {
            String sql = "select * from user where 1 = 1";
            //2.定义一个StringBuilder拼接复杂查询语句
            StringBuilder sb = new StringBuilder(sql);

            //3.获取condition中的参数
            Set<String> keySet = condition.keySet();
            //4.定义参数的集合,存储name对应的value或者address对应的value或者邮箱对应的value
            List<Object> params = new ArrayList<>();
            for (String key : keySet) {

                if("currentPage".equals(key) || "rows".equals(key)) {
                    continue;
                }
                //获取value
                String value = condition.get(key)[0];
                //判断value是否有值
                if(value != null && !"".equals(value)) {
                    sb.append(" and "+key+" like ? ");
                    params.add("%"+value+"%");
                }
            }
//            System.out.println(sql);
            //添加分页查询
            sb.append(" limit ?,? ");
            //添加分页查询参数值
            params.add(start);
            params.add(rows);

            sql = sb.toString();

//            System.out.println(params);
            return template.query(sql, new BeanPropertyRowMapper<User>(User.class), params.toArray());
        }
    }

