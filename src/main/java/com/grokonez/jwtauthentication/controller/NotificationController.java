package com.grokonez.jwtauthentication.controller;

import com.grokonez.jwtauthentication.DTOS.NotificationDTO;
import com.grokonez.jwtauthentication.model.*;
import com.grokonez.jwtauthentication.repository.ActiviteRepository;
import com.grokonez.jwtauthentication.repository.DirectionRepository;
import com.grokonez.jwtauthentication.repository.NotificationRepository;
import com.grokonez.jwtauthentication.repository.UserRepository;
import com.grokonez.jwtauthentication.security.services.ActiviteService;
import com.grokonez.jwtauthentication.security.services.DirectionService;
import com.grokonez.jwtauthentication.security.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin ( origins = "http://localhost:4200" , allowedHeaders = "*")
@RequestMapping("/api/auth")
public class NotificationController {

    @Autowired
    ActiviteRepository activiteRepository;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    ActiviteService activiteService;
    @Autowired
    NotificationService notificationService;
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/notifications")
    public List<NotificationDTO> showNotificationByUser(@RequestParam("username") String username){
        User user = userRepository.findByUsername(username).get();
        List<Notification> notifications = notificationRepository.findNotificationByUser(user);
        List<NotificationDTO> notificationDTOS = notificationService.getAllNotificationDTOSByUser(notifications);
        return notificationDTOS;
    }
    
    @GetMapping(value = "/notifications/Users/{id}")
    public List<NotificationDTO> showNotifications(@PathVariable int id){

        List<NotificationDTO> notificationDTOS = notificationService.getAllNotificationDTOS(id);
        return notificationDTOS;
    }


}
