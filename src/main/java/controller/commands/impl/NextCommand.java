package controller.commands.impl;

import controller.commands.Command;
import model.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

public class NextCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Integer value = Integer.valueOf((String)request.getSession().getAttribute("currentPosition"));

        int currentPosition = Objects.isNull(value)?5:value + 5;
        request.getSession().setAttribute("currentPosition", currentPosition);
        List<Product> products = productService.getAllProducts();
        int size = products.size();

        if(currentPosition == 0) {
            request.setAttribute("products", products.subList(0, 5));
        } else if (currentPosition == size) {
            request.setAttribute("products", products.subList(currentPosition - 5, size));
        } else {
            if ( size < currentPosition + 5 ) {
                request.setAttribute("products", products.subList(currentPosition,size));
            } else {
                request.setAttribute("products", products.subList(currentPosition,currentPosition + 5));
            }
        }


        return CASHIER_PAGE_JSP;
    }
}
