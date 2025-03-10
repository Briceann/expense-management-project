package edu.matc.controller;

import edu.matc.persistence.ExpenseDao;
import edu.matc.persistence.UserDao;
import edu.matc.entity.Expense;
import edu.matc.entity.ExpenseCategory;
import edu.matc.entity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/AddExpense")
public class AddExpense extends HttpServlet {
    private final ExpenseDao expenseDao = new ExpenseDao();
    private final UserDao userDao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 🔹 Get the user ID from the request (Assuming AWS passes userId in request)
            int userId = Integer.parseInt(request.getParameter("userId"));

            // 🔹 Fetch User object from database
            User user = userDao.getUserById(userId);

            // 🔹 Fetch the ExpenseCategory object
            ExpenseCategory category = new ExpenseCategory();
            category.setCategoryId(Integer.parseInt(request.getParameter("category")));

            // 🔹 Retrieve other expense details
            int amount = Integer.parseInt(request.getParameter("amount"));
            LocalDate date = LocalDate.parse(request.getParameter("date"));
            String description = request.getParameter("description");

            // 🔹 Create an Expense object
            Expense expense = new Expense(user, category, amount, date, description);

            // 🔹 Save to database
            int generatedId = expenseDao.insert(expense);
            System.out.println("Expense added with ID: " + generatedId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("ViewExpense"); // Redirect to expenses list
    }
}
