package com.my.portofolio.service;

import com.my.portofolio.dto.cvprofile.CvProfileCreateRequest;
import com.my.portofolio.dto.cvprofile.CvProfileResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CvProfileService {

    void createCvProfile(CvProfileCreateRequest request);
    void updateCvProfile(CvProfileCreateRequest request);
    void deleteCvProfile(Long cvProfileId);
    CvProfileResponse getCvProfile(Long cvProfileId);

    Page<CvProfileResponse> getCvProfiles(Pageable pageable);

}
