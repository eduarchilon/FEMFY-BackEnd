package com.femfy.femfyapi.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.femfy.femfyapi.entity.User;

@SpringBootTest
@WebAppConfiguration
class UserControllerTest {

	private final static String BASE_URL="/api/v1/user";
	MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	void saveUpdate() {
		try {
			User user = builduser();
			
			MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(MapToJson(user))).andReturn();
			
			assertEquals(200, mockMvcResult.getResponse().getStatus());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


	@Test
	void getUsers() {
		try {
			MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
					.accept(MediaType.APPLICATION_JSON)).andReturn();
			assertEquals(200, mockMvcResult.getResponse().getStatus());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String MapToJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
	
	private User builduser() {
		/*User user=User.builder()
				.age(20)
				.firstName("pepe")
				.lastName("lopez")
				.gender("M")
				.phone("123456789")
				.email("pi@gmail.com")
				.build();*/
		User user= new User();
		user.setAge(20);
		user.setEmail("peperr@gmail.com");
		user.setFirstName("pepe");
		//user.setGender("Masculino");
		user.setLastName("lopez");
		user.setPhone("123456789");
		return user;
	}

}
