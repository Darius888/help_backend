package com.find.helpo.model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "profile_photo", schema = "public")
public class ProfilePhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_photo_id")
    private Integer profilePhotoID;
    @Column(name = "absolute_path")
    private String absolutePath;
    @Column(name = "image_name")
    private String imageName;
    @Column(name = "related_user_id")
    private Integer relatedUserID;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "helpo_user_id", referencedColumnName = "helpo_user_id", insertable = false)
    private HelpoUser helpoUserProfilePhoto;

}
