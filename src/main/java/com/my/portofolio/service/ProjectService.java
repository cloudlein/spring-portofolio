package com.my.portofolio.service;

import com.my.portofolio.dto.ApiResponse;
import com.my.portofolio.dto.project.ProjectCreateRequest;
import com.my.portofolio.dto.project.ProjectResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {

    void createProject(ProjectCreateRequest projectCreateRequest);
    void updateProject(ProjectCreateRequest projectCreateRequest);
    void deleteProject(Long id);

    ApiResponse<ProjectResponse> getProjectById(Long id);

    Page<ApiResponse<ProjectResponse>> getAllProjects(Pageable pageable);
}
