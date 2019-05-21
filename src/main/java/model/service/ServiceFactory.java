package model.service;

import model.dao.DaoFactory;
import model.service.impl.BillServiceImpl;
import model.service.impl.InvoiceServiceImpl;
import model.service.impl.ProductServiceImpl;
import model.service.impl.UserServiceImpl;

public final class ServiceFactory {
    private static final DaoFactory daoFactory = DaoFactory.getInstance();

    private ServiceFactory() {}

    public static UserService getUserService() {
        UserService service = new UserServiceImpl(daoFactory);
        return service;
    }

    public static ProductService getProductService() {
        ProductService service = new ProductServiceImpl(daoFactory);
        return service;
    }

    public static InvoiceService getInvoiceService() {
        InvoiceService service = new InvoiceServiceImpl(daoFactory);
        return service;
    }

    public static BillService getBillService() {
        BillService service = new BillServiceImpl(daoFactory);
        return service;
    }
}
