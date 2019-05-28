package controller.commands.impl;

import controller.commands.Command;
import model.entity.Product;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ManagerPageCommand implements Command {
    private static final Logger logger = Logger.getLogger(ManagerPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = productService.getAllProducts();
        logger.info("Get all products: " + products);
        request.setAttribute("products", products);
        return MANAGER_PAGE;
    }
}
