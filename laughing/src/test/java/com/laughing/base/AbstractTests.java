package com.laughing.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ContextConfiguration("classpath:/config/spring/root.xml")
public class AbstractTests {

	@Autowired
	protected WebApplicationContext wac;

}
