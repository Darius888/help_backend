package com.find.helpo.service;

import com.find.helpo.model.Helper;
import com.find.helpo.repository.HelperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtHelperDetailsService implements UserDetailsService {

    @Autowired
    private HelperRepository helperRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        Helper helper = helperRepository.findByEmail(userEmail);
        if (helper == null) {
            throw new UsernameNotFoundException("User not found with email: " + userEmail);
        }
        return new org.springframework.security.core.userdetails.User(helper.getEmail(), helper.getPassword(),
                new ArrayList<>());
    }

    public String saveHelper(Helper helper) {

        Helper newHelper = new Helper();
        String response = "";
        if(!(checkIfHelperExists(helper.getEmail())))
        {
            newHelper.setEmail(helper.getEmail());
            newHelper.setPassword(bcryptEncoder.encode(helper.getPassword()));
            newHelper.setFirstName(helper.getFirstName());
            newHelper.setLastName(helper.getLastName());
            newHelper.setUsername(helper.getUsername());
            helperRepository.save(newHelper);
            response = "Helper registered successfully";
        } else
        {
            response = "Helper with such email already exists";
        }

        return response;
    }

    public Boolean checkIfHelperExists(String helperEmail)
    {
        return helperRepository.existsByEmail(helperEmail);
    }
}