package com.find.helpo.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserLoginResponse {

    private String response;
    private String userEmail;
    private String token;

}
