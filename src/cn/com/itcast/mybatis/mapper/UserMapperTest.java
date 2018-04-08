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

    //一级缓存测试
    @Test
    public void testCache1() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);


        //下边查询需要使用同一个sqlSession。
        //第一次发起请求，查询id=1的用户
        User user1 = userMapper.findUserById(1);


        //sqlSession执行update、insert、delete后（不需要commit），sqlSession会清空一级缓存。insert、delete实际会调用update。
        // 而update、commit、rollback都会清除一级缓存，insert在某种条件下（？）也会清除缓存。
        user1.setUsername("王小武");
        userMapper.updateUserById(user1);


        //第二次发起请求，查询id=1的用户
        User user2 = userMapper.findUserById(1);

        sqlSession.close();
    }

    //二级缓存测试
    @Test
    public void testCache2() throws Exception {

        //下边查询需要使用同一个sqlSession。
        //第一次发起请求，查询id=1的用户
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        User user1 = userMapper1.findUserById(1);

        //将sqlsession关闭，才会将数据写到二级缓存中。
        sqlSession1.close();


        //二级缓存没有commit,二级缓存不会被清空。
        SqlSession sqlSession3 = sqlSessionFactory.openSession();
        UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
        user1.setUsername("王小武");
        userMapper3.updateUserById(user1);
        sqlSession3.commit();
        sqlSession3.close();


        //第二次发起请求，查询id=1的用户
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = userMapper2.findUserById(1);
        sqlSession2.close();

    }

}
