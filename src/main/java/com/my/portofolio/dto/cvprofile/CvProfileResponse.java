package com.my.portofolio.dto.cvprofile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CvProfileResponse {
    private Long id;
    private Long userId;
    private String fullName;
    private String title;
    private String summary;
    private String email;
    private String phone;
    private String address;
    private String profilePicUrl;
    private String githubUrl;
    private String linkedinUrl;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
