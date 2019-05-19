package controller.commands.impl;

import controller.commands.Command;
import model.entity.Invoice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Invoice invoice = new Invoice();

//        invoice.setId(Integer.parseInt(request.getParameter("id")));
        try {
                invoiceService.delete((Integer.parseInt(request.getParameter("id"))));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("invoices", invoiceService.getAllInvoices());

        return SHOW_SENIOR_CASHIER;
    }
}

