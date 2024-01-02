package org.gameshop.management;

import org.gameshop.management.mock.UserDaoMock;
import org.gameshop.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {
    private UserDaoMock userDao;

    @BeforeEach
    void setUp() {
        userDao = new UserDaoMock();
    }

    @Test
    void testCreateUser() {
        User user = new User(1L, "TestUser", "test_user", null, "test123", 100.00);
        assertDoesNotThrow(() -> userDao.createUser(user));
        assertNotNull(user.getId());
    }

    @Test
    void testGetUserById() {
        User user = new User(null, "TestUser", "test_user", null, "test123", 100);
        userDao.createUser(user);

        Long userId = user.getId();
        User retrievedUser = assertDoesNotThrow(() -> userDao.getUserById(userId));
        assertNotNull(retrievedUser);
        assertEquals(user.getName(), retrievedUser.getName());
        assertEquals(user.getNickname(), retrievedUser.getNickname());
        assertEquals(user.getBirthday(), retrievedUser.getBirthday());
        assertEquals(user.getPassword(), retrievedUser.getPassword());
        assertEquals(user.getAmount(), retrievedUser.getAmount());
    }

    @Test
    void testUpdateUser() {
        UserDaoMock userDao = new UserDaoMock();
        User user = new User(1L, "TestUser", "test_user", null, "test123", 100.00);

        assertDoesNotThrow(() -> userDao.createUser(user));
        Long userId = user.getId();

        user.setName("UpdatedName");
        assertDoesNotThrow(() -> userDao.updateUser(user));

        User updatedUser = assertDoesNotThrow(() -> userDao.getUserById(userId));
        assertNotNull(updatedUser);
        assertEquals("UpdatedName", updatedUser.getName());
    }

    @Test
    void testGetUserByNicknameAndPassword() {
        UserDaoMock userDao = new UserDaoMock();
        User user = new User(1L, "TestUser", "test_user", null, "test123", 100.00);

        assertDoesNotThrow(() -> userDao.createUser(user));
        User retrievedUser = assertDoesNotThrow(() -> userDao.getUserByNicknameAndPassword("test_user", "test123"));
        assertNotNull(retrievedUser);
        assertEquals(user.getName(), retrievedUser.getName());
        assertEquals(user.getNickname(), retrievedUser.getNickname());
        assertEquals(user.getBirthday(), retrievedUser.getBirthday());
        assertEquals(user.getPassword(), retrievedUser.getPassword());
        assertEquals(user.getAmount(), retrievedUser.getAmount());
    }

}
