package com.find.helpo.DTO;


import com.find.helpo.model.HelpoUser;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AverageScoreDTO {

    private Integer scoreID;
    private Float userScore;
    private Integer scoreOwnerID;
    private Integer scoreReceiverID;

    public void setScoreOwnerID(HelpoUser helpoUser) {
        this.scoreOwnerID = helpoUser.getHelpoUserID();
    }
    public Integer getScoreOwnerID(HelpoUser helpoUser) {
        return helpoUser.getHelpoUserID();
    }

    public void setScoreReceiverID(HelpoUser helpoUser) {
        this.scoreReceiverID = helpoUser.getHelpoUserID();
    }
    public Integer getScoreReceiverID(HelpoUser helpoUser) {
        return helpoUser.getHelpoUserID();
    }

}
