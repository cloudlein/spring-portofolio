package com.my.portofolio.service;

import com.my.portofolio.dto.cvprofile.CvProfileCreateRequest;
import com.my.portofolio.dto.cvprofile.CvProfileResponse;
import com.my.portofolio.dto.cvprofile.CvProfileUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CvProfileService {

    CvProfileResponse createCvProfile(CvProfileCreateRequest request);

    CvProfileResponse updateCvProfile(Long cvProfileId, CvProfileUpdateRequest request);

    void deleteCvProfile(Long cvProfileId);

    CvProfileResponse getCvProfile(Long cvProfileId);

    CvProfileResponse getCvProfileByUserId(Long userId);

    Page<CvProfileResponse> getCvProfiles(String search, Pageable pageable);
}
