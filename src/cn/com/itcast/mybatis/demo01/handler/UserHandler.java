package cn.com.itcast.mybatis.demo01.handler;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class UserHandler {
    /**
     * call
     */
    @Test
    public void findUserById() throws IOException {

        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂，传入mybatis的配置文件信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //通过会话工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //通过SqlSession操作数据库
        sqlSession.selectOne()
    }
}
