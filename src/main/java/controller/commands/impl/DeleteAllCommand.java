package controller.commands.impl;

import controller.commands.Command;
import controller.commands.impl.util.PageResourseManager;
import model.service.InvoiceService;
import model.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAllCommand implements Command {
    private static final Logger logger = Logger.getLogger(DeleteAllCommand.class);
    private InvoiceService invoiceService = ServiceFactory.getInvoiceService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            invoiceService.deleteAll();
            logger.info("Call service deleteAll");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("invoices", invoiceService.getAllInvoices());
        logger.info("Call service ");

        return PageResourseManager.getProperty("show.senior.cashier");

    }
}