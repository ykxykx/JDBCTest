package com.ykx.api.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author: yangkx
 * @Title: DruidUserPart
 * @ProjectName: JDBCTest
 * @Description: Druid连接池使用类
 * @date: 2023-06-03 11:48
 */
public class DruidUserPart {

    /**
     * 通过读取外部配置文件的方法，实例化druid连接池对象
     * @author yangkx
     * @date 2023-06-03 11:53
     */
    @Test
    public void test() throws Exception {

        //1.读取外部配置文件properties
        Properties properties = new Properties();
        //src下的文件，可以使用类加载器提供
        InputStream ips = DruidUserPart.class.getClassLoader().getResourceAsStream("druid.properties");
        properties.load(ips);

        //2.使用连接池的工具类的工程模式，创建连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        //数据库crud
        connection.close();

    }

}
