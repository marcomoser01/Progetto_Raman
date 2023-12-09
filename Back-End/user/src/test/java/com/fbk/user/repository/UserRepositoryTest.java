package com.fbk.user.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.fbk.user.domain.User;
import com.fbk.user.services.UserService;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserService userService;

    @Test
    public void testFindById() {
        // Creazione di un utente fittizio
        int userId = 1;
        User mockedUser = new User();
        mockedUser.setId(userId);
        mockedUser.setName("TestName");
        mockedUser.setCognome("TestCognome");

        // Mocking del comportamento del metodo findById del repository
        when(userRepositoryMock.findById(userId)).thenReturn(Optional.of(mockedUser));

        // Chiamata al metodo del servizio che utilizza il repository
        User foundUser = userService.getUser(String.valueOf(userId));

        // Assert
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(userId);
        assertThat(foundUser.getName()).isEqualTo("TestName");
        assertThat(foundUser.getCognome()).isEqualTo("TestCognome");
    }

    @Test
    public void testSaveUser() {
        // Creazione di un utente fittizio da salvare
        User userToSave = new User();
        userToSave.setName("TestName");
        userToSave.setCognome("TestCognome");

        // Mocking del comportamento del metodo save del repository
        when(userRepositoryMock.save(userToSave)).thenReturn(userToSave);

        // Chiamata al metodo del servizio che salva l'utente utilizzando il repository
        User savedUser = userService.saveUser(userToSave);

        // Assert
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getName()).isEqualTo("TestName");
        assertThat(savedUser.getCognome()).isEqualTo("TestCognome");
    }
}
