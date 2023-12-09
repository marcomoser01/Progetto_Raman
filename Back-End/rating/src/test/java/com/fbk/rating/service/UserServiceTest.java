package com.fbk.rating.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.fbk.rating.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UserService userService = new UserService();

    @Test
    public void testGetUser() {
        String userId = "1";
        User expectedUser = new User(1, "JohnDoe");

        // Usare i matchers per tutti gli argomenti del metodo getForObject
        when(restTemplate.getForObject(anyString(), eq(User.class), eq(userId)))
                .thenReturn(expectedUser);

        User retrievedUser = userService.getUser(userId);

        assertEquals(expectedUser, retrievedUser);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(User.class), eq(userId));
        verifyNoMoreInteractions(restTemplate);
    }

}
