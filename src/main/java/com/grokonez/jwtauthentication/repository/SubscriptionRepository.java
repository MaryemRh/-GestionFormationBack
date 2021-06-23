package com.grokonez.jwtauthentication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grokonez.jwtauthentication.model.Subscription;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription,Integer>{

}
