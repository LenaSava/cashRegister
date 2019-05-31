package model.service;

import model.dao.factory.DaoFactory;
import model.service.impl.BillServiceImpl;
import model.service.impl.InvoiceServiceImpl;
import model.service.impl.ProductServiceImpl;
import model.service.impl.UserServiceImpl;

public final class ServiceFactory {
//    private static final DaoFactory daoFactory = DaoFactory.getInstance();

    private ServiceFactory() {}

    public static UserService getUserService() {
        return new UserServiceImpl();
    }

    public static ProductService getProductService() {
        return new ProductServiceImpl();
    }

    public static InvoiceService getInvoiceService() {
        return new InvoiceServiceImpl();
    }

    public static BillService getBillService() {
        return new BillServiceImpl();
    }
}
