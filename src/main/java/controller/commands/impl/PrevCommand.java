package controller.commands.impl;

import controller.commands.Command;
import controller.commands.impl.util.PageResourseManager;
import model.entity.Product;
import model.service.ProductService;
import model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PrevCommand implements Command {
    ProductService productService = ServiceFactory.getProductService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int currentPosition = 5;
        List<Product> products = productService.getAllProducts();
//        products.subList(currentPosition,20);
        request.setAttribute("products", products.subList(currentPosition,5));

        return PageResourseManager.getProperty("cashier.page.jsp");
    }
}

