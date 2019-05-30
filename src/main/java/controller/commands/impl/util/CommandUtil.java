package controller.commands.impl.util;

public enum CommandUtil {

    SIGN_IN("login_submit"),
    LOGIN_PAGE("login"),
    ERROR_PAGE("error"),
    REGISTRATION("registration"),
    SHOW_REGISTRATION("show_registration"),
    LOG_OUT("log_out"),
    INFO_PAGE("info_page"),
    SENIOR_CASHIER_PAGE("senior_cashier_list"),
    MANAGER_PAGE("manager_page"),
    CREATE_UPDATE("create_update"),
    SHOW_CREATE("show_create"),
    CASHIER_PAGE("cashier_page"),
    DELETE("delete"),
    BILLS_PAGE("bills_page"),
    CONFIRM_BILL("confirm_bill"),
    CANCEL_BILL("cancel_bill"),
    FIND_PRODUCT("find_product"),
    DELETE_ALL("delete_all"),
    CREATE_INVOICE("create_invoice"),
    X_REPORT("x_report"),
    Y_REPORT("y_report"),
    PREV("prev"),
    NEXT("next");


    private String path;

    CommandUtil(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

