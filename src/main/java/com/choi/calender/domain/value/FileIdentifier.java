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
public enum FileIdentifier {
    Diary("diary");

    private final String value;

    FileIdentifier(String value) {
        this.value = value;
    }

    @MappedTypes(FileIdentifier.class)
    public static class ErrorStatusHandler extends BaseTypeHandler<FileIdentifier> {

        @Override
        public void setNonNullParameter(PreparedStatement ps, int i, FileIdentifier parameter, JdbcType jdbcType)
                throws SQLException {
            ps.setString(i, parameter.getValue());
        }

        @Override
        public FileIdentifier getNullableResult(ResultSet rs, String columnName) throws SQLException {
            String code = rs.getString(columnName);
            return getValue(code);
        }

        @Override
        public FileIdentifier getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
            String code = rs.getString(columnIndex);
            return getValue(code);
        }

        @Override
        public FileIdentifier getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
            String code = cs.getString(columnIndex);
            return getValue(code);
        }

        private FileIdentifier getValue(String value) {
            try {
                for (FileIdentifier enumValue: FileIdentifier.values()) {
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
