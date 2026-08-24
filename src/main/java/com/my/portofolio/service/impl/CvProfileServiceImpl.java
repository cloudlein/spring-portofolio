package com.my.portofolio.service.impl;

import com.my.portofolio.dto.cvprofile.CvProfileCreateRequest;
import com.my.portofolio.dto.cvprofile.CvProfileResponse;
import com.my.portofolio.dto.cvprofile.CvProfileUpdateRequest;
import com.my.portofolio.exception.ConflictException;
import com.my.portofolio.exception.ResourceNotFoundException;
import com.my.portofolio.mapper.CvProfileMapper;
import com.my.portofolio.model.CvProfile;
import com.my.portofolio.model.User;
import com.my.portofolio.repository.CvProfileRepository;
import com.my.portofolio.repository.UserRepository;
import com.my.portofolio.service.CvProfileService;
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
public class CvProfileServiceImpl implements CvProfileService {

    private final CvProfileRepository cvProfileRepository;
    private final UserRepository userRepository;
    private final CvProfileMapper cvProfileMapper;

    @Override
    @Transactional
    public CvProfileResponse createCvProfile(CvProfileCreateRequest request) {
        log.info("Creating CV profile for userId: {}", request.getUserId());

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));

        if (cvProfileRepository.existsByUserId(request.getUserId())) {
            throw new ConflictException("CV profile already exists for user id: " + request.getUserId());
        }

        CvProfile cvProfile = cvProfileMapper.toEntity(request);
        cvProfile.setUser(user);

        CvProfile savedProfile = cvProfileRepository.save(cvProfile);
        log.info("CV profile created successfully with id: {}", savedProfile.getId());

        return cvProfileMapper.toResponse(savedProfile);
    }

    @Override
    @Transactional
    public CvProfileResponse updateCvProfile(Long cvProfileId, CvProfileUpdateRequest request) {
        log.info("Updating CV profile with id: {}", cvProfileId);

        CvProfile cvProfile = cvProfileRepository.findById(cvProfileId)
                .orElseThrow(() -> new ResourceNotFoundException("CV profile not found with id: " + cvProfileId));

        if (!cvProfile.getUser().getId().equals(request.getUserId())) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));

            if (cvProfileRepository.existsByUserIdAndIdNot(request.getUserId(), cvProfileId)) {
                throw new ConflictException("CV profile already exists for user id: " + request.getUserId());
            }

            cvProfile.setUser(user);
        }

        cvProfileMapper.updateEntity(request, cvProfile);

        CvProfile updatedProfile = cvProfileRepository.save(cvProfile);
        log.info("CV profile updated successfully with id: {}", updatedProfile.getId());

        return cvProfileMapper.toResponse(updatedProfile);
    }

    @Override
    @Transactional
    public void deleteCvProfile(Long cvProfileId) {
        log.info("Deleting CV profile with id: {}", cvProfileId);

        if (!cvProfileRepository.existsById(cvProfileId)) {
            throw new ResourceNotFoundException("CV profile not found with id: " + cvProfileId);
        }

        cvProfileRepository.deleteById(cvProfileId);
        log.info("CV profile deleted successfully with id: {}", cvProfileId);
    }

    @Override
    public CvProfileResponse getCvProfile(Long cvProfileId) {
        log.info("Fetching CV profile with id: {}", cvProfileId);

        return cvProfileRepository.findById(cvProfileId)
                .map(cvProfileMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("CV profile not found with id: " + cvProfileId));
    }

    @Override
    public CvProfileResponse getCvProfileByUserId(Long userId) {
        log.info("Fetching CV profile for userId: {}", userId);

        return cvProfileRepository.findByUserId(userId)
                .map(cvProfileMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("CV profile not found for user id: " + userId));
    }

    @Override
    public Page<CvProfileResponse> getCvProfiles(String search, Pageable pageable) {
        log.info("Fetching CV profiles with search: '{}', pageable: {}", search, pageable);

        Page<CvProfile> profilePage;
        if (search != null && !search.trim().isEmpty()) {
            profilePage = cvProfileRepository.searchCvProfiles(search.trim(), pageable);
        } else {
            profilePage = cvProfileRepository.findAll(pageable);
        }

        return profilePage.map(cvProfileMapper::toResponse);
    }
}
