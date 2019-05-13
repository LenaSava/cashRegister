package controller.commands.impl.util;

public enum CommandUtil {

    SIGN_IN("login_submit"),
    LOGIN_PAGE("login"),
    ERROR_PAGE("error"),
    REGISTRATION("registration"),
    SHOW_REGISTRATION("show_registration"),
    SHOW_SENIOR_CASHIER("show_senior_cashier"),
    LOG_OUT("log_out"),
    INFO_PAGE("info_page"),
    USER_HOME_PAGE("user_home_page"),
    ROOM_LIST("room_list"),
    SENIOR_CASHIER_PAGE("senior_cashier_list"),
    MANAGER_PAGE("manager_page"),
    CREATE_ROOM("create_room"),
    SHOW_CREATE("show_create"),
    CAHIER_PAGE("cashier_page"),
    CHIEF_CAHIER("chief_page"),

    CHOSE_APARTMENT_BY_ADMIN("chose_apartment"),
    SEND_BILL_TO_CLIENT("create_bill"),
    CLIENT_BILLS_PAGE("bills-page"),
    CHANGE_LOCALE("change-locale"),
    BILL_PAYMENT("billPayment"),
    REJECT_ORDER("reject-order");


    private String path;

    CommandUtil(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

