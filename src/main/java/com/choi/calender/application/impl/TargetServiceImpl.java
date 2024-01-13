package com.choi.calender.application.impl;

import com.choi.calender.application.dto.TargetDto;
import com.choi.calender.application.dto.TodoTargetDto;
import com.choi.calender.application.service.TargetService;
import com.choi.calender.domain.api.SearchTargetBean;
import com.choi.calender.domain.api.TargetBean;
import com.choi.calender.domain.api.TodoTargetBean;
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
    public String updateSuccessYn(TargetDto targetDto) {
        TargetBean targetBean = new TargetBean().convertBeanToDto(targetDto);
        return targetMapper.updateSuccessYn(targetBean) == 1
                ? "Y".equals(targetBean.getSuccessYn()) ? "목표 달성했네요!! 고생하셨습니다!" : "목표 달성을 취소하였습니다! 달성을 위해 화이팅!"
                : "업데이트에 실패했습니다. 코드를 수정해주세요.";
    }

    @Override
    public String insertTodoSuccessYn(TodoTargetDto todoTargetDto) {
        TodoTargetBean todoTargetBean = new TodoTargetBean().convertDtoToBean(todoTargetDto);
        return targetMapper.insertTodoSuccessYn(todoTargetBean) == 1
                ? "Y".equals(todoTargetBean.getSuccessYn()) ? "목표 달성했네요!! 고생하셨습니다!" : "목표 달성을 취소하였습니다! 달성을 위해 화이팅!"
                : "Y".equals(todoTargetBean.getSuccessYn()) ? "목표 달성했네요!! 고생하셨습니다!" : "목표 달성을 취소하였습니다! 달성을 위해 화이팅!";
    }

    @Override
    public List<TargetDto> selectTarget(SearchTargetBean searchTargetBean) {
        List<TargetBean> targetBeanList = targetMapper.selectTarget(searchTargetBean);
        if(targetBeanList == null) {
            return null;
        }
        return targetBeanList.stream().map(targetBean -> new TargetDto().convertBeanToDto(targetBean)).collect(Collectors.toList());
    }

    @Override
    public List<TargetDto> selectRepeatTodoTarget(SearchTargetBean searchTargetBean) {
        List<TargetBean> targetBeanList = targetMapper.selectRepeatTodoTarget(searchTargetBean);
        if(targetBeanList == null) {
            return null;
        }
        return targetBeanList.stream().map(targetBean -> new TargetDto().convertBeanToDto(targetBean)).collect(Collectors.toList());
    }
}