package com.my.portofolio.service.impl;

import com.my.portofolio.dto.cvprofile.CvProfileCreateRequest;
import com.my.portofolio.dto.cvprofile.CvProfileResponse;
import com.my.portofolio.mapper.CvProfileMapper;
import com.my.portofolio.repository.CvProfileRepository;
import com.my.portofolio.service.CvProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CvProfileServiceImpl implements CvProfileService {

    private CvProfileRepository cvProfileRepository;
    private CvProfileMapper cvProfileMapper;

    @Override
    public void createCvProfile(CvProfileCreateRequest request) {

    }

    @Override
    public void updateCvProfile(CvProfileCreateRequest request) {

    }

    @Override
    public void deleteCvProfile(Long cvProfileId) {

    }

    @Override
    public CvProfileResponse getCvProfile(Long cvProfileId) {
        return null;
    }

    @Override
    public Page<CvProfileResponse> getCvProfiles(Pageable pageable) {
        return null;
    }
}
