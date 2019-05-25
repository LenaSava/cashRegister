package controller.commands.impl;

import controller.commands.Command;
import model.entity.Bill;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BillsPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
//        List<Bill> bills = billService.getAllBills();
//        request.setAttribute("bills", bills);

        return BILLS_PAGE;
    }
}

