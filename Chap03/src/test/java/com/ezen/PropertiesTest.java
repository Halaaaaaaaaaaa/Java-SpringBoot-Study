package com.ezen;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest(properties= {"author.name=SeHyun", "author.age=29", "author.nation=SouthKorea"})
public class PropertiesTest {
	
	@Autowired
	Environment environment;
	
	@Test
	public void testEnviron() {
		System.out.println("===> testEnviron() properties Test");
		System.out.println("이름: " + environment.getProperty("author.name"));
		System.out.println("나이: " + environment.getProperty("author.age"));
	}

}
