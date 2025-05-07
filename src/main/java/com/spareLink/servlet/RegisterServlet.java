package com.spareLink.servlet;

import com.spareLink.model.User;
import com.spareLink.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("full_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("Received user data:");
        System.out.println("Full Name: " + fullName);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        User user = new User(fullName, email, password);
        boolean success = userService.registerUser(user);

        if (success) {
            System.out.println("User registered successfully.");
            response.sendRedirect("sign-in.jsp?success=1");
        } else {
            System.out.println("User registration failed (maybe duplicate email).");
            response.sendRedirect("register.jsp?error=email_exists");
        }
    }
}
