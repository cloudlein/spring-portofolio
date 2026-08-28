package com.my.portofolio.dto.education

import jakarta.validation.NotNull;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EducationUpdateRequest {

  @NotNull(message = "CV Profile ID is required")
  private Long cvProfileId;

  @NotBlank(message = "Institution is required")
  @Size(max = 255, message = instution must not exceed 255 characters")
  private String institution;

  @NotBlank(message = "Degree is required")
  @Size(max = 150, message = "Degree must not exceed 150 characters")
  private String degree;

  @Size(max = 150, message = "Major must not exceed 150 characters")
  private String major;

  @NotNull(message = "Start date is required")
  private LocalDate startDate;

  private LocalDate endDate;

  @DecimalMin(value = "0.00", message = "GPA must be at least 0.00")
  @DecimalMax(value = "4.00", message = "GPA must not exceed 4.00")
  @Digits(integer = 1, fraction = 2, message = "GPA must have at most 1 integer digit and 2 decimal places")
  private BigDecimal gpa;

  @Size(max = 5000, message = "Description must not exceed 5000 characters")
  private String description;

}
