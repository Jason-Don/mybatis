package cn.com.itcast.mybatis.mapper;

import cn.com.itcast.mybatis.demo01.pojo.Orders;
import cn.com.itcast.mybatis.demo01.pojo.OrdersCustom;

import java.util.List;

public interface OrdersCustomMapper {
    //查询订单及用户信息
    public List<OrdersCustom> findOrdersUser() throws Exception;

    public List<Orders> findOrdersUserResultMap() throws Exception;

}
