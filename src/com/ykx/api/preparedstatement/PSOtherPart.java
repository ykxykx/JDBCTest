package com.ykx.api.preparedstatement;

import org.junit.Test;

import java.sql.*;

/**
 * @author: yangkx
 * @Title: PSOtherPart
 * @ProjectName: JDBCTest
 * @Description:
 * @date: 2023-06-03 10:49
 */
public class PSOtherPart {

    @Test
    public void test() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu?rewriteBatchedStatements=true", "root", "ykxykx");
        String sql = "insert into t_user(account,password,nickname) values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        for(int i = 0; i < 10; i++){
            preparedStatement.setObject(1, "test1"+i);
            preparedStatement.setObject(2, "123456"+i);
            preparedStatement.setObject(3, "哈士奇"+i);

            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();

        preparedStatement.close();
        connection.close();
    }

}
