package com.my.portofolio.service;

import com.my.portofolio.dto.education.EducationCreateRequest;
import com.my.portofolio.dto.education.EducationResponse;
import com.my.portofolio.dto.education.EducationUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EducationService {

  EducationResponse createEducation(EducationCreateRequest request);

  EducationResponse updateEducation(Long id, EducationUpdateRequest request);

  void deleteEducation(Long id);

  EducationResponse getEducationById(Long id);

  Page<EducationResponse> getAllEducation(String search, Pageable pageable);
}

