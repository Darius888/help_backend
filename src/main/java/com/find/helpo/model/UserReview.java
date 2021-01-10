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
    @Column(name = "user_review")
    private String userReview;
    @Column(name = "review_owner_id")
    private Integer reviewOwnerID;
    @Column(name = "review_receiver_id")
    private Integer reviewReceiverID;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "helpo_user_id", referencedColumnName = "helpo_user_id", insertable = false, nullable = false)
    private HelpoUser helpoUserReview;


}
