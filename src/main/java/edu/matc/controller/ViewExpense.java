package edu.matc.controller;

import edu.matc.persistence.ExpenseDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/ViewExpense")
public class ViewExpense extends HttpServlet {
    private final ExpenseDao expenseDao = new ExpenseDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

//        if (userId == null) {
//            response.sendRedirect("login.jsp");
//            return;
//        }

        request.setAttribute("expenses", expenseDao.getExpensesByUser(userId));
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
