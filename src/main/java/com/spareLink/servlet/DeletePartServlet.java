package com.spareLink.servlet;

import com.spareLink.service.SparePartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/DeletePartServlet")
public class DeletePartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final SparePartService sparePartService = new SparePartService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean deleted = sparePartService.deleteSparePartById(id);

            if (deleted) {
                response.sendRedirect("products?deleted=true");
            } else {
                response.sendRedirect("products?error=delete_failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
