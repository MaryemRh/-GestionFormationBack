package com.grokonez.jwtauthentication.security.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.grokonez.jwtauthentication.DTOS.MessageDTO;
import com.grokonez.jwtauthentication.model.Message;


@Service
public class MessageService {

	
	   public List<MessageDTO> getAllMessageDTOSByUser(List<Message> messages) {
	        return messages
	                .stream()
	                .map(this::convertToMessageDTO)
	                .collect(Collectors.toList());
	    }

	    public MessageDTO convertToMessageDTO(Message message) {
	    	MessageDTO messageDTO = new MessageDTO();
	    	messageDTO.setMessage(message.getMesssage());
	        return messageDTO;
	    }
	    
}
