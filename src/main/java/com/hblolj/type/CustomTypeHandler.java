package com.hblolj.type;

import org.apache.ibatis.type.*;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: hblolj
 * @Date: 2019/4/27 15:57
 * @Description:
 * @Version:
 **/
@MappedJdbcTypes(value = JdbcType.VARCHAR, includeNullJdbcType = false)
@MappedTypes({String.class})
public class CustomTypeHandler implements TypeHandler<String> {

    /**
     * 查询参数类型转换，需要在 xml 对应的 sql 语句的参数中显示指定 typeHandler，或者指定 includeNullJdbcType = true
     * @param preparedStatement
     * @param i
     * @param s
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        System.out.println(preparedStatement.getMetaData());
        System.out.println(preparedStatement.getParameterMetaData());
        preparedStatement.setString(i, s);
    }

    /**
     * 查询结果类型转换，在 sql 对应的 ResultMap 中需要转换的参数的 result 上添加 typeHandler
     * 在 mybatis-config 中添加 typeHandler 默认是对全局的查询结果生效
     * @param resultSet
     * @param columnName
     * @return
     * @throws SQLException
     */
    @Override
    public String getResult(ResultSet resultSet, String columnName) throws SQLException {
        if (columnName.equals("name")){
            return resultSet.getString(columnName) + "-Custom";
        }
        return resultSet.getString(columnName);

    }

    @Override
    public String getResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return resultSet.getString(columnIndex);
    }

    @Override
    public String getResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return callableStatement.getString(columnIndex);
    }
}
