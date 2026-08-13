package com.my.portofolio.dto.cvprofile;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CvProfileCreateRequest {

    @NotNull
    private Long userId;

    @NotBlank
    @Size(max = 255)
    private String fullName;

    @Size(max = 150)
    private String title;

    private String summary;

    @Email
    @Size(max = 255)
    private String email;

    @Size(max = 50)
    @Pattern(regexp = "^\\+?[0-9\\-\\s()]+$", message = "Invalid phone number")
    private String phone;

    @Size(max = 255)
    private String address;

    @Size(max = 512)
    private String profilePicUrl;

    @Size(max = 512)
    @URL
    private String githubUrl;

    @Size(max = 512)
    @URL
    private String linkedinUrl;

}
