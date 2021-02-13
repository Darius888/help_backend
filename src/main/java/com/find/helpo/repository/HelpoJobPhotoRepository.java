package com.find.helpo.repository;

import com.find.helpo.model.HelpoJobPhoto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HelpoJobPhotoRepository extends CrudRepository<HelpoJobPhoto, Integer> {

      List<HelpoJobPhoto> findAllByRelatedJobID(Integer relatedJobID);

      Integer countByRelatedJobID(Integer relatedJobID);

      Boolean existsByRelatedJobID(Integer relatedJobID);

      Boolean existsByImageName(String imageName);

}
