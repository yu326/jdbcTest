package com.example.demo;


import org.junit.Test;

import java.sql.*;
import java.util.List;

/**
 * Created by koreyoshi on 2018/1/31.
 */
public class JdbcTest {
    @Test
    public void test() {
        Connection connection;
        PreparedStatement pstm;
        ResultSet resultSet;
        List<Object> result = null;
        //数据库配置信息
        String url = "jdbc:mysql://127.0.0.1:3306/yu?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String user = "root";
        String password = "root";

        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //连接数据库
            connection = DriverManager.getConnection(url, user, password);
            //创建数据库连接
            String sql = "select *  from score where id = ? ";
            pstm = connection.prepareStatement(sql);
            pstm.setObject(1, 1);
            //获取结果
            resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                Object id = resultSet.getObject(1);
                Object score = resultSet.getObject(2);
                Object subject_name = resultSet.getObject(3);
                System.out.println("the id is: [ " + id + " ],the name is: [ " + score + " ],the subject name is : [ " + subject_name + " ].");
            }
            pstm.setInt(1, 2);
            resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                Object id = resultSet.getObject(1);
                Object score = resultSet.getObject(2);
                Object subject_name = resultSet.getObject(3);
                System.out.println("the id is: [ " + id + " ],the name is: [ " + score + " ],the subject name is : [ " + subject_name + " ].");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("ErrorInfo : [ 加载驱动类失败 ]");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("the end");
        }
    }

    @Test
    public void test1() {
        Connection connection;
        Statement pstm;
        ResultSet resultSet;
        List<Object> result = null;
        //数据库配置信息
        String url = "jdbc:mysql://127.0.0.1:3306/yu?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String user = "root";
        String password = "root";

        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //连接数据库
            connection = DriverManager.getConnection(url, user, password);
            //创建数据库连接
            /**
             * 模拟一下sql注入
             *  正常情况下，我们需要匹配学科名称.
             *  非正常情况就是，我们传入改造后的sql语句。以逻辑关系达到查询目的
             */
            //  normal
//            String subjectName = "语文";
            //  unnormal
            String subjectName = " 1' or 1='1";
            String sql = "select *  from score where subject_name =  '" + subjectName + "'";

            pstm = connection.createStatement();

            //获取结果
            resultSet = pstm.executeQuery(sql);
            while (resultSet.next()) {
                Object id = resultSet.getObject(1);
                Object score = resultSet.getObject(2);
                Object subject_name = resultSet.getObject(3);
                System.out.println("the id is: [ " + id + " ],the name is: [ " + score + " ],the subject name is : [ " + subject_name + " ].");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("ErrorInfo : [ 加载驱动类失败 ]");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("the end");
        }
    }
}
