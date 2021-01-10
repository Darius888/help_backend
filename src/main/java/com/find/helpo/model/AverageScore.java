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
    @Column(name = "score_id")
    private Integer scoreID;
    @Column(name = "user_score")
    private Float userScore;
    @Column(name = "score_owner_id")
    private Integer scoreOwnerID;
    @Column(name = "score_receiver_id")
    private Integer scoreReceiverID;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "helpo_user_id", referencedColumnName = "helpo_user_id", insertable = false)
    private HelpoUser helpoUserAverageScore;

}
