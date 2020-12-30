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
@Table(name = "helper", schema = "public")
public class Helper implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "helper_id")
    private Long helperId;
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

    @OneToMany(mappedBy = "helper")
    private List<Job> favoredJobList;

//    @OneToOne(mappedBy = "helperProfilePhoto", cascade =  CascadeType.ALL)
//    private ProfilePhoto profilePhoto;

    @OneToMany(mappedBy = "helperUserReview")
    private List<UserReview> userReviewList;

    @OneToMany(mappedBy = "helperAverageScore")
    private List<AverageScore> averageScoreList;




}
