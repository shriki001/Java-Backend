package com.ex3;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet that handle the user connectivity and transfer the user to the chat
 * if he already connect from another tab
 */
@WebServlet(name = "InChatServlet", urlPatterns = "/inChat")
public class InChatServlet extends HttpServlet {

    /**
     * function that handle the connectivity of users and forward the response and the request
     * to {@link LobbyServlet}
     *
     * @param request  that comes from clients
     * @param response that goes to the clients
     * @throws IOException if any errors
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/";
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("name")) {
                request.setAttribute("username", c.getValue());
                url = "/lobby";
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
