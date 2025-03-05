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


    public ExpenseCategory getCategoryById(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        ExpenseCategory expenseCategory = null;

        return expenseCategory;
    }
}
