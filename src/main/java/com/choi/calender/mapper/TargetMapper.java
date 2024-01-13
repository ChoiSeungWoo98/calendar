package com.choi.calender.mapper;

import com.choi.calender.domain.api.target.SearchTargetBean;
import com.choi.calender.domain.api.target.TargetBean;
import com.choi.calender.domain.api.target.TodoTargetBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TargetMapper {

    int insertTarget(TargetBean targetBean);

    int updateSuccessYn(TargetBean targetBean);

    int insertTodoSuccessYn(TodoTargetBean todoTargetBean);

    List<TargetBean> selectTarget(SearchTargetBean searchTargetBean);

    List<TargetBean> selectRepeatTodoTarget(SearchTargetBean searchTargetBean);

}