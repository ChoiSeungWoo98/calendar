package com.choi.calender.application.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListToStringTypeHandler extends BaseTypeHandler<List<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<String> strings, JdbcType jdbcType) throws SQLException {
        // List<String>을 문자열로 변환하여 VARCHAR 컬럼에 저장
        String concatenatedString = strings.stream().collect(Collectors.joining(","));
        preparedStatement.setString(i, concatenatedString);
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        // 저장된 문자열을 다시 List<String>으로 변환하여 반환
        String concatenatedString = resultSet.getString(s);
        return Arrays.asList(concatenatedString.split(","));
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String concatenatedString = resultSet.getString(i);
        return Arrays.asList(concatenatedString.split(","));
    }

    @Override
    public List<String> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String concatenatedString = callableStatement.getString(i);
        return Arrays.asList(concatenatedString.split(","));
    }
}
