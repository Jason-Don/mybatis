package cn.com.itcast.mybatis.demo01.pojo;

/**
 * 用户扩展类
 */
public class UserCustom extends User {
    //可以扩展用户信息
    private int a;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "UserCustom{" +
                "a=" + a +
                '}';
    }
}
