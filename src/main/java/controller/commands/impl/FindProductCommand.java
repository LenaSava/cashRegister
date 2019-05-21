package controller.commands.impl;

import controller.commands.Command;
import model.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

public class FindProductCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException {
        String codeFromRequest = request.getParameter("search");

        try {

        Optional<Product> findCode = productService.findByCode(Integer.parseInt(codeFromRequest));

            if (findCode.isPresent()) {
                request.setAttribute("products", Arrays.asList(findCode.get()));
            } else {
                request.setAttribute("errorMessage", "product with such code not found!");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("products", productService.getAllProducts());
        }
        return CAHIER_PAGE_JSP;
    }
}
