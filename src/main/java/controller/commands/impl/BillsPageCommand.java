package controller.commands.impl;

import controller.commands.Command;
import controller.commands.impl.util.PageResourseManager;
import model.entity.Bill;
import model.service.BillService;
import model.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BillsPageCommand implements Command {
    private static final Logger logger = Logger.getLogger(BillsPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Bill> bills = ServiceFactory.getBillService().getAllBills();
        logger.info("Get all bills" + bills);
        request.setAttribute("bills", bills);

         return PageResourseManager.getProperty("bills.page");
    }
}

