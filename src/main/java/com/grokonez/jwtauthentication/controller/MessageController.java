package com.grokonez.jwtauthentication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grokonez.jwtauthentication.DTOS.MessageDTO;
import com.grokonez.jwtauthentication.DTOS.NotificationDTO;
import com.grokonez.jwtauthentication.model.Message;
import com.grokonez.jwtauthentication.model.Notification;
import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.MessageRepository;
import com.grokonez.jwtauthentication.repository.NotificationRepository;
import com.grokonez.jwtauthentication.repository.UserRepository;
import com.grokonez.jwtauthentication.security.services.ActiviteService;
import com.grokonez.jwtauthentication.security.services.MessageService;
import com.grokonez.jwtauthentication.security.services.NotificationService;

@RestController
@CrossOrigin ( origins = "http://localhost:4200" , allowedHeaders = "*")
@RequestMapping("/api/auth")
public class MessageController {
	
	    @Autowired
	    MessageRepository messageRepository;
	    @Autowired
	    MessageService messageService;
	    @Autowired
	    UserRepository userRepository;
	
    @GetMapping(value = "/messages")
    public List<MessageDTO> showMessageByUser(@RequestParam("username") String username){
        User user = userRepository.findByUsername(username).get();
        List<Message> messages = messageRepository.findMessageByUser(user);
        List<MessageDTO> messageDTOS = messageService.getAllMessageDTOSByUser(messages);
        return messageDTOS;
    }

}
