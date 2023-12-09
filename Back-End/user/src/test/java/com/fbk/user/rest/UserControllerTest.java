package com.fbk.user.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fbk.user.domain.User;
import com.fbk.user.services.UserService;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Inizializza i mock
    }

    @Test
    public void testListUsers() {
        List<User> userList = new ArrayList<>();
        // Aggiungi utenti alla lista userList

        when(userService.getAllUsers()).thenReturn(userList);

        List<User> result = userController.listUsers();

        assertEquals(userList, result);
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testGetUser() {
        String userId = "1";
        User expectedUser = new User(); // Crea un utente di esempio
        when(userService.getUser(userId)).thenReturn(expectedUser);

        User result = userController.getUser(userId);

        assertEquals(expectedUser, result);
        verify(userService, times(1)).getUser(userId);
    }

    @Test
    public void testAddUser() {
        User user = new User(); // Crea un nuovo utente per il test
        when(userService.saveUser(user)).thenReturn(user);

        User result = userController.addUser(user);

        assertEquals(user, result);
        verify(userService, times(1)).saveUser(user);
    }

    @Test
    public void testAddUsers() {
        List<User> users = new ArrayList<>(); // Aggiungi utenti alla lista users

        when(userService.saveUsers(users)).thenReturn(users);

        List<User> result = userController.addUsers(users);

        assertEquals(users, result);
        verify(userService, times(1)).saveUsers(users);
    }
}
