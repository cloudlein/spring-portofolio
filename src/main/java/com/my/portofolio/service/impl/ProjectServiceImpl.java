package com.my.portofolio.service.impl;

import com.my.portofolio.dto.project.ProjectCreateRequest;
import com.my.portofolio.dto.project.ProjectResponse;
import com.my.portofolio.dto.project.ProjectUpdateRequest;
import com.my.portofolio.exception.ConflictException;
import com.my.portofolio.exception.ResourceNotFoundException;
import com.my.portofolio.mapper.ProjectMapper;
import com.my.portofolio.model.CvProfile;
import com.my.portofolio.model.Project;
import com.my.portofolio.repository.CvProfileRepository;
import com.my.portofolio.repository.ProjectRepository;
import com.my.portofolio.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final CvProfileRepository cvProfileRepository;
    private final ProjectMapper projectMapper;

    @Override
    @Transactional
    public ProjectResponse createProject(ProjectCreateRequest request) {
        log.info("Creating new project: '{}' for cvProfileId: {}", request.getName(), request.getCvProfileId());

        CvProfile cvProfile = cvProfileRepository.findById(request.getCvProfileId())
                .orElseThrow(() -> new ResourceNotFoundException("CvProfile not found with id: " + request.getCvProfileId()));

        if (projectRepository.existsByCvProfileIdAndNameIgnoreCase(request.getCvProfileId(), request.getName())) {
            throw new ConflictException("Project with name '" + request.getName() + "' already exists for this CV profile");
        }

        Project project = projectMapper.toEntity(request);
        project.setCvProfile(cvProfile);

        Project savedProject = projectRepository.save(project);
        log.info("Project created successfully with id: {}", savedProject.getId());

        return projectMapper.toResponse(savedProject);
    }

    @Override
    @Transactional
    public ProjectResponse updateProject(Long id, ProjectUpdateRequest request) {
        log.info("Updating project with id: {}", id);

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        if (!project.getCvProfile().getId().equals(request.getCvProfileId())) {
            CvProfile cvProfile = cvProfileRepository.findById(request.getCvProfileId())
                    .orElseThrow(() -> new ResourceNotFoundException("CvProfile not found with id: " + request.getCvProfileId()));
            project.setCvProfile(cvProfile);
        }

        if (projectRepository.existsByCvProfileIdAndNameIgnoreCaseAndIdNot(request.getCvProfileId(), request.getName(), id)) {
            throw new ConflictException("Project with name '" + request.getName() + "' already exists for this CV profile");
        }

        projectMapper.updateEntity(request, project);

        Project updatedProject = projectRepository.save(project);
        log.info("Project updated successfully with id: {}", updatedProject.getId());

        return projectMapper.toResponse(updatedProject);
    }

    @Override
    @Transactional
    public void deleteProject(Long id) {
        log.info("Deleting project with id: {}", id);

        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Project not found with id: " + id);
        }

        projectRepository.deleteById(id);
        log.info("Project deleted successfully with id: {}", id);
    }

    @Override
    public ProjectResponse getProjectById(Long id) {
        log.info("Fetching project with id: {}", id);

        return projectRepository.findById(id)
                .map(projectMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
    }

    @Override
    public Page<ProjectResponse> getAllProjects(String search, Pageable pageable) {
        log.info("Fetching all projects with search: '{}', pageable: {}", search, pageable);

        Page<Project> projectPage;
        if (search != null && !search.trim().isEmpty()) {
            projectPage = projectRepository.searchProjects(search.trim(), pageable);
        } else {
            projectPage = projectRepository.findAll(pageable);
        }

        return projectPage.map(projectMapper::toResponse);
    }

    @Override
    public Page<ProjectResponse> getProjectsByCvProfileId(Long cvProfileId, String search, Pageable pageable) {
        log.info("Fetching projects for cvProfileId: {} with search: '{}', pageable: {}", cvProfileId, search, pageable);

        if (!cvProfileRepository.existsById(cvProfileId)) {
            throw new ResourceNotFoundException("CvProfile not found with id: " + cvProfileId);
        }

        Page<Project> projectPage;
        if (search != null && !search.trim().isEmpty()) {
            projectPage = projectRepository.searchProjectsByCvProfileId(cvProfileId, search.trim(), pageable);
        } else {
            projectPage = projectRepository.findByCvProfileId(cvProfileId, pageable);
        }

        return projectPage.map(projectMapper::toResponse);
    }
}
