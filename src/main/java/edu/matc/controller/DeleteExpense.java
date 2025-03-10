package edu.matc.controller;

import edu.matc.entity.Expense;
import edu.matc.persistence.ExpenseDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/DeleteExpense")
public class DeleteExpense extends HttpServlet {
    private final ExpenseDao expenseDao = new ExpenseDao();

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
            expenseDao.delete(expense);
        }

        response.sendRedirect("ViewExpense");
    }
}

