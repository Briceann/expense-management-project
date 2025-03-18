package edu.matc.controller;

import edu.matc.entity.Expense;
import edu.matc.entity.ExpenseCategory;
import edu.matc.entity.User;
import edu.matc.persistence.ExpenseCategoryDao;
import edu.matc.persistence.ExpenseDao;
import edu.matc.persistence.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/expense")
public class ExpenseServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ExpenseServlet.class);
    private final ExpenseDao expenseDao = new ExpenseDao();
    private final ExpenseCategoryDao categoryDao = new ExpenseCategoryDao();
    private final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            listExpenses(request, response);
        } else {
            switch (action) {
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteExpense(request, response);
                    break;
                default:
                    listExpenses(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            updateExpense(request, response);
        } else {
            addExpense(request, response);
        }
    }

    private void listExpenses(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Expense> expenses = expenseDao.getAllExpenses();

        if (expenses.isEmpty()) {
            logger.warn("No expenses found.");
        } else {
            logger.info("Retrieved " + expenses.size() + " expenses.");
        }

        request.setAttribute("expenses", expenses);
        request.getRequestDispatcher("/viewExpense.jsp").forward(request, response);
    }

    private void addExpense(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            double amount = Double.parseDouble(request.getParameter("amount"));
            String description = request.getParameter("description");
            LocalDate date = LocalDate.parse(request.getParameter("date"));

            User user = userDao.getUserById(userId);
            ExpenseCategory category = categoryDao.getCategoryById(categoryId);

            if (user == null || category == null) {
                logger.error("Invalid User or Category for expense.");
                response.sendRedirect("expense");
                return;
            }

            Expense expense = new Expense(user, category, amount, date, description);
            expenseDao.insert(expense);
            logger.info("Added new expense for User ID: " + userId);
        } catch (Exception e) {
            logger.error("Error adding expense", e);
        }
        response.sendRedirect("addExpense.jsp?error=invalid_data");
    }

    private void deleteExpense(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int expenseId = Integer.parseInt(request.getParameter("id"));
        Expense expense = expenseDao.getById(expenseId);

        if (expense != null) {
            expenseDao.delete(expense);
            logger.info("Deleted expense with ID: " + expenseId);
        } else {
            logger.warn("Expense ID not found: " + expenseId);
        }

        response.sendRedirect("expense");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int expenseId = Integer.parseInt(request.getParameter("id"));
        Expense existingExpense = expenseDao.getById(expenseId);

        if (existingExpense != null) {
            request.setAttribute("expense", existingExpense);
            request.getRequestDispatcher("addExpense.jsp").forward(request, response);
        } else {
            logger.warn("Edit request for non-existing expense ID: " + expenseId);
            response.sendRedirect("expense");
        }
    }

    private void updateExpense(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int expenseId = Integer.parseInt(request.getParameter("id"));
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            double amount = Double.parseDouble(request.getParameter("amount"));
            String description = request.getParameter("description");
            LocalDate date = LocalDate.parse(request.getParameter("date"));

            Expense expense = expenseDao.getById(expenseId);
            ExpenseCategory category = categoryDao.getCategoryById(categoryId);

            if (expense == null || category == null) {
                logger.error("Invalid Expense or Category update request.");
                response.sendRedirect("expense");
                return;
            }

            expense.setCategory(category);
            expense.setAmount(amount);
            expense.setDate(date);
            expense.setDescription(description);

            expenseDao.update(expense);
            logger.info("Updated expense with ID: " + expenseId);
        } catch (Exception e) {
            logger.error("Error updating expense", e);
        }

        response.sendRedirect("editExpense.jsp?id=" + request.getParameter("id"));
    }

}
