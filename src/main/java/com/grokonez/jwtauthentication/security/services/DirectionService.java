package com.grokonez.jwtauthentication.security.services;

import com.grokonez.jwtauthentication.repository.ActiviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grokonez.jwtauthentication.DTOS.ActiviteDTO;
import com.grokonez.jwtauthentication.DTOS.DirectionDTO;
import com.grokonez.jwtauthentication.DTOS.UserDTO;
import com.grokonez.jwtauthentication.model.Activite;
import com.grokonez.jwtauthentication.model.Direction;
import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.DirectionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DirectionService {

    @Autowired
    DirectionRepository directionRepository;
    @Autowired
    ActiviteRepository activiteRepository;
    @Autowired
    UserDetailsServiceImpl employeeService;
    @Autowired
    ActiviteService activiteService;

    @Transactional
    public List<ActiviteDTO> getActivitiesByDirectionId(Direction direction){
		
		return (List<ActiviteDTO>) activiteRepository.findActiviteByDirection(direction)
				.stream()
                .map(this::convertToActiviteDTO)
                .collect(Collectors.toList());
                
	}
    @Transactional
    public List<DirectionDTO> getAllDirectionsDTOS() {
        return ((List<Direction>) directionRepository
                .findAll())
                .stream()
                .map(this::convertToDirectionDTO)
                .collect(Collectors.toList());
    }
    @Transactional
    public DirectionDTO convertToDirectionDTO(Direction direction) {
    	
    	DirectionDTO directionDTO = new DirectionDTO();

        

        directionDTO.setNomDirection(direction.getNomDirection());
        directionDTO.setId(direction.getId());
        directionDTO.setUserDTO(employeeService.convertToUserDTO(direction.getUser()));
        return directionDTO;
    }
    @Transactional
    public ActiviteDTO convertToActiviteDTO(Activite activite) {
        ActiviteDTO activiteDTO = new ActiviteDTO(activite.getNomActivite());
        Direction direction = activite.getDirection();
       // activiteDTO.setIdDirection(direction.getId());
        activiteDTO.setDirection(convertToDirectionDTO(directionRepository.findByNomDirection(direction.getNomDirection())));
        activiteDTO.setResponsable(activite.getUser().getUsername());

        return activiteDTO;
    }

}
