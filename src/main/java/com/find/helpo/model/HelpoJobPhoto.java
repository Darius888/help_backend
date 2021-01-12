package com.find.helpo.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "job_photo", schema = "public")
public class HelpoJobPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_photo_id")
    private Integer jobPhotoID;
    @Column(name = "absolute_path")
    private String absolutePath;
    @Column(name = "image_name")
    private String imageName;
    @Column(name = "related_job_id")
    private Integer relatedJobID;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "helpo_job_id", referencedColumnName = "helpo_job_id", insertable = false)
    private HelpoJob helpoJobPhotos;

}
