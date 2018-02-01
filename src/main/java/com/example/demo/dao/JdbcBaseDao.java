package com.example.demo.dao;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by koreyoshi on 2018/1/31.
 */
@Repository("JdbcCommonDao")
public class JdbcBaseDao {

    @Resource(name = "dataSource")
    private DataSource dataSource;

    public Connection getConnect() {
        Connection connection = null;
        connection = DataSourceUtils.getConnection(dataSource);
        return connection;
    }

    public void queryAll() {
        Connection con = getConnect();
        PreparedStatement pstm;
        ResultSet resultset;
        String sql = "select *  from score where id = ? ";
        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, 1);
            resultset = pstm.executeQuery();
            while (resultset.next()) {
                Object id = resultset.getObject(1);
                Object score = resultset.getObject(2);
                Object subjectName = resultset.getObject(3);
                System.out.println("the id is: [ " + id + " ],the score is: [ " + score + " ],the subject name is: [ " + subjectName + " ]");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
