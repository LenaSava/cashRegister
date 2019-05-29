package controller.commands.impl;

import controller.commands.Command;
import model.entity.Product;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CashierPageCommand implements Command {
    private static final Logger logger = Logger.getLogger(CashierPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Product> products = productService.getAllProducts();
        request.setAttribute("products", products);
        logger.info("Get all products" + products);

        return CASHIER_PAGE_JSP;
    }
}
