package com.ezen;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

//@RunWith
@WebMvcTest
public class BoardControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testHello() throws Exception {
		mockMvc.perform(get("/test").param("name", "SeHyun"))
			.andExpect(status().isOk())	
			.andExpect(content().string("Test: SeHyun"))
			.andDo(print());
	}
}
