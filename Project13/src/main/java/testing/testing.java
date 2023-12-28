package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        User mockUser = new User();
        mockUser.setId(userId);
        Mockito.when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(mockUser));

        User result = userService.getUserById(userId);

        assertNotNull(result);
        assertEquals(userId, result.getId());
    }

}

@DataJpaTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Lemon");
        user.setEmail("johnlemon@gmail.com");
        User savedUser = userService.saveUser(user);
        assertNotNull(savedUser.getId());
        assertEquals("John", savedUser.getFirstName());
        assertEquals("Lemon", savedUser.getLastName());
        assertEquals("johnlemon@gmail.com", savedUser.getEmail());
    }

}

    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setFirstName("John");
        existingUser.setLastName("Lemon");
        existingUser.setEmail("johnlemon@gmail.com");

        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setFirstName("Lena");
        updatedUser.setLastName("Donna");
        updatedUser.setEmail("lennadona@gmail.com");

        Mockito.when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(existingUser));
        userService.updateUser(userId, updatedUser);

        Mockito.verify(userRepository, Mockito.times(1)).save(updatedUser);
    }
}