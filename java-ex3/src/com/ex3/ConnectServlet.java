package com.ex3;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Servlet that handel the connect and disconnect users
* */
@WebServlet(name="ConnectServlet", urlPatterns = "/connect")
public class ConnectServlet extends HttpServlet {

    /**
     * function that handle the post request from clients
     * that contain the login details
     * @param request from the client
     * @param response that transfer the client into the chat
     * @throws IOException if any errors
     * */
    @Override
    protected void doPost(HttpServletRequest request,
    HttpServletResponse response) throws IOException {
        String name = request.getParameter("name").trim();
        Cookie client = new Cookie("name",name);
        response.addCookie(client);
        response.sendRedirect("inChat");
    }

    /**
     * function that handle the get request from clients
     * that logout the clients and delete the cookie
     * @param request from the client
     * @param response that transfer the client back to the login page
     * @throws IOException if any errors
     * */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("name")) {
                //delete the cookie
                c.setMaxAge(0);
                response.addCookie(c);
            }
        }
        String greetings = "/";

        response.setContentType("text/html");
        response.getWriter().write(greetings);
    }
}
