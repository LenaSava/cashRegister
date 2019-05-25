package controller.commands.impl;

import controller.commands.Command;
import model.entity.Invoice;
import model.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateInvoiceCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Product product = new Product();
        Invoice invoice = new Invoice();
        int idFromRequest = Integer.parseInt(request.getParameter("id"));


        product.setId(Integer.parseInt(request.getParameter("id")));
        product.setCost(Double.parseDouble(request.getParameter("cost")));
        product.setQuantity(Integer.parseInt(request.getParameter("quantity")));

        invoice.setProduct_id(Integer.parseInt(request.getParameter("id")));
        invoice.setCost(Double.parseDouble(request.getParameter("cost")));
        invoice.setQuantity(Integer.parseInt(request.getParameter("quantity")));

//        product.setCode(Integer.parseInt(request.getParameter("code")));
        try {
//            productService.insertIntoInvoices((Integer.parseInt(request.getParameter("id"))));
//            productService.insertIntoInvoices(product);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("products", productService.getAllProducts());

        return CAHIER_PAGE_JSP;
    }
}
