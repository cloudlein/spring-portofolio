package com.my.portofolio.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import lombok.experimental.SuperBuilder;
import jakarta.persistence.*;

@Entity
@Table(name = "skills", indexes = {
    @Index(name = "idx_skills_cv", columnList = "cv_profile_id")
})
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Skill extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cv_profile_id", nullable = false)
    private CvProfile cvProfile;

    @NotBlank
    @Size(max = 100)
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 50)
    @Column(name = "level", length = 50)
    private String level;

    @Size(max = 100)
    @Column(name = "category", length = 100)
    private String category;

}

