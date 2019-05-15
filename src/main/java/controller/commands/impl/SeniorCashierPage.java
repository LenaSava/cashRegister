package controller.commands.impl;

import controller.commands.Command;
import model.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SeniorCashierPage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return SHOW_SENIOR_CASHIER;
    }
}
