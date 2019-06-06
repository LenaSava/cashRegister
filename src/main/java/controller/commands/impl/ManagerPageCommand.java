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
import java.util.Optional;

public class ManagerPageCommand implements Command {
    private static final Logger logger = Logger.getLogger(ManagerPageCommand.class);
    private ProductService productService = ServiceFactory.getProductService();
    private int recordsPerPage = 4;
    private int currentPage = 1;
    private int numberOfPages;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        getCurrentPage(request);
        int numberOfRows = productService.getNumberOfproducts();


        List<Product> products = productService.findProducts(currentPage, recordsPerPage);
        getNumberOfPages(numberOfRows);
        logger.info("Get all products: " + products);
        request.setAttribute("products", products);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("numberOfPages", numberOfPages);

        return PageResourseManager.getProperty("manager.page");
    }

    private void getCurrentPage(HttpServletRequest request) {
        Optional<String> page = Optional.ofNullable(request.getParameter("currentPage"));
        currentPage = page.map(Integer::valueOf).orElse(1);
    }

    private void getNumberOfPages(int numberOfRows) {
        numberOfPages = numberOfRows / recordsPerPage;
        if (numberOfRows % recordsPerPage > 0) {
            numberOfPages++;
        }
    }
}
