package controller.filter;

import controller.commands.impl.util.CommandUtil;
import model.entity.User;
import model.entity.types.Role;
import org.apache.log4j.Logger;

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
    private static final Logger logger = Logger.getLogger(AccessFilter.class);
    private List<String> allowedUrls = new ArrayList<>();
    private List<String> cashierUrls = new ArrayList<>();
    private List<String> seniorCashierUrls = new ArrayList<>();
    private List<String> managerUrls = new ArrayList<>();



    @Override
    public void init(FilterConfig filterConfig) {
        String initParameter = filterConfig.getInitParameter("allowed-urls");
        String cashierParameter = filterConfig.getInitParameter("cashier-urls");
        String seniorParameter = filterConfig.getInitParameter("senior-cashier-urls");
        String managerParameter = filterConfig.getInitParameter("manager-urls");

        if (initParameter != null && !initParameter.isEmpty()) {
            allowedUrls = Arrays.stream(initParameter.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
        }
        if (cashierParameter != null && !cashierParameter.isEmpty()) {
            cashierUrls = Arrays.stream(cashierParameter.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
        }
        if (seniorParameter != null && !seniorParameter.isEmpty()) {
            seniorCashierUrls = Arrays.stream(seniorParameter.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
        }
        if (managerParameter != null && !managerParameter.isEmpty()) {
            managerUrls = Arrays.stream(managerParameter.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
        }
        logger.info("Get  managerUrls: " +  managerUrls);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpSession session = request.getSession(false);

        if (allowedUrls.contains(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }
        if (session != null && session.getAttribute("User") != null) {
            User user = (User) session.getAttribute("User");
            if (Role.MANAGER.getRole() == user.getRole())  {
                logger.info("Get user role: " +  user.getRole());
                if (managerUrls.contains(request.getRequestURI())) {
                    filterChain.doFilter(request, response);
                    return;
                }
            } else if (Role.CAHIER.getRole() == user.getRole()) {
                logger.info("Get user role: " +  user.getRole());
                if (cashierUrls.contains(request.getRequestURI())) {
                    filterChain.doFilter(request, response);
                    return;
                }
            } else if (Role.SENIOR_CASHIER.getRole() == user.getRole()) {
                logger.info("Get user role: " +  user.getRole());
                if (seniorCashierUrls.contains(request.getRequestURI())) {
                    filterChain.doFilter(request, response);
                    return;
                }
            }
        }

        request.getRequestDispatcher("/" + CommandUtil.ERROR_PAGE.getPath()).forward(request, response);
    }

    @Override
    public void destroy() {

    }
}