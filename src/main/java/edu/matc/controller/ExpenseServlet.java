package edu.matc.controller;

import edu.matc.entity.Expense;
import edu.matc.entity.ExpenseCategory;
import edu.matc.entity.User;
import edu.matc.service.ExpenseCategoryService;
import edu.matc.service.ExpenseService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import java.time.LocalDate;

@WebServlet(
        urlPatterns = {"/addExpense"}
)
public class ExpenseServlet extends HttpServlet {

    private ExpenseService expenseService = new ExpenseService();
    private ExpenseCategoryService categoryService = new ExpenseCategoryService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        edu.matc.entity.User user = (edu.matc.entity.User) session.getAttribute("loggedInUser");

        // Retrieve category ID from request
        int categoryId = Integer.parseInt(request.getParameter("category"));

        // Fetch the corresponding ExpenseCategory from the database
        ExpenseCategory category = categoryService.getCategoryById(categoryId);

        Expense expense = new Expense();
        expense.setUser(user);
        expense.setCategory(category);
        expense.setAmount((int) Double.parseDouble(request.getParameter("amount")));
        expense.setDate(LocalDate.parse(request.getParameter("date")));
        expense.setDescription(request.getParameter("description"));

        // Add the expense using the service
        expenseService.addExpense(expense);

        // Redirect to the dashboard
        response.sendRedirect("/dashboard.jsp");
    }
}