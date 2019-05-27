package controller.commands.impl;

import controller.commands.Command;
import model.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CahierPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = productService.getAllProducts();
        request.setAttribute("products", products);

        return CASHIER_PAGE_JSP;
    }
}
