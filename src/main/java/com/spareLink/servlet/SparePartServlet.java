package com.spareLink.servlet;

import com.spareLink.model.SparePart;
import com.spareLink.service.SparePartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home") // maps to '/home'
public class SparePartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SparePartService sparePartService;

    @Override
    public void init() throws ServletException {
        // initialize service
        sparePartService = new SparePartService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // retrieve all spare parts
        List<SparePart> spareParts = sparePartService.getAllSpareParts();
        // set as request attribute
        request.setAttribute("spare_parts", spareParts);
        // forward to JSP view
        request.getRequestDispatcher("homePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // for now, delegate POST to GET
        doGet(request, response);
    }
}
