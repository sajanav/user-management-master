package com.user.mngmnt.service;

import com.user.mngmnt.model.User;
import com.user.mngmnt.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;
    List<User> users;

    public UserServiceImplTest() {
        MockitoAnnotations.initMocks(this);
    }
    public User createUser (String firstName, String lastName, String email) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        return user;
    }

    @BeforeEach
    void setUp() {
        users = Arrays.asList(createUser("John", "Wick", "johnw@gmail.com"),
                createUser("Alex", "kolenchiski", "alexk@yahoo.com"),
                createUser("Steve", "Waugh", "swaugh@gmail.com"));

    }

    @Test
    void searchBy_searchUserByFirstName_successfullySearchedUserByFirstName() {

        // given - pre-condition or setup
        String keyword = "john";
        String criteria= "firstName";
        given(userRepository.findByFirstNameIgnoreCaseContaining(keyword)).willReturn(users);

        // when - action or the behaviour that we are going test
        List<User> searchedUser = userService.searchBy(keyword, criteria);

        // then - verify the output
        assertNotNull(searchedUser);
        assertEquals(users.get(0).getFirstName(), searchedUser.get(0).getFirstName());
    }

    @Test
    void searchBy_searchUserByLastName_successfullySearchedUserByLastName() {

        // given - pre-condition or setup
        String keyword = "kolenchiski";
        String criteria= "lastName";
        given(userRepository.findByLastNameIgnoreCaseContaining(keyword)).willReturn(users);

        // when - action or the behaviour that we are going test
        List<User> searchedUser = userService.searchBy(keyword, criteria);

        // then - verify the output
        assertNotNull(searchedUser);
        assertEquals(users.get(1).getLastName(), searchedUser.get(1).getLastName());
    }

    @Test
    void searchBy_searchUserByEmail_successfullySearchedUserByEmail() {

        // given - pre-condition or setup
        String keyword = "swaugh";
        String criteria= "email";
        given(userRepository.findByEmailIgnoreCaseContaining(keyword)).willReturn(users);

        // when - action or the behaviour that we are going test
        List<User> searchedUser = userService.searchBy(keyword, criteria);

        // then - verify the output
        assertNotNull(searchedUser);
        assertEquals(users.get(2).getEmail(), searchedUser.get(2).getEmail());
    }

}