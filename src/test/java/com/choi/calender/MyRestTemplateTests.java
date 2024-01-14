package com.choi.calender;

import com.choi.calender.application.service.EventService;
import com.choi.calender.domain.api.event.NationalHolidayBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class MyRestTemplateTests {

	@Resource
	EventService eventService;

	@Test
	public void send() throws UnsupportedEncodingException {
		try {
			// API 요청 URL
			String apiUrl = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getHoliDeInfo";

			// API 요청 파라미터 설정
			String solYear = "2024";
			String serviceKey = "U3F3wbF6hrTzEdO7ERe2XmNqq7vCJhCxp/oVYKmcOqBM5M6b+s484JtBGvOJpBdC17kK1LheFWzXZlmEVJHg2w==";
			int numOfRows = 100;

			// URL 및 파라미터 설정
			String urlWithParams = apiUrl + "?solYear=" + solYear
					+ "&ServiceKey=" + URLEncoder.encode(serviceKey, "UTF-8")
					+ "&numOfRows=" + numOfRows + "&_type=json";

			// URL 객체 생성
			URL url = new URL(urlWithParams);

			// HTTP 연결 객체 생성
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// HTTP 요청 메소드 설정
			connection.setRequestMethod("GET");

			// 응답 코드 확인
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				// 응답 데이터 읽기
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				StringBuilder response = new StringBuilder();

				while ((line = reader.readLine()) != null) {
					response.append(line);
				}

				reader.close();

				// 응답 데이터 출력
				// ObjectMapper 생성
				ObjectMapper objectMapper = new ObjectMapper();

				// JSON 문자열을 Map으로 변환
				Map<String, Object> resultMap = objectMapper.readValue(response.toString(), Map.class);
				Map<String, Object> items = (Map<String, Object>) ((Map<String, Object>) ((Map<String, Object>) resultMap.get("response")).get("body")).get("items");
				List<Map<String, Object>> item = (List<Map<String, Object>>) items.get("item");
				List<NationalHolidayBean> list = item.stream().map(it -> new NationalHolidayBean().convertMapToBean(it)).collect(Collectors.toList());
				eventService.insertEvents(list);
			} else {
				System.out.println("API 요청 실패. 응답 코드: " + responseCode);
			}

			// 연결 해제
			connection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
