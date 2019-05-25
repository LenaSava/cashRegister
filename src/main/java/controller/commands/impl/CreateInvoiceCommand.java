package controller.commands.impl;

import controller.commands.Command;
import model.entity.Bill;
import model.entity.Invoice;
import model.entity.Product;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class CreateInvoiceCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Invoice invoice = new Invoice();
        int idFromRequest = Integer.parseInt(request.getParameter("id"));
        Optional<Product> service = productService.findById(idFromRequest);
        User user = (User) request.getSession(true).getAttribute("User");
        Bill bill = billService.findOrCreate(user.getId());

//        billService вызвать метод и получить конкретный билинг и с него взять айди и вставить в инвойс
        invoice.setProduct_id(service.get().getId());
        invoice.setCost(service.get().getCost());
        invoice.setQuantity(service.get().getQuantity());
        invoice.setUserId(user.getId());
        invoice.setUserRoleId(user.getRole());
        invoice.setBillId(bill.getId());

        try {
            invoiceService.create(invoice);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("products", productService.getAllProducts());

        return CAHIER_PAGE_JSP;
    }
}
