package cn.itcast.dao;

import cn.itcast.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 定义用户操作数据库的接口
 */
public interface UserDao {
    /**
     * 用户从数据库中查询所有用户信息
     * @return List<User>
     */
    List<User> findAll();

    /**
     * 根据用户名和密码查找用户
     * @param username 用户名
     * @param password 密码
     * @return user
     */
    User findUserByUsernameAndPassword(String username, String password);

    /**
     * 添加用户
     * @param user 用户对象
     */
    void add(User user);

    /**
     * 根据id删除用户
     * @param id 被删除用户的id
     */
    void delUserById(int id);

    /**
     * 根据id查找用户
     * @param id 需要查找的用户的id
     * @return user
     */
    User findUserById(int id);

    /**
     * 根据用户提交的内容(从对象user中获取)进行修改
     * @param user 用户
     */
    void updateUser(User user);

    /**
     * 查找user表中有多少记录
     * @return 总记录数
     * @param condition
     */
    int findTotalCount(Map<String, String[]> condition);

    /**
     * 查询currentPage的数据
     * @param start 查询语句开始的索引
     * @param rows 每页展示的条数
     * @param condition
     * @return 每页展示的用户集合
     */
    List<User> findUserByPage(int start, int rows, Map<String, String[]> condition);
}
