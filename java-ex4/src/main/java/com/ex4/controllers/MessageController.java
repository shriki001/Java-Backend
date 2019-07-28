package com.ex4.controllers;

import com.ex4.repo.Messages;
import com.ex4.repo.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * controller that handle all the messages transactions
 */
@Controller
public class MessageController {

    // the application context is injected via ctor
    private final ApplicationContext context;

    @Autowired
    public MessageController(ApplicationContext context) {
        this.context = context;
    }

    private MessagesRepository getRepo() {

        return (MessagesRepository) this.context.getBean(MessagesRepository.class);
    }

    // send new message from user and get all the messages that he missed
    @PostMapping("/sendmsg")
    public @ResponseBody
    List<Messages> saveMsg(@Valid Messages msg, Model model, HttpSession session) {
        MessagesRepository repo = context.getBean(MessagesRepository.class);
        synchronized (context) {
            Long id = (Long) session.getAttribute("LAST_ID");
            if (id == null)
                return null; // in case that the user disconnect himself
            repo.save(msg);
            Messages tmp = getRepo().findFirstByOrderByIdDesc();
            session.setAttribute("LAST_ID", tmp.getId());
            return getRepo().findMessagesByCount(id);
        }
    }

    // when ajax refresh after 30 sec the user will get the exact message that he miss
    @PostMapping(value = "/getjson/{id}")
    public @ResponseBody
    List<Messages> getAll(@PathVariable("id") Long id, Model model) {
        return getRepo().findMessagesByCount(id);
    }

    // search by author
    @PostMapping(value = "/searcha/{name}")
    public @ResponseBody
    List<Messages> getAllByAuthor(@PathVariable("name") String name, Model model) {
        return getRepo().findByAuthorContainingIgnoreCase(name);
    }

    // search by message
    @PostMapping(value = "/searchm/{msg}")
    public @ResponseBody
    List<Messages> getAllByMessage(@PathVariable("msg") String name, Model model) {
        return getRepo().findByMsgContainingIgnoreCase(name);
    }
}