package com.my.portofolio.mapper;

import com.my.portofolio.dto.cvprofile.CvProfileCreateRequest;
import com.my.portofolio.dto.cvprofile.CvProfileResponse;
import com.my.portofolio.dto.cvprofile.CvProfileUpdateRequest;
import com.my.portofolio.model.CvProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CvProfileMapper {

    @Mapping(target = "user", ignore = true)
    CvProfile toEntity(CvProfileCreateRequest cvProfileCreateRequest);

    @Mapping(source = "user.id", target = "userId")
    CvProfileResponse toResponse(CvProfile cvProfile);

    @Mapping(target = "user", ignore = true)
    void updateEntity(CvProfileUpdateRequest cvProfileUpdateRequest, @MappingTarget CvProfile cvProfile);
}
