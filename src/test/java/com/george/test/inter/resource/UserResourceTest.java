package com.george.test.inter.resource;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserResourceTest {

	@Autowired
	public WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void test01findUserByIdSuccess() throws Exception {
		String url = "/users/1";
		this.mvc.perform(get(url)).andExpect(status().isOk()).andExpect(jsonPath("name", equalTo("George")))
				.andExpect(jsonPath("email", equalTo("george@mail.com")));
	}

	@Test
	public void test02findUserByIdNotFound() throws Exception {
		String url = "/users/5";
		this.mvc.perform(get(url)).andExpect(status().isNotFound());
	}

	@Test
	public void test03deleteUserByIdSuccess() throws Exception {
		String url = "/users/2";
		this.mvc.perform(delete(url)).andExpect(status().isNoContent());
	}

	@Test
	public void test04eleteUserByIdNotFound() throws Exception {
		String url = "/users/5";
		this.mvc.perform(delete(url)).andExpect(status().isNotFound());
	}

	@Test
	public void test05findUserSingleDigitsByIdSuccess() throws Exception {
		String url = "/users/1/single-digits";
		this.mvc.perform(get(url)).andExpect(status().isOk())
				.andExpect(content().json("[\n" + "  {\n" + "    \"id\": 1,\n" + "    \"digit\": 123,\n"
						+ "    \"times\": 1,\n" + "    \"result\": 6\n" + "  }\n" + "]"));
	}

	@Test
	public void test06findUserSingleDigitsByIdNotFound() throws Exception {
		String url = "/users/5/single-digits";
		this.mvc.perform(get(url)).andExpect(status().isNotFound());
	}

	@Test
	public void test07saveUserSuccess() throws Exception {
		String url = "/users";
		this.mvc.perform(post(url)
				.content("{ \"name\": \"Tester2\", \"email\": \"tester2@mail.com\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", is("http://localhost/users/3")))
				.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void test08saveUserFail() throws Exception {
		String url = "/users";
		this.mvc.perform(post(url)
				.content("{ \"name\": \"\", \"email\": \"tester2@mail.com\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void test09saveUserFail() throws Exception {
		String url = "/users";
		this.mvc.perform(post(url)
				.content("{ \"nme\": \"fail\", \"email\": \"tester2@mail.com\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void test10updateUserSuccess() throws Exception {
		String url = "/users/1";
		this.mvc.perform(put(url)
				.content("{ \"name\": \"success test\", \"email\": \"success@mail.com\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
	
	@Test
	public void test11updateUserNotFound() throws Exception {
		String url = "/users/5";
		this.mvc.perform(put(url)
				.content("{ \"name\": \"not found test\", \"email\": \"success@mail.com\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void test12updateUserFail() throws Exception {
		String url = "/users/1";
		this.mvc.perform(put(url)
				.content("{ \"nme\": \"fail test\", \"email\": \"success@mail.com\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
}
