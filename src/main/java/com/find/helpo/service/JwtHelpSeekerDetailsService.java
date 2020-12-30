package com.find.helpo.service;

import com.find.helpo.model.HelpSeeker;
import com.find.helpo.model.Helper;
import com.find.helpo.repository.HelpSeekerRepository;
import com.find.helpo.repository.HelperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtHelpSeekerDetailsService implements UserDetailsService {

    @Autowired
    private HelpSeekerRepository helpSeekerRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        HelpSeeker helpSeeker = helpSeekerRepository.findByEmail(userEmail);
        if (helpSeeker == null) {
            throw new UsernameNotFoundException("User not found with email: " + userEmail);
        }
        return new org.springframework.security.core.userdetails.User(helpSeeker.getEmail(), helpSeeker.getPassword(),
                new ArrayList<>());
    }

    public String saveHelpSeeker(HelpSeeker helpSeeker) {

        HelpSeeker newHelpSeeker = new HelpSeeker();
        String response = "";
        if(!(checkIfHelpSeekerExists(helpSeeker.getEmail())))
        {
            newHelpSeeker.setEmail(helpSeeker.getEmail());
            newHelpSeeker.setPassword(bcryptEncoder.encode(helpSeeker.getPassword()));
            newHelpSeeker.setFirstName(helpSeeker.getFirstName());
            newHelpSeeker.setLastName(helpSeeker.getLastName());
            newHelpSeeker.setUsername(helpSeeker.getUsername());
            helpSeekerRepository.save(newHelpSeeker);

            response = "Help seeker registered successfully";
        } else
        {
            response = "Help seeker with such email already exists";
        }

        return response;
    }

    public Boolean checkIfHelpSeekerExists(String helpSeekerEmail)
    {
        return helpSeekerRepository.existsByEmail(helpSeekerEmail);
    }
}