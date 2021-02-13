package com.find.helpo.model;

import lombok.*;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Transactional
public class HelpoJobGet {

    private String jobTitle;
    private String jobPostDate;
    private String jobType;
    private String jobDescription;
    private String jobReward;
    private String jobStatus;
    private String jobFavoredStatus;
    private String jobOwnerFirstName;
    private String jobOwnerLastName;
    private ArrayList<String> jobPhotoList;

}
