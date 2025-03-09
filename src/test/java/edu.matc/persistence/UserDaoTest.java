package edu.matc.persistence;

import edu.matc.entity.User;
import edu.matc.util.Database;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {

    UserDao userDao;
    private Session HibernateUtil;

    @BeforeEach
    void setUp() {
        userDao = new UserDao();
        Database database = Database.getInstance();
        System.out.println("Running cleanDB.sql...");
        database.runSQL("cleanDB.sql");
        System.out.println("cleanDB.sql executed successfully.");

        // Log initial database state
        List<User> initialUsers = userDao.getAllUsers();
        System.out.println("Initial Users after cleanDB.sql: " + initialUsers.size());
        for (User user : initialUsers) {
            System.out.println(user.getUserId() + " - " + user.getFirstName() + " " + user.getLastName());
        }

        // Verify initial state
        assertEquals(3, initialUsers.size(), "Database state is not as expected after running cleanDB.sql");

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
        userToUpdate.setLastName("Jackson");
        userDao.update(userToUpdate);

        User actualUser = userDao.getUserById(1);
        assertEquals("Jackson", actualUser.getLastName());
    }

    @Test
    void insertUser() {
        User userToInsert = new User("Cyn", "Skai", "cskai", "cskai20@gmail.com", "cskai11");
        int insertedUserId = userDao.insertUser(userToInsert);
        assertNotEquals(0, insertedUserId);
        User insertedUser = userDao.getUserById(insertedUserId);
        assertEquals("Cyn", insertedUser.getFirstName());
    }

    @Test
    void deleteUser() {
        //userDao.delete(userDao.getUserById(2));
        //assertNull(userDao.getUserById(2));

        User userToDelete = userDao.getUserById(2);
        if (userToDelete != null) {
            userDao.delete(userToDelete);
            assertNull(userDao.getUserById(2));
        } else {
            fail("User with ID 2 does not exist");
        }
    }

    @Test
    void getAllUsers() {
        List<User> users = userDao.getAllUsers();
        System.out.println("Users after cleanDB.sql: " + users.size());
        assertEquals(3, users.size());
    }

    @AfterEach
    void tearDown() {
        // Log state after each test
        List<User> users = userDao.getAllUsers();
        System.out.println("Users after test: " + users.size());
    }
}
