package controller.commands.impl;

import controller.commands.Command;
import model.entity.Bill;
import model.entity.enumeration.BillStatus;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class YReportCommand implements Command {
    private static final Logger logger = Logger.getLogger(YReportCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Bill> bills = billService.xReport(BillStatus.CONFIRM.name());
        logger.info("Get all bills" + bills);
        request.setAttribute("bills", bills);


        return Y_REPORT;
    }
}
