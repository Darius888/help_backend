package com.find.helpo.repository;

import com.find.helpo.model.HelpSeeker;
import com.find.helpo.model.Helper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelpSeekerRepository extends CrudRepository<HelpSeeker, Long> {

    HelpSeeker findByEmail(String helpSeekerEmail);
    Boolean existsByEmail(String helpSeekerEmail);

}
