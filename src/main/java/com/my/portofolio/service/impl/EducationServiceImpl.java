package com.my.portofolio.service.impl;

import com.my.portofolio.dto.education.EducationCreateRequest;
import com.my.portofolio.dto.education.EducationResponse;
import com.my.portofolio.dto.education.EducationUpdateRequest;
import com.my.portofolio.exception.ResourceNotFoundException;
import com.my.portofolio.mapper.EducationMapper;
import com.my.portofolio.model.CvProfile;
import com.my.portofolio.model.Education;
import com.my.portofolio.repository.CvProfileRepository;
import com.my.portofolio.repository.EducationRepository;
import com.my.portofolio.service.EducationService;
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
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final CvProfileRepository cvProfileRepository;
    private final EducationMapper educationMapper;

    @Override
    @Transactional
    public EducationResponse createEducation(EducationCreateRequest request) {
        log.info("Creating new education at '{}' for cvProfileId: {}", request.getInstitution(), request.getCvProfileId());

        CvProfile cvProfile = cvProfileRepository.findById(request.getCvProfileId())
                .orElseThrow(() -> new ResourceNotFoundException("CvProfile not found with id: " + request.getCvProfileId()));

        Education education = educationMapper.toEntity(request);
        education.setCvProfile(cvProfile);

        Education savedEducation = educationRepository.save(education);
        log.info("Education created successfully with id: {}", savedEducation.getId());

        return educationMapper.toResponse(savedEducation);
    }

    @Override
    @Transactional
    public EducationResponse updateEducation(Long id, EducationUpdateRequest request) {
        log.info("Updating education with id: {}", id);

        Education education = educationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Education not found with id: " + id));

        if (!education.getCvProfile().getId().equals(request.getCvProfileId())) {
            CvProfile cvProfile = cvProfileRepository.findById(request.getCvProfileId())
                    .orElseThrow(() -> new ResourceNotFoundException("CvProfile not found with id: " + request.getCvProfileId()));
            education.setCvProfile(cvProfile);
        }

        educationMapper.updateEntity(request, education);

        Education updatedEducation = educationRepository.save(education);
        log.info("Education updated successfully with id: {}", updatedEducation.getId());

        return educationMapper.toResponse(updatedEducation);
    }

    @Override
    @Transactional
    public void deleteEducation(Long id) {
        log.info("Deleting education with id: {}", id);

        if (!educationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Education not found with id: " + id);
        }

        educationRepository.deleteById(id);
        log.info("Education deleted successfully with id: {}", id);
    }

    @Override
    public EducationResponse getEducationById(Long id) {
        log.info("Fetching education with id: {}", id);

        return educationRepository.findById(id)
                .map(educationMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Education not found with id: " + id));
    }

    @Override
    public Page<EducationResponse> getAllEducation(String search, Pageable pageable) {
        log.info("Fetching all educations with search: '{}', pageable: {}", search, pageable);

        Page<Education> educationPage;
        if (search != null && !search.trim().isEmpty()) {
            educationPage = educationRepository.searchEducations(search.trim(), pageable);
        } else {
            educationPage = educationRepository.findAll(pageable);
        }

        return educationPage.map(educationMapper::toResponse);
    }
}
