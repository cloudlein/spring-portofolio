package com.my.portofolio.repository;

import com.my.portofolio.model.Education;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {

    Page<Education> findByCvProfileId(Long cvProfileId, Pageable pageable);

    @Query("SELECT e FROM Education e WHERE " +
           "LOWER(e.institution) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.degree) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.major) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Education> searchEducations(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e FROM Education e WHERE e.cvProfile.id = :cvProfileId AND (" +
           "LOWER(e.institution) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.degree) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.major) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Education> searchEducationsByCvProfileId(@Param("cvProfileId") Long cvProfileId, @Param("keyword") String keyword, Pageable pageable);

}
