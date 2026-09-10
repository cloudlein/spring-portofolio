package com.my.portofolio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.my.portofolio.dto.education.EducationCreateRequest;
import com.my.portofolio.dto.education.EducationResponse;
import com.my.portofolio.dto.education.EducationUpdateRequest;
import com.my.portofolio.model.Education;

@Mapper(componentModel = "spring")
public interface EducationMapper {

  @Mapping(target = "cvProfile", ignore = true)
  Education toEntity(EducationCreateRequest request);

  @Mapping(source = "cvProfile.id", target = "cvProfileId")
  EducationResponse toResponse(Education education);

  @Mapping(target = "cvProfile", ignore = true)
  @Mapping(target = "id", ignore = true)
  void updateEntity(EducationUpdateRequest request, @MappingTarget Education education);

}
