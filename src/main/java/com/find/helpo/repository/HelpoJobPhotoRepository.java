package com.find.helpo.repository;

import com.find.helpo.model.HelpoJobPhoto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelpoJobPhotoRepository extends CrudRepository<HelpoJobPhoto, Integer> {

      Integer countByRelatedJobID(Integer relatedJobID);

      Boolean existsByRelatedJobID(Integer relatedJobID);

      Boolean existsByImageName(String imageName);

}
