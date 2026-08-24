package com.my.portofolio.dto.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProjectResponse {

    private Long id;
    private Long cvProfileId;
    private String name;
    private String description;
    private String technologiesUsed;
    private String projectUrl;
    private String repoUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
