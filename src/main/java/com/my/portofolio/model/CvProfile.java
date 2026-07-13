package com.my.portofolio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "cv_profiles", indexes = {
    @Index(name = "idx_cv_profiles_user", columnList = "user_id")
})
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CvProfile extends BaseModel {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @NotBlank
    @Size(max = 255)
    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;

    @Size(max = 150)
    @Column(name = "title", length = 150)
    private String title;

    @Column(name = "summary", columnDefinition = "TEXT")
    private String summary;

    @Email
    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @Size(max = 50)
    @Pattern(
            regexp = "^\\+?[0-9\\-\\s()]+$",
            message = "Invalid phone number"
    )
    @Column(name = "phone", length = 50)
    private String phone;

    @Size(max = 255)
    @Column(name = "address")
    private String address;

    @Size(max = 512)
    @Column(name = "profile_pic_url", length = 512)
    private String profilePicUrl;

    @Size(max = 512)
    @URL
    @Column(name = "github_url", length = 512)
    private String githubUrl;

    @Size(max = 512)
    @URL
    @Column(name = "linkedin_url", length = 512)
    private String linkedinUrl;
}