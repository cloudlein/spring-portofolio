package com.my.portofolio.dto.education;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
class EducationResponse {
  private Long id;
  private Long cvProfileId;
  private String institution;
  private String degree;
  private String major;
  private LocalDate startDate;
  private LocalDate endDate;
  private BigDecimal gpa;
  private String description;
  private LocalDate createdAt;
  private LocalDate updatedAt;
}
