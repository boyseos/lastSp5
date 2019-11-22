package com.temp.web.user;

import static org.junit.Assert.*;
import javax.servlet.ServletContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.mock.web.MockServletContext;
import  org.springframework.test.context.ContextConfiguration;
import  org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import  org.springframework.test.context.web.WebAppConfiguration;
import  org.springframework.test.web.servlet.MockMvc;
import  org.springframework.test.web.servlet.setup.MockMvcBuilders;
import  org.springframework.web.context.WebApplicationContext;
import  org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.temp.web.ctx.RootConfig;
import com.temp.web.ctx.WebConfig;
import com.temp.web.ctx.ServletConfig;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServletConfig.class,WebConfig.class,RootConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class UserServiceTest {
	@Autowired UserService userService;
	@Test
	public void testYy() {
		assertThat(userService.selectall(), is(equalTo("50")));
	}

}
