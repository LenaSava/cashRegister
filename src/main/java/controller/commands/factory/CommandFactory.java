package controller.commands.factory;

import controller.commands.Command;
import controller.commands.impl.*;
import controller.commands.impl.util.CommandUtil;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static volatile CommandFactory commandFactory;
    private static final Logger logger = Logger.getLogger(CommandFactory.class);

    private static Map<String, Command> commands = new HashMap<>();

    static {
        commands.put(CommandUtil.LOGIN_PAGE.getPath(), new SignInCommand());
        commands.put(CommandUtil.REGISTRATION.getPath(), new RegistrationCommand());
        commands.put(CommandUtil.LOG_OUT.getPath(), new LogOutCommand());
        commands.put(CommandUtil.ERROR_PAGE.getPath(), new ErrorCommand());
        commands.put(CommandUtil.SIGN_IN.getPath(), new UserCommand());
        commands.put(CommandUtil.SHOW_REGISTRATION.getPath(), new ShowRegistrationPageCommand());
        commands.put(CommandUtil.INFO_PAGE.getPath(), new InfoCommand());
        commands.put(CommandUtil.SENIOR_CASHIER_PAGE.getPath(), new SeniorCashierPage());
        commands.put(CommandUtil.CREATE_UPDATE.getPath(), new CreateProductCommand());
        commands.put(CommandUtil.SHOW_CREATE.getPath(), new ShowCreateProductCommand());
        commands.put(CommandUtil.CASHIER_PAGE.getPath(), new CashierPageCommand());
        commands.put(CommandUtil.MANAGER_PAGE.getPath(), new ManagerPageCommand());
        commands.put(CommandUtil.DELETE.getPath(), new DeleteCommand());
        commands.put(CommandUtil.FIND_PRODUCT.getPath(), new FindProductCommand());
        commands.put(CommandUtil.CREATE_INVOICE.getPath(), new CreateInvoiceCommand());
        commands.put(CommandUtil.BILLS_PAGE.getPath(), new BillsPageCommand());
        commands.put(CommandUtil.CONFIRM_BILL.getPath(), new ConfirmBillCommand());
        commands.put(CommandUtil.CANCEL_BILL.getPath(), new CancelBillCommand());
        commands.put(CommandUtil.DELETE_ALL.getPath(), new DeleteAllCommand());
        commands.put(CommandUtil.X_REPORT.getPath(), new XReportCommand());
        commands.put(CommandUtil.Y_REPORT.getPath(), new YReportCommand());

    }

    public static Command getCommand(String url) {
        Command command = commands.getOrDefault(url, (r,response) ->"/index.jsp");

        if( command == null){
            return new ErrorCommand();
        }

        return command;

    }
}