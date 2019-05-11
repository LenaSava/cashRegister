package controller.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocaleFilter implements Filter {
    private static final Logger logger = Logger.getLogger(LocaleFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        logger.info("localisation filter");
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI();

        path = path.replaceAll("language/", "");

        if (path.equals("/views//")) {
            path = "/";
        }

        String language = request.getParameter("language");

        boolean isEnglish = language.equals("en");
        boolean isUkrainian = language.equals("ua");


        if (isEnglish) {
            request.getSession().setAttribute("language", "en");
        } else if (isUkrainian) {
            request.getSession().setAttribute("language", "ua");
        }

        logger.info("session language " + request.getSession().getAttribute("language"));
        response.sendRedirect(path);
    }

    @Override
    public void destroy() {

    }
}

