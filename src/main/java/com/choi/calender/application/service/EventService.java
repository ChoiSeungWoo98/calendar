package com.choi.calender.application.service;

import com.choi.calender.application.dto.file.FileDto;
import com.choi.calender.domain.api.event.NationalHolidayBean;
import com.choi.calender.domain.api.file.SearchFileBean;

import java.io.IOException;
import java.util.List;

public interface EventService {

    boolean isYearEvent(String year);

    List<FileDto> selectEventList(SearchFileBean searchFileBean);

    boolean insertEvents(List<NationalHolidayBean> list) throws IOException;

}
