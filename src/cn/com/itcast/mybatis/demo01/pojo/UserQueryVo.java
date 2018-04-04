package cn.com.itcast.mybatis.demo01.pojo;

import java.util.List;

/**
 * 包装类型
 */
public class UserQueryVo {
    //这里包装需要的查询条件

    //传入多个id
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    //用户查询条件
    private UserCustom userCustom;

    public UserCustom getUserCustom() {
        return userCustom;
    }

    public void setUserCustom(UserCustom userCustom) {
        this.userCustom = userCustom;
    }
    //可以包装其他查询条件，订单、商品
}
