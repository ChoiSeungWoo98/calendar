package com.choi.calender.application.impl;

import com.choi.calender.application.dto.file.FileDto;
import com.choi.calender.application.service.EventService;
import com.choi.calender.application.service.FileService;
import com.choi.calender.domain.api.event.NationalHolidayBean;
import com.choi.calender.domain.api.file.FileBean;
import com.choi.calender.domain.api.file.SearchFileBean;
import com.choi.calender.domain.value.FileIdentifier;
import com.choi.calender.mapper.EventMapper;
import com.choi.calender.mapper.FileMapper;
import com.choi.calender.util.Common;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Resource
    private EventMapper eventMapper;

    @Override
    public boolean isYearEvent(String year) {
        return eventMapper.isYearEvent(year);
    }

    @Override
    public List<FileDto> selectEventList(SearchFileBean searchFileBean) {
        return eventMapper.selectEventList(searchFileBean).stream().map(file -> new FileDto().convertBeanToDto(file)).collect(Collectors.toList());
    }

    @Override
    public boolean insertEvents(List<NationalHolidayBean> list) throws IOException {
        return eventMapper.insertEvents(list) >= 1
                ? true
                : false;
    }

}