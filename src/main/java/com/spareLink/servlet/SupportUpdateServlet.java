package com.spareLink.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.spareLink.service.SupportService;

@WebServlet("/supportUpdate")
public class SupportUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read parameters from form
        String supportId = request.getParameter("supportId");
        String issueType = request.getParameter("issueType");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String contactNo = request.getParameter("contactNo");
        String inquiry = request.getParameter("inquiry");

        // Validate supportId is not null or empty
        if (supportId == null || supportId.isEmpty()) {
            response.getWriter().println("<script>alert('Invalid Support ID'); history.back();</script>");
            return;
        }

        // Call updatedata with all parameters
        boolean isUpdated = SupportService.updatedata(supportId, issueType, fullName, email, contactNo, inquiry);

        if (isUpdated) {
            // Redirect to supportGetAll servlet to reload updated list
            response.sendRedirect("supportGetAll");
        } else {
            // Show alert on failure
            response.setContentType("text/html");
            response.getWriter().println("<script>alert('Update failed. Please try again.'); history.back();</script>");
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // You can redirect GET to your main listing page or show an error
        response.sendRedirect("supportGetAll");  // or any suitable URL
    }

}
