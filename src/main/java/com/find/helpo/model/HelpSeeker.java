package com.find.helpo.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "help_seeker", schema = "public")
public class HelpSeeker implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "help_seeker_id")
    private Long helpSeekerId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "helpSeeker")
    private List<Job> jobList;

//    @OneToOne(mappedBy = "helpSeekerProfilePhoto", cascade =  CascadeType.ALL)
//    private ProfilePhoto profilePhoto;

    @OneToMany(mappedBy = "helpSeekerUserReview")
    private List<UserReview> userReviewList;

    @OneToMany(mappedBy = "helpSeekerAverageScore")
    private List<AverageScore> averageScoreList;




}
