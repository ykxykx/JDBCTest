package com.ykx.api.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * @author: yangkx
 * @Title: PSUserLoginPart
 * @ProjectName: JDBCTest
 * @Description:
 * @date: 2023-06-01 17:37
 */
public class PSUserLoginPart {

    public static void main(String[] args) throws Exception {

        //1.获取用户输入信息
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入账号：");
        String account = scanner.nextLine();
        System.out.println("输入密码：");
        String password = scanner.nextLine();

        //PS的数据库流程
        //2.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //3.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu", "root", "ykxykx");
        //4.编写SQL语句结构
        String sql = "select *from t_user where account = ? and password = ?;";
        //5.创建PS 并设置SQL语句结构
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //6.占位符赋值
        preparedStatement.setObject(1, account);
        preparedStatement.setObject(2, password);
        //7.发送SQL语句，并获取返回结果,这里eQ()不用再传sql了！！！
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            System.out.println("登录成功！");
        }else{
            System.out.println("登录失败");
        }
        //8.关闭资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }


}
