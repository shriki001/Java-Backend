package com.ex4.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * interface that extends {@link JpaRepository}
 * and handle the Message connection with the database
 */
public interface MessagesRepository extends JpaRepository<Messages, Long> {
    // return message by given author name
    List<Messages> findByAuthor(String author);

    //return list of messages by id after given id
    @Query("SELECT m FROM Messages m WHERE m.id > ?1")
    List<Messages> findMessagesByCount(Long id);

    // return the last message form the database
    Messages findFirstByOrderByIdDesc();

    // return list of the last 3 messages
    List<Messages> findTop3ByOrderByIdDesc();

    // return list of messages that contains the given string
    List<Messages> findByMsgContainingIgnoreCase(@NotBlank(message = "Name is " +
            "mandatory") String msg);

    // return list of messages that wrote by the given author name
    List<Messages> findByAuthorContainingIgnoreCase(@NotBlank(message = "Name is mandatory") String author);
}