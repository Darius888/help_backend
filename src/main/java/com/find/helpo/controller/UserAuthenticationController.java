package com.find.helpo.controller;

import com.find.helpo.DTO.HelpoUserDTO;
import com.find.helpo.DTO.HelpoUserEmailDTO;
import com.find.helpo.config.JwtTokenUtil;
import com.find.helpo.model.*;
import com.find.helpo.repository.HelpoUserRepository;
import com.find.helpo.response.UserEmailValidityResponse;
import com.find.helpo.response.UserLoginResponse;
import com.find.helpo.response.UserRegisterResponse;
import com.find.helpo.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;


@RestController
@RequestMapping(path = "/users")
public class UserAuthenticationController {
    
    private final String HEADER = "Authorization";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService helpoUserDetailsService;

    @Autowired
    private HelpoUserRepository helpoUserRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<UserLoginResponse> createAuthenticationTokenForHelpSeeker(@RequestBody HelpoUserLogin helpoUserAuthenticationRequest, HttpServletResponse res, HttpServletRequest request) throws Exception {
        authenticate(helpoUserAuthenticationRequest.getUserEmail(), helpoUserAuthenticationRequest.getPassword());
        final UserDetails userDetails = helpoUserDetailsService.loadUserByUsername(helpoUserAuthenticationRequest.getUserEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);
        final UserLoginResponse userLoginResponse = new UserLoginResponse();
        Cookie[] cookiez = request.getCookies();
        Cookie cook;
        for(int i=0; i<cookiez.length; i++)
        {
            cook=cookiez[i];
            System.out.println("AAA " + cook.getValue());
            System.out.println("AAA " + cook.getName());
        }
        System.out.println(Arrays.toString(request.getCookies()));
        if (helpoUserDetailsService.checkIfHelpSeekerExists(helpoUserAuthenticationRequest.getUserEmail())) {

            userLoginResponse.setResponse("Login successfull");
            userLoginResponse.setToken(token);
            Cookie cookie = new Cookie("token", token);
            cookie.setSecure(false);
            cookie.setHttpOnly(false);
//            cookie.setDomain("/");
//            res.setHeader("Access-Control-Allow-Origin", "*");
            res.setHeader("Access-Control-Allow-Credentials", "true");
            res.setHeader("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
            res.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
            res.addCookie(cookie);

        } else {
            userLoginResponse.setResponse("User does not exist");
            userLoginResponse.setToken("User does not exist");
        }


        return ResponseEntity.ok().body(userLoginResponse);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/validateEmail", method = RequestMethod.POST)
    public ResponseEntity<UserEmailValidityResponse> checkIfUserWithSuchEmailExist(@RequestBody HelpoUserEmailDTO userEmail) throws Exception {
        final UserEmailValidityResponse userEmailValidityResponse = new UserEmailValidityResponse();
        if (helpoUserDetailsService.checkIfHelpSeekerExists(userEmail.getEmail())) {

            userEmailValidityResponse.setResponse("Exists");

        } else {

            userEmailValidityResponse.setResponse("User does not exist");

        }
        return ResponseEntity.ok(userEmailValidityResponse);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserRegisterResponse> saveHelpSeeker(@RequestBody HelpoUserDTO helpoUserDTO) throws Exception {
        String response = helpoUserDetailsService.saveHelpSeeker(helpoUserDTO);
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setResponse(response);
        return ResponseEntity.ok(userRegisterResponse);
    }

    @CrossOrigin(origins = "http://localhost:4200")
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