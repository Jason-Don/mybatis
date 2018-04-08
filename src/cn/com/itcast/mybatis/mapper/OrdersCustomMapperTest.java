package cn.com.itcast.mybatis.mapper;

import cn.com.itcast.mybatis.demo01.pojo.Orders;
import cn.com.itcast.mybatis.demo01.pojo.OrdersCustom;
import cn.com.itcast.mybatis.demo01.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class OrdersCustomMapperTest {
    private SqlSessionFactory sqlSessionFactory;

    //加载配置环境，创建sqlSessionfactory
    @Before
    public void upSet() throws IOException {
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testFindOrdersUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersCustomMapper ordersCustomMapper = sqlSession.getMapper(OrdersCustomMapper.class);
        List<OrdersCustom> ordersCustomsList = ordersCustomMapper.findOrdersUser();
        sqlSession.close();

        System.out.println(ordersCustomsList);

    }

    @Test
    public void testFindOrdersUserResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersCustomMapper ordersCustomMapper = sqlSession.getMapper(OrdersCustomMapper.class);
        List<Orders> ordersList = ordersCustomMapper.findOrdersUserResultMap();
        sqlSession.close();

        System.out.println(ordersList);

    }
    @Test
    public void testFindOrderAndDetialsRsultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersCustomMapper ordersCustomMapper = sqlSession.getMapper(OrdersCustomMapper.class);
        List<Orders> ordersList = ordersCustomMapper.findOrderAndDetialsRsultMap();
        sqlSession.close();

        System.out.println(ordersList);
    }

    @Test
    public void testFindUserAndItemsRsultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersCustomMapper ordersCustomMapper = sqlSession.getMapper(OrdersCustomMapper.class);
        List<User> userList = ordersCustomMapper.findUserAndItemsRsultMap();
        sqlSession.close();

        System.out.println(userList);
    }

    @Test
    public void testFindOrdersUserLazyLoading() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersCustomMapper ordersCustomMapper = sqlSession.getMapper(OrdersCustomMapper.class);

        //选查询订单信息（单表查询）
        List<Orders> ordersList = ordersCustomMapper.findOrdersUserLazyLoading();

        //当执行getUser()方法时，去查询用户信息，实现按需加载
        for(Orders orders : ordersList){
            System.out.println(orders.getUser());

        }

        sqlSession.close();

    }
}
