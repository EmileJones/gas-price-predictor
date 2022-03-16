package com.ruoyi.gas.module.price.mapper.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.DateTime;

import java.sql.*;

public class DateTimeHandler implements TypeHandler<DateTime> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, DateTime dateTime, JdbcType jdbcType) throws SQLException {
        if (dateTime != null){
            preparedStatement.setDate(i, new Date(dateTime.getMillis()));
        }else {
            preparedStatement.setNull(i,Types.DATE);
        }
    }

    @Override
    public DateTime getResult(ResultSet resultSet, String s) throws SQLException {
        Date date = resultSet.getDate(s);
        return new DateTime(date.getTime());
    }

    @Override
    public DateTime getResult(ResultSet resultSet, int i) throws SQLException {
        Date date = resultSet.getDate(i);
        return new DateTime(date.getTime());
    }

    @Override
    public DateTime getResult(CallableStatement callableStatement, int i) throws SQLException {
        Date date = callableStatement.getDate(i);
        return new DateTime(date.getTime());
    }
}
