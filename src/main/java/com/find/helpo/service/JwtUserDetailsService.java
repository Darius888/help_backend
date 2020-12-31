package com.find.helpo.service;

import com.find.helpo.model.HelpoUser;
import com.find.helpo.repository.HelpoUserRepository;
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

    public String saveHelpSeeker(HelpoUser helpoUser) {

        HelpoUser newHelpoUser = new HelpoUser();
        String response = "";
        if(!(checkIfHelpSeekerExists(helpoUser.getEmail())))
        {
            newHelpoUser.setEmail(helpoUser.getEmail());
            newHelpoUser.setPassword(bcryptEncoder.encode(helpoUser.getPassword()));
            newHelpoUser.setFirstName(helpoUser.getFirstName());
            newHelpoUser.setLastName(helpoUser.getLastName());
            newHelpoUser.setUsername(helpoUser.getUsername());
            helpoUserRepository.save(newHelpoUser);

            response = "Help seeker registered successfully";
        } else
        {
            response = "Help seeker with such email already exists";
        }

        return response;
    }

    public Boolean checkIfHelpSeekerExists(String helpSeekerEmail)
    {
        return helpoUserRepository.existsByEmail(helpSeekerEmail);
    }
}