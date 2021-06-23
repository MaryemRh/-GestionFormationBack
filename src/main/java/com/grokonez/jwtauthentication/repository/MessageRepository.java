package com.grokonez.jwtauthentication.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grokonez.jwtauthentication.model.Message;
import com.grokonez.jwtauthentication.model.User;

@Repository
public interface MessageRepository extends CrudRepository<Message,Integer>{

	List<Message> findMessageByUser(User user);
}
