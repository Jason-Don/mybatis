package cn.com.itcast.mybatis.jdbc;

import java.sql.*;

/**
 * JDBC 练习
 */
public class JdbcLearning {
    public static void main(String[] args) {
        //创建连接
        Connection connection = null;
        //预处理PrepareStatement,提高数据库性能
        PreparedStatement preparedStatement = null;
        //返回结果
        ResultSet resultSet = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://192.168.0.104:3306/studydb?characterEncoding=utf-8","study","study");
            String sql ="select * from user where username = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"张小明");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                System.out.println(resultSet.getString("id")+"  "+resultSet.getString("username"));
            }

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException E){

                }
            }
            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                }catch (SQLException E){

                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch(SQLException E){

                }

            }
        }


    }
}
