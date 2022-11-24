package cn.itcast.test;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserDaoTest {

    @Test
    public void testLoginUser() {

        UserDao dao = new UserDaoImpl();
        List<User> users = dao.findAll();
        for(User user : users) {
            System.out.println(user.getName());
        }
    }
}
