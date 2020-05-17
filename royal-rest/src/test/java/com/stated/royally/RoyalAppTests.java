package com.stated.royally;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RoyalAppTests {

	@Autowired private WebApplicationContext wac;

	@Test
	public void contextLoads() {
		ServletContext servletContext = wac.getServletContext();
		assertThat(servletContext, notNullValue());
		assertThat(servletContext, instanceOf(MockServletContext.class));
	}

}
