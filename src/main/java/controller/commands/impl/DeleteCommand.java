package controller.commands.impl;

import controller.commands.Command;
import controller.commands.impl.util.PageResourseManager;
import model.service.InvoiceService;
import model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        InvoiceService invoiceService = ServiceFactory.getInvoiceService();

        try {
                invoiceService.delete((Integer.parseInt(request.getParameter("id"))));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("invoices", invoiceService.getAllInvoices());

        return PageResourseManager.getProperty("show.senior.cashier");
    }
}

