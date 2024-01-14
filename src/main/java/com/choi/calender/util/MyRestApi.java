package com.choi.calender.util;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
public class MyRestTemplate {

    private final RestTemplate restTemplate = new RestTemplate();

    public void send(String url, List<Map<String, String>> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        // 각각의 쿼리 파라미터를 추가
        for (Map<String, String> param : params) {
            try {
                // ServiceKey 값은 이미 인코딩된 상태이므로 디코딩하여 추가
                builder.queryParam(param.get("key"), URLDecoder.decode(param.get("value"), StandardCharsets.UTF_8.toString()));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        String finalUrl = builder.build(false).toString();

        ResponseEntity<String> response = restTemplate.getForEntity(finalUrl, String.class);

        // 응답 처리
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            // TODO: 응답 데이터 처리 로직 추가
            System.out.println(responseBody);
        } else {
            System.out.println("공휴일 api 실패");
        }
    }
}
