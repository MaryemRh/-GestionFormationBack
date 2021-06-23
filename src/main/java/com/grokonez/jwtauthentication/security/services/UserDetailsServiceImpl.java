package com.grokonez.jwtauthentication.security.services;


import com.grokonez.jwtauthentication.DTOS.ParticipantDTO;
import com.grokonez.jwtauthentication.DTOS.UserDTO;
import com.grokonez.jwtauthentication.model.*;
import com.grokonez.jwtauthentication.repository.UserRepository;


import java.text.ParseException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
@Autowired
private ModelMapper modelMapper;
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}
	
 


    @Transactional
    public List<UserDTO> getAllemployeeDTOS() {
        return ((List<User>) userRepository
                .findAll())
                .stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }
    @Transactional
    public User convertToUser(UserDTO userDTO)  {

        User user =modelMapper.map(userDTO,User.class);

        return user;
    }
    @Transactional
    public UserDTO convertToUserDTO(User user) {

        UserDTO userDTO =modelMapper.map(user,UserDTO.class);

        return userDTO;
    }
    


}