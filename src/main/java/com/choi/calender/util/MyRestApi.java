package com.choi.calender.util;

import com.choi.calender.application.service.EventService;
import com.choi.calender.domain.api.event.NationalHolidayBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MyRestApi {

    @Resource
    EventService eventService;

    public void sendNatureHoliday(String year) {
        try {
            String apiUrl = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getHoliDeInfo";
            String serviceKey = "U3F3wbF6hrTzEdO7ERe2XmNqq7vCJhCxp/oVYKmcOqBM5M6b+s484JtBGvOJpBdC17kK1LheFWzXZlmEVJHg2w==";
            int numOfRows = 100;
            String type = "json";
            String methodType = "GET";

            String urlWithParams = apiUrl
                    + "?solYear=" + year
                    + "&ServiceKey=" + URLEncoder.encode(serviceKey, "UTF-8")
                    + "&numOfRows=" + numOfRows
                    + "&_type=" + type;

            conection(urlWithParams, methodType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void conection(String urlWithParams, String methodType) throws IOException {
        URL url = new URL(urlWithParams);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.setRequestMethod(methodType);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String response = readResponseValue(connection);
                List<NationalHolidayBean> list = convertHolidayData(response);
                eventService.insertEvents(list);
            } else {
                System.out.println("API 요청 실패. 응답 코드: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    private String readResponseValue(HttpURLConnection connection) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return response.toString();
    }

    private List<NationalHolidayBean> convertHolidayData(String response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> resultMap = objectMapper.readValue(response.toString(), Map.class);
        Map<String, Object> items = (Map<String, Object>) ((Map<String, Object>) ((Map<String, Object>) resultMap.get("response")).get("body")).get("items");
        List<Map<String, Object>> item = (List<Map<String, Object>>) items.get("item");
        return item.stream().map(it -> new NationalHolidayBean().convertMapToBean(it)).collect(Collectors.toList());
    }
}
