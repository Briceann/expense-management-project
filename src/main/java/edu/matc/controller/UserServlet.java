package edu.matc.controller;

import edu.matc.persistence.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserDao userDao = new UserDao();

        if (request.getParameter("submit").equals("search")) {
            request.setAttribute("users", userDao.getByPropertyEqual("lastName", request.getParameter("searchTerm")));
        } else {
            request.setAttribute("users", userDao.getAllUsers());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user-list.jsp");
        dispatcher.forward(request, response);

    }

}
