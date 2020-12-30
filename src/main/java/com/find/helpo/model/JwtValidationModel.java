package com.find.helpo.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtValidationModel {

    private String userEmail;
    private String token;

}
