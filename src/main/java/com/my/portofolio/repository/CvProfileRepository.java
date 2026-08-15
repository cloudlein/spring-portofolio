package com.my.portofolio.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface CvProfileRepository {

    boolean existsById(Long id);

}
