package com.find.helpo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "job", schema = "public")
public class Job {

    //TO-DO add foreign key's

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long jobID;
    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "job_post_date")
    private Date jobPostDate;
    @Column(name = "job_type")
    private String jobType;
    @Column(name = "job_description")
    private String jobDescription;
    @Column(name = "job_reward")
    private String jobReward;
    @Column(name = "job_status")
    private String jobStatus;
    @Column(name = "job_owner_id")
    private Long jobOwnerID;
    @Column(name = "job_favored_by_helper_id")
    private Long jobFavoredByHelperID;
    @Column(name = "job_favored_status")
    private String jobFavoredStatus;

    @ManyToOne
    @JoinColumn(name = "helper_id", referencedColumnName = "helper_id", insertable = false, nullable = false)
    private Helper helper;

    @ManyToOne
    @JoinColumn(name = "help_seeker_id", referencedColumnName = "help_seeker_id", insertable = false, nullable = false)
    private HelpSeeker helpSeeker;

    @OneToMany(mappedBy = "jobPhotos")
    private List<JobPhoto> jobPhotoList;



}
