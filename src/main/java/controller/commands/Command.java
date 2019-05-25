package controller.commands;

import model.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    String SIGN_IN_JSP = "/WEB-INF/views/login.jsp";
    String SHOW_SENIOR_CASHIER = "/WEB-INF/views/seniorCashier.jsp";
    String REGISTRATION_PAGE_JSP = "/WEB-INF/views/registration.jsp";
    String ERROR_PAGE = "/WEB-INF/views/error.jsp";
    String HOME = "/index.jsp";
    String INFO_VIEW = "/WEB-INF/views/infoView.jsp";
    String CAHIER_PAGE_JSP = "/WEB-INF/views/cahierPage.jsp";
    String MANAGER_PAGE = "/WEB-INF/views/manager.jsp";
    String CREATE_UPDATE = "/WEB-INF/views/createUpdate.jsp";
    String BILLS_PAGE = "/WEB-INF/views/billsPage.jsp";


    ProductService productService = ServiceFactory.getProductService();
    UserService userService = ServiceFactory.getUserService();
    InvoiceService invoiceService = ServiceFactory.getInvoiceService();
    BillService billService = ServiceFactory.getBillService();

    String execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
