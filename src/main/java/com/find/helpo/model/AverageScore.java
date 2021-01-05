package com.find.helpo.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "average_score")
public class AverageScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "average_score_id")
    private Integer averageScoreID;
    @Column(name = "user_type")
    private String userType;
    @Column(name = "user_score")
    private Float userScore;
    @Column(name = "average_score_helpo_user_id")
    private Integer averageScoreHelpoUserID;

    @ManyToOne
    @JoinColumn(name = "helpo_user_id", referencedColumnName = "helpo_user_id", insertable = false, nullable = false)
    private HelpoUser helpoUserAverageScore;

}
