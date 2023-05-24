package com.ezen;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)// 웹서버를 사용하지 않는 테스트
public class BoardControllerTest2 {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testHello() throws Exception {
		System.out.println("===> MockHttpServletRequest testHello() 결과");
		mockMvc.perform(get("/test").param("name", "SeHyun"))
			.andExpect(status().isOk())	
			.andExpect(content().string("Test: SeHyun"))
			.andDo(print());
	}
	
	
}
