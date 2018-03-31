package cn.com.itcast.mybatis.demo01.dao;

import cn.com.itcast.mybatis.demo01.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;

public class UserDaoTest {


    private SqlSessionFactory sqlSessionFactory;

    //创建SqlSessionFactory
    @Before
    public void setUp() throws Exception{
        String resource = "SqlMapConfig.xml";
        //加载mybatis配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    @Test
    public void testFindUserById() throws Exception{
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = userDao.findUserById(116);
        System.out.println(user);
    }

    @Test
    public void testInsertUser() throws Exception {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = new User();
        user.setUsername("小胖");
        user.setSex("0");
        user.setBirthday(new Date());
        user.setAddress("北京");
        User user1 = userDao.insertUser(user);
        System.out.println(user1);
    }
}
