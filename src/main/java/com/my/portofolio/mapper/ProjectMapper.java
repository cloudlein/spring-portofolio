package com.my.portofolio.mapper;

import com.my.portofolio.dto.project.ProjectCreateRequest;
import com.my.portofolio.dto.project.ProjectResponse;
import com.my.portofolio.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(target = "cvProfile", ignore = true)
    Project toEntity(ProjectCreateRequest request);

    @Mapping(source = "cvProfile.id", target = "cvProfileId")
    ProjectResponse toResponse(Project project);

}
