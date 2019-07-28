package com.ex3;

import javax.json.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * Servlet that handel the messaging traffic
 */

@WebServlet(name = "MessagingServlet", urlPatterns = "/MessagingServlet")
public class MessagingServlet extends HttpServlet {

    /**
     * function that initialize the servlet and create the context
     *
     * @throws ServletException if any errors with the servlet create
     */
    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        context.setAttribute("messages", new StringBuilder());
        context.setAttribute("connectedUsers", new HashMap<String, String>());
    }

    /**
     * function that handle the message types
     *
     * @param request  that comes from clients
     * @param response that goes to the clients
     * @throws IOException if any errors
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int type = Integer.parseInt(request.getParameter("type"));

        switch (type) {
            case 0:
                updateServer(request);
                fetchData(request, response);
                break;
            case 1:
                fetchData(request, response);
                break;
            case 2:
                onConnect(request);
                break;
            case 3:
                onDisconnect(request);
                break;
            default:
                break;
        }
    }


    /**
     * function that push new message to the server messages
     *
     * @param request that comes from clients
     */
    private void updateServer(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String msg = request.getParameter("msg");

        // retrieve data structures from the servlet context
        ServletContext context = getServletContext();
        StringBuilder messages = (StringBuilder) context.getAttribute("messages");
        messages.append(userName).append(": ").append(msg).append('\n');
    }

    /**
     * function that pull the messages and the connected users from the the server
     *
     * @param request  that comes from clients
     * @param response that goes to the clients
     * @throws IOException if any errors
     */
    private void fetchData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonObject result = getChatJsonData(request);
        try (OutputStream out = response.getOutputStream()) {
            JsonWriter jsonw = Json.createWriter(out);
            jsonw.write(result);
            jsonw.close();
        }

    }

    /**
     * function that create the connected users Map and add to the Servlet Context
     *
     * @param request that comes from clients
     */
    private void onConnect(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String userUid = request.getSession(false).getId();

        // retrieve data structures from the servlet context
        ServletContext context = getServletContext();
        HashMap<String, String> connectedUsers = (HashMap<String, String>) context.getAttribute("connectedUsers");

        // add a new connected user ,only if it's his first message
        if (!connectedUsers.keySet().contains(userUid))
            connectedUsers.put(userUid, userName);
    }

    /**
     * function that handle the disconnect users in the Servlet Context
     *
     * @param request that comes from clients
     */
    private void onDisconnect(HttpServletRequest request) {
        String userUid = request.getSession(false).getId();

        // retrieve data structures from the servlet context
        ServletContext context = getServletContext();
        HashMap<String, String> connectedUsers = (HashMap<String, String>) context.getAttribute("connectedUsers");

        connectedUsers.remove(userUid);
    }

    /**
     * function that get the data from the Servlet Context and parsing a Json to be send to the clients
     *
     * @param request that comes from clients
     * @return json object to clients
     */
    private JsonObject getChatJsonData(HttpServletRequest request) {
        JsonObjectBuilder usersJson = Json.createObjectBuilder();
        String userUid = request.getSession(false).getId();

        // retrieve data structures from the servlet context
        ServletContext context = getServletContext();
        HashMap<String, String> connectedUsers = (HashMap<String, String>) context.getAttribute("connectedUsers");
        StringBuilder messages = (StringBuilder) context.getAttribute("messages");

        var users = connectedUsers.values().toArray(new String[0]);
        for (int i = 0; i < users.length; i++)
            usersJson.add(String.valueOf(i), users[i]);


        return Json.createObjectBuilder()
                .add("messages", messages.toString())
                .add("users", usersJson.build())
                .add("connected", connectedUsers.keySet().contains(userUid))
                .build();
    }
}
