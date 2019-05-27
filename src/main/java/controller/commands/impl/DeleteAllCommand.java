package controller.commands.impl;

import controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAllCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            invoiceService.deleteAll();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("invoices", invoiceService.getAllInvoices());

        return SHOW_SENIOR_CASHIER;
    }
}