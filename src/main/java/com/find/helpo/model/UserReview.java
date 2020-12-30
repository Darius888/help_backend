package com.find.helpo.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user_review", schema = "public")
public class UserReview {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_review_id")
    private Long userReviewID;
    @Column(name = "user_review_type")
    private String userReviewType;
    @Column(name = "user_review")
    private String userReview;
    @Column(name = "review_owner_help_seeker_id")
    private Long reviewOwnerHelpSeekerID;
    @Column(name = "review_owner_helper_id")
    private Long reviewOwnerHelperID;

    @ManyToOne
    @JoinColumn(name = "help_seeker_id", referencedColumnName = "help_seeker_id", insertable = false, nullable = false)
    private HelpSeeker helpSeekerUserReview;

    @ManyToOne
    @JoinColumn(name = "helper_id", referencedColumnName = "helper_id", insertable = false, nullable = false)
    private Helper helperUserReview;



}
