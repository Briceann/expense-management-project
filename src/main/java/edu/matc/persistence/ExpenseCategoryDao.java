package edu.matc.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import edu.matc.entity.ExpenseCategory;
//import edu.matc.persistence.HibernateUtil;

import edu.matc.entity.ExpenseCategory;
import org.hibernate.annotations.GenericGenerator;

public class ExpenseCategoryDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Get ExpenseCategory by Id
     *
     * @param id the category Id
     * @return the ExpenseCategory object
     */
    public ExpenseCategory getCategoryById(int id) {
        //ExpenseCategory expenseCategory = null;
        Session session = sessionFactory.openSession();
        //Transaction transaction = session.beginTransaction();
        ExpenseCategory expenseCategory = session.get(ExpenseCategory.class, id);
        //transaction.commit();
        session.close();
        return expenseCategory;
    }
}
