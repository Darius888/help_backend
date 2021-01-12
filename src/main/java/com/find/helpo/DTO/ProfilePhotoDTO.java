package com.find.helpo.DTO;

import com.find.helpo.model.HelpoUser;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProfilePhotoDTO {

    private Integer profilePhotoID;
    private Integer relatedUserID;

    public void setRelatedUserID(HelpoUser helpoUser) {
        this.relatedUserID = helpoUser.getHelpoUserID();
    }
    public Integer getRelatedUserID(HelpoUser helpoUser) {
        return helpoUser.getHelpoUserID();
    }

}
