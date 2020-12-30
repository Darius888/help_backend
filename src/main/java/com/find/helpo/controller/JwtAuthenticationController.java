package com.find.helpo.controller;

import com.find.helpo.config.JwtTokenUtil;
import com.find.helpo.model.*;
import com.find.helpo.service.JwtHelpSeekerDetailsService;
import com.find.helpo.service.JwtHelperDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(path = "/users")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtHelperDetailsService helperDetailsService;

    @Autowired
    private JwtHelpSeekerDetailsService helpSeekerDetailsService;


    @RequestMapping(value = "/authenticate/helper", method = RequestMethod.POST) //TO-DO add login model
    public ResponseEntity<UserLoginResponse> createAuthenticationTokenForHelper(@RequestBody HelperLogin helperAuthenticationRequest) throws Exception {

        authenticate(helperAuthenticationRequest.getUserEmail(), helperAuthenticationRequest.getPassword());
        final UserDetails userDetails = helperDetailsService.loadUserByUsername(helperAuthenticationRequest.getUserEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);
        final UserLoginResponse userLoginResponse = new UserLoginResponse();
        if (helperDetailsService.checkIfHelperExists(helperAuthenticationRequest.getUserEmail())) {

            userLoginResponse.setUserEmail("success");
            userLoginResponse.setResponse("Login successfull");
            userLoginResponse.setToken(token);
        } else {
            userLoginResponse.setUserEmail("failure");
            userLoginResponse.setResponse("User does not exist");
            userLoginResponse.setToken("sorry");
        }

        return ResponseEntity.ok(userLoginResponse);
    }



    @RequestMapping(value = "/authenticate/helpseeker", method = RequestMethod.POST) //TO-DO add login model
    public ResponseEntity<UserLoginResponse> createAuthenticationTokenForHelpSeeker(@RequestBody HelpSeekerLogin helpSeekerAuthenticationRequest) throws Exception {

        authenticate(helpSeekerAuthenticationRequest.getUserEmail(), helpSeekerAuthenticationRequest.getPassword());
        final UserDetails userDetails = helperDetailsService.loadUserByUsername(helpSeekerAuthenticationRequest.getUserEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);
        final UserLoginResponse userLoginResponse = new UserLoginResponse();
        if (helperDetailsService.checkIfHelperExists(helpSeekerAuthenticationRequest.getUserEmail())) {

            userLoginResponse.setUserEmail("success");
            userLoginResponse.setResponse("Login successfull");
            userLoginResponse.setToken(token);
        } else {
            userLoginResponse.setUserEmail("failure");
            userLoginResponse.setResponse("User does not exist");
            userLoginResponse.setToken("sorry");
        }

        return ResponseEntity.ok(userLoginResponse);
    }

    @RequestMapping(value = "/register/helper", method = RequestMethod.POST)
    public ResponseEntity<UserRegisterResponse> saveHelper(@RequestBody Helper helper) throws Exception {
        String response = helperDetailsService.saveHelper(helper);
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setResponse(response);
        userRegisterResponse.setUserEmail(helper.getEmail());
        return ResponseEntity.ok(userRegisterResponse);
    }

    @RequestMapping(value = "/register/helpseeker", method = RequestMethod.POST)
    public ResponseEntity<UserRegisterResponse> saveHelpSeeker(@RequestBody HelpSeeker helpSeeker) throws Exception {
        String response = helpSeekerDetailsService.saveHelpSeeker(helpSeeker);
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setResponse(response);
        userRegisterResponse.setUserEmail(helpSeeker.getEmail());
        return ResponseEntity.ok(userRegisterResponse);
    }


    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public String validateToken(@RequestBody JwtValidationModel jwtValidationModel) throws Exception {
        String response = "";
        if (!(jwtTokenUtil.validateToken2(jwtValidationModel.getUserEmail(), jwtValidationModel.getToken()))) {
            response = "INVALID";
        } else {
            response = "VALID";
        }
        return response;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}