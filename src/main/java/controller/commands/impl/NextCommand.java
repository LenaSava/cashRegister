package controller.commands.impl;

import controller.commands.Command;
import controller.commands.impl.util.PageResourseManager;
import model.entity.Product;
import model.service.ProductService;
import model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

public class NextCommand implements Command {
    ProductService productService = ServiceFactory.getProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Object data = request.getSession(true).getAttribute("currentPosition");
        int currentPosition= Objects.isNull(data) ? 5: Integer.parseInt(String.valueOf(data));

        request.getSession().setAttribute("currentPosition", currentPosition);
        List<Product> products = productService.getAllProducts();
        int size = products.size();

        try {
            if(currentPosition == 0) {
                request.setAttribute("products", products.subList(0, 5));
            } else if (currentPosition == size) {
                request.setAttribute("products", products.subList(currentPosition - 5, size));
            } else {
                if (size < currentPosition + 5) {
                    request.setAttribute("products", products.subList(currentPosition, size));
                } else {
                    request.setAttribute("products", products.subList(currentPosition, currentPosition+5));
                }
            }
        } catch (ClassCastException ex) {
            request.setAttribute("products", products.subList(0, 5));
        }
        return PageResourseManager.getProperty("cashier.page.jsp");
    }
}
