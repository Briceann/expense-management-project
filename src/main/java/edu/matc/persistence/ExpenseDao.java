package edu.matc.persistence;

import edu.matc.entity.Expense;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Expense Dao class
 * @author Btaneh
 */
public class ExpenseDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Get expense by id.
     *
     * @param id Expense id.
     * @return Expense corresponding to the given id.
     */
    public Expense getById(int id) {
        Session session = sessionFactory.openSession();
        Expense expense = session.get(Expense.class, id);
        session.close();
        return expense;
    }

    /**
     * Update an expense.
     *
     * @param expense Expense to be updated.
     */
    public void update(Expense expense) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(expense);
        transaction.commit();
        session.close();
    }

    /**
     * Insert a new expense.
     *
     * @param expense Expense to be inserted.
     * @return The generated id for the new expense.
     */
    public int insert(Expense expense) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(expense);
        transaction.commit();
        session.close();
        return expense.getExpenseId();
    }

    /**
     * Delete an expense.
     *
     * @param expense Expense to be deleted.
     */
    public void delete(Expense expense) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(expense);
        transaction.commit();
        session.close();
    }

    /**
     * Return a list of all expenses.
     *
     * @return All expenses.
     */
    public List<Expense> getExpensesByUser(int userId) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Expense> query = session.getCriteriaBuilder().createQuery(Expense.class);
        Root<Expense> root = query.from(Expense.class);
        query.select(root).where(builder.equal(root.get("userId"), userId));
        List<Expense> expenses = session.createQuery(query).getResultList();

        logger.debug("The list of expenses: " + expenses);
        session.close();

        return expenses;
    }
}
