package com.my.portofolio.dto.education;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EducationResponse {
  private Long id;
  private Long cvProfileId;
  private String institution;
  private String degree;
  private String major;
  private LocalDate startDate;
  private LocalDate endDate;
  private BigDecimal gpa;
  private String description;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}

