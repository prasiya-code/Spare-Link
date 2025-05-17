package com.spareLink.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spareLink.service.SupportService;

@WebServlet("/supportDelete")
public class SupportDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handle GET request to delete support entry
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String supportId = request.getParameter("supportId");

        if (supportId != null && !supportId.isEmpty()) {
            boolean isDeleted = SupportService.deleteSupport(supportId);

            if (isDeleted) {
                response.sendRedirect("supportGetAll");  // Redirect to list after successful delete
            } else {
                response.setContentType("text/html");
                response.getWriter().println("<script>alert('Failed to delete record.'); history.back();</script>");
            }
        } else {
            response.setContentType("text/html");
            response.getWriter().println("<script>alert('Missing or invalid support ID.'); history.back();</script>");
        }
    }

    // Optionally allow POST for delete
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
