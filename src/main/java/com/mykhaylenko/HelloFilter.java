package com.mykhaylenko;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pavlo.Mykhaylenko on 4/11/2016.
 */
public class HelloFilter implements Filter {
    private FilterConfig filterConfig = null;
    private String param = "";
    private Map<String, String> users;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        param = filterConfig.getInitParameter("filterParam");
        users = new HashMap<String, String>();
        users.put("user1", "user1");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("username") != null) {
            String username = (String) session.getAttribute("username");
            request.setAttribute("username", username);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect("hello");
        }

    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
