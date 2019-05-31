package controller.commands.impl;

import controller.commands.Command;
import controller.commands.impl.util.PageResourseManager;
import model.entity.Bill;
import model.entity.Invoice;
import model.entity.Product;
import model.entity.User;
import model.service.BillService;
import model.service.InvoiceService;
import model.service.ProductService;
import model.service.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class CreateInvoiceCommand implements Command {
    private static final Logger logger = Logger.getLogger(CreateInvoiceCommand.class);
    ProductService productService = ServiceFactory.getProductService();
    InvoiceService invoiceService = ServiceFactory.getInvoiceService();
    BillService billService = ServiceFactory.getBillService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Invoice invoice = new Invoice();
        int idFromRequest = Integer.parseInt(request.getParameter("id"));
        Optional<Product> service = productService.findById(idFromRequest);
        User user = (User) request.getSession(true).getAttribute("User");
        Bill bill = billService.findOrCreate(user.getId());

        invoice.setProduct_id(service.get().getId());
        invoice.setCost(service.get().getCost());
        invoice.setQuantity(service.get().getQuantity());
        invoice.setUserId(user.getId());
        invoice.setUserRoleId(user.getRole());
        invoice.setBillId(bill.getId());

        try {
            invoiceService.create(invoice);
            logger.info("Create invoice");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("products", productService.getAllProducts());
        logger.info("Get all products");

        return PageResourseManager.getProperty("cashier.page.jsp");
    }
}
