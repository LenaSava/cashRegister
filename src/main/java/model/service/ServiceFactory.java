package model.service;

import model.dao.DaoFactory;
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
}
