package model.service.factory;

import model.service.BillService;
import model.service.InvoiceService;
import model.service.ProductService;
import model.service.UserService;
import model.service.impl.BillServiceImpl;
import model.service.impl.InvoiceServiceImpl;
import model.service.impl.ProductServiceImpl;
import model.service.impl.UserServiceImpl;

public final class ServiceFactory {

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
