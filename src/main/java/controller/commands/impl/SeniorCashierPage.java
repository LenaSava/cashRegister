package controller.commands.impl;

import controller.commands.Command;
import model.entity.Invoice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SeniorCashierPage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        request.setAttribute("invoices", invoices);
        return SHOW_SENIOR_CASHIER;
    }
}
