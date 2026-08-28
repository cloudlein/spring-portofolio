package com.my.portofolio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.my.portofolio.dto.education.EducationCreateRequest;
import com.my.portofolio.model.Education;

@Mapper(componentModel = "spring")
public interface EducationMapper {

  @MappingTarget(target = "cvProfile", ignore = true)
  Education toEntity(EducationCreateRequest request); 

  @Mapping(target = "cvProfile.id", target = "" ignore = true)

}
