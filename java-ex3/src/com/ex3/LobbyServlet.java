package com.ex3;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet that handel the lobby page
 * */
@WebServlet(name = "LobbyServlet", urlPatterns = "/lobby")
public class LobbyServlet extends HttpServlet {
    /**
     * function that handle lobby page interface and build servlet with html file
     * @param request that comes from the {@link InChatServlet} and goes to the lobby
     * @param response that goes to the {@link InChatServlet} and goes to the lobby
     * @throws ServletException if any errors with the servlet create
     * @throws IOException if any errors
     * */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = getServletContext();
        RequestDispatcher rd =  context.getRequestDispatcher("/lobby.html");
        rd.include(request, response);


    }

}
