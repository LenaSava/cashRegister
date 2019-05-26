package controller.filter;

import controller.commands.impl.util.CommandUtil;
import model.entity.User;
import model.entity.types.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AccessFilter implements Filter {

    private List<String> allowedUrls = new ArrayList<>();
    private List<String> cashierUrls = new ArrayList<>();
    private List<String> seniorCashierUrls = new ArrayList<>();
    private List<String> managerUrls = new ArrayList<>();


    @Override
    public void init(FilterConfig filterConfig) {
        String initParameter = filterConfig.getInitParameter("allowed-urls");
        if (initParameter != null && !initParameter.isEmpty()) {
            allowedUrls = Arrays.stream(initParameter.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
        }
        String cashierParameter = filterConfig.getInitParameter("cashier-urls");
        if (cashierParameter != null && !cashierParameter.isEmpty()) {
            cashierUrls = Arrays.stream(cashierParameter.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
        }
        String seniorParameter = filterConfig.getInitParameter("senior-cashier-urls");
        if (seniorParameter != null && !seniorParameter.isEmpty()) {
            seniorCashierUrls = Arrays.stream(seniorParameter.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
        }
        String managerParameter = filterConfig.getInitParameter("manager-urls");
        if (managerParameter != null && !managerParameter.isEmpty()) {
            managerUrls = Arrays.stream(managerParameter.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (allowedUrls.contains(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        final HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("User") != null) {
            User user = (User) session.getAttribute("User");
            if (Role.MANAGER.getRole() == user.getRole())  {
                if (managerUrls.contains(request.getRequestURI())) {
                    filterChain.doFilter(request, response);
                    return;
                }
            }
            if (Role.CAHIER.getRole() == user.getRole()) {
                //todo check for client grants
                if (cashierUrls.contains(request.getRequestURI())) {
                    filterChain.doFilter(request, response);
                    return;
                }
            }
            if (Role.SENIOR_CASHIER.getRole() == user.getRole()) {
                //todo check for client grants
                if (seniorCashierUrls.contains(request.getRequestURI())) {
                    filterChain.doFilter(request, response);
                    return;
                }
            }
            if (Role.VIZITOR.getRole() == user.getRole()) {
                //todo check for visitor grants
                System.out.println("I'm visitor");
            }
        }

        request.getRequestDispatcher("/api/" + CommandUtil.ERROR_PAGE.getPath()).forward(request, response);
    }

    @Override
    public void destroy() {

    }
}