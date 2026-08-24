package com.my.portofolio.repository;

import com.my.portofolio.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Page<Project> findByCvProfileId(Long cvProfileId, Pageable pageable);

    boolean existsByCvProfileIdAndNameIgnoreCase(Long cvProfileId, String name);

    boolean existsByCvProfileIdAndNameIgnoreCaseAndIdNot(Long cvProfileId, String name, Long id);

    @Query("SELECT p FROM Project p WHERE " +
           "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.technologiesUsed) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Project> searchProjects(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT p FROM Project p WHERE p.cvProfile.id = :cvProfileId AND (" +
           "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.technologiesUsed) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Project> searchProjectsByCvProfileId(@Param("cvProfileId") Long cvProfileId, @Param("keyword") String keyword, Pageable pageable);
}
