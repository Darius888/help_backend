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
    private Integer userReviewID;
    @Column(name = "user_review_type")
    private String userReviewType;
    @Column(name = "user_review")
    private String userReview;
    @Column(name = "review_owner_helpo_user_id")
    private Integer reviewOwnerHelpoUserID;

    @ManyToOne
    @JoinColumn(name = "helpo_user_id", referencedColumnName = "helpo_user_id", insertable = false, nullable = false)
    private HelpoUser helpoUserReview;


}
