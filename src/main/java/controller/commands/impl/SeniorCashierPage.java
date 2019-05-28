package controller.commands.impl;

import controller.commands.Command;
import model.entity.Invoice;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SeniorCashierPage implements Command {
    private static final Logger logger = Logger.getLogger(SeniorCashierPage.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        logger.info("Get all invoices: " + invoices);
        request.setAttribute("invoices", invoices);
        return SHOW_SENIOR_CASHIER;
    }
}
