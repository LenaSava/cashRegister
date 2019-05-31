package controller.commands.impl;

import controller.commands.Command;
import controller.commands.impl.util.PageResourseManager;
import model.entity.Invoice;
import model.service.InvoiceService;
import model.service.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SeniorCashierPage implements Command {
    private static final Logger logger = Logger.getLogger(SeniorCashierPage.class);
    InvoiceService invoiceService = ServiceFactory.getInvoiceService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        logger.info("Get all invoices: " + invoices);
        request.setAttribute("invoices", invoices);

        return PageResourseManager.getProperty("show.senior.cashier");
    }
}
