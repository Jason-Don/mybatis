package cn.com.itcast.mybatis.mapper;

import cn.com.itcast.mybatis.demo01.pojo.User;
import cn.com.itcast.mybatis.demo01.pojo.UserCustom;
import cn.com.itcast.mybatis.demo01.pojo.UserQueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UserMapperTest {

    SqlSessionFactory sqlSessionFactory;
    //SqlSessionFactory
    @Before
    public void setUp() throws IOException {
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    @Test
    public void testFindUserById() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findUserById(117);
        System.out.println(user);
    }

    @Test
    public void testFindUserName() throws Exception {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findUserByName("张");
        System.out.println(userList);
    }

    @Test
    public void testFindUserList() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        UserCustom userCustom = new UserCustom();
        userCustom.setSex("1");
        userCustom.setUsername("张");
        UserQueryVo userQueryVo = new UserQueryVo();
        userQueryVo.setUserCustom(userCustom);
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(10);
        ids.add(16);
        userQueryVo.setIds(ids);
        List<UserCustom> userCustomsList = userMapper.findUserList(userQueryVo);
        System.out.println(userCustomsList);
    }

    @Test
    public void testFindUserNum() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        UserCustom userCustom = new UserCustom();
        userCustom.setSex("1");
        userCustom.setUsername("张");
        UserQueryVo userQueryVo = new UserQueryVo();
        userQueryVo.setUserCustom(userCustom);
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(10);
        ids.add(16);
        userQueryVo.setIds(ids);
        int number = userMapper.findUserNum(userQueryVo);
        System.out.println(number);
    }

    @Test
    public void testFindUserListResulMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        UserCustom userCustom = new UserCustom();
        userCustom.setSex("1");
        userCustom.setUsername("张");
        UserQueryVo userQueryVo = new UserQueryVo();
        userQueryVo.setUserCustom(userCustom);
        List<UserCustom> userCustomsList = userMapper.findUserListResulMap(userQueryVo);
        System.out.println(userCustomsList);
    }
}
