package com.my.portofolio.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProjectCreateRequest {

    @NotNull(message = "CV Profile ID is required")
    private Long cvProfileId;

    @NotBlank(message = "Project name is required")
    @Size(max = 255, message = "Project name must not exceed 255 characters")
    private String name;

    @Size(max = 5000, message = "Description must not exceed 5000 characters")
    private String description;

    @Size(max = 255, message = "Technologies used must not exceed 255 characters")
    private String technologiesUsed;

    @URL(message = "Invalid project URL format")
    @Size(max = 512, message = "Project URL must not exceed 512 characters")
    private String projectUrl;

    @URL(message = "Invalid repository URL format")
    @Size(max = 512, message = "Repository URL must not exceed 512 characters")
    private String repoUrl;

}
