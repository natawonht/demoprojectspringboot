package com.ais.demoproject.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.*;

import com.ais.demoproject.model.User;
import com.ais.demoproject.repository.UserRepository;
import com.ais.demoproject.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TestUserService {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    // Set variable mock
    String mockUserName = "userTest";
    User mockUser = new User();
    List<User> mockListUser = new ArrayList<>();

    @BeforeEach
    void setup() {
        mockUser.setUserName(mockUserName);
        mockUser.setFirstName("Tester01");
        mockUser.setLastName("TT");
        mockUser.setEmail("tester@mail.com");
        mockListUser.add(mockUser);

        lenient().when(userRepository.findAll()).thenReturn(mockListUser);
        lenient().when(userRepository.findUserByuserName(mockUserName)).thenReturn(mockUser);
        lenient().when(userRepository.save(mockUser)).thenReturn(mockUser);
    }

    @Test
    void testHelloSuccess(){
        String actual = userService.hello();
        assertEquals("Hello World!", actual);
    }


    @Test
    void testSayHelloSuccess(){
        String actual = userService.sayHello(mockUserName);
        assertEquals("Hello, userTest!", actual);
    }

    @Test
    void testAddUserSuccess(){
        User actual = userService.addUser(mockUser);
        assertEquals(mockUser, actual);
    }

    @Test
    void testGetUserSuccess(){
        List<User> actual = userService.getUser();
        assertEquals(mockListUser, actual);
    }

    @Test
    void testGetUserByIdSuccess(){
        User actual = userService.getUserById(mockUserName);
        assertEquals(mockUser, actual);
    }
    
}
