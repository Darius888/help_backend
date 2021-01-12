package com.find.helpo.repository;

import com.find.helpo.model.ProfilePhoto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilePhotoRepository extends CrudRepository<ProfilePhoto, Integer> {

        Boolean existsByRelatedUserID(Integer relatedUserID);

}
