package cn.com.itcast.mybatis.demo01.handler;

import cn.com.itcast.mybatis.demo01.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserHandler {
    //mybatis配置文件
    private String resource = "SqlMapConfig.xml";
    //得到配置文件流
    InputStream inputStream;

    {
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //创建会话工厂，传入mybatis的配置文件信息
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    /**
     * call
     */
    @Test
    public void findUserById() throws IOException {
        //通过会话工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            User user = sqlSession.selectOne("test.findUserById",1);
            System.out.printf(user.toString());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
        //通过SqlSession操作数据库

    }
    /**
     * 根据用户名称模糊查询
     */
    @Test
    public void findUserByName(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> userList = null;
        try{
            userList = sqlSession.selectList("test.findUserByName","张");
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
            System.out.println(userList);
        }
    }
}
