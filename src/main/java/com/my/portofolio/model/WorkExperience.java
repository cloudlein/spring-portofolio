package com.my.portofolio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

import lombok.experimental.SuperBuilder;
import jakarta.persistence.*;

@Entity
@Table(name = "work_experiences", indexes = {
    @Index(name = "idx_work_experiences_cv", columnList = "cv_profile_id")
})
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperience extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cv_profile_id", nullable = false)
    private CvProfile cvProfile;

    @NotBlank
    @Size(max = 255)
    @Column(name = "company_name", nullable = false, length = 255)
    private String companyName;

    @NotBlank
    @Size(max = 150)
    @Column(name = "position", nullable = false, length = 150)
    private String position;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Builder.Default
    @Column(name = "is_current_job")
    private Boolean isCurrentJob = false;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

}
