package cn.com.itcast.mybatis.demo01.dao;

import cn.com.itcast.mybatis.demo01.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements UserDao{


    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public User findUserById(int id) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("user.findUserById",id);
        sqlSession.close();
        return user;
    }

    @Override
    public List<User> findUserByName(String userName) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> userList = sqlSession.selectList("user.findUserByName",userName);
        sqlSession.close();
        return userList;
    }

    @Override
    public User insertUser(User user) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert("user.insertUser",user);
        sqlSession.commit();
        sqlSession.close();
        return user;
    }

    @Override
    public void delUserById(int id) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.delete("user.delUserById",id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateUserById(User user) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.update("user.updateUserById",user);
        sqlSession.commit();
        sqlSession.close();
    }

}
