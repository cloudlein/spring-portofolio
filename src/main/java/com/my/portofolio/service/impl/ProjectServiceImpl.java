package com.my.portofolio.service.impl;

import com.my.portofolio.dto.ApiResponse;
import com.my.portofolio.dto.project.ProjectCreateRequest;
import com.my.portofolio.dto.project.ProjectResponse;
import com.my.portofolio.exception.ResourceNotFoundException;
import com.my.portofolio.mapper.ProjectMapper;
import com.my.portofolio.model.Project;
import com.my.portofolio.repository.CvProfileRepository;
import com.my.portofolio.repository.ProjectRepository;
import com.my.portofolio.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectMapper projectMapper;
    private ProjectRepository projectRepository;
    private CvProfileRepository cvProfileRepository;

    @Override
    public void createProject(ProjectCreateRequest projectCreateRequest) {
        if (!cvProfileRepository.existsById(projectCreateRequest.getCvProfileId())) {
            throw new ResourceNotFoundException("CV Profile Not Found");
        }

        Project project = projectMapper.toEntity(projectCreateRequest);

        projectRepository.save(project);

    }

    @Override
    public void updateProject(ProjectCreateRequest projectCreateRequest) {

    }

    @Override
    public void deleteProject(Long id) {

    }

    @Override
    public ApiResponse<ProjectResponse> getProjectById(Long id) {
        return null;
    }

    @Override
    public Page<ApiResponse<ProjectResponse>> getAllProjects(Pageable pageable) {
        return null;
    }
}
