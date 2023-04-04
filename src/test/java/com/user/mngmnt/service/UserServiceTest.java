package com.user.mngmnt.service;

import com.user.mngmnt.model.User;
import com.user.mngmnt.repository.UserRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest extends TestCase {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;


    public UserServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearchBy() {
        List<User> users = userService.searchBy("ab", "lastname");
        assertNotNull(users);
    }

    @Test
    public void testSearchBy_firstName_success() {
        List<User> users = Arrays.asList( createUser("arun", "kumar"), createUser("arjun", "Mohanan"), createUser("arrahman", "P"));
        Mockito.when(userRepository.findByFirstNameIgnoreCaseContaining("ar")).thenReturn(users);
        List<User> usersActual = userService.searchBy("ar", "firstName");
        assertNotNull(usersActual);
        assertEquals(3, usersActual.size());
        assertEquals("arun", usersActual.get(0).getFirstName());
    }

    @Test
    public void testSearchBy_lastName_success() {
        List<User> users = Arrays.asList( createUser("Abdur", "Rahuman"), createUser("KR", "Rahul"));
        Mockito.when(userRepository.findByLastNameIgnoreCaseContaining("Ra")).thenReturn(users);
        List<User> usersActual = userService.searchBy("Ra", "lastName");

        assertNotNull(usersActual);
        assertEquals(2, usersActual.size());
    }

    @Test
    public void testSearchBy_lastNameBlockCalled_success() {
        userService.searchBy("Ra", "lastName");
        Mockito.verify(userRepository).findByLastNameIgnoreCaseContaining("Ra");
    }

    @Test
    public void testSearchBy_emailBlock_success() {
        List<User> users = Arrays.asList( createUser("Abdur", "Rahuman"), createUser("KR", "Rahul"));
        Mockito.when(userRepository.findByEmailIgnoreCaseContaining("Ra")).thenReturn(users);
        userService.searchBy("Ra@gmail.com", "email");
        Mockito.verify(userRepository).findByEmailIgnoreCaseContaining("Ra@gmail.com");
    }

    public User createUser (String firstName, String lastName) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }


}