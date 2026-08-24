package com.my.portofolio.service;

import com.my.portofolio.dto.project.ProjectCreateRequest;
import com.my.portofolio.dto.project.ProjectResponse;
import com.my.portofolio.dto.project.ProjectUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {

    ProjectResponse createProject(ProjectCreateRequest projectCreateRequest);

    ProjectResponse updateProject(Long id, ProjectUpdateRequest projectUpdateRequest);

    void deleteProject(Long id);

    ProjectResponse getProjectById(Long id);

    Page<ProjectResponse> getAllProjects(String search, Pageable pageable);

    Page<ProjectResponse> getProjectsByCvProfileId(Long cvProfileId, String search, Pageable pageable);
}
