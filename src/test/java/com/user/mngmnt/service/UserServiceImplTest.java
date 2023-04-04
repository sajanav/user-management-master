package com.user.mngmnt.service;

import static org.junit.jupiter.api.Assertions.*;
import com.user.mngmnt.model.User;
import com.user.mngmnt.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Test class for User Service")
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Verify that FirstName, LastName, or  Email is " +
            "returned depending on the criteria and keyword")
    void testSearchBy() {

        List<User> users = new ArrayList<>();
        String[] criteria = {"firstName", "lastName", "email"};

        User user = new User();
        user.setFirstName("Varsha");
        user.setLastName("Suresh");
        user.setEmail("varsha.suresh@ust.com");
        users.add(user);

        Mockito.when(userRepository.findByFirstNameIgnoreCaseContaining(Mockito.anyString())).thenReturn(users);
        Mockito.when(userRepository.findByLastNameIgnoreCaseContaining(Mockito.anyString())).thenReturn(users);
        Mockito.when(userRepository.findByEmailIgnoreCaseContaining(Mockito.any())).thenReturn(users);

        assertEquals(userService.searchBy(Mockito.anyString(),criteria[0]).get(0).getFirstName(), user.getFirstName());
        assertEquals(userService.searchBy(Mockito.anyString(),criteria[1]).get(0).getLastName(), user.getLastName());
        assertEquals(userService.searchBy(Mockito.anyString(),criteria[2]).get(0).getEmail(), user.getEmail());
        assertNotNull(userService.searchBy(Mockito.anyString(), ""));
    }
}