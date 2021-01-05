package com.find.helpo.controller;

import com.find.helpo.config.JwtTokenUtil;
import com.find.helpo.model.*;
import com.find.helpo.repository.HelpoUserRepository;
import com.find.helpo.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/users")
public class JwtAuthenticationController {


    private final String HEADER = "Authorization";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService helpoUserDetailsService;

    @Autowired
    private HelpoUserRepository helpoUserRepository;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<UserLoginResponse> createAuthenticationTokenForHelpSeeker(@RequestBody HelpoUserLogin helpoUserAuthenticationRequest) throws Exception {
        authenticate(helpoUserAuthenticationRequest.getUserEmail(), helpoUserAuthenticationRequest.getPassword());
        final UserDetails userDetails = helpoUserDetailsService.loadUserByUsername(helpoUserAuthenticationRequest.getUserEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);
        final UserLoginResponse userLoginResponse = new UserLoginResponse();
        if (helpoUserDetailsService.checkIfHelpSeekerExists(helpoUserAuthenticationRequest.getUserEmail())) {

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

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserRegisterResponse> saveHelpSeeker(@RequestBody HelpoUser helpoUser) throws Exception {
        String response = helpoUserDetailsService.saveHelpSeeker(helpoUser);
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setResponse(response);
        userRegisterResponse.setUserEmail(helpoUser.getEmail());
        return ResponseEntity.ok(userRegisterResponse);
    }


    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public String validateToken(@RequestBody JwtValidationModel jwtValidationModel) throws Exception {
        if (!(jwtTokenUtil.validateToken(jwtValidationModel.getToken(),jwtValidationModel.getUserEmail())))
            return "INVALID";
            else return "VALID";

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