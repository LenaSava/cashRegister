package controller.commands;

import model.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    ProductService productService = ServiceFactory.getProductService();
    UserService userService = ServiceFactory.getUserService();
    InvoiceService invoiceService = ServiceFactory.getInvoiceService();
    BillService billService = ServiceFactory.getBillService();


    String execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
