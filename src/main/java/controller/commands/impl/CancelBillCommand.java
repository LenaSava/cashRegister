package controller.commands.impl;

import controller.commands.Command;
import controller.commands.impl.util.PageResourseManager;
import model.entity.Bill;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CancelBillCommand implements Command {
    private static final Logger logger = Logger.getLogger(CancelBillCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int idFromRequest = Integer.parseInt(request.getParameter("id"));
        logger.info("Cancel bill" + idFromRequest);
        billService.cancel(idFromRequest);

        List<Bill> bills = billService.getAllBills();
        request.setAttribute("bills", bills);

        return PageResourseManager.getProperty("bills.page");
    }
}
