package com.ykx.api.preparedstatement;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yangkx
 * @Title: PSCURDPart
 * @ProjectName: JDBCTest
 * @Description: 使用pS进行t_user的curd操作
 * @date: 2023-06-01 19:00
 */
public class PSCURDPart {

    @Test
    public void testInsert() throws Exception {
        //1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu", "root", "ykxykx");
        //3.编写SQL语句结构
        String sql = "insert into t_user(account,password,nickname) values(?,?,?);";
        //4.创建PS 并设置SQL语句结构
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //5.占位符赋值
        preparedStatement.setObject(1, "test");
        preparedStatement.setObject(2, "test");
        preparedStatement.setObject(3, "二狗子");
        //6.发送SQL语句，并获取返回结果,这里eQ()不用再传sql了！！！
        int i = preparedStatement.executeUpdate();
        //7.输出结果
        if(i > 0)   System.out.println("执行行数：" + i);
        else System.out.println("插入失败~");
        //8.关闭资源
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testUpdate() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu", "root", "ykxykx");
        String sql = "update t_user set nickname = ? where id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, "三狗子");
        preparedStatement.setObject(2, 3);
        int i = preparedStatement.executeUpdate();
        if(i > 0) System.out.println("执行行数：" + i);
        else System.out.println("更新失败~");
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testDelete() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu", "root", "ykxykx");
        String sql = "delete from t_user where id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, 3);
        int i = preparedStatement.executeUpdate();
        if(i > 0) System.out.println("执行行数：" + i);
        else System.out.println("删除失败~");
        preparedStatement.close();
        connection.close();
    }

    /**
     * 目标:查询所有数据，并且封装到一个List<Map> list集合中
     * @author yangkx
     * @date 2023-06-02 9:29
     */
    @Test
    public void testSelect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu", "root", "ykxykx");
        String sql = "select *from t_user";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Map> list = new ArrayList<>();
        //metaData 装的当前结果集列的信息对象
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount(); //获取有多少列
        while(resultSet.next()){
            Map map = new HashMap();
            //下标从1开始
            for(int i = 1; i <= columnCount; i++){
                //获取指定列下角标的名称
                String columnLabel = metaData.getColumnLabel(i);
                //获取指定列下角标的值
                Object object = resultSet.getObject(i);
                map.put(columnLabel,object);
            }
            list.add(map);
        }
        System.out.println(list);
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

}
