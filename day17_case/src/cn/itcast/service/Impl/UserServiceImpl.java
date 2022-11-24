package cn.itcast.service.Impl;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        //充当桥梁,调用dao完成查询,返回给servlet
        return dao.findAll();
    }

    @Override
    public User login(User user) {
        return dao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public void delUserById(String id) {
        dao.delUserById(Integer.parseInt(id));
    }

    @Override
    public User findUserById(String id) {
        return dao.findUserById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Override
    public void delSelectedUser(String[] ids) {
        //遍历ids,调用dao.delUser()方法
        for(String id : ids) {
            dao.delUserById(Integer.parseInt(id));
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        if(currentPage <= 0) {
            currentPage = 1;
        }
        //1.创建一个空的PageBean<User>对象
        PageBean<User> pageBean = new PageBean<User>();
        //2.设置参数
        pageBean.setCurrentPage(currentPage);
        pageBean.setRows(rows);

        //3.调用dao.findTotalCount()查找总记录数
        int totalCount = dao.findTotalCount(condition);
        pageBean.setTotalCount(totalCount);
        //4.调用dao.findUserByPage()得到list<User>
        //计算数据库查询语句的开始索引
        int start = (currentPage - 1) * rows;
        List<User> list = dao.findUserByPage(start, rows, condition);
        pageBean.setList(list);

        //5.计算总的页码
        int totalPage = (totalCount % rows) == 0 ? totalCount / rows : (totalCount / rows + 1);
        pageBean.setTotalPage(totalPage);

        return pageBean;
    }

    @Override
    public void addUser(User user) {
        dao.add(user);
    }
}
