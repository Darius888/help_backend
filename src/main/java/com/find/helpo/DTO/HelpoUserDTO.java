package com.find.helpo.DTO;

import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class HelpoUserDTO {

    private Integer helpoUserID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
