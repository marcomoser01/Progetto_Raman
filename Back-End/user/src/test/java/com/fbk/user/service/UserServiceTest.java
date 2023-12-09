package com.fbk.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fbk.user.domain.User;
import com.fbk.user.repository.UserRepository;
import com.fbk.user.services.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUser() {
        String id = "1";
        User user = new User();
        when(userRepositoryMock.findById(Integer.parseInt(id))).thenReturn(Optional.of(user));

        User foundUser = userService.getUser(id);

        assertThat(foundUser).isNotNull();
        assertThat(foundUser).isEqualTo(user);
        verify(userRepositoryMock, times(1)).findById(Integer.parseInt(id));
    }

    @Test
    public void testGetAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        when(userRepositoryMock.findAll()).thenReturn(userList);

        List<User> retrievedUsers = userService.getAllUsers();

        assertThat(retrievedUsers).isNotNull();
        assertThat(retrievedUsers).hasSize(2);
        verify(userRepositoryMock, times(1)).findAll();
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        when(userRepositoryMock.save(user)).thenReturn(user);

        User savedUser = userService.saveUser(user);

        assertThat(savedUser).isNotNull();
        verify(userRepositoryMock, times(1)).save(user);
    }

    @Test
    public void testSaveUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        when(userRepositoryMock.saveAll(userList)).thenReturn(userList);

        List<User> savedUsers = userService.saveUsers(userList);

        assertThat(savedUsers).isNotNull();
        assertThat(savedUsers).hasSize(2);
        verify(userRepositoryMock, times(1)).saveAll(userList);
    }
}
