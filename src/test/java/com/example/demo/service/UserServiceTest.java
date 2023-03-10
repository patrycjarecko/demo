package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveUser() {
        // given
        User user = new User("janedoe", "Puyc9r4.", "Jane", "Doe");

        // when
        when(userRepository.save(user)).thenReturn(user);

        // then
        User savedUser = userService.createUser(user);
        assertEquals("Jane", savedUser.getFirstName());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void shouldGetAllUsers() {
        // given
        List<User> list = new ArrayList<>();
        list.add(new User("janedoe", "Puyc9r4.", "Jane", "Doe"));
        list.add(new User("johndoe", "U7nd54j.", "John", "Doe"));

        // when
        when(userRepository.findAll()).thenReturn(list);

        // then
        List<User> testList = userService.getUsers();
        assertEquals(2, testList.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void shouldGetUserByUsername() {
        // given
        User user1 = new User("janedoe", "Puyc9r4.", "Jane", "Doe");

        // when
        when(userRepository.findByUsername("janedoe")).thenReturn(Optional.of(user1));

        // then
        User user = userService.loadUserByUsername("janedoe");// error
        assertEquals("Jane", user.getFirstName());
        assertEquals("Doe", user.getLastName());
    }

    @Test
    void shouldChangePassword() throws Exception {
        // given
        User user = new User("janedoe", "Puyc9r4.", "Jane", "Doe");
        user.setId(1L);

        // when
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        // then
        User updatedUser = userService.changePassword(user, "A8vem3o.", "Puyc9r4.");
        assertEquals("A8vem3o.", updatedUser.getPassword());
        verify(userRepository, times(1)).save(user);
    }
}
