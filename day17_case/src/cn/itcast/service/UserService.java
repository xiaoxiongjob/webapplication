package cn.itcast.service;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 定义管理用户信息的接口
 */
public interface UserService {
    /**
     * 从service获取查询到的所有用户的信息
     * @return list<User>
     */
    List<User> findAll();

    /**
     * 根据用户名和密码,查找并返回user
     * @param user 用户对象
     * @return user
     */
    User login(User user);

    /**
     * 保存user对象
     * @param user 用户对象
     */
    void addUser(User user);

    /**
     * 将id传到dao包,调用其delUser方法
     * @param id 用户id
     */
    void delUserById(String id);

    /**
     * 将id传到dao包,调用其findUserById()方法
     * @param id 用户的id
     * @return user
     */
    User findUserById(String id);

    /**
     * 根据用户提交的表单中的隐藏域中的id来锁定唯一记录,进行修改
     * @param user 用户对象
     */
    void updateUser(User user);

    /**
     * 批量删除
     * @param ids 多个选中项的id数组
     */
    void delSelectedUser(String[] ids);

    /**
     * 查找Page的属性,封装进对象并返回
     * @param currentPage 当前页码
     * @param rows 每页应该展示的条数
     * @param condition 条件查询的参数
     * @return PageBean<User>
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);

}
