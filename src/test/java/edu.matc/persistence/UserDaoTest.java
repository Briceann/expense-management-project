package edu.matc.persistence;

import edu.matc.entity.Expense;
import edu.matc.entity.User;
import edu.matc.test.util.Database;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.Entity;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {

    UserDao userDao;

    @BeforeEach
    public void setUp() {
        userDao = new UserDao();
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
    }

    @Test
    void getByUserID() {
        User retrievedUser = userDao.getUserById(1);
        assertNotNull(retrievedUser);
        assertEquals("Jenny", retrievedUser.getFirstName());

    }

    @Test
    void updateUser() {
        User userToUpdate = userDao.getUserById(1);
        userToUpdate.setFirstName("Jenny");
        userDao.update(userToUpdate);

        User actualUser = userDao.getUserById(1);
        assertEquals("Jenny", actualUser.getFirstName());
    }

    @Test
    void insertUser() {
        User userToInsert = new User("Dave", "Keys", "dkeys", "mdkeys04@gmail.com", "dkeys02");
        int insertedUserId = userDao.insertUser(userToInsert);
        assertNotEquals(0, insertedUserId);
        User insertedUser = userDao.getUserById(insertedUserId);
        assertEquals("Dave", insertedUser.getFirstName());
    }

    @Test
    void deleteUser() {
        userDao.delete(userDao.getUserById(1));
        assertNull(userDao.getUserById(1));
    }

    @Test
    void getAllUsers() {
        List<User> users = userDao.getAllUsers();
        assertEquals(3, users.size());
    }
}
