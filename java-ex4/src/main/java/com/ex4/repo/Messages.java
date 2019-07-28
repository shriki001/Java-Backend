package com.ex4.repo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * class Message that inject message to database
 * */
@Entity
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name is mandatory")
    private String author;

    @NotBlank(message = "Name is mandatory")
    private String msg;

    public Messages() {}

    public Messages(String author, String msg) {
        this.author = author;
        this.msg = msg;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }


    @Override
    public String toString() {
        return "MSG{" + "user=" + author + ", msg=" + msg + '}';
    }
}

