package com.find.helpo.DTO;

import com.find.helpo.model.HelpoUser;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProfilePhotoDTO {

    private Integer profilePhotoID;
    private Integer relatedUserID;
    private MultipartFile profilePhoto;

    public void setRelatedUserID(HelpoUser helpoUser) {
        this.relatedUserID = helpoUser.getHelpoUserID();
    }
    public Integer getRelatedUserID(HelpoUser helpoUser) {
        return helpoUser.getHelpoUserID();
    }

}
