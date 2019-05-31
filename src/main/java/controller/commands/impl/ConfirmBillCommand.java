package controller.commands.impl;

import controller.commands.Command;
import controller.commands.impl.util.PageResourseManager;
import model.entity.Bill;
import model.service.BillService;
import model.service.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ConfirmBillCommand implements Command {
    private static final Logger logger = Logger.getLogger(ConfirmBillCommand.class);
    BillService billService = ServiceFactory.getBillService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int idFromRequest = Integer.parseInt(request.getParameter("id"));
        billService.confirm(idFromRequest);
        logger.info("Confirm bill" + idFromRequest);

        List<Bill> bills = billService.getAllBills();
        request.setAttribute("bills", bills);
        logger.info("Get all bills" + bills);

        return PageResourseManager.getProperty("bills.page");
    }
}
