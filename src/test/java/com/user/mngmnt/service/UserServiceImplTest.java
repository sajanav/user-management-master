package com.user.mngmnt.service;

import com.user.mngmnt.model.User;
import com.user.mngmnt.repository.UserRepository;
import org.junit.After;import org.junit.Before;import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class UserServiceImplTest {
  @InjectMocks private UserService userService = new UserServiceImpl();

  @Mock UserRepository userRepository;

  @Autowired private List<User> users;
  @Autowired private String[] criteria = {"firstName", "lastName", "email"};

@Before
public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    User user = new User();
    user.setFirstName("Juan");
    user.setLastName("Dela Cruz");
    user.setEmail("juan.delacruz@gmail.com");
    this.users.add(user);
  }

    @Test
    public void testSearchBy() {
      Mockito.when(userRepository.findByFirstNameIgnoreCaseContaining(Mockito.anyString()))
        .thenReturn(users);
      Mockito.when(userRepository.findByLastNameIgnoreCaseContaining(Mockito.anyString())).thenReturn(users);
      Mockito.when(userRepository.findByEmailIgnoreCaseContaining(Mockito.any())).thenReturn(users);

      assertEquals(userService.searchBy(Mockito.anyString(),criteria[0]).get(0).getFirstName(), user.getFirstName());
      assertEquals(userService.searchBy(Mockito.anyString(),criteria[1]).get(0).getLastName(), user.getLastName());
      assertEquals(userService.searchBy(Mockito.anyString(),criteria[2]).get(0).getEmail(), user.getEmail());
      assertNotNull(userService.searchBy(Mockito.anyString(), ""));
      }
}