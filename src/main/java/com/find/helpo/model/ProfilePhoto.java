package com.find.helpo.model;

import lombok.*;

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
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "po_helpo_user_id")
    private Integer photoOwnerHelpoUserID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "helpo_user_id", referencedColumnName = "helpo_user_id", insertable = false, nullable = false)
    private HelpoUser helpoUserProfilePhoto;

}
