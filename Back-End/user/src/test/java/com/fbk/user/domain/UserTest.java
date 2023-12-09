
package com.fbk.user.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    public void testUserGettersAndSetters() {
        // Creare un'istanza di User
        User user = new User();

        // Impostare i valori utilizzando i metodi setter
        user.setId(1);
        user.setName("TestName");
        user.setCognome("TestCognome");

        // Testare i metodi getter per garantire che restituiscano i valori attesi
        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getName()).isEqualTo("TestName");
        assertThat(user.getCognome()).isEqualTo("TestCognome");
    }

    @Test
    public void testUserConstructor() {
        // Creare un'istanza di User utilizzando il costruttore
        User user = new User(1, "TestName", "TestCognome");

        // Testare i metodi getter per garantire che il costruttore abbia impostato correttamente i valori
        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getName()).isEqualTo("TestName");
        assertThat(user.getCognome()).isEqualTo("TestCognome");
    }
}
