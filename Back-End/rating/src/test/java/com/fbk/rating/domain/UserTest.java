package com.fbk.rating.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        User user = new User();
        Integer id = 1;
        String name = "John Doe";

        // Act
        user.setId(id);
        user.setName(name);

        // Assert
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
    }

    @Test
    public void testDefaultConstructor() {
        // Arrange
        User user = new User();

        // Act and Assert
        assertNull(user.getId());
        assertNull(user.getName());
    }

    @Test
    public void testParameterizedConstructor() {
        // Arrange
        Integer id = 1;
        String name = "John Doe";

        // Act
        User user = new User(id, name);

        // Assert
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
    }
}
