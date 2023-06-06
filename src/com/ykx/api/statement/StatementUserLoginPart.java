package com.ykx.api.statement;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.Scanner;

/**
 * @author: yangkx
 * @Title: StatementUserLoginPart
 * @ProjectName: JDBCTest
 * @Description:
 * @date: 2023-06-01 16:37
 */
public class StatementUserLoginPart {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //1.获取用户输入信息
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入账号：");
        String account = scanner.nextLine();
        System.out.println("输入密码：");
        String password = scanner.nextLine();
        //2.注册驱动
        //方式一： 不建议
        //DriverManager.registerDriver(new Driver());
        //方式二： 建议
        Class.forName("com.mysql.cj.jdbc.Driver");
        //3.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu", "root", "ykxykx");
        //4.创建statement
        Statement statement = connection.createStatement();
        //5.发送sql，获取返回结果
        String sql = "select * from t_user where account = '"+account+"' and password = '"+password+"';";
        ResultSet resultSet = statement.executeQuery(sql);
        //6.结果集解析
//        while (resultSet.next()){
//            int id = resultSet.getInt("id");
//            String account1 = resultSet.getString("account");
//            String password1 = resultSet.getString("password");
//            String nickname = resultSet.getString("nickname");
//        }
        if(resultSet.next()){
            System.out.println("登录成功！");
        }else{
            System.out.println("登录失败");
        }
        //7.关闭资源
        resultSet.close();
        statement.close();
        connection.close();
    }

}
