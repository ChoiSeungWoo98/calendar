package com.choi.calender.mapper;

import com.choi.calender.domain.api.SearchTargetBean;
import com.choi.calender.domain.api.TargetBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TargetMapper {

    int insertTarget(TargetBean targetBean);

    List<TargetBean> selectYearTarget(SearchTargetBean searchTargetBean);

    List<TargetBean> selectMonthTarget(SearchTargetBean searchTargetBean);

}