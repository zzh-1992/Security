package com.grapefruit.postconsumer;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class PostconsumerApplicationTests {

	@Test
	void contextLoads() {
		//RestTemplate post请求
		HttpHeaders httpHeaders = new HttpHeaders();
		MediaType type=MediaType.parseMediaType("application/json;charset=UTF-8");
		httpHeaders.setContentType(type);

//        MultiValueMap<String, Object> map=new LinkedMultiValueMap<>();

		String url = "http://localhost:9999/post/A";
		RestTemplate restTemplate = new RestTemplate();

		//参数封装
		HashMap<String, Object> map = new HashMap<>();
		map.put("userName","ghghg");
		map.put("id",90);

		HttpEntity<Map<String, Object>> objectHttpEntity = new HttpEntity<>(map,httpHeaders);

		ResponseEntity<String> responseResultResponseEntity = restTemplate.postForEntity(url, objectHttpEntity, String.class);

		String result = responseResultResponseEntity.getBody();

		System.out.println("jieguo:"+ result);
	}
}

@Data
class Stu{
	String name;
	int id;
}


