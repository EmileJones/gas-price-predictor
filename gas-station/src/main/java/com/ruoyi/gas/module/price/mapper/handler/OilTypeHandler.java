package com.ruoyi.gas.module.price.mapper.handler;

import com.ruoyi.gas.module.price.domain.framwork.OilType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OilTypeHandler implements TypeHandler<OilType> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, OilType oilType, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, oilType.getTypeNumber());
    }

    @Override
    public OilType getResult(ResultSet resultSet, String s) throws SQLException {
        int typeNumber = resultSet.getInt(s);
        return OilType.getOilByTypeNumber(typeNumber);
    }

    @Override
    public OilType getResult(ResultSet resultSet, int i) throws SQLException {
        int typeNumber = resultSet.getInt(i);
        return OilType.getOilByTypeNumber(typeNumber);
    }

    @Override
    public OilType getResult(CallableStatement callableStatement, int i) throws SQLException {
        int typeNumber = callableStatement.getInt(i);
        return OilType.getOilByTypeNumber(typeNumber);
    }
}
