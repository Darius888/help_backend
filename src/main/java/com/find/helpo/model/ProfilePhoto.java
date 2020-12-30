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
    private Long profilePhotoID;
    @Column(name = "absolute_path")
    private String absolutePath;
    @Column(name = "user_type")
    private String userType;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "po_help_seeker_id")
    private Long photoOwnerHelpSeekerId;
    @Column(name = "po_helper_id")
    private Long photoOwnerHelperId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "help_seeker_id", referencedColumnName = "help_seeker_id", insertable = false, nullable = false)
    private HelpSeeker helpSeekerProfilePhoto;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "helper_id", referencedColumnName = "helper_id", insertable = false, nullable = false)
    private Helper helperProfilePhoto;

}
