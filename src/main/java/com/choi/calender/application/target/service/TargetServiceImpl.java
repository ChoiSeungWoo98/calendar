package com.choi.calender.application.target.service;

import com.choi.calender.application.diary.dto.DiaryDto;
import com.choi.calender.application.target.dto.TargetDto;
import com.choi.calender.domain.api.DiaryBean;
import com.choi.calender.domain.api.SearchTargetBean;
import com.choi.calender.domain.api.TargetBean;
import com.choi.calender.mapper.DiaryMapper;
import com.choi.calender.mapper.TargetMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TargetServiceImpl implements TargetService {

    @Resource
    private TargetMapper targetMapper;

    @Override
    public String insertTarget(TargetDto targetDto) {
        TargetBean targetBean = new TargetBean().convertBeanToDto(targetDto);
        return targetMapper.insertTarget(targetBean) == 1
                ? "새로운 목표를 향해 화이팅!"
                : "저장에 실패했습니다. 코드를 수정해주세요.";
    }

    @Override
    public List<TargetDto> selectYearTarget(SearchTargetBean searchTargetBean) {
        return targetMapper.selectYearTarget(searchTargetBean).stream().map(targetBean -> new TargetDto().convertBeanToDto(targetBean)).collect(Collectors.toList());
    }

    @Override
    public List<TargetDto> selectMonthTarget(SearchTargetBean searchTargetBean) {
        return targetMapper.selectMonthTarget(searchTargetBean).stream().map(targetBean -> new TargetDto().convertBeanToDto(targetBean)).collect(Collectors.toList());
    }
}