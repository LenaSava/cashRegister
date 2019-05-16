package controller.commands.impl;

import controller.commands.Command;
import model.entity.Product;
import util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateProductCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
//        String typeFromRequest = request.getParameter("type");
//        int capacityFromRequest = Integer.parseInt(request.getParameter("capacity"));
//        int costFromRequest = Integer.parseInt(request.getParameter("cost"));
//        int quotaFromRequest = Integer.parseInt(request.getParameter("quota"));


        boolean isNewProduct = false;
        Product product = new Product();
        if (StringUtil.isEmpty(request.getParameter("id"))) {
            isNewProduct = true;
        } else {
            product.setId(Integer.parseInt(request.getParameter("id")));
        }
        if (!StringUtil.isEmpty(request.getParameter("invoiceId"))) {
            product.setInvoiceId(Integer.parseInt(request.getParameter("invoiceId")));
        }
        product.setCode(Integer.parseInt(request.getParameter("code")));
        product.setName(request.getParameter("name"));
        product.setCost(Double.parseDouble(request.getParameter("cost")));
        product.setQuantity(Double.parseDouble(request.getParameter("quantity")));

        try {
            if (isNewProduct) {
                productService.create(product);
            } else {
                productService.update(product);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("products", productService.getAllProducts());

        return MANAGER_PAGE;
    }

}