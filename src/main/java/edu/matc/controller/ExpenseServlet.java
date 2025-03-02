package edu.matc.controller;

import edu.matc.entity.Expense;
import edu.matc.entity.User;
import edu.matc.service.ExpenseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.time.LocalDate;

@WebServlet(
        urlPatterns = {"/addExpense"}
)
public class ExpenseServlet extends HttpServlet {

    private ExpenseService expenseService = new ExpenseService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        edu.matc.entity.User user = (edu.matc.entity.User) session.getAttribute("loggedInUser");

        Expense expense = new Expense();
        expense.setUser(user);
        expense.setCategory(request.getParameter("category"));
        expense.setAmount((int) Double.parseDouble(request.getParameter("amount")));
        expense.setDate(LocalDate.parse(request.getParameter("date")));
        expense.setDescription(request.getParameter("description"));

        // Add the expense using the service
        expenseService.addExpense(expense);

        // Redirect to the dashboard
        response.sendRedirect("/dashboard.jsp");
    }
}