package controller.commands.impl;

import controller.commands.Command;
import controller.commands.impl.util.PageResourseManager;
import model.entity.Product;
import util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class NextCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String value = (String)request.getSession().getAttribute("currentPosition");

        int currentPosition = StringUtil.isEmpty(value)?5:Integer.valueOf(value)+5;
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
                    request.setAttribute("products", products.subList(currentPosition, currentPosition + 5));
                }
            }
        } catch (ClassCastException ex) {
            request.setAttribute("products", products.subList(0, 5));
        }
        return PageResourseManager.getProperty("cashier.page.jsp");
    }
}
