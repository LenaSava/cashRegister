package controller.commands.impl;

import controller.commands.Command;
import controller.commands.impl.util.PageResourseManager;
import model.service.InvoiceService;
import model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCommand implements Command {
    private InvoiceService invoiceService = ServiceFactory.getInvoiceService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        invoiceService.delete((Integer.parseInt(request.getParameter("id"))));
        request.setAttribute("invoices", invoiceService.getAllInvoices());

        return PageResourseManager.getProperty("show.senior.cashier");
    }
}

