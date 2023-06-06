package com.ykx.api.statement;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

/**
 * @author: yangkx
 * @Title: StatementQueryPart
 * @ProjectName: JDBCTest
 * @Description:
 * @date: 2023-06-01 16:06
 */
public class StatementQueryPart {

    public static void main(String[] args) throws SQLException {
        //1.注册驱动
        DriverManager.registerDriver(new Driver());
        //2.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu", "root", "ykxykx");
        //3.创建statement
        Statement statement = connection.createStatement();
        //4.发送sql，获取返回结果
        String sql = "select *from t_user";
        ResultSet resultSet = statement.executeQuery(sql);
        //5.结果集解析
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String account = resultSet.getString("account");
            String password = resultSet.getString("password");
            String nickname = resultSet.getString("nickname");
            System.out.println(id + account + password + nickname);
        }
        //6.关闭资源
        resultSet.close();
        statement.close();
        connection.close();
    }

}
