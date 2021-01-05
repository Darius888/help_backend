package com.find.helpo.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "helpo_user", schema = "public")
public class HelpoUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "helpo_user_id")
    private Integer helpoUserID;
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

    @OneToMany(mappedBy = "helpoUser", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<HelpoJob> helpoJobList = new ArrayList<>();

//    @OneToOne(mappedBy = "helpSeekerProfilePhoto", cascade =  CascadeType.ALL)
//    private ProfilePhoto profilePhoto;

    @OneToMany(mappedBy = "helpoUserReview", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserReview> userReviewList;

    @OneToMany(mappedBy = "helpoUserAverageScore", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AverageScore> averageScoreList;



}
