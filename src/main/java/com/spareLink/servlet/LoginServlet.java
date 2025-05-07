package com.spareLink.servlet;

import com.spareLink.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserService userService = new UserService();
        boolean isValid = userService.validateUser(email, password);

        if (isValid) {
            HttpSession session = request.getSession();
            session.setAttribute("userEmail", email);

            //Forward to ProductServlet
            request.getRequestDispatcher("/home").forward(request, response);
        } else {
            request.setAttribute("error", "Invalid email or password");
            request.getRequestDispatcher("sign-in.jsp").forward(request, response);
        }
    }
}
