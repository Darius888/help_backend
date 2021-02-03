package com.find.helpo.DTO;

import com.find.helpo.model.HelpoJob;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class HelpoJobPhotoDTO {

    private Integer jobPhotoID;
    private Integer relatedJobID;
    private MultipartFile jobPhoto;

    public void setRelatedJobID(HelpoJob helpoJob) {
        this.relatedJobID = helpoJob.getHelpoJobID();
    }
    public Integer getRelatedJobID(HelpoJob helpoJob) {
        return helpoJob.getHelpoJobID();
    }

}
