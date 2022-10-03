package com.example.demo.domain.typeHandler;
import com.example.demo.enums.ProvinceType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Rich
 * @Description: TODO
 * @date 2022/1/2 10:34
 */
public class ProvinceTypeHandler extends BaseTypeHandler<ProvinceType>{

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ProvinceType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.toString());
    }

    @Override
    public ProvinceType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return ProvinceType.value2Object(rs.getString(columnName));
    }

    @Override
    public ProvinceType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return ProvinceType.value2Object(rs.getString(columnIndex));
    }

    @Override
    public ProvinceType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return ProvinceType.value2Object(cs.getString(columnIndex));
    }
}



