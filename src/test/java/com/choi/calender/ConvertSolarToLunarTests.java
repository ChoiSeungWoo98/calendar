package com.choi.calender;

import com.choi.calender.application.service.EventService;
import com.choi.calender.domain.api.event.LunarBean;
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
class ConvertSolarToLunarTests {

	@Resource
	EventService eventService;

	@Test
	public void send() throws UnsupportedEncodingException {
		try {
			String apiUrl = "http://apis.data.go.kr/B090041/openapi/service/LrsrCldInfoService/getSolCalInfo";

			String lunYear = "2024";
			String lunMonth = "03";
			String lunDay = "19";
			String type = "json";
			String serviceKey = "U3F3wbF6hrTzEdO7ERe2XmNqq7vCJhCxp/oVYKmcOqBM5M6b+s484JtBGvOJpBdC17kK1LheFWzXZlmEVJHg2w==";

			String urlWithParams = apiUrl
					+ "?lunYear=" + lunYear
					+ "&lunMonth=" + lunMonth
					+ "&lunDay=" + lunDay
					+ "&ServiceKey=" + URLEncoder.encode(serviceKey, "UTF-8")
					+ "&_type=" + type;

			URL url = new URL(urlWithParams);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				StringBuilder response = new StringBuilder();

				while ((line = reader.readLine()) != null) {
					response.append(line);
				}

				reader.close();

				ObjectMapper objectMapper = new ObjectMapper();
				Map<String, Object> resultMap = objectMapper.readValue(response.toString(), Map.class);
				LunarBean item = new LunarBean().convertMapToBean((Map<String, Object>) ((Map<String, Object>) ((Map<String, Object>) ((Map<String, Object>) resultMap.get("response")).get("body")).get("items")).get("item"), 999999);
				System.out.println(item);
			} else {
				System.out.println("API 요청 실패. 응답 코드: " + responseCode);
			}

			connection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
