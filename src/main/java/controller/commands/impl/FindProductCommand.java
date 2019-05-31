package controller.commands.impl;

import controller.commands.Command;
import controller.commands.impl.util.PageResourseManager;
import model.entity.Product;
import model.service.ProductService;
import model.service.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

public class FindProductCommand implements Command {
    private static final Logger logger = Logger.getLogger(FindProductCommand.class);
    ProductService productService = ServiceFactory.getProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException {
        String codeFromRequest = request.getParameter("search");
        logger.info("Code from request: " + codeFromRequest);

        try {

        Optional<Product> findCode = productService.findByCode(Integer.parseInt(codeFromRequest));

            if (findCode.isPresent()) {
                request.setAttribute("products", Arrays.asList(findCode.get()));
            } else {
                request.setAttribute("errorMessage", true);
                logger.info("Error message");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("products", productService.getAllProducts());
        }
        return PageResourseManager.getProperty("cashier.page.jsp");
    }
}
