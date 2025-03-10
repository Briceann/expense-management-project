package edu.matc.controller;

import edu.matc.entity.Expense;
import edu.matc.entity.ExpenseCategory;
import edu.matc.persistence.ExpenseDao;
import edu.matc.persistence.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/UpdateExpense")
public class UpdateExpense extends HttpServlet {
    private final ExpenseDao expenseDao = new ExpenseDao();
    private final UserDao userDao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int expenseId = Integer.parseInt(request.getParameter("expenseId"));
        Expense expense = expenseDao.getById(expenseId);

        if (expense != null && expense.getUser().getUserId() == userId) {
            ExpenseCategory category = new ExpenseCategory();
            category.setCategoryId(Integer.parseInt(request.getParameter("category")));

            expense.setCategory(category);
            expense.setAmount(Integer.parseInt(request.getParameter("amount")));
            expense.setDate(LocalDate.parse(request.getParameter("date")));
            expense.setDescription(request.getParameter("description"));

            expenseDao.update(expense);
        }

        response.sendRedirect("ViewExpense");
    }
}

