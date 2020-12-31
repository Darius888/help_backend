package com.find.helpo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "helpo_job", schema = "public")
public class HelpoJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Integer jobID;
    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "job_post_date")
    private String jobPostDate;
    @Column(name = "job_type")
    private String jobType;
    @Column(name = "job_description")
    private String jobDescription;
    @Column(name = "job_reward")
    private String jobReward;
    @Column(name = "job_status")
    private String jobStatus;
    @Column(name = "job_owner_id")
    private Integer jobOwnerID;
    @Column(name = "job_favored_status")
    private String jobFavoredStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "helpo_user_id", referencedColumnName = "helpo_user_id", insertable = false, nullable = false)
    private HelpoUser helpoUser;

    @OneToMany(mappedBy = "helpoJobPhotos")
    private List<JobPhoto> jobPhotoList;



}
