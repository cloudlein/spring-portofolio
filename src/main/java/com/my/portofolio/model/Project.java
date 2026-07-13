package com.my.portofolio.model;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "projects", indexes = {
    @Index(name = "idx_projects_cv", columnList = "cv_profile_id")
})
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Project extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cv_profile_id", nullable = false)
    private CvProfile cvProfile;

    @NotBlank
    @Size(max = 255)
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Size(max = 255)
    @Column(name = "technologies_used", length = 255)
    private String technologiesUsed;

    @URL
    @Size(max = 512)
    @Column(name = "project_url", length = 512)
    private String projectUrl;

    @URL
    @Size(max = 512)
    @Column(name = "repo_url", length = 512)
    private String repoUrl;
}
