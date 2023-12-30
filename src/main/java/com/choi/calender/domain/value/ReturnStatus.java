package com.choi.calender.domain.value;

import lombok.Getter;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeException;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
public enum ReturnStatus {
    SUCCESS("0000"),
    NO_VALUE("9000"),
    DUPLICATION("9001"),
    NO_SEARCH_DATA("9002"),
    FAILED_DATA("9003"),
    DELETE_DATA("9010"),
    NO_APPROVE("9011"),
    FAIL("9999");

    private final String value;

    ReturnStatus(String value) {
        this.value = value;
    }

    @MappedTypes(ReturnStatus.class)
    public static class ErrorStatusHandler extends BaseTypeHandler<ReturnStatus> {

        @Override
        public void setNonNullParameter(PreparedStatement ps, int i, ReturnStatus parameter, JdbcType jdbcType)
                throws SQLException {
            ps.setString(i, parameter.getValue());
        }

        @Override
        public ReturnStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
            String code = rs.getString(columnName);
            return getValue(code);
        }

        @Override
        public ReturnStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
            String code = rs.getString(columnIndex);
            return getValue(code);
        }

        @Override
        public ReturnStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
            String code = cs.getString(columnIndex);
            return getValue(code);
        }

        private ReturnStatus getValue(String value) {
            try {
                for (ReturnStatus enumValue: ReturnStatus.values()) {
                    if (enumValue.getValue().equals(value)) {
                        return enumValue;
                    }
                }
                return null;
            } catch (Exception e) {
                throw new TypeException("Can't make enum  '" + value + "'", e);
            }
        }
    }



}
