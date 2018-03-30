package cn.com.itcast.mybatis.demo01.handler;

import cn.com.itcast.mybatis.demo01.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
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
    {
        try{
            inputStream.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * call
     */
    @Test
    public void findUserById() throws IOException {
        //通过会话工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try{
            User user = sqlSession.selectOne("user.findUserById",1);
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
            userList = sqlSession.selectList("user.findUserByName","张");
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
            System.out.println(userList);
        }
    }
    /**
     * 保存一个User
     */
    @Test
    public void insertUser(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        //user.setId(110); 自增字段
        user.setUsername("王小丫");
        user.setBirthday(new Date());
        user.setSex("1");
        user.setAddress("北京");
        try{
            sqlSession.insert("user.insertUser",user);
            //MyBatis的SqlSession的insert、update、delete都需要使用sqlSession.commit();手动提交
            sqlSession.commit();

            //获取新加User对象自增主键 自增主键返回


        }catch(Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
            System.out.println(user);
        }

    }

    /**
     * 通过id删除User
     */
    @Test
    public void delUserbyId(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            sqlSession.delete("user.delUserById",117);
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 通过id更新user信息
     */
    @Test
    public void updateUserById(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User newUser = new User();
        newUser.setId(110);
        newUser.setUsername("张小胖");
        newUser.setBirthday(new Date());
        newUser.setSex("0");
        newUser.setAddress("河北沧州");
        try{
            sqlSession.update("user.updateUserById",newUser);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }


}
