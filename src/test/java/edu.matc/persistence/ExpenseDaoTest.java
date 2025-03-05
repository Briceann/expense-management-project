package edu.matc.persistence;

import edu.matc.entity.Expense;
import edu.matc.entity.User;
import edu.matc.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ExpenseDaoTest {

    private ExpenseDao expenseDao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
    }

    @Test
    void getById() {
        expenseDao = new ExpenseDao();
        Expense retrievedExpense = expenseDao.getById(1);
        assertNotNull(retrievedExpense);
        assertEquals("February Rent", retrievedExpense.getDescription());
    }

    @Test
    void update() {
    }

    @Test
    void insert() {
    }

    @Test
    void delete() {
    }

    @Test
    void getExpensesByUser() {
    }
}
