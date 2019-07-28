package com.ex4.listeners;

import com.ex4.repo.User;
import com.ex4.repo.UserRepository;
import org.springframework.context.ApplicationContext;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * class that implements {@link javax.servlet.http.HttpSessionListener}
 * and handle the session events
 * */

@WebListener
public class MySessionListener implements javax.servlet.http.HttpSessionListener {
    private final ApplicationContext context;

    /**
     * c-tor for the session
     * @param context of the application
     * */
    public MySessionListener(ApplicationContext context){
        this.context = context;
    }

    /**
     * function that handle the session create event
     * insert default value to "LAST_ID" attribute
     * @param se the event
     * */
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute("LAST_ID", 0L);
    }

    /**
     * function that handle the session destroy event
     * and remove the client from the database
     * @param se the event
     * */
    public void sessionDestroyed(HttpSessionEvent se) {
        synchronized (context) {
            User tmp = (User) se.getSession().getAttribute("MY_SESSION_USER");
            if(tmp != null){
            se.getSession().removeAttribute("MY_SESSION_USER");
            UserRepository repo = context.getBean(UserRepository.class);
            User user = repo.findById(tmp.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + tmp.getId()));
            repo.delete(user);
            }
        }
    }
}