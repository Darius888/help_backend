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
    private Long averageScoreID;
    @Column(name = "user_type")
    private String userType;
    @Column(name = "user_score")
    private String userScore;
    @Column(name = "average_score_help_seeker_id")
    private Integer averageScoreHelpSeekerID;
    @Column(name = "average_score_helper_id")
    private Integer averageScoreHelperID;

    @ManyToOne
    @JoinColumn(name = "help_seeker_id", referencedColumnName = "help_seeker_id", insertable = false, nullable = false)
    private HelpSeeker helpSeekerAverageScore;

    @ManyToOne
    @JoinColumn(name = "helper_id", referencedColumnName = "helper_id", insertable = false, nullable = false)
    private Helper helperAverageScore;

}
