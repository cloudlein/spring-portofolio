package com.my.portofolio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "educations", indexes = {
    @Index(name = "idx_educations_cv", columnList = "cv_profile_id")
})
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Education extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cv_profile_id", nullable = false)
    private CvProfile cvProfile;

    @NotBlank
    @Size(max = 255)
    @Column(name = "institution", nullable = false, length = 255)
    private String institution;

    @NotBlank
    @Size(max = 150)
    @Column(name = "degree", nullable = false, length = 150)
    private String degree;

    @Size(max = 150)
    @Column(name = "major", length = 150)
    private String major;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @DecimalMin("0.00")
    @DecimalMax("4.00")
    @Digits(integer = 1, fraction = 2)
    @Column(name = "gpa", precision = 3, scale = 2)
    private BigDecimal gpa;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}