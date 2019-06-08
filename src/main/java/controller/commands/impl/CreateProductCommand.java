package controller.commands.impl;

import controller.commands.Command;
import controller.commands.impl.util.PageResourseManager;
import model.entity.Product;
import model.service.ProductService;
import model.service.factory.ServiceFactory;
import org.apache.log4j.Logger;
import util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateProductCommand implements Command {
    private static final Logger logger = Logger.getLogger(CreateProductCommand.class);
    private ProductService productService = ServiceFactory.getProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int codeFromRequest = Integer.parseInt(request.getParameter("code"));
        String nameFromRequest = request.getParameter("name");
        String nameUAFromRequest = request.getParameter("name_ua");
        Double costFromRequest = Double.parseDouble(request.getParameter("cost"));
        Integer quantityRequest = Integer.parseInt(request.getParameter("quantity"));


        boolean isNewProduct = false;
        Product product = productService.getProduct(codeFromRequest, nameFromRequest, nameUAFromRequest, costFromRequest, quantityRequest);
        if (StringUtil.isEmpty(request.getParameter("id"))) {
            logger.info("The product is new");
            isNewProduct = true;
        } else {
            product.setId(Integer.parseInt(request.getParameter("id")));
        }
        if (!StringUtil.isEmpty(request.getParameter("invoiceId"))) {
            product.setInvoiceId(Integer.parseInt(request.getParameter("invoiceId")));
        }

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

        logger.info("Get all products");

        return "redirect" + PageResourseManager.getProperty("manager.pages");
    }

}