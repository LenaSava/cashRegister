package controller.commands.impl;

import controller.commands.Command;
import controller.commands.impl.util.PageResourseManager;
import model.entity.Product;
import model.service.ProductService;
import model.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowCreateProductCommand implements Command {
    private static final Logger logger = Logger.getLogger(ShowCreateProductCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("id");
        logger.info("Get id from product: " + id);
        Product product;
        ProductService productService = ServiceFactory.getProductService();
        if (id == null || id.trim().length() == 0) {
            product = new Product();
        } else {
            product = productService.findById(Integer.parseInt(id)).orElseThrow(()->new RuntimeException("product by id: "+ id + " not found"));
            logger.info("product by id: "+ id + " not found");
        }
        request.setAttribute("product", product);

        return PageResourseManager.getProperty("create.update");
    }
}
