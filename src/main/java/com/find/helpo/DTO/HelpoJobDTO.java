package com.find.helpo.DTO;

import com.find.helpo.model.HelpoUser;
import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class HelpoJobDTO {

    private Integer helpoJobID;
    private String jobTitle;
    private String jobPostDate;
    private String jobType;
    private String jobDescription;
    private String jobReward;
    private String jobStatus;
    private Integer jobOwnerID;
    private String jobFavoredStatus;

    public void setJobOwnerID(HelpoUser helpoUser) {
        this.jobOwnerID = helpoUser.getHelpoUserID();
    }
    public Integer getJobOwnerID(HelpoUser helpoUser) {
        return helpoUser.getHelpoUserID();
    }

}
