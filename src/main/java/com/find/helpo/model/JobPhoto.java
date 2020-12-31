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
public class JobPhoto {

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

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "job_id", insertable = false, nullable = false)
    private HelpoJob helpoJobPhotos;

}
