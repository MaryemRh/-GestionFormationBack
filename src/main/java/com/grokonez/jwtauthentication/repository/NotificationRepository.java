package com.grokonez.jwtauthentication.repository;


import com.grokonez.jwtauthentication.model.Activite;
import com.grokonez.jwtauthentication.model.Notification;
import com.grokonez.jwtauthentication.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends CrudRepository<Notification,Integer> {

    List<Notification> findNotificationByUser(User user);
    /*
    @Query( "select e from User e join e.activite a  where a.id =:idA")
 	List<User> getEmployeesByActiviteId(@Param("idA")int idA);
*/
}
