package com.find.helpo.service;

import com.find.helpo.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;

@Service
@Transactional
public class CookieValidationService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public Boolean validateCookieToken(Cookie[] cookies)
    {
        if(cookies.length !=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {

                    if ((jwtTokenUtil.validateToken(cookie.getValue(), jwtTokenUtil.getUserEmailFromToken(cookie.getValue()))))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Cookie getCookieWithTokenValue(Cookie[] cookies)
    {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    return cookie;
                }
            }
            return null;
    }

}
