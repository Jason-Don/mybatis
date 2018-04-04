package cn.com.itcast.mybatis.mapper;

import cn.com.itcast.mybatis.demo01.pojo.User;
import cn.com.itcast.mybatis.demo01.pojo.UserCustom;
import cn.com.itcast.mybatis.demo01.pojo.UserQueryVo;

import java.util.List;

public interface UserMapper {
    //根据id查询用户
    public User findUserById(int id) throws Exception;

    //姓名模糊查询
    public List<User> findUserByName(String userName) throws Exception;

    //添加用户 返回自增主键ID到传入对象user的id属性，将传入user对象返回
    public User insertUser(User user) throws Exception;

    //删除用户
    public void delUserById(int id) throws Exception;

    //通过id更新user
    public void updateUserById(User user) throws Exception;

    //用户信息的综合查询
    public List<UserCustom> findUserList(UserQueryVo userQueryVo) throws Exception;

    //用户信息数量的综合查询
    public int findUserNum(UserQueryVo userQueryVo) throws Exception;

    //用户信息的综合查询 resultMap用输出
    public List<UserCustom> findUserListResulMap(UserQueryVo userQueryVo) throws Exception;
}
