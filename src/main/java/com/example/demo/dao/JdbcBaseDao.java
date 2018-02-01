package com.example.demo.dao;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

    //泛型类参数作为返回参数，还有sql，多参数
    public <T> List<T> queryAll(Class<T> classes, String sql, Object... params) {
        List<T> response = new ArrayList<T>();
        Connection con = getConnect();
        PreparedStatement pstm;
        ResultSet resultset;
        try {
            pstm = con.prepareStatement(sql);
            // 设置条件参数
            setStatementParams(sql, pstm, params);
            resultset = pstm.executeQuery();
            while (resultset.next()) {
                //// TODO: 2018/2/1  返回字段 依据反射获取到字段名，及字段值
                Object id = resultset.getObject(1);
                Object score = resultset.getObject(2);
                Object subjectName = resultset.getObject(3);
                System.out.println("the id is: [ " + id + " ],the score is: [ " + score + " ],the subject name is: [ " + subjectName + " ]");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return response;
    }

    public void setStatementParams(String sql, PreparedStatement pstm, Object... params) throws SQLException {
        if (params == null) {
            return;
        }
        // 获取sql中占位符个数
        int placeholderNum = appearNumber(sql,"\\?");
        if (placeholderNum == params.length) {
            int i = 1;
            for (Object param : params) {
                pstm.setObject(i, param);
                i++;
            }
        } else {
            throw new RuntimeException("sql语句和传入的参数 不一致!");
        }
    }

    /**
     * 查找字符串中子串出现次数
     *
     * @param srcText  字符串
     * @param findText 待查询的子串
     * @return 出现次数
     */
    public int appearNumber(String srcText, String findText) {
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            count++;
        }
        return count;
    }

}
