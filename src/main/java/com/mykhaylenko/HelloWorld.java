package com.mykhaylenko;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pavlo.Mykhaylenko on 4/11/2016.
 */
//@WebServlet(urlPatterns = {"/hello"}, name = "HelloWorld")
public class HelloWorld extends HttpServlet {

    private Map<String, String> users;
    private String message;

    @Override
    public void init() throws ServletException {
        message = "Hello word";
        users = new HashMap<String, String>();
        users.put("user1", "user1");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", message);
        req.getRequestDispatcher("hello.jsp").forward(req, resp);

//        String requestParameter;
//        requestParameter = req.getParameter("last_name");// can read parameters from url String
//        System.out.println("Last name: " + requestParameter);

        //reading all information from the header
//        Enumeration headerNames = req.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = (String) headerNames.nextElement();
//            System.out.println(headerName + " | " + req.getHeader(headerName));
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login;
        String password;

        login = req.getParameter("login");
        password = req.getParameter("password");

        if (password == null) {
            password = "";
        }

        if (users.containsKey(login) && password.equals(users.get(login))) {
            HttpSession session = req.getSession(false);
            session.setAttribute("username", login);
            resp.sendRedirect("secret");
        } else {
            resp.sendRedirect("error.jsp");
        }

    }
}
