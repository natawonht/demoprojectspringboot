package com.ais.demoproject.user;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.*;

import com.ais.demoproject.model.Result;
import com.ais.demoproject.model.User;
import com.ais.demoproject.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TestUserController {
    
    @MockBean
	UserService mockUserService;

	@Autowired
	private MockMvc mvc;

    final String uri = "/user";

    String mockUserName = "userTest";
    String mockMessageResponse = "SUCCESS";
    User mockUser = new User();
    List<User> mockListUser = new ArrayList<>();
    
    @BeforeEach
	void setup() {
        mockUser.setUserName(mockUserName);
        mockUser.setFirstName("Tester01");
        mockUser.setLastName("TT");
        mockUser.setEmail("tester@mail.com");
        mockListUser.add(mockUser);

        when(mockUserService.hello()).thenReturn("Hello World!");
        when(mockUserService.sayHello(mockUserName)).thenReturn("Hello, userTest!");
        when(mockUserService.addUser(mockUser)).thenReturn(mockUser);
        when(mockUserService.getUser()).thenReturn(mockListUser);
        when(mockUserService.getUserById(mockUserName)).thenReturn(mockUser);

    }

    @Test
	void testGetHelloSuccess() throws Exception {

        // Mock Expect Return Response
		String expectResponse = new ObjectMapper().writer()
				.writeValueAsString(new Result(200, "Hello World!", new ArrayList<>()));

        // Set Request for test
		mvc.perform(MockMvcRequestBuilders.get(uri+"/hello")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string(expectResponse));
	}

    @Test
	void testGetSayHelloSuccess() throws Exception {

        // Mock Expect Return Response
		String expectResponse = new ObjectMapper().writer()
				.writeValueAsString(new Result(200, "Hello, userTest!", new ArrayList<>()));

        // Set Request for test
		mvc.perform(MockMvcRequestBuilders.get(uri+"/sayHello?userName="+mockUserName)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string(expectResponse));
	}

    @Test
	void testAddUserSuccess() throws Exception {

        // Mock String Request
        String request = new ObjectMapper().writer().writeValueAsString(mockUser);

        // Mock Expect Return Response
		String expectResponse = new ObjectMapper().writer()
				.writeValueAsString(new Result(200, mockMessageResponse, new ArrayList<>()));

        // Set Request for test
		mvc.perform(MockMvcRequestBuilders.post(uri+"/addUser")
				.contentType(MediaType.APPLICATION_JSON)
                .content(request))
				.andExpect(content().string(expectResponse));
	}

    @Test
	void testGetUserSuccess() throws Exception {

        // Mock Expect Return Response
		String expectResponse = new ObjectMapper().writer()
				.writeValueAsString(new Result(200, mockMessageResponse, mockListUser));

        // Set Request for test
		mvc.perform(MockMvcRequestBuilders.get(uri+"/getUser")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string(expectResponse));
	}

    @Test
	void testGetUserByUserNameSuccess() throws Exception {

        // Mock Expect Return Response
		String expectResponse = new ObjectMapper().writer()
				.writeValueAsString(new Result(200, mockMessageResponse, mockUser));

        // Set Request for test
		mvc.perform(MockMvcRequestBuilders.get(uri+"/getUser/"+mockUserName)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string(expectResponse));
	}

}
