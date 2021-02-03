package com.find.helpo.service;

import com.find.helpo.DTO.HelpoUserDTO;
import com.find.helpo.model.HelpoJob;
import com.find.helpo.model.HelpoUser;
import com.find.helpo.repository.HelpoUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private HelpoUserRepository helpoUserRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        HelpoUser helpoUser = helpoUserRepository.findByEmail(userEmail);
        if (helpoUser == null) {
            throw new UsernameNotFoundException("User not found with email: " + userEmail);
        }
        return new org.springframework.security.core.userdetails.User(helpoUser.getEmail(), helpoUser.getPassword(),
                new ArrayList<>());
    }

    public String saveHelpSeeker(HelpoUserDTO helpoUserDTO) {

        ModelMapper modelMapper = new ModelMapper();
        HelpoUser newHelpoUser = modelMapper.map(helpoUserDTO, HelpoUser.class);
        String response = "";
        if(!(checkIfHelpSeekerExists(helpoUserDTO.getEmail())))
        {
            newHelpoUser.setEmail(helpoUserDTO.getEmail());
            newHelpoUser.setPassword(bcryptEncoder.encode(helpoUserDTO.getPassword()));
            newHelpoUser.setFirstName(helpoUserDTO.getFirstName());
            newHelpoUser.setLastName(helpoUserDTO.getLastName());
            helpoUserRepository.save(newHelpoUser);

            response = "User registered successfully";
        } else
        {
            response = "User with such email already exists";
        }

        return response;
    }

    public Boolean checkIfHelpSeekerExists(String helpSeekerEmail)
    {
        return helpoUserRepository.existsByEmail(helpSeekerEmail);
    }
}