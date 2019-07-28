package com.ex4.controllers;

import com.ex4.repo.MessagesRepository;
import com.ex4.repo.User;
import com.ex4.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * main controller that handle all the user connections
 */
@Controller
public class LoginController {

    // the application context is injected via ctor
    private final ApplicationContext context;

    @Autowired
    public LoginController(ApplicationContext context) {
        this.context = context;
    }

    private UserRepository getRepo() {

        return (UserRepository) this.context.getBean(UserRepository.class);
    }

    // get the home page
    @GetMapping("/")
    public String start(User user, Model model, HttpSession session) {
        User olduser = (User) session.getAttribute("MY_SESSION_USER");
        if (olduser == null)
            return "index";

        MessagesRepository mrepo = context.getBean(MessagesRepository.class);
        model.addAttribute("topmsg", mrepo.findTop3ByOrderByIdDesc());
        model.addAttribute("users", getRepo().findAll());
        model.addAttribute("user", olduser);
        return "lobby";
    }

    // get the search page
    @GetMapping("/search")
    public String search() {
        return "search";
    }

    // adding new user to database chat
    @PostMapping("/connect")
    public String addNewUser(@Valid User user, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors())
            return "index";
        synchronized (context) {
            getRepo().save(user);
            User tmp = getRepo().findFirstByOrderByIdDesc();
            user.setId(tmp.getId());
            session.setAttribute("MY_SESSION_USER", user);
        }
        return "redirect:/";
    }

    // handle disconnection of users
    @GetMapping("/disconnect/{id}")
    public String disconnectUser(@PathVariable("id") Long id, Model model, HttpSession session) {
        session.removeAttribute("MY_SESSION_USER");
        User user = getRepo().findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        getRepo().delete(user);
        session.invalidate();
        return "redirect:/";
    }

    // get all users list
    @PostMapping(value = "/getusers")
    public @ResponseBody
    List<User> getAll(Model model) {
        return getRepo().findAll();
    }
}