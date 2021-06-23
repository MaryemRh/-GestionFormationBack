package com.grokonez.jwtauthentication.security.services;


import com.grokonez.jwtauthentication.DTOS.ActiviteDTO;
import com.grokonez.jwtauthentication.DTOS.NotificationDTO;
import com.grokonez.jwtauthentication.DTOS.UserDTO;
import com.grokonez.jwtauthentication.model.*;
import com.grokonez.jwtauthentication.repository.ActiviteRepository;
import com.grokonez.jwtauthentication.repository.DirectionRepository;
import com.grokonez.jwtauthentication.repository.NotificationRepository;
import com.grokonez.jwtauthentication.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    ActiviteRepository activiteRepository;
    @Autowired
    DirectionRepository directionRepository;
    @Autowired
    UserDetailsServiceImpl employeeService;
    @Autowired
    DirectionService directionService;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    UserRepository userRepository;

    public List<NotificationDTO> getAllNotificationDTOSByUser(List<Notification> notifications) {
        return notifications
                .stream()
                .map(this::convertToNotificationDTO)
                .collect(Collectors.toList());
    }

    public List<NotificationDTO> getAllNotificationDTOS(@PathVariable int id) {
    	User user = userRepository.getUserById(id).get();
        List<Notification> notifications = new ArrayList<>();

        return ( user.getNotifications())
                .stream()
                .map(this::convertToNotificationDTO)
                .collect(Collectors.toList());
    }
    
    public NotificationDTO convertToNotificationDTO(Notification notification) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setMessage(notification.getMesssage());
        return notificationDTO;
    }

}
