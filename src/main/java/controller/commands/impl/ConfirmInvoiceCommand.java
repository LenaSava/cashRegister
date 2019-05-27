package controller.commands.impl;

import controller.commands.Command;
import model.entity.Bill;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmInvoiceCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
//        Bill bill = new Bill();
        int idFromRequest = Integer.parseInt(request.getParameter("id"));
        billService.confirm(idFromRequest);
        return SHOW_SENIOR_CASHIER;
    }
}
