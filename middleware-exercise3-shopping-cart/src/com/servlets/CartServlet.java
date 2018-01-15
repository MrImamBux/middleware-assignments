package com.servlets;

import com.dao.DAOUtil;
import com.dao.ItemBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(session.isNew()) {
            session.setAttribute("cart", new ArrayList<Integer>());
        }
        List<Integer> cart = (List<Integer>)session.getAttribute("cart");

        if(request.getServletPath().equals("/getItemsQuantity")) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.write("{\"quantity\":" + cart.size() + "}");
        } else if(request.getServletPath().equals("/deleteFromCart")) {
            Integer itemId = Integer.parseInt(request.getParameter("item"));
            cart.remove(itemId);
        } else if(request.getServletPath().equals("/loadItemsFromCart")) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.write(DAOUtil.getJsonifiedItemsByIds(cart));
        } else {
            Integer itemId = Integer.parseInt(request.getParameter("item"));
            cart.add(itemId);
        }

    }
}
