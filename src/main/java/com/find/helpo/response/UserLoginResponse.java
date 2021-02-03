package com.find.helpo.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserLoginResponse {

    private String response;
    private String token;

}
