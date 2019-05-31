package controller.commands.impl;

import controller.commands.Command;
import controller.commands.impl.util.PageResourseManager;
import model.entity.Product;
import model.service.ProductService;
import model.service.ServiceFactory;
import org.apache.log4j.Logger;
import util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateProductCommand implements Command {
    private static final Logger logger = Logger.getLogger(CreateProductCommand.class);
    ProductService productService = ServiceFactory.getProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        boolean isNewProduct = false;
        Product product = new Product();
        if (StringUtil.isEmpty(request.getParameter("id"))) {
            logger.info("The product is new");
            isNewProduct = true;
        } else {
            product.setId(Integer.parseInt(request.getParameter("id")));
        }
        if (!StringUtil.isEmpty(request.getParameter("invoiceId"))) {
            product.setInvoiceId(Integer.parseInt(request.getParameter("invoiceId")));
        }
        product.setCode(Integer.parseInt(request.getParameter("code")));
        product.setName(request.getParameter("name"));
        product.setName_ua(request.getParameter("name_ua"));
        product.setCost(Double.parseDouble(request.getParameter("cost")));
        product.setQuantity(Integer.parseInt(request.getParameter("quantity")));

        try {
            if (isNewProduct) {
                productService.create(product);
                logger.info("The product is create" + product);
            } else {
                productService.update(product);
                logger.info("The product is update" + product);
            }
        } catch (RuntimeException e) {
            request.setAttribute("wrongInputData", true);
        }
        request.setAttribute("products", productService.getAllProducts());
        logger.info("Get all products");

        return PageResourseManager.getProperty("manager.page");
    }

}