package controller.commands.impl;

import controller.commands.Command;
import controller.commands.impl.util.PageResourseManager;
import model.entity.Product;
import model.service.ProductService;
import model.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CashierPageCommand implements Command {
    private static final Logger logger = Logger.getLogger(CashierPageCommand.class);
    ProductService productService = ServiceFactory.getProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Product> products = productService.getAllProducts();
        request.setAttribute("products", products.subList(0, 5));
//        request.setAttribute("products", products);
        logger.info("Get all products" + products);

        return PageResourseManager.getProperty("cashier.page.jsp");
    }
}
