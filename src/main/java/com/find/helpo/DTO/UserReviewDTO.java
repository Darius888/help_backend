package com.find.helpo.DTO;

import com.find.helpo.model.HelpoUser;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserReviewDTO {

    private Integer userReviewID;
    private String userReview;
    private Integer reviewOwnerID;
    private Integer reviewReceiverID;

    public void setReviewOwnerID(HelpoUser helpoUser) {
        this.reviewOwnerID = helpoUser.getHelpoUserID();
    }
    public Integer getReviewOwnerID(HelpoUser helpoUser) {
        return helpoUser.getHelpoUserID();
    }

    public void setReviewReceiverID(HelpoUser helpoUser) {
        this.reviewReceiverID = helpoUser.getHelpoUserID();
    }
    public Integer getReviewReceiverID(HelpoUser helpoUser) {
        return helpoUser.getHelpoUserID();
    }

}
